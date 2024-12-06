import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day6 {

    static enum Direction {
        UP, RIGHT, DOWN, LEFT;

        public Direction turnRight() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
                default:
                    throw new IllegalStateException("Unknown direction: " + this);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<List<String>> matrix = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line;
        int row = -1, col = -1;

        while ((line = br.readLine()) != null) {
            List<String> rowList = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '^') {
                    row = matrix.size();
                    col = i;
                }
                rowList.add(String.valueOf(line.charAt(i)));
            }
            matrix.add(rowList);
        }
        br.close();

        int cnt = foo(matrix, row, col);
        System.out.println(cnt);
    }

    public static int foo(List<List<String>> matrix, int row, int col) {
        Set<String> visPos = new HashSet<>();
        Direction dir = Direction.UP;

        visPos.add(row + "," + col);

        while (true) {
            int nextRow = moveRow(row, dir);
            int nextCol = moveCol(col, dir);

            if (!isValidPos(matrix, nextRow, nextCol)) {
                break;
            }

            if (isObstacle(matrix, row, col, dir)) {
                dir = dir.turnRight();
            } else {
                row = nextRow;
                col = nextCol;

                visPos.add(row + "," + col);
            }
        }

        return visPos.size();
    }

    public static boolean isValidPos(List<List<String>> matrix, int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    public static boolean isObstacle(List<List<String>> matrix, int row, int col, Direction dir) {
        int nextRow = moveRow(row, dir);
        int nextCol = moveCol(col, dir);
        return !isValidPos(matrix, nextRow, nextCol) || "#".equals(matrix.get(nextRow).get(nextCol));
    }

    public static int moveRow(int row, Direction dir) {
        if (dir == Direction.UP)
            return row - 1;
        else if (dir == Direction.DOWN)
            return row + 1;
        else
            return row;

    }

    public static int moveCol(int col, Direction dir) {
        if (dir == Direction.LEFT)
            return col - 1;
        else if (dir == Direction.RIGHT)
            return col + 1;
        else
            return col;
    }
}
