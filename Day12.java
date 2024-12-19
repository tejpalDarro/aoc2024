import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day12 not complete
 */
public class Day12 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        List<List<String>> matrix = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            List<String> list = new ArrayList<>();
            String[] str = line.split("");
            for (String s : str) {
                list.add(s);
            }
            matrix.add(list);
        }
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                foo(matrix, visited, i, j);
            }
        }
        br.close();
    }

    public static void foo(List<List<String>> matrix, boolean[][] visited, int x, int y) {
        Stack<String> s = new Stack<>();
        s.push(x + "," + y);
        while (!s.isEmpty()) {
            String str = s.pop();
            if (!visited[x][y]) {
                visited[x][y] = true;
            } else {
                continue;
            }
        }
    }

}
