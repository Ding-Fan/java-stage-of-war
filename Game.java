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
                    group.addCharacter(new Character("Warrior", 4, 1, 2));
                    break;
                case 'B':
                    group.addCharacter(new Character("Wizard", 3, 3, 3));
                    break;
                case 'C':
                    group.addCharacter(new Character("Tank", 10, 0, 4));
                    break;
                case 'D':
                    group.addCharacter(new Character("Assassin", 2, 0, 10));
                    break;
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            // Battle logic
            // pick the first character(index 0) from each group
            // and let them attack each other
            // then remove the defeated character from the group
            // repeat until one group is defeated

            Character blueCharacter = blueGroup.getCharacters().get(0);
            Character purpleCharacter = purpleGroup.getCharacters().get(0);

            // divider
            System.out.println("\n----------------------------------------\n");

            // show the characters in each group
            this.showStatus();

            try {
                System.out.println(
                        "\nBlue [" + blueCharacter.LABEL + "] and Purple [" + purpleCharacter.LABEL
                                + "] are in battle!\n");
                Thread.sleep(1000);

                System.out.println("Blue [" + blueCharacter.LABEL + "] health: " + blueCharacter.getHealth());
                int blueAttack = blueCharacter.attack();
                System.out.println("Blue [" + blueCharacter.LABEL + "] attacks for " + blueAttack + " damage!\n");
                Thread.sleep(700);

                System.out.println("Purple [" + purpleCharacter.LABEL + "] health: " + purpleCharacter.getHealth());
                int purpleAttack = purpleCharacter.attack();
                System.out.println("Purple [" + purpleCharacter.LABEL + "] attacks for " + purpleAttack + " damage!\n");
                Thread.sleep(700);

                blueCharacter.setHealth(blueCharacter.getHealth() - purpleAttack);
                purpleCharacter.setHealth(purpleCharacter.getHealth() - blueAttack);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }



            if (purpleCharacter.getHealth() <= 0) {
                purpleGroup.getCharacters().remove(purpleCharacter);
                System.out.println("Purple [" + purpleCharacter.LABEL + "] defeated!");
            }

            if (blueCharacter.getHealth() <= 0) {
                blueGroup.getCharacters().remove(blueCharacter);
                System.out.println("Blue [" + blueCharacter.LABEL + "] defeated!");
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

    public void showStatus() {
        List<Character> blueCharacters = blueGroup.getCharacters();
        String blueGroupString = "Blue Group: [" + blueCharacters.get(0).LABEL + "]" + (blueCharacters.size() > 1 ? ", " : "");
        for (int counter = 1; counter < blueCharacters.size(); counter++) {
            blueGroupString = blueGroupString
                    + blueCharacters.get(counter).LABEL
                    + (counter == blueCharacters.size() - 1 ? "" : ", ");
        }
        System.out
                .println(blueGroupString);

        List<Character> purpleCharacters = purpleGroup.getCharacters();
        String purpleGroupString = "Purple Group: [" + purpleCharacters.get(0).LABEL + "]" + (purpleCharacters.size() > 1 ? ", " : "");
        for (int counter = 1; counter < purpleCharacters.size(); counter++) {
            purpleGroupString = purpleGroupString
                    + purpleCharacters.get(counter).LABEL
                    + (counter == purpleCharacters.size() - 1 ? "" : ", ");
        }
        System.out
                .println(purpleGroupString);
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
