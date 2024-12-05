import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day5{

    static int cnt = 0;
    static int tot = 0;
    static List<Integer> l1 = new ArrayList<>();
    static List<Integer> l2 = new ArrayList<>();

    static List<List<Integer>> pageList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        while (( line = br.readLine()) != null)  {
            if (line.equals("")) {
                System.out.println();
                continue;
            }
            if (line.contains("|")) {
                String[] str = line.split("\\|");
                // System.out.println("s1: " + str[0] + " s2: " + str[1]);
                l1.add(Integer.parseInt(str[0].trim()));
                l2.add(Integer.parseInt(str[1].trim()));
            } else {
                String[] str = line.split(",");
                List<Integer> pages = new ArrayList<>();
                for (String s : str) {
                    pages.add(Integer.parseInt(s));
                }
                pageList.add(pages);
            }

        }
        for (int i=0; i<pageList.size(); i++) {
            if (foo(pageList.get(i), l1, l2)) {
                cnt++;
            }
        }
        System.out.println();
        System.out.println(tot);
       
        br.close();
    }

    public static boolean foo(List<Integer> list, List<Integer> l1, List<Integer> l2) {
        Map<Integer, List<Integer>> map = new LinkedHashMap<>(); 
        for (int i=0; i<list.size(); i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0; j<l1.size(); j++) {
                if (list.get(i) == l1.get(j)) {
                    temp.add(l2.get(j));
                }
            }
            map.put(list.get(i), temp);
        }

        for (int i=1; i<list.size(); i++) {
            for (int j=0; j<i; j++) {
                List<Integer> l = map.get(list.get(i));
                for (int x : l) {
                    if (x == list.get(j)) {
                        return false;
                    }
                }
            } 
        }

        int mid = list.size() >> 1;
        // System.out.println("mid: " + mid);
        tot += list.get(mid);


        map.forEach((k,v) -> System.out.println("k: " + k + " v: " + v));
        System.out.println();
        return true;
    }
}
