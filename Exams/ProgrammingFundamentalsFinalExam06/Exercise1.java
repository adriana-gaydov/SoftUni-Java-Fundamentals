import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();

        String command = sc.nextLine();
        while (!command.equals("Complete")) {
            String[] commandArgs = command.split("\\s+");
            String action = commandArgs[0];

            switch (action) {
                case "Make":
                    if (commandArgs[1].equals("Upper")) {
                        email = email.toUpperCase();
                        System.out.println(email);
                    } else if (commandArgs[1].equals("Lower")) {
                        email = email.toLowerCase();
                        System.out.println(email);
                    }
                    break;
                case "GetDomain":
                    int count = Integer.parseInt(commandArgs[1]);
                    System.out.println(email.substring(email.length() - count));
                    break;
                case "GetUsername":
                    if (!email.contains("@")) {
                        System.out.printf("The email %s doesn't contain the @ symbol.%n", email);
                    } else {
                        int indexOfSymbol = email.indexOf('@');
                        System.out.println(email.substring(0, indexOfSymbol));
                    }
                    break;
                case "Replace":
                    char charToReplace = commandArgs[1].charAt(0);
                    email = email.replace(charToReplace, '-');
                    System.out.println(email);
                    break;
                case "Encrypt":
                    for (char c : email.toCharArray()) {
                        System.out.print((int)c + " ");
                    }
                    System.out.println();
                    break;
            }
            command = sc.nextLine();
        }
    }
}
