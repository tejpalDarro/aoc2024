import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day9 part2
 */
public class Day9_part2 {

    static StringBuilder sb = new StringBuilder("");
    static StringBuilder index = new StringBuilder("");
    static StringBuilder revIndex = new StringBuilder("");
    static List<String> list = new ArrayList<>();
    static List<String> ansList = new ArrayList<>();
    static Map<Integer, Integer> mapId = new HashMap<>();
    static Map<Integer, Integer> mapIndex = new HashMap<>();
    static int totLen = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            foo(line);
        }
        // for (char c : sb.toString().toCharArray()) {
        // if (c == '.') {
        // }
        // }
        System.out.println(sb.toString());
        // System.out.println(index.toString());
        String[] str = index.toString().split(" ");
        String res = revIndex.reverse().toString();
        // System.out.println("rev " + revIndex.toString());
        for (int i = 0; i < str.length; i++) {
            try {
                ansList.set(Integer.parseInt(str[i].trim()), list.getLast());
                ansList.removeLast();
                list.removeLast();
            } catch (Exception e) {
            }
        }
        System.out.println(totLen);

        long result = 0;
        for (int i = 0; i < totLen; i++) {
            long temp = i * Integer.parseInt(ansList.get(i));
            result += temp;
            System.out.print(ansList.get(i) + " ");
        }
        System.out.println();
        System.out.println("res: " + result);
        mapId.forEach((k, v) -> {
            System.out.println("k :" + k + " v: " + v);
        });

        br.close();

    }

    public static void foo(String s) {
        int j = 0;
        int idx = -1;
        int temp;
        for (int i = 0; i < s.length(); i++) {
            if ((i % 2) == 0) {
                for (int k = 0; k < s.charAt(i) - '0'; k++) {
                    totLen++;
                    idx++;
                    sb.append(j);
                    revIndex.append(j);
                    list.add(j + "");
                    ansList.add(j + "");
                    mapIndex.put(j, mapIndex.getOrDefault(j, 0) + 1);
                }
                j++;
            } else {
                for (int k = 0; k < s.charAt(i) - '0'; k++) {
                    idx++;
                    index.append(idx + " ");
                    sb.append(".");
                    ansList.add(".");
                }
            }
        }
    }
}
