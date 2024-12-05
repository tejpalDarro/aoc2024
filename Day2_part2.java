import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2_part2 {

    static enum Level {
        INCREASING, DECREASING, EQUAL;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String lines = null;
        int tot=0;
        while (( lines = br.readLine()) != null) {
            String[] str = lines.split(" ");
            if (foo(str)) tot++;
        }
        System.out.println("ANS: " + tot);
        br.close();

    }

    public static boolean foo(String[] str) {
        int cnt = 0;
            int eq = 0;
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        Level currLevel;
        if (x > y) {
            currLevel = Level.DECREASING;
        } else {
            currLevel = Level.INCREASING;
        }

        for (int i=1; i<str.length; i++) {
            int a = Integer.parseInt(str[i-1]);
            int b = Integer.parseInt(str[i]);
            int diff = Math.abs(a-b);
            if (diff == 1 || diff == 2 || diff == 3) {
            } else if (diff == 0) {
                eq++;
            } else {
                return false;
            }

            Level tempLevel;
            if (a > b) {
                tempLevel = Level.DECREASING;
            } else {
                tempLevel = Level.INCREASING;
            } 

            if (currLevel != tempLevel) {
                cnt++;
                System.out.println("Level false: " + a + " " + b);
            }
        }
        if (cnt >= 1 && eq >= 1) return false;
        return true;
    }
}

