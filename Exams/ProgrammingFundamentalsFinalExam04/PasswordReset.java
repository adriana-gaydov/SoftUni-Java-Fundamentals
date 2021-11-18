package ProgrammingFundamentalsFinalExam04;

import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        String command = sc.nextLine();

        while(!command.equals("Done")) {
            String[] commandParts = command.split("\\s+");

            switch (commandParts[0]) {
                case "TakeOdd":
                    password = getOddIndexes(password);
                    System.out.println(password);
                    break;
                case "Cut":
                    int startIndex = Integer.parseInt(commandParts[1]);
                    int length = Integer.parseInt(commandParts[2]);
                    String substringToRemove = password.substring(startIndex, length + startIndex);
                    password = password.replaceFirst(substringToRemove, "");
                    System.out.println(password);
                    break;
                case "Substitute":
                    String substring = commandParts[1];
                    String substitute = commandParts[2];
                    if (password.contains(substring)) {
                        password = password.replaceAll(substring, substitute);
                        System.out.println(password);
                    } else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }
            command = sc.nextLine();
        }
        System.out.println("Your password is: " + password);
    }

    private static String getOddIndexes(String password) {
        StringBuilder newPassword = new StringBuilder();

        for (int i = 1; i < password.length(); i+=2) {
            newPassword.append(password.charAt(i));
        }
        return newPassword.toString();
    }
}
