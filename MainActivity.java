import java.util.Scanner;

public class MainActivity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Each group has 3 of A(Warrior), 2 of B(Wizard), 1 of C(Monk), 3 of D(Assassin), 1 of E(Giant) characters.\n\nA(Warrior) has 5 health, 1-3 attack\nB(Wizard) has 4 health, 4 attack\nC(Monk) has 9 health, 1-4 attack\nD(Assassin) has 1 health, 0-12 attack\nE(Giant) has 15 health, 1-3 attack\n");
        System.out.println("Enter the Blue Group string: (example: 'AAABBCDDDE')");
        String blueGroup = scanner.nextLine();

        System.out.println("Enter the Purple Group string: (example: 'AAABBCDDDE')");

        String purpleGroup = scanner.nextLine();

        Game game = new Game(blueGroup, purpleGroup);
        game.start();
    }

}