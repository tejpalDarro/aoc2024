import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3_part2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        // String regex = "mul\\((\\d{1,3}),\\d{1,3}\\)"; 
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\)|don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        int tot = 0;
        boolean flag = true;

        while(( line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println("Found: " + matcher.group());
                if (matcher.group() == "don't()") {
                    flag = false;
                } else if (matcher.group() == "do()") {
                    flag = true;
                } else {
                    flag = true;
                }
                if (flag) {
                    String firstNumberStr = matcher.group(1);
                    String secondNumberStr = matcher.group(2);

                    int firstNumber = Integer.parseInt(firstNumberStr);
                    int secondNumber = Integer.parseInt(secondNumberStr);

                    int multiplicationResult = firstNumber * secondNumber;

                    tot += multiplicationResult;
                }

            }
        }
        System.out.println(tot);
        br.close();



    }
}
