import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    static Scanner scan = new Scanner(System.in);
    protected static Account player;

    public static void main(String[] args) {
        System.out.println("Welcome to Cadia! Land of Fantasy and Adventure!");
        mainMenu();
        System.out.println("\nGreetings " + player.name + "! Your Journey Begins...");

        //Game Loop
        GameLoop game = new GameLoop(player);

        game.encounter1();
    }

    public static void mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. New Game");
        System.out.println("2. Load Game"); //not implemented
        System.out.println("3. Exit");
        int choice = scan.nextInt();
        if (choice == 1) {
            newGame();
        } else if (choice == 2) {
            loadGame();
        } else if (choice == 3) {
            System.out.println("\nGoodbye!\n");
            System.exit(0);
        } else {
            System.out.println("Invalid choice, please try again.");
            mainMenu();
        }
    }

    public static void newGame(){
        //make new account with entered username
        System.out.println("Enter your username:");
        String username = scan.next();

        //choose class
        System.out.println("Choose your class:");
        System.out.println("1. Warrior (The Unmovable Wall)");
        System.out.println("2. Mage (The Glass Cannon Caster)");
        System.out.println("3. Rogue (The Speedy Slasher)");
        System.out.println("4. Priest (The Healer)");
        int choice = scan.nextInt();

        player = new Account(username, choice);
    }

    public static void loadGame(){
        //not implemented
    }
}
