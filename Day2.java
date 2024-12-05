import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    static enum Level {
        INCREASING, DECREASING;
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
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        Level currLevel;
        if (x > y) {
            currLevel = Level.INCREASING;
        } else {
            currLevel = Level.DECREASING;
        }

        for (int i=1; i<str.length; i++) {
            int a = Integer.parseInt(str[i-1]);
            int b = Integer.parseInt(str[i]);
            if (a == b) {
                System.out.println("a, b: " + a + " " + b);
                return false;
            }
            Level tempLevel;
            if (a > b) {
                tempLevel = Level.INCREASING;
            } else {
                tempLevel = Level.DECREASING;
            }
            if (currLevel != tempLevel) {
                System.out.println("Level false: " + a + " " + b);
                return false;
            }
            int diff = Math.abs(a-b);
            if (diff > 3) {
                System.out.println("diff " + diff);
                return false;
            }
        }
        return true;
    }
}
