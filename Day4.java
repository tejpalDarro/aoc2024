import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    static final int[] DIR_X = {0, 1, 0, -1, 1, 1, -1, -1};
    static final int[] DIR_Y = {1, 0, -1, 0, 1, -1, 1, -1};
    static final String WORD = "MAS";   
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        List<List<Character>> arr = new ArrayList<>();
        String line = null;
        while(( line = br.readLine()) != null) {
            List<Character> chList = new ArrayList<>();
            char[] chArray = line.toCharArray();
            for (char a : chArray) {
                chList.add(a);
            }
            arr.add(chList);
        }
        System.out.println(foo(arr));
        br.close();
    }

    public static int foo(List<List<Character>> ch) {
        int count = 0;
        for (int i = 0; i < ch.size(); i++) {
            for (int j = 0; j < ch.get(i).size(); j++) {
                if (ch.get(i).get(j) == WORD.charAt(0)) {
                    for (int dir = 0; dir < 8; dir++) {
                        if (foo(ch, i, j, dir)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static boolean foo(List<List<Character>> grid, int x, int y, int direction) {
        int len = WORD.length();

        int endX = x + (len - 1) * DIR_X[direction];
        int endY = y + (len - 1) * DIR_Y[direction];

        if (endX < 0 || endX >= grid.size() || endY < 0 || endY >= grid.size()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            int newX = x + i * DIR_X[direction];
            int newY = y + i * DIR_Y[direction];
            if (grid.get(newX).get(newY) != WORD.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
