package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.Menu;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

import java.util.HashMap;
import java.util.Map;

/**
 * The MenuMap class maintains a mapping between commands and their corresponding actions.
 * It provides methods to execute these actions based on input commands.
 */
public class MenuMap {
    private Map<Commands, Action> actionMap = new HashMap<>();
    private Menu menu;
    private String filename;

    /**
     * Constructs a new MenuMap and initializes the action map with command-action pairs.
     */
    public MenuMap() {
        menu = new Menu();
        // Initialize the action map with enum FOR COMMAND
        actionMap.put(Commands.OPEN, new Open());
        actionMap.put(Commands.NEW, new NewGrammar());
        actionMap.put(Commands.LIST, new List());
        actionMap.put(Commands.EMPTY, new Empty());
        actionMap.put(Commands.PRINT, new Print());
        actionMap.put(Commands.SAVE, new Save());
        actionMap.put(Commands.SAVEAS, new SaveAs());
        actionMap.put(Commands.SAVECHANGES, new SaveChanges());
        actionMap.put(Commands.ADDRULE, new AddRule());
        actionMap.put(Commands.REMOVERULE, new RemoveRule());
        actionMap.put(Commands.CHOMSKY, new Chomsky());
        actionMap.put(Commands.CHOMSKIFY, new Chomskify());
        actionMap.put(Commands.UNION, new Union());
        actionMap.put(Commands.CONCAT, new Concat());
        actionMap.put(Commands.ITER, new Iter());
        actionMap.put(Commands.CYK, new CYK());
        actionMap.put(Commands.HELP, new Help());
        actionMap.put(Commands.CLOSE, new Close());
        actionMap.put(Commands.EXIT, new Exit());
    }

    /**
     * Gets the Menu instance associated with this MenuMap.
     *
     * @return The Menu instance.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Sets the filename associated with this MenuMap.
     *
     * @param filename The filename to be set.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Checks if an action is mapped to the specified command.
     *
     * @param command The command to check.
     * @return True if the command has a mapped action, false otherwise.
     */
    public boolean hasAction(Commands command) {
        return actionMap.containsKey(command);
    }

    /**
     * Gets the action associated with the specified command.
     *
     * @param command The command for which the action is to be retrieved.
     * @return The action mapped to the command, or null if no action is mapped.
     */
    public Action getAction(Commands command) {
        return actionMap.get(command);
    }

    /**
     * Executes the action associated with the specified command using the provided tokens.
     *
     * @param command The command whose action is to be executed.
     * @param tokens  The tokens to be passed to the action.
     */
    public void executeAction(Commands command, String[] tokens) {
        Action action = actionMap.get(command);
        try {
            if (action != null) {
                action.action(this, tokens);
            } else {
                throw new MenuException("Invalid action: " + command);
            }
        } catch (MenuException e) {
            System.out.println("MenuException: " + e.getMessage());
        }
    }

    /**
     * Executes the action associated with the specified action key using the provided tokens.
     *
     * @param actionKey The key of the action to be executed.
     * @param tokens    The tokens to be passed to the action.
     */
    public void executeAction(String actionKey, String[] tokens) {
        try {
            Commands command = Commands.valueOf(actionKey.toUpperCase());
            executeAction(command, tokens);
        } catch (IllegalArgumentException e) {
            System.out.println("MenuException: Invalid action: " + actionKey);
        }
    }
}