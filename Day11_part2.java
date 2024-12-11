import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day11_part2 {

    private static final Map<Long, Long> memoCache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        long res = 0;
        line = br.readLine();
        long startTime = System.nanoTime();
        res = foo(line);
        long endTime = System.nanoTime();
        long dur = endTime - startTime;
        System.out.println("Result: " + res);
        long durationMillis = dur / 1_000_000;

        long minutes = durationMillis / 60_000;
        long seconds = (durationMillis % 60_000) / 1000;
        long milliseconds = durationMillis % 1000;

        System.out.println(
                "Execution time: " + minutes + " minutes, " + seconds + " seconds, " + milliseconds + " milliseconds");
        // memoCache.forEach((k, v) -> System.out.println("k: " + k + ", v: " + v));
        br.close();
    }

    public static long foo(String string) {
        long ans = 0;
        int blinks = 75;
        for (String s : string.split(" ")) {
            // System.out.println(s);
            ans += bar(Integer.parseInt(s.trim()), blinks);
            System.out.println("string: " + s + ", ans: " + ans);
        }
        return ans;
    }

    public static long bar(int s, int blinks) {
        blinks--;
        long key = s * 100 + blinks;
        if (memoCache.containsKey(key))
            return memoCache.get(key);
        long total = 0;
        if (blinks >= 0) {
            if (s == 0) {
                total += bar(1, blinks);
            } else if (countDigit(s) % 2 == 0) {
                int pow = (int) Math.pow(10, countDigit(s) / 2);
                total += bar(s / pow, blinks);
                total += bar(s % pow, blinks);
            } else {
                total += bar(s * 2024, blinks);
            }

        } else {
            total = 1;
        }
        memoCache.put(key, total);
        return total;
    }

    public static int countDigit(int n) {
        if (n == 0)
            return 1;
        int cnt = 0;
        while (n > 0) {
            cnt++;
            n = (int) Math.floor(n / 10);
        }
        return cnt;
        // return (int) Math.log10(n) + 1;
    }

}
