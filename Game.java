import java.util.*;

public class Game {
    private Group blueGroup;
    private Group purpleGroup;

    public Game(String blueGroupString, String purpleGroupString) {
        this.blueGroup = new Group("Blue");
        this.purpleGroup = new Group("Purple");
        initializeGroup(blueGroup, blueGroupString);
        initializeGroup(purpleGroup, purpleGroupString);
    }

    private void initializeGroup(Group group, String groupString) {
        for (char c : groupString.toCharArray()) {
            switch (c) {
                case 'A':
                    group.addCharacter(new Warrior());
                    break;
                case 'B':
                    group.addCharacter(new Wizard());
                    break;
                case 'C':
                    group.addCharacter(new Monk());
                    break;
                case 'D':
                    group.addCharacter(new Assassin());
                    break;
                case 'E':
                    group.addCharacter(new Giant());
                    break;
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        boolean isDuel = false;

        while (gameRunning) {
            // Battle logic
            // pick the first character(index 0) from each group
            // and let them attack each other
            // then remove the defeated character from the group
            // repeat until one group is defeated

            Character blueCharacter = blueGroup.getCharacters().get(0);
            Character purpleCharacter = purpleGroup.getCharacters().get(0);

            int blueAttack = blueCharacter.attack();
            int purpleAttack = purpleCharacter.attack();

            Character blueNextCharacter = null;
            if (blueGroup.getCharacters().size() > 1) {
                blueNextCharacter = blueGroup.getCharacters().get(1);
            }

            Character purpleNextCharacter = null;
            if (purpleGroup.getCharacters().size() > 1) {
                purpleNextCharacter = purpleGroup.getCharacters().get(1);
            }

            System.out.println();
            if (blueNextCharacter != null && blueNextCharacter.label.equals(Wizard.LABEL)) {
                blueAttack = blueAttack + 1;
                System.out.println("--------");
                System.out.println("Blue " + blueNextCharacter.label + ": You are powered up, get in there!");
                System.out.println("Blue " + blueCharacter.label + " attack +1");
                System.out.println();
            }
            if (purpleNextCharacter != null && purpleNextCharacter.label.equals(Wizard.LABEL)) {
                purpleAttack = purpleAttack + 1;
                System.out.println("--------");
                System.out.println("Purple " + purpleNextCharacter.label + ": You are powered up, get in there!");
                System.out.println("Purple " + purpleCharacter.label + " attack +1");
                System.out.println();
            }

            if (blueNextCharacter == null
                    && purpleNextCharacter == null
                    && blueCharacter.label.equals(Warrior.LABEL)
                    && purpleCharacter.label.equals(Warrior.LABEL)) {

                isDuel = true;

                System.out.println("------------");
                System.out.println("------------");
                System.out.println("Special Scene: THE LAST WARRIOR");
                System.out.println("------------");
                System.out.println("------------");

                try {

                    System.out.println();
                    System.out.println("Duel...");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

            }

            // divider
            System.out.println("\n----------------------------------------\n");

            // show the characters in each group
            this.showStatus();

            try {
                System.out.println(
                        "\nBlue [" + blueCharacter.label + "] and Purple [" + purpleCharacter.label
                                + "] are in battle!\n");
                Thread.sleep(1000);

                System.out.println("Blue [" + blueCharacter.label + "] health: " + blueCharacter.getHealth());
                System.out.println("Blue [" + blueCharacter.label + "] attacks for " + blueAttack + " damage!");
                Thread.sleep(700);
                System.out.println("Purple [" + purpleCharacter.label + "] health: " + purpleCharacter.getHealth());
                System.out.println("Purple [" + purpleCharacter.label + "] attacks for " + purpleAttack + " damage!\n");
                Thread.sleep(700);

                if (isMonkAndWizard(blueCharacter, purpleCharacter)) {
                    System.out.println("Monk trying to do meditaion...");
                    if (tossACoin()) {
                        System.out.println("Reflection!");
                        int[] reflectionResult = handleReflection(blueCharacter, purpleCharacter, blueAttack,
                                purpleAttack);
                        blueAttack = reflectionResult[0];
                        purpleAttack = reflectionResult[1];
                    } else {
                        System.out.println("Monk fall asleep...");
                        int[] fellAsleepResult = handleFellAsleep(blueCharacter, purpleCharacter, blueAttack,
                                purpleAttack);
                        blueAttack = fellAsleepResult[0];
                        purpleAttack = fellAsleepResult[1];
                    }
                }

                if (isDuel) {
                    if (blueAttack == purpleAttack) {
                        System.out.println();
                        System.out.println("Deflect!");
                        blueAttack = 0;
                        purpleAttack = 0;
                        System.out.println();
                    } else if (blueAttack > purpleAttack) {
                        System.out.println();
                        System.out.println("Blue " + blueCharacter.label + ": Too slow!");
                        blueAttack = blueAttack + purpleAttack;
                        purpleAttack = 0;
                        System.out.println();
                    } else if (blueAttack < purpleAttack) {
                        System.out.println();
                        System.out.println("Purple " + purpleCharacter.label + ": Too slow!");
                        purpleAttack = purpleAttack + blueAttack;
                        blueAttack = 0;
                        System.out.println();
                    }
                }

                int blueCurrentHealth = blueCharacter.getHealth();
                int blueNextHealth = blueCharacter.getHealth() - purpleAttack;
                int purpleCurrentHealth = purpleCharacter.getHealth();
                int purpleNextHealth = purpleCharacter.getHealth() - blueAttack;

                System.out.println("Blue " + blueCharacter.label + " health: ");
                System.out.println(
                        blueCurrentHealth + "->" + blueNextHealth);
                blueCharacter.setHealth(blueNextHealth);

                System.out.println("Purple " + purpleCharacter.label + " health: ");
                System.out.println(
                        purpleCurrentHealth + "->" + purpleNextHealth);
                purpleCharacter.setHealth(purpleNextHealth);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            if (purpleCharacter.getHealth() <= 0) {
                purpleGroup.getCharacters().remove(purpleCharacter);
                System.out.println("Purple [" + purpleCharacter.label + "] defeated!");
            }

            if (blueCharacter.getHealth() <= 0) {
                blueGroup.getCharacters().remove(blueCharacter);
                System.out.println("Blue [" + blueCharacter.label + "] defeated!");
            }

            if (blueGroup.isDefeated() && purpleGroup.isDefeated()) {
                System.out.println("\nIt's a draw!");
                break;
            } else if (blueGroup.isDefeated()) {
                System.out.println("\nBlue group defeated!\nVictory for\n\nPurple group!\n\n");
                break;
            } else if (purpleGroup.isDefeated()) {
                System.out.println("\nPurple group defeated!\nVictory for\n\nBlue group!\n\n");
                break;
            }

            // show the rest of the characters in each group
            System.out.println();
            this.showStatus();

            System.out.println();
            System.out.println();
            System.out.println("================================");
            System.out.println("================================");
            System.out.println("================================");
            System.out.println("================================");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();

            // Check for dice roll event
            // System.out.println("Do you want to roll the dice for a random event?
            // (yes/no)");
            // String response = scanner.nextLine();
            // if (response.equalsIgnoreCase("yes")) {
            // applyRandomEvent();
            // }

            // // Check for end of game
            // gameRunning = groups.values().stream().filter(g -> !g.isDefeated()).count() >
            // 1;
        }

        System.out.println("Winner winner chicken dinner!\n");

    }

    private boolean isMonkAndWizard(Character blueCharacter, Character purpleCharacter) {
        return (blueCharacter.label.equals("Monk") && purpleCharacter.label.equals("Wizard"))
                || (blueCharacter.label.equals("Wizard") && purpleCharacter.label.equals("Monk"));
    }

    private int[] handleReflection(Character blueCharacter, Character purpleCharacter, int blueAttack,
            int purpleAttack) {
        if (blueCharacter.label.equals("Monk") && purpleCharacter.label.equals("Wizard")) {
            blueAttack = 2;
            purpleAttack = 0;
        } else if (blueCharacter.label.equals("Wizard") && purpleCharacter.label.equals("Monk")) {
            blueAttack = 0;
            purpleAttack = 2;
        }

        return new int[] { blueAttack, purpleAttack };
    }

    private int[] handleFellAsleep(Character blueCharacter, Character purpleCharacter, int blueAttack,
            int purpleAttack) {
        if (blueCharacter.label.equals("Monk") && purpleCharacter.label.equals("Wizard")) {
            blueAttack = 0;
            purpleAttack = purpleAttack * 2;
        } else if (blueCharacter.label.equals("Wizard") && purpleCharacter.label.equals("Monk")) {
            purpleAttack = 0;
            blueAttack = blueAttack * 2;
        }

        return new int[] { blueAttack, purpleAttack };
    }

    public void showStatus() {
        List<Character> blueCharacters = blueGroup.getCharacters();
        String blueGroupString = "Blue Group: [" + blueCharacters.get(0).label + "]"
                + (blueCharacters.size() > 1 ? ", " : "");
        for (int counter = 1; counter < blueCharacters.size(); counter++) {
            blueGroupString = blueGroupString
                    + blueCharacters.get(counter).label
                    + (counter == blueCharacters.size() - 1 ? "" : ", ");
        }
        System.out
                .println(blueGroupString);

        List<Character> purpleCharacters = purpleGroup.getCharacters();
        String purpleGroupString = "Purple Group: [" + purpleCharacters.get(0).label + "]"
                + (purpleCharacters.size() > 1 ? ", " : "");
        for (int counter = 1; counter < purpleCharacters.size(); counter++) {
            purpleGroupString = purpleGroupString
                    + purpleCharacters.get(counter).label
                    + (counter == purpleCharacters.size() - 1 ? "" : ", ");
        }
        System.out
                .println(purpleGroupString);
    }

    private boolean tossACoin() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private void applyRandomEvent() {

        // critical hit
        // double hit
        // absolute defense
        // drain health
        // prevent death once, remain 1 health
        // instant death, will be 50% chance at the first event

        // there will be two events chance
        // if you choose to skip the first
        // you can roll dice twice for the second event
        // and you can choose to use or skip the result, both

        // Random random = new Random();
        // for (Group group : groups.values()) {
        // int event = random.nextInt(3); // Example: 3 possible events
        // for (Character character : group.getCharacters()) {
        // switch (event) {
        // case 0:
        // // Buff health
        // character.setHealth(character.getHealth() + 20);
        // System.out.println("Group " + group.getGroupId() + " gets a health buff!");
        // break;
        // case 1:
        // // Buff attack
        // // This could modify attack range instead
        // unbelievable, character attacks for n damage
        // the damage could be a random number between -5 to 5
        // break;
        // case 2:
        // // Debuff health
        // character.setHealth(character.getHealth() - 20);
        // System.out.println("Group " + group.getGroupId() + " gets a health debuff!");
        // break;
        // }
        // }
        // }
    }

}
