package ProgrammingFundamentalsFinalExam03;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SecretChat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        String command = sc.nextLine();
        while (!command.equals("Reveal")) {
            String[] commandArgs = command.split(":\\|:");
            String action = commandArgs[0];

            switch (action) {
                case "InsertSpace":
                    int index = Integer.parseInt(commandArgs[1]);
                    StringBuilder temp = new StringBuilder(message);
                    temp.insert(index, " ");
                    message = temp.toString();
                    System.out.println(message);
                    break;
                case "Reverse":
                    String substring = commandArgs[1];
                    if (!message.contains(substring)) {
                        System.out.println("error");
                    } else {
                       temp = new StringBuilder(substring);
                       temp.reverse();
                       message = message.replaceFirst(Pattern.quote(substring), "");
                       message += temp.toString();
                       System.out.println(message);
                    }
                    break;
                case "ChangeAll":
                    substring = commandArgs[1];
                    String replacement = commandArgs[2];
                    message = message.replaceAll(substring, replacement);
                    System.out.println(message);
                    break;
            }
            command = sc.nextLine();
        }
        System.out.printf("You have a new text message: %s", message);
    }
}
