package ProgrammingFundamentalsFinalExam05;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Pirates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        Map<String, Integer> citiesPopulation = new LinkedHashMap<>();
        Map<String, Integer> citiesGold = new LinkedHashMap<>();

        while (!command.equals("Sail")) {
            String[] commandArgs = command.split("\\|{2}");
            String city = commandArgs[0];
            int population = Integer.parseInt(commandArgs[1]);
            int gold = Integer.parseInt(commandArgs[2]);

            if(!citiesPopulation.containsKey(city)) {
                citiesGold.put(city, gold);
                citiesPopulation.put(city, population);
            } else {
                citiesGold.put(city, gold + citiesGold.get(city));
                citiesPopulation.put(city, population + citiesPopulation.get(city));
            }
            command = sc.nextLine();
        }

        command = sc.nextLine();
        while (!command.equals("End")) {
            String[] commandArgs = command.split("=>");
            String event = commandArgs[0];
            String town = commandArgs[1];

            switch (event) {
                case "Plunder":
                    int people = Integer.parseInt(commandArgs[2]);
                    int gold = Integer.parseInt(commandArgs[3]);
                    int currentPeople = citiesPopulation.get(town);
                    int currentGold = citiesGold.get(town);

                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", town, gold, people);
                    if (currentPeople - people <= 0 || currentGold - gold <= 0) {
                        citiesGold.remove(town);
                        citiesPopulation.remove(town);
                        System.out.printf("%s has been wiped off the map!%n", town);
                    } else {
                        citiesGold.put(town, currentGold - gold);
                        citiesPopulation.put(town, currentPeople - people);
                    }
                    break;
                case "Prosper":
                    gold = Integer.parseInt(commandArgs[2]);
                    if (gold < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                    } else {
                        citiesGold.put(town, gold + citiesGold.get(town));
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", gold, town, citiesGold.get(town));
                    }
            }
            command = sc.nextLine();
        }

       if (citiesGold.size() != 0) {
           System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", citiesGold.size());
           citiesGold.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                   .forEach(e -> System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", e.getKey(), citiesPopulation.get(e.getKey()), e.getValue()));
       } else {
           System.out.printf("Ahoy, Captain! All targets have been plundered and destroyed!");
       }
       }
}
