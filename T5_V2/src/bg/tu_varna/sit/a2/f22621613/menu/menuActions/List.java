package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The List class implements the Action interface to list all context-free grammars.
 */
public class List implements Action {
    /**
     * Executes the list action to display all available grammars.
     * This method retrieves and prints the list of all grammars from the ListOfGrammars instance.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 1) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command>");
            }

            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            System.out.println(grammars.list());
        } catch (MenuException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
