package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Print class implements the Action interface to print the grammar of a given ID.
 */
public class Print implements Action {
    /**
     * Executes the action to print the grammar with the specified ID.
     * This method retrieves the grammar by ID from the ListOfGrammars and prints it.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id>");
            }
            int id = Integer.parseInt(tokens[1]);
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            grammars.getGrammarById(id).printGrammar(id);
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }
}
