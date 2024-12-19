import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Day13
 */
public class Day13 {

    private static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        int xA = 0;
        int yA = 0;
        int xB = 0;
        int yB = 0;
        int xP = 0;
        int yP = 0;
        int tot = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty())
                continue;
            StringTokenizer st = new StringTokenizer(line.split("Button A: ")[1], ",");
            xA = Integer.parseInt(st.nextToken().split("\\+")[1].trim());
            yA = Integer.parseInt(st.nextToken().split("\\+")[1].trim());

            line = br.readLine();
            st = new StringTokenizer(line.split("Button B: ")[1], ",");
            xB = Integer.parseInt(st.nextToken().split("\\+")[1].trim());
            yB = Integer.parseInt(st.nextToken().split("\\+")[1].trim());

            line = br.readLine();
            st = new StringTokenizer(line.split("Prize: ")[1], ",");
            xP = Integer.parseInt(st.nextToken().split("\\=")[1].trim());
            yP = Integer.parseInt(st.nextToken().split("\\=")[1].trim());

            int res = foo(xA, yA, xB, yB, xP, yP, 0, 0);
            memo = new HashMap<>();
            if (res != Integer.MAX_VALUE) {
                tot += res;
            }

        }
        System.out.println(tot);
        br.close();
    }

    public static int foo(int xA, int yA, int xB, int yB, int xP, int yP, int x, int y) {
        if (x == xP && y == yP)
            return 0;

        String key = x + "," + y;

        if (memo.containsKey(key))
            return memo.get(key);

        int minTokens = Integer.MAX_VALUE;

        if (x + xA <= xP && y + yA <= yP) {
            int result = foo(xA, yA, xB, yB, xP, yP, x + xA, y + yA);
            if (result != Integer.MAX_VALUE)
                minTokens = Math.min(minTokens, result + 3);
        }
        if (x + xB <= xP && y + yB <= yP) {
            int result = foo(xA, yA, xB, yB, xP, yP, x + xB, y + yB);
            if (result != Integer.MAX_VALUE)
                minTokens = Math.min(minTokens, result + 1);
        }

        memo.put(key, minTokens);
        return minTokens;
    }
}
