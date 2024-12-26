import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Day15
 */
public class Day15 {

    protected static List<List<String>> list = new ArrayList<>();

    static enum DIR {
        UP, DOWN, LEFT, RIGHT;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        String path = null;
        int row = 0;
        int col = 0;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                line = br.readLine();
                path = line;
                break;
            }

            List<String> temp = new ArrayList<>();
            int j = 0;
            for (String s : line.split("")) {
                temp.add(s);
                if (s.equals("@")) {
                    row = i;
                    col = j;
                }
                j++;
            }
            list.add(temp);
            i++;
        }

        for (List<String> s : list) {
            for (String ss : s) {
                System.out.print(ss + " ");
            }
            System.out.println();
        }
        System.out.println(path);
        System.out.println("i: " + row + "j : " + col);
        foo(list, row, col, path);
        int tot = 0;
        int rRow = 0;
        for (List<String> s : list) {
            int rCol = 0;
            for (String ss : s) {
                if (list.get(rRow).get(rCol).equals("O")) {
                    tot += 100 * rRow + rCol;
                }
                rCol++;
            }
            rRow++;
        }
        System.out.println("ans: " + tot);
        br.close();
    }

    public static void foo(List<List<String>> list, int row, int col, String path) {
        int x = 0;
        int y = 0;
        DIR dir = null;
        for (int i = 0; i < path.length(); i++) {
            char point = path.charAt(i);
            switch (point) {
                case '<':
                    x = 0;
                    y = -1;
                    dir = DIR.LEFT;
                    break;
                case '>':
                    x = 0;
                    y = +1;
                    dir = DIR.RIGHT;
                    break;
                case '^':
                    x = -1;
                    y = 0;
                    dir = DIR.UP;
                    break;
                case 'v':
                    x = +1;
                    y = 0;
                    dir = DIR.DOWN;
                    break;
            }
            int newX = x + row;
            int newY = y + col;
            // if (list.get(newX).get(newY).equals("#")) {
            // continue;
            // }
            if (list.get(newX).get(newY).equals(".")) {
                list.get(newX).set(newY, "@");
                list.get(row).set(col, ".");
                row = newX;
                col = newY;
                // for (List<String> s : list) {
                // for (String ss : s) {
                // System.out.print(ss + " ");
                // }
                // System.out.println();
                // }
            }
            if (list.get(newX).get(newY).equals("O")) {
                int[] newDir = findNext(list, newX, newY, dir);

                if (newDir == null) {
                    continue;
                }
                fill(newX, newY, newDir[0], newDir[1], dir);
                list.get(newX).set(newY, "@");
                list.get(row).set(col, ".");
                row = newX;
                col = newY;

                // for (List<String> s : list) {
                // for (String ss : s) {
                // System.out.print(ss + " ");
                // }
                // System.out.println();
                // }
                // else if (list.get(nextX).get(nextY).equals("O") ||
                // list.get(nextX).get(nextY).equals("#")) {
                // continue;
                // }
            }
        }

    }

    public static void fill(int newX, int newY, int newDirX, int newDirY, DIR dir) {

        if (newX == newDirX && newY == newDirY) {
            list.get(newX).set(newY, "O");
            return;
        }

        list.get(newX).set(newY, "O");

        if (dir == DIR.UP) {
            fill(newX - 1, newY, newDirX, newDirY, dir);
        }
        if (dir == DIR.DOWN) {
            fill(newX + 1, newY, newDirX, newDirY, dir);
        }
        if (dir == DIR.LEFT) {
            fill(newX, newY - 1, newDirX, newDirY, dir);
        }
        if (dir == DIR.RIGHT) {
            fill(newX, newY + 1, newDirX, newDirY, dir);
        }
        return;
    }

    public static int[] findNext(List<List<String>> list, int newX, int newY, DIR dir) {
        int nextX = 0;
        int nextY = 0;
        if (dir == DIR.LEFT) {
            nextX = newX;
            nextY = newY - 1;
        }
        if (dir == DIR.RIGHT) {
            nextX = newX;
            nextY = newY + 1;
        }
        if (dir == DIR.UP) {
            nextY = newY;
            nextX = newX - 1;
        }
        if (dir == DIR.DOWN) {
            nextY = newY;
            nextX = newX + 1;
        }
        if (list.get(nextX).get(nextY).equals("O")) {
            return findNext(list, nextX, nextY, dir);
        } else if (list.get(nextX).get(nextY).equals(".")) {
            return new int[] { nextX, nextY };
        } else {
            return null;
        }
    }
}
