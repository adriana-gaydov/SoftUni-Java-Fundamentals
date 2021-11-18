package ProgrammingFundamentalsFinalExam02;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, Integer> plantsRarity = new LinkedHashMap<>();
        Map<String, List<Double>> plantsRating = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("<->");
            String plant = tokens[0];
            int rarity =  Integer.parseInt(tokens[1]);
            plantsRarity.put(plant, rarity);
            plantsRating.put(plant, new ArrayList<>());
        }

        String command = sc.nextLine();
        while (!command.equals("Exhibition")) {
            String[] commandArgs = command.split(": | - ");
            String action = commandArgs[0];
            String plant = commandArgs[1];

            switch (action) {
                case "Rate":
                    double rating = Double.parseDouble(commandArgs[2]);
                    if (!plantsRating.containsKey(plant)) {
                        System.out.println("error");
                        break;
                    }
                    plantsRating.get(plant).add(rating);
                    break;
                case "Update":
                    int newRarity = Integer.parseInt(commandArgs[2]);
                    if (!plantsRarity.containsKey(plant)) {
                        System.out.println("error");
                        break;
                    }
                    plantsRarity.put(plant, newRarity);
                    break;
                case "Reset":
                    if (!plantsRating.containsKey(plant)) {
                        System.out.println("error");
                        break;
                    }
                    plantsRating.put(plant, new ArrayList<>());
                    break;
            }
            command = sc.nextLine();
        }
        System.out.println("Plants for the exhibition:");

       /* List<String> sortedPlants = plantsRating.entrySet().stream().sorted((e1, e2) -> {
            int sortedResult = Double.compare(getAverageRating(e2.getValue()), getAverageRating(e1.getValue()));
            return sortedResult;
        }).map(Map.Entry::getKey).collect(Collectors.toList());

        sortedPlants = sortedPlants.stream().sorted((e1, e2) -> {
            int sortedResult = Integer.compare(plantsRarity.get(e2), plantsRarity.get(e1));
            return sortedResult;
        }).collect(Collectors.toList());

        sortedPlants.forEach(e -> {
            System.out.printf("- %s; Rarity: %d; Rating: %.2f%n", e, plantsRarity.get(e), getAverageRating(plantsRating.get(e)));
        });*/

        plantsRarity.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()
                .thenComparingDouble(x -> plantsRating.get(x.getKey()).stream()
                        .mapToDouble(Double::doubleValue)
                        .average().orElse(0.0))
                .reversed())
                .forEach(e -> System.out.printf("- %s; Rarity: %d; Rating: %.2f%n", e.getKey(), e.getValue(),
                        plantsRating.get(e.getKey()).stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));
    }

    public static double getAverageRating(List<Double> rating) {
        double average = rating.stream().mapToDouble(e -> e).average().orElse(0);
        return average;
    }
}



