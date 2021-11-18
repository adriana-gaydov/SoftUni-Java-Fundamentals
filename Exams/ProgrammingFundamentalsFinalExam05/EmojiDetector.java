package ProgrammingFundamentalsFinalExam05;

import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        BigInteger coolThreshold = getCoolThreshold(input);
        Pattern pattern = Pattern.compile("(?<symbols>[:]{2}|[*]{2})(?<emoji>[A-Z][a-z]{2,})\\1");
        Matcher matcher = pattern.matcher(input);

        List<String> coolEmojis = new ArrayList<>();
        int countValidEmojis = 0;
        while (matcher.find()) {
            countValidEmojis++;
            int coolness = getCoolness(matcher.group("emoji"));
            if (coolThreshold.compareTo(BigInteger.valueOf(coolness)) < 0) {
                coolEmojis.add(matcher.group());
            }
        }

        System.out.printf("Cool threshold: %s%n", coolThreshold);
        System.out.printf("%d emojis found in the text. The cool ones are:%n", countValidEmojis);
        coolEmojis.forEach(System.out::println);
    }

    private static int getCoolness(String group) {
        int coolness = 0;
        for (char c : group.toCharArray()) {
            coolness += c;
        }
        return coolness;
    }

    private static BigInteger getCoolThreshold(String input) {
        BigInteger product = new BigInteger("1");
        int count = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
                product = product.multiply(BigInteger.valueOf(Integer.parseInt(String.valueOf(c))));
            }
        }
        return (count != 0) ? product : new BigInteger("0");
    }
}
