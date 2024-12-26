import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day20
 */
public class Day20 {

    protected static List<List<String>> matrix = new ArrayList<>();
    static final int[] DIR_X = { 0, 1, 0, -1 };
    static final int[] DIR_Y = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        int sRow = 0;
        int sCol = 0;
        int eRow = 0;
        int eCol = 0;
        int i = 0;
        while ((line = br.readLine()) != null) {
            int j = 0;
            List<String> temp = new ArrayList<>();
            String[] str = line.split("");
            for (String s : str) {
                if (s.equals("S")) {
                    sRow = i;
                    sCol = j;
                }
                if (s.equals("E")) {
                    eRow = i;
                    eCol = j;
                }
                temp.add(s);
                j++;
            }
            matrix.add(temp);
            i++;
        }
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
        for (List<String> s : matrix) {
            for (String ss : s) {
                System.out.print(ss + " ");
            }
            System.out.println();
        }
        System.out.println("Start: " + sRow + "," + sCol);
        System.out.println("End: " + eRow + "," + eCol);

        int res = foo(matrix, visited, sRow, sCol, eRow, eCol);

        for (boolean[] b : visited) {
            for (boolean bb : b) {
                if (bb)
                    System.out.print("." + " ");
                else
                    System.out.print("#" + " ");
            }
            System.out.println();
        }

        System.out.println(res);

        br.close();
    }

    public static int foo(List<List<String>> list, boolean[][] visited, int sRow, int sCol, int eRow, int eCol) {

        if (list.get(sRow).get(sCol).equals("E")) {
            System.out.println("got e");
            return 1;
        }
        visited[sRow][sCol] = true;
        int total = 0;

        for (int i = 0; i < DIR_X.length; i++) {
            int newX = sRow + DIR_X[i];
            int newY = sCol + DIR_Y[i];
            if (!list.get(newX).get(newY).equals("#") && !visited[newX][newY]) {
                total = foo(list, visited, newX, newY, eRow, eCol);
                if (total != -1) {
                    return total + 1;
                }
            }
        }
        return -1;

    }
}
