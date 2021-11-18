package ProgrammingFundamentalsFinalExam03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Pattern pattern = Pattern.compile("(?<surround>[@#])(?<word>[A-Za-z]{3,})\\1\\1(?<word2>[A-Za-z]{3,})\\1");
        Matcher matcher = pattern.matcher(text);
        int wordPairsCount = 0;
        List<String> mirrorWords = new ArrayList<>();

        while (matcher.find()) {
            wordPairsCount++;
            StringBuilder wordTwoReversed = new StringBuilder(matcher.group("word2"));
            wordTwoReversed.reverse();
            if (matcher.group("word").equals(wordTwoReversed.toString())) {
                mirrorWords.add(matcher.group("word") + " <=> " + matcher.group("word2"));
            }
        }

        if (wordPairsCount == 0) {
            System.out.println("No word pairs found!");
        } else {
            System.out.printf("%d word pairs found!%n", wordPairsCount);
        }

        if (mirrorWords.isEmpty()) {
            System.out.println("No mirror words!");
        } else {
            System.out.println("The mirror words are:");
            System.out.println(String.join(", ", mirrorWords));
        }
    }
}
