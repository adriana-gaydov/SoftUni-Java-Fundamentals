package ProgrammingFundamentalsFinalExam01;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdAstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        Pattern pattern = Pattern.compile("(?<separator>[\\|#])(?<itemName>[A-Za-z ]+)\\1(?<expirationDate>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d+)\\1");
        Matcher matcher = pattern.matcher(text);

        int calories = 0;
        while(matcher.find()) {
            calories += Integer.parseInt(matcher.group("calories"));
        }

        System.out.printf("You have food to last you for: %d days!%n", calories / 2000);

        matcher = pattern.matcher(text);
        while(matcher.find()) {
            System.out.printf("Item: %s, Best before: %s, Nutrition: %d%n", matcher.group("itemName"),
                    matcher.group("expirationDate"), Integer.parseInt(matcher.group("calories")));
        }
    }
}
