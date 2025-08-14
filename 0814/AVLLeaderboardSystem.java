import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        int playerId;
        int score;
        Node left, right;
        int height;
        int size; // 子樹大小

        Node(int playerId, int score) {
            this.playerId = playerId;
            this.score = score;
            height = 1;
            size = 1;
        }
    }

    private Node root;
    private Map<Integer, Integer> playerScores = new HashMap<>(); // playerId -> score

    // 比較規則：分數高的在前，分數相同時 ID 小的在前
    private int compare(int playerId1, int score1, int playerId2, int score2) {
        if (score1 != score2) return Integer.compare(score2, score1); // 分數高排前
        return Integer.compare(playerId1, playerId2); // ID 小排前
    }

    // 節點高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 子樹大小
    private int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    // 更新節點高度與大小
    private void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    // 平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 左旋
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);
        return y;
    }

    // 右旋
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);
        return x;
    }

    // 插入節點
    private Node insertRec(Node node, int playerId, int score) {
        if (node == null) return new Node(playerId, score);

        int cmp = compare(playerId, score, node.playerId, node.score);
        if (cmp < 0) {
            node.left = insertRec(node.left, playerId, score);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, playerId, score);
        } else {
            return node; // 相同玩家與分數（不應發生）
        }

        update(node);

        int balance = getBalance(node);
        if (balance > 1 && compare(playerId, score, node.left.playerId, node.left.score) < 0)
            return rotateRight(node);
        if (balance < -1 && compare(playerId, score, node.right.playerId, node.right.score) > 0)
            return rotateLeft(node);
        if (balance > 1 && compare(playerId, score, node.left.playerId, node.left.score) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && compare(playerId, score, node.right.playerId, node.right.score) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    // 刪除節點
    private Node deleteRec(Node node, int playerId, int score) {
        if (node == null) return null;

        int cmp = compare(playerId, score, node.playerId, node.score);
        if (cmp < 0) {
            node.left = deleteRec(node.left, playerId, score);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, playerId, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = minValueNode(node.right);
                node.playerId = successor.playerId;
                node.score = successor.score;
                node.right = deleteRec(node.right, successor.playerId, successor.score);
            }
        }

        if (node == null) return null;

        update(node);

        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) return rotateRight(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return rotateLeft(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    // 找最小值節點
    private Node minValueNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 添加玩家分數
    public void addPlayer(int playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            System.out.println("玩家已存在，請用 updateScore");
            return;
        }
        playerScores.put(playerId, score);
        root = insertRec(root, playerId, score);
    }

    // 更新玩家分數
    public void updateScore(int playerId, int newScore) {
        if (!playerScores.containsKey(playerId)) {
            System.out.println("玩家不存在");
            return;
        }
        int oldScore = playerScores.get(playerId);
        root = deleteRec(root, playerId, oldScore);
        playerScores.put(playerId, newScore);
        root = insertRec(root, playerId, newScore);
    }

    // 查詢玩家排名（1 為最高分）
    public int getRank(int playerId) {
        if (!playerScores.containsKey(playerId)) return -1;
        int score = playerScores.get(playerId);
        return getRankRec(root, playerId, score) + 1; // 排名從 1 開始
    }

    private int getRankRec(Node node, int playerId, int score) {
        if (node == null) return 0;

        int cmp = compare(playerId, score, node.playerId, node.score);
        if (cmp < 0) {
            return getRankRec(node.left, playerId, score);
        } else if (cmp > 0) {
            return size(node.left) + 1 + getRankRec(node.right, playerId, score);
        } else {
            return size(node.left);
        }
    }

    // 查詢前 K 名玩家
    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topKRec(root, k, result);
        return result;
    }

    private void topKRec(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topKRec(node.left, k, result);
        if (result.size() < k) {
            result.add("玩家" + node.playerId + " 分數:" + node.score);
        }
        topKRec(node.right, k, result);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addPlayer(1, 100);
        leaderboard.addPlayer(2, 200);
        leaderboard.addPlayer(3, 150);
        leaderboard.addPlayer(4, 250);

        System.out.println("前 3 名: " + leaderboard.topK(3));
        System.out.println("玩家 3 排名: " + leaderboard.getRank(3));

        leaderboard.updateScore(3, 300);
        System.out.println("更新後玩家 3 排名: " + leaderboard.getRank(3));
        System.out.println("前 3 名: " + leaderboard.topK(3));
    }
}

