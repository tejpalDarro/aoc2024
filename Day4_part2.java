import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4_part2 {

    static final int[] DIR_X = {1, 1, -1, -1};
    static final int[] DIR_Y = {1, -1, 1, -1};
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
        for (int i = 1; i < ch.size()-1; i++) {
            for (int j = 1; j < ch.get(i).size()-1; j++) {
                if (ch.get(i).get(j) == 'A') {
                        if (checkWord(ch, i, j)) {
                            count++;
                        }
                }
            }
        }
        return count;
    }

    public static boolean checkWord(List<List<Character>> grid, int x, int y) {

        if ((grid.get(x+1).get(y+1) == 'M' && grid.get(x-1).get(y-1) == 'S') ||
            (grid.get(x+1).get(y+1) == 'S' && grid.get(x-1).get(y-1) == 'M')) {
        
            if ((grid.get(x+1).get(y-1) == 'M' && grid.get(x-1).get(y+1) == 'S') || 
                (grid.get(x+1).get(y-1) == 'S' && grid.get(x-1).get(y+1) == 'M')) {
                return true;
            }
        }

        return false;
    }

    public static boolean bar(int x, int y, List<List<Character>> list, String s, String ans) {
        if (ans.length() == 4) {
            if (ans.equals(s)) {
                System.out.println("ans: " + ans);
                System.out.println("YES " + ++cnt);
                return true;
            }
            return false; 
        }
        if (x < 0 || y < 0 || x >= list.size() || y >= list.get(x).size()) {
            return false; 
        }

        char currentChar = list.get(x).get(y);

        if (bar(x - 1, y, list, s, ans + currentChar)) {
            list.get(x).set(y, '.');
        }
        bar(x + 1, y, list, s, ans + currentChar); 
        bar(x, y - 1, list, s, ans + currentChar); 
        bar(x, y + 1, list, s, ans + currentChar); 
        return false;
    }
}
