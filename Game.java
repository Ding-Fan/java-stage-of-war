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
                    group.addCharacter(new Character("A", 4, 1, 2));
                    break;
                case 'B':
                    group.addCharacter(new Character("B", 3, 3, 3));
                    break;
                case 'C':
                    group.addCharacter(new Character("C", 10, 0, 5));
                    break;
                case 'D':
                    group.addCharacter(new Character("D", 2, 0, 10));
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

            // show the characters in each group
            System.out.println("Blue Group: " + blueGroup.getRestCharacters());
            System.out.println("Purple Group: " + purpleGroup.getRestCharacters());

            System.out.println(
                    "\nBlue (" + blueCharacter.LABEL + ") and Purple (" + purpleCharacter.LABEL + ") are in battle!\n");

            System.out.println("Blue (" + blueCharacter.LABEL + ") health: " + blueCharacter.getHealth());
            int blueAttack = blueCharacter.attack();
            System.out.println("Blue (" + blueCharacter.LABEL + ") attacks for " + blueAttack + " damage!\n");

            System.out.println("Purple (" + purpleCharacter.LABEL + ") health: " + purpleCharacter.getHealth());
            int purpleAttack = purpleCharacter.attack();
            System.out.println("Purple (" + purpleCharacter.LABEL + ") attacks for " + purpleAttack + " damage!\n");

            blueCharacter.setHealth(blueCharacter.getHealth() - purpleAttack);
            purpleCharacter.setHealth(purpleCharacter.getHealth() - blueAttack);

            if (purpleCharacter.getHealth() <= 0) {
                purpleGroup.getCharacters().remove(purpleCharacter);
                System.out.println("Purple " + purpleCharacter.LABEL + " defeated!");
            }

            if (blueCharacter.getHealth() <= 0) {
                blueGroup.getCharacters().remove(blueCharacter);
                System.out.println("Blue " + blueCharacter.LABEL + " defeated!");
            }

            if (blueGroup.isDefeated() && purpleGroup.isDefeated()) {
                System.out.println("\nIt's a draw!");
                break;
            } else if (blueGroup.isDefeated()) {
                System.out.println("\nBlue group defeated! Victory for Purple group!");
                break;
            } else if (purpleGroup.isDefeated()) {
                System.out.println("\nPurple group defeated! Victory for Blue group!");
                break;
            }

            // show the rest of the characters in each group
            System.out.println();
            System.out.println("Blue Group remaining characters: " + blueGroup.getRestCharacters());
            System.out.println("Purple Group remaining characters: " + purpleGroup.getRestCharacters());

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

    private void applyRandomEvent() {
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
