import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Day11_part2 {

    private static final List<Integer> input = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        long res = 0;
        while ((line = br.readLine()) != null) {
            String[] str = line.split(" ");
            for (String s : str) {
                input.add(Integer.parseInt(s.trim()));
            }
        }
        long startTime = System.nanoTime();
        res = foo(input, 75);
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

    public static long foo(List<Integer> inp, int blinks) {
        Map<String, Long> mem = new HashMap<>();
        return inp.stream().mapToLong(num -> bar(num, blinks, mem)).sum();
    }

    public static long bar(long num, int i, Map<String, Long> mem) {
        if (i == 0)
            return 1;

        String key = num + "," + i;
        if (mem.containsKey(key))
            return mem.get(key);

        long total = 0;
        List<Long> arr = rules(num);
        for (long n : arr) {
            total += bar(n, i - 1, mem);
        }
        mem.put(key, total);
        return total;
    }

    public static List<Long> rules(long number) {

        if (number == 0) {
            return Arrays.asList(1l);
        }

        String numStr = Long.toString(number);
        int len = numStr.length();

        if (len % 2 == 0) {
            String left = numStr.substring(0, len / 2);
            String right = numStr.substring(len / 2);
            return Arrays.asList(Long.parseLong(left), Long.parseLong(right));
        }

        return Arrays.asList(number * 2024);
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
