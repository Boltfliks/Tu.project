package bg.tu_varna.sit.a2.f22621613;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.Menu.Menu;
import bg.tu_varna.sit.a2.f22621613.Menu.menuActions.Action;
import bg.tu_varna.sit.a2.f22621613.Menu.menuActions.MenuMap;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
// Create a scanner to read user input
        MenuMap menuMap = new MenuMap();

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Main command loop
        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            // Split the input into command and arguments
            String[] tokens = input.split("\\s+");
            String command = tokens[0].toLowerCase(); // Get the command in lowercase

            // Execute the command using the menu map
            if (menuMap.hasAction(command)) {
                // Execute the command using the menu map
                Action action = menuMap.getAction(command);
                if (tokens.length >= 0) {
                    // If additional arguments are provided, pass them to the action
                    action.action(menuMap,tokens);
                }
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }

    }
}
