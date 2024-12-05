import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = null;
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        int tot = 0;

        while(( line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println("Found: " + matcher.group());
                String firstNumberStr = matcher.group(1);
                String secondNumberStr = matcher.group(2);
                int firstNumber = Integer.parseInt(firstNumberStr);
                int secondNumber = Integer.parseInt(secondNumberStr);
                int multiplicationResult = firstNumber * secondNumber;
                tot += multiplicationResult;
            }
        }
        System.out.println(tot);
        br.close();
    }
}
