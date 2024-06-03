import java.util.Scanner;

public class MainActivity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Each group has 2 of A, 2 of B, 1 of C, 1 of D characters.\n\n A has 4 health, 1-2 attack\nB has 3 health, 3 attack\nC has 10 health, 0-4 attack\nD has 2 health, 0-10 attack\n\n");
        System.out.println("Enter the Blue Group string: (example: 'AABBCD')");
        String blueGroup = scanner.nextLine();

        System.out.println("Enter the Purple Group string: (example: 'AABBCD')");

        String purpleGroup = scanner.nextLine();

        Game game = new Game(blueGroup, purpleGroup);
        game.start();
    }

}