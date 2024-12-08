import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Day7
 */
public class Day7_part2 {

    static long tot = 0l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        Map<Long, List<Long>> map = new LinkedHashMap<>();
        String line = null;
        int cnt = 0;
        while ((line = br.readLine()) != null) {
            List<Long> list = new ArrayList<>();
            String[] str = line.split(":");
            String[] listOfString = str[1].trim().split(" ");
            for (String s : listOfString) {
                list.add(Long.parseLong(s));
            }
            map.put(Long.parseLong(str[0]), list);
            if (foo(Long.parseLong(str[0]), list, Long.parseLong(listOfString[0]), 1))
                cnt++;
        }
        System.out.println("map size :" + map.size());
        System.out.println("cnt: " + cnt);
        System.out.println("tot: " + tot);
        br.close();
    }

    public static boolean foo(long target, List<Long> list, long ans, int idx) {
        if (idx == list.size()) {
            if (target == ans) {
                tot += ans;
                // System.out.println(ans);
            }

            return target == ans;
        }
        long temp = list.get(idx);
        String s = Long.toString(temp);
        String a = Long.toString(ans);
        long t2 = Long.parseLong(a + s);
        if (foo(target, list, t2, idx + 1))
            return true;
        if (foo(target, list, ans + temp, idx + 1))
            return true;
        if (foo(target, list, ans * temp, idx + 1))
            return true;
        return false;
    }

}
