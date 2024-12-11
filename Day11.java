import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day11
 */
public class Day11 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        int res = 0;
        line = br.readLine();
        long startTime = System.nanoTime();
        res = foo(line);
        long endTime = System.nanoTime();
        long dur = endTime - startTime;
        System.out.println("Result: " + res);
        long durationMillis = dur / 1_000_000;

        // Calculate minutes, seconds, and milliseconds
        long minutes = durationMillis / 60_000;
        long seconds = (durationMillis % 60_000) / 1000;
        long milliseconds = durationMillis % 1000;

        // Print the execution time in minutes, seconds, and milliseconds
        System.out.println(
                "Execution time: " + minutes + " minutes, " + seconds + " seconds, " + milliseconds + " milliseconds");
        br.close();
    }

    public static int foo(String string) {
        List<String> str = new ArrayList<>();
        for (String s : string.split(" ")) {
            str.add(s);
        }
        System.out.print("input");
        str.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (int i = 0; i < 45; i++) {
            str = bar(str);
        }
        return str.size();
    }

    public static List<String> bar(List<String> str) {
        List<String> temp = new ArrayList<>();
        BigInteger mulNumb = new BigInteger("2024");
        for (String s : str) {
            if (s.equals("0")) {
                temp.add("1");
                continue;
            }
            int digit = cntDigit(s);
            if (digit % 2 == 0) {
                int hf = digit >> 1;
                String first = s.substring(0, hf);
                first = leading(first);
                String second = s.substring(hf, s.length());
                second = leading(second);
                temp.add(first);
                temp.add(second);
            } else {
                BigInteger bigInt = new BigInteger(s);
                String res = bigInt.multiply(mulNumb) + "";
                temp.add(res);
            }
        }
        return temp;
    }

    public static String leading(String s) {
        if (s.length() == 1)
            return s;
        int cnt = 0;
        if (s.charAt(0) == '0') {
            for (char c : s.toCharArray()) {
                if (c - '0' == 0) {
                    cnt++;
                } else {
                    break;
                }
            }
        }
        if (cnt == s.length())
            return "0";
        return s.substring(cnt, s.length());
    }
    // public static String checkAllZero(String s) {
    // int tot = 0;
    // for (char c : s.toCharArray()) {
    // tot += c - '0';
    // }
    // if (tot == 0) {
    // return "0";
    // } else {
    // return s;
    // }
    // }

    public static int cntDigit(String s) {
        String[] tempString = s.split("");
        return tempString.length;
    }
}
