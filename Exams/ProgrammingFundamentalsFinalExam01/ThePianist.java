package ProgrammingFundamentalsFinalExam01;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ThePianist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, String> piecesComposer = new LinkedHashMap<>();
        Map<String, String> piecesKey = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\|");
            String piece = tokens[0];
            String composer = tokens[1];
            String key = tokens[2];

            piecesComposer.put(piece, composer);
            piecesKey.put(piece, key);
        }

        String command = sc.nextLine();
        while (!command.equals("Stop")) {
            String[] commandArgs = command.split("\\|");
            String action = commandArgs[0];
            String piece = commandArgs[1];

            switch (action) {
                case "Add":
                    String composer = commandArgs[2];
                    String key = commandArgs[3];
                    if (piecesComposer.containsKey(piece) || piecesKey.containsKey(piece)) {
                        System.out.printf("%s is already in the collection!%n", piece);
                    } else {
                        piecesComposer.put(piece, composer);
                        piecesKey.put(piece, key);
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                    }
                    break;
                case "Remove":
                    if (piecesComposer.containsKey(piece) && piecesKey.containsKey(piece)) {
                        piecesComposer.remove(piece);
                        piecesKey.remove(piece);
                        System.out.printf("Successfully removed %s!%n", piece);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
                case "ChangeKey":
                    String newKey = commandArgs[2];
                    if (piecesComposer.containsKey(piece) && piecesKey.containsKey(piece)) {
                        piecesKey.put(piece, newKey);
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
            }
            command = sc.nextLine();
        }
        piecesComposer.entrySet().stream().sorted(Map.Entry.<String, String>comparingByKey().thenComparing(Map.Entry.comparingByValue()))
                .forEach(e -> System.out.printf("%s -> Composer: %s, Key: %s%n", e.getKey(), e.getValue(), piecesKey.get(e.getKey())));
    }
}
