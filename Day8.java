import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day8
 */
public class Day8 {

    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        Map<String, List<String>> map = new LinkedHashMap<>();
        List<List<String>> matrix = new ArrayList<>();
        String line = null;
        int row = 0;
        while ((line = br.readLine()) != null) {
            List<String> list = new ArrayList<>();
            String[] str = line.split("");
            for (int i = 0; i < str.length; i++) {
                if (!str[i].equals(".")) {
                    if (map.containsKey(str[i])) {
                        map.get(str[i]).add(row + "," + i);
                    } else {
                        List<String> coord = new ArrayList<>();
                        coord.add(row + "," + i);
                        map.put(str[i], coord);

                    }
                }
                list.add(str[i]);
            }
            matrix.add(list);
            row++;
        }
        map.forEach((k, v) -> System.out.println("k :" + k + " v: " + v));
        map.forEach((k, v) -> {
            for (int i = 0; i < v.size(); i++) {
                for (int j = i + 1; j < v.size(); j++) {
                    foo(matrix, v.get(i), v.get(j), k);
                }
            }
        });

        for (List<String> s : matrix) {
            for (String ss : s) {
                System.out.print(ss + " ");
            }
            System.out.println();
        }
        System.out.println("set size: " + set.size());
        br.close();

    }

    public static void foo(List<List<String>> list, String coord1, String coord2, String obj) {
        System.out.println(obj + " " + coord1 + ", " + coord2);

        String[] parts1 = coord1.split(",");
        String[] parts2 = coord2.split(",");

        int x1 = Integer.parseInt(parts1[0]);
        int y1 = Integer.parseInt(parts1[1]);
        int x2 = Integer.parseInt(parts2[0]);
        int y2 = Integer.parseInt(parts2[1]);

        int X = Math.abs(x1 - x2);
        int Y = Math.abs(y1 - y2);

        int newY1 = 0;
        int newY2 = 0;
        int newX1 = x1 - X;
        if (y2 > y1) {
            newY1 = y1 - Y;
            newY2 = y2 + Y;
        } else {
            newY1 = y1 + Y;
            newY2 = y2 - Y;
        }
        int newX2 = x2 + X;

        if (valid(newX1, newY1, list.get(0).size(), list.size(), list)) {
            System.out.println("valid coord " + newX1 + "," + newY1);
            if (list.get(newX1).get(newY1).equals(".")) {
                list.get(newX1).set(newY1, "#");
                set.add(newX1 + "," + newY1);
            } else {
                set.add(newX1 + "," + newY1);
            }
        }
        if (valid(newX2, newY2, list.get(0).size(), list.size(), list)) {
            System.out.println("valid coord " + newX2 + "," + newY2);
            if (list.get(newX2).get(newY2).equals(".")) {
                list.get(newX2).set(newY2, "#");
                set.add(newX2 + "," + newY2);
            } else {
                set.add(newX2 + "," + newY2);
            }
        }

        // System.out.println("x1 " + x1 + ", y1 " + y1);
        // System.out.println("x2 " + x2 + ", y2 " + y2);
        // System.out.println("1: " + newX1 + ", " + newY1);
        // System.out.println("2: " + newX2 + ", " + newY2);
    }

    public static boolean valid(int x, int y, int maxX, int maxY, List<List<String>> list) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }
}
