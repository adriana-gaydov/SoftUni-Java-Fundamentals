package ProgrammingFundamentalsFinalExam01;

import java.util.Scanner;

public class TheImitationGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder message = new StringBuilder(sc.nextLine());

        String command = sc.nextLine();
        while (!command.equals("Decode")) {
            String[] commandArgs = command.split("\\|");
            String action = commandArgs[0];

            switch (action) {
                case "Move":
                    int numberOfLetters = Integer.parseInt(commandArgs[1]);
                    message.append(message, 0, numberOfLetters);
                    message.delete(0, numberOfLetters);
                    break;
                case "Insert":
                    int index = Integer.parseInt(commandArgs[1]);
                    String value = commandArgs[2];
                    message.insert(index, value);
                    break;
                case "ChangeAll":
                    String substring = commandArgs[1];
                    String replacement = commandArgs[2];
                    if (message.toString().contains(substring)) {
                        message = new StringBuilder(message.toString().replace(substring, replacement));
                    }
                    break;
            }
            command = sc.nextLine();
        }
        System.out.printf("The decrypted message is: %s%n", message);
    }
}
