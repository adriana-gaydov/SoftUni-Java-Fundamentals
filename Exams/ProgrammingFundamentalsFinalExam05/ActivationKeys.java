package ProgrammingFundamentalsFinalExam05;

import java.util.Scanner;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rawPassword = sc.nextLine();

        String command = sc.nextLine();

        while (!command.equals("Generate")) {
            String[] commandArgs = command.split(">>>");
            String action = commandArgs[0];

            switch (action) {
                case "Contains":
                    String substring = commandArgs[1];
                    if (rawPassword.contains(substring)) {
                        System.out.printf("%s contains %s%n", rawPassword, substring);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    int startIndex = Integer.parseInt(commandArgs[2]);
                    int endIndex = Integer.parseInt(commandArgs[3]);
                    String substringToReplace = rawPassword.substring(startIndex, endIndex);
                    if (commandArgs[1].equals("Upper")) {
                        rawPassword = rawPassword.replaceAll(substringToReplace, substringToReplace.toUpperCase());
                    } else if (commandArgs[1].equals("Lower")) {
                        rawPassword = rawPassword.replaceAll(substringToReplace, substringToReplace.toLowerCase());
                    }
                    System.out.println(rawPassword);
                    break;
                case "Slice":
                    startIndex = Integer.parseInt(commandArgs[1]);
                    endIndex = Integer.parseInt(commandArgs[2]);
                    rawPassword = rawPassword.replaceAll(rawPassword.substring(startIndex, endIndex), "");
                    System.out.println(rawPassword);
                    break;
            }
            command = sc.nextLine();
        }
        System.out.printf("Your activation key is: %s%n", rawPassword);
    }
}
