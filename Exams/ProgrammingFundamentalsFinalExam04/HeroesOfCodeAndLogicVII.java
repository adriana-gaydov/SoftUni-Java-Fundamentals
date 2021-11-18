package ProgrammingFundamentalsFinalExam04;

import java.util.*;

public class HeroesOfCodeAndLogicVII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> heroesHP = new LinkedHashMap<>();
        Map<String, Integer> heroesMP = new LinkedHashMap<>();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String heroName = tokens[0];
            int hp = Integer.parseInt(tokens[1]);
            int mp = Integer.parseInt(tokens[2]);

            if (hp <= 100) {
                heroesHP.put(heroName, hp);
            }

            if (mp <= 200) {
                heroesMP.put(heroName, mp);
            }
        }

        String command = sc.nextLine();

        while (!command.equals("End")) {
            String[] commandArgs = command.split(" - ");
            String action = commandArgs[0];
            String heroName = commandArgs[1];

            switch (action) {
                case "CastSpell":
                    int mpNeeded = Integer.parseInt(commandArgs[2]);
                    String spellName = commandArgs[3];
                    int currentMp = heroesMP.get(heroName);

                    if (currentMp >= mpNeeded) {
                        int mpLeft = currentMp - mpNeeded;
                        heroesMP.put(heroName, mpLeft);
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n", heroName, spellName, mpLeft);
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!%n", heroName, spellName);
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(commandArgs[2]);
                    String attacker = commandArgs[3];
                    int currentHp = heroesHP.get(heroName);
                    int leftHp = currentHp - damage;

                    if (leftHp > 0) {
                        heroesHP.put(heroName, leftHp);
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", heroName, currentHp - leftHp, attacker, leftHp);
                    } else {
                        heroesHP.remove(heroName);
                        heroesMP.remove(heroName);
                        System.out.printf("%s has been killed by %s!%n", heroName, attacker);
                    }
                    break;
                case "Recharge":
                    int amount = Integer.parseInt(commandArgs[2]);
                    currentMp = heroesMP.get(heroName);
                    currentMp += amount;

                    if (currentMp > 200) {
                        currentMp = 200;
                    }

                    System.out.printf("%s recharged for %d MP!%n", heroName, currentMp - heroesMP.get(heroName));
                    heroesMP.put(heroName, currentMp);
                    break;
                case "Heal":
                    amount = Integer.parseInt(commandArgs[2]);
                    currentHp = heroesHP.get(heroName);
                    currentHp += amount;

                    if (currentHp > 100) {
                        currentHp = 100;
                    }

                    System.out.printf("%s healed for %d HP!%n", heroName, currentHp - heroesHP.get(heroName));
                    heroesHP.put(heroName, currentHp);
                    break;
            }
            command = sc.nextLine();
        }

        //heroesMP = (Map<String, Integer>) heroesMP.entrySet().stream().sorted(Map.Entry.comparingByKey());
        heroesHP.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.printf("  HP: %d%n", e.getValue());
                    System.out.printf("  MP: %d%n", heroesMP.get(e.getKey()));
                });
    }
}
