package ProgrammingFundamentalsFinalExam02;

import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder allStops = new StringBuilder(sc.nextLine());

        String command = sc.nextLine();
        while (!command.equals("Travel")) {
            String[] commandArgs = command.split(":");
            String action = commandArgs[0];

            switch (action) {
                case "Add Stop":
                    int index = Integer.parseInt(commandArgs[1]);
                    String stringToInsert = commandArgs[2];

                    if (isValidIndex(index, allStops.toString())) {
                        allStops.insert(index, stringToInsert);
                    }
                    System.out.println(allStops);
                    break;
                case "Remove Stop":
                    int startIndex = Integer.parseInt(commandArgs[1]);
                    int endIndex = Integer.parseInt(commandArgs[2]);

                    if (isValidIndex(startIndex, allStops.toString()) && isValidIndex(endIndex, allStops.toString())) {
                        allStops.delete(startIndex, endIndex + 1);
                    }
                    System.out.println(allStops);
                    break;
                case "Switch":
                    String oldString = commandArgs[1];
                    String newString = commandArgs[2];

                    if (allStops.toString().contains(oldString)) {
                       allStops = new StringBuilder(allStops.toString().replaceAll(oldString, newString));
                    }
                    System.out.println(allStops);
                    break;
            }
            command = sc.nextLine();
        }
        System.out.printf("Ready for world tour! Planned stops: %s%n", allStops);
    }
    public static boolean isValidIndex(int index, String text) {
        return index >= 0 && index < text.length();
    }
}
