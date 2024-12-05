import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1_part2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        int cnt = 0;
        while ((line = br.readLine()) != null) {
            String[] split = line.split("  ");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1].stripLeading()));
            cnt++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int v : right) {
            map.put(v, map.getOrDefault(v, 0) +1);
        }

        int tot = 0;

        for (int v: left) {

            int diff = map.getOrDefault(v, 0);
            int sum = v * diff;
            tot += sum;
        }

        // for (int i=0; i<cnt; i++) {
        //     int diff=0;
        //     for (int y=0; y<cnt; y++) {
        //         int l = left.get(i);
        //         int r = right.get(y);
        //         if (l == r) {
        //             diff++;
        //         }
        //     }
        //     int sum = left.get(i) * diff;
        //     tot += sum;
        //     System.out.println(sum);
        // }
        System.out.println(tot);

        br.close();
    }
}
