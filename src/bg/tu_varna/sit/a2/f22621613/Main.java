package bg.tu_varna.sit.a2.f22621613;

import bg.tu_varna.sit.a2.f22621613.menu.menuActions.Action;
import bg.tu_varna.sit.a2.f22621613.menu.menuActions.Commands;
import bg.tu_varna.sit.a2.f22621613.menu.menuActions.MenuMap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuMap menuMap = new MenuMap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            String[] tokens = input.split("\\s+");
            String commandStr = tokens[0].toUpperCase();

            try {
                Commands command = Commands.valueOf(commandStr);

                if (menuMap.hasAction(command)) {
                    Action action = menuMap.getAction(command);
                    action.action(menuMap, tokens);
                } else {
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }
}
