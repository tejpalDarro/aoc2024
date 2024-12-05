import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.undo.CompoundEdit;

public class Day1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        int cnt = 0;
        while ((line = br.readLine()) != null) {
            String[] split = line.split("  ");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1].stripLeading()));
            cnt++;
        }

        Collections.sort(left);
        Collections.sort(right);

        int min;
        int sum = 0;
        for (int i=0; i<cnt; i++) {
            min = Math.abs(left.get(i) - right.get(i));
            sum += min;
        }
        System.out.println(sum);

        br.close();


    }
}
