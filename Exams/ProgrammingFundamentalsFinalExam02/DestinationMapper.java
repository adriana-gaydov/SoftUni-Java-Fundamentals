package ProgrammingFundamentalsFinalExam02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Pattern pattern = Pattern.compile("(?<surroundSymbol>[=\\/])(?<destination>[A-Z][A-Za-z]{2,})\\1");
        Matcher matcher = pattern.matcher(input);
        List<String> destinations = new ArrayList<>();

        while (matcher.find()) {
            destinations.add(matcher.group("destination"));
        }

        int travelPoints = destinations.stream().mapToInt(String::length).sum();
        System.out.printf("Destinations: %s%n", String.join(", ", destinations));
        System.out.printf("Travel Points: %d", travelPoints);
    }
}
