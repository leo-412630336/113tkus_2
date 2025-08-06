import java.util.*;

public class RecursiveTreePreview {

    static class Folder {
        String name;
        List<Object> contents; 
        Folder(String name) {
            this.name = name;
            contents = new ArrayList<>();
        }

        void add(Object item) {
            contents.add(item);
        }
    }

    static class File {
        String name;

        File(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Object item) {
        if (item instanceof File) return 1;
        Folder folder = (Folder) item;
        int count = 0;
        for (Object o : folder.contents) {
            count += countFiles(o);
        }
        return count;
    }

    public static void printMenu(List<Object> menu, int depth) {
        for (Object item : menu) {
            if (item instanceof String) {
                System.out.println("  ".repeat(depth) + "- " + item);
            } else if (item instanceof List) {
                printMenu((List<Object>) item, depth + 1);
            }
        }
    }

    public static List<Integer> flattenList(List<Object> nestedList) {
        List<Integer> flat = new ArrayList<>();
        for (Object o : nestedList) {
            if (o instanceof Integer) {
                flat.add((Integer) o);
            } else if (o instanceof List) {
                flat.addAll(flattenList((List<Object>) o));
            }
        }
        return flat;
    }

    public static int maxDepth(List<Object> nestedList) {
        int max = 1;
        for (Object o : nestedList) {
            if (o instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<Object>) o));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("a.txt"));
        Folder sub1 = new Folder("sub1");
        sub1.add(new File("b.txt"));
        sub1.add(new File("c.txt"));
        Folder sub2 = new Folder("sub2");
        sub2.add(new File("d.txt"));
        root.add(sub1);
        root.add(sub2);
        System.out.println("Total files: " + countFiles(root));

        List<Object> menu = Arrays.asList(
            "首頁",
            Arrays.asList("產品", "手機", "筆電"),
            "關於我們",
            Arrays.asList("客服", "聯絡方式")
        );
        System.out.println("\nMenu:");
        printMenu(menu, 0);

        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4), 5), 6);
        System.out.println("\nFlattened list: " + flattenList(nestedList));

        System.out.println("Max depth: " + maxDepth(nestedList)); 
    }
}