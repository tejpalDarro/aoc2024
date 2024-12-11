import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Day10
 */
public class Day10 {

    static final int[] DIR_X = { 0, 1, 0, -1 };
    static final int[] DIR_Y = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        List<String> coord = new ArrayList<>();
        List<List<String>> matrix = new ArrayList<>();
        int row = 0;
        while ((line = br.readLine()) != null) {
            String[] str = line.split("");
            int col = -1;
            List<String> list = new ArrayList<>();
            for (String s : str) {
                col++;
                if (s.equals("0")) {
                    coord.add(row + "," + col);
                }
                list.add(s);
            }
            matrix.add(list);
            row++;
        }
        for (String c : coord) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (List<String> ss : matrix) {
            for (String s : ss) {
                System.out.print(s);
            }
            System.out.println();
        }
        int tot = 0;
        for (String cord : coord) {
            String[] str = cord.split(",");
            int r = Integer.parseInt(str[0].trim());
            int c = Integer.parseInt(str[1].trim());
            boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
            tot += foo(matrix, r, c, visited, 0);
        }
        System.out.println("tot " + tot);
        br.close();
    }

    public static int foo(List<List<String>> matrix, int row, int col, boolean[][] visited, int height) {
        if (row < 0 || row >= matrix.size() || col < 0 || col >= matrix.get(0).size() || visited[row][col]) {
            return 0;
        }
        int h = Integer.parseInt(matrix.get(row).get(col));
        if (h != height)
            return 0;
        if (h == 9)
            return 1;

        visited[row][col] = true;
        int reach9 = 0;

        for (int i = 0; i < 4; i++) {
            int r = row + DIR_X[i];
            int c = col + DIR_Y[i];
            reach9 += foo(matrix, r, c, visited, h + 1);
        }
        visited[row][col] = false;
        return reach9;
    }

    // public static boolean isValidPath(List<List<String>> matrix, int row, int
    // col) {
    // System.out.println("path");
    // return row >= 0 && row < matrix.size() && col >= 0 && col <
    // matrix.get(row).size();
    // }

    // public static boolean isValidCoord(List<List<String>> matrix, int row, int
    // col, int height) {
    // if (row < 0 || row >= matrix.size() || col < 0 || col >=
    // matrix.get(row).size())
    // return false;
    // int nextHeight = Integer.parseInt(matrix.get(row).get(col));
    // return nextHeight == height + 1;
    // }

}
