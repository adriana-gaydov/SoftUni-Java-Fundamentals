package ProgrammingFundamentalsFinalExam03;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class NeedForSpeedIII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, Integer> carsMileage = new LinkedHashMap<>();
        Map<String, Integer> carsFuel = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] carDetails = sc.nextLine().split("\\|");
            String car = carDetails[0];
            int mileage = Integer.parseInt(carDetails[1]);
            int fuel = Integer.parseInt(carDetails[2]);
            carsMileage.putIfAbsent(car, mileage);
            carsFuel.putIfAbsent(car, fuel);
        }

        String command = sc.nextLine();
        while (!command.equals("Stop")) {
            String[] commandArgs = command.split(" : ");
            String action = commandArgs[0];
            String car = commandArgs[1];

            switch (action) {
                case "Drive":
                    int distance = Integer.parseInt(commandArgs[2]);
                    int neededFuel = Integer.parseInt(commandArgs[3]);
                    int currentFuel = carsFuel.get(car);

                    if (currentFuel < neededFuel) {
                        System.out.println("Not enough fuel to make that ride");
                    } else {
                        carsFuel.put(car, currentFuel - neededFuel);
                        carsMileage.put(car, carsMileage.get(car) + distance);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", car, distance, neededFuel);

                        if (carsMileage.get(car) >= 100000) {
                            carsFuel.remove(car);
                            carsMileage.remove(car);
                            System.out.printf("Time to sell the %s!%n", car);
                        }
                    }
                    break;
                case "Refuel":
                    int fuel = Integer.parseInt(commandArgs[2]);
                    currentFuel = carsFuel.get(car);

                    if (currentFuel + fuel > 75) {
                        carsFuel.put(car, 75);
                    } else {
                        carsFuel.put(car, currentFuel + fuel);
                    }

                    System.out.printf("%s refueled with %d liters%n", car, carsFuel.get(car) - currentFuel);
                    break;
                case "Revert":
                    int kilometers = Integer.parseInt(commandArgs[2]);
                    int currentMileage = carsMileage.get(car);

                    if (currentMileage - kilometers >= 10000) {
                        carsMileage.put(car, currentMileage - kilometers);
                        System.out.printf("%s mileage decreased by %d kilometers%n", car, kilometers);
                    } else {
                        carsMileage.put(car, 10000);
                    }
                    break;
            }
            command = sc.nextLine();
        }
        carsMileage.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", e.getKey(), e.getValue(), carsFuel.get(e.getKey())));
    }
}
