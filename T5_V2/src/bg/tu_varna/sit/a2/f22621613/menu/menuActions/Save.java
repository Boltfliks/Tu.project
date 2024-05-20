package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Save class implements the Action interface to save a grammar to a specified file.
 */
public class Save implements Action {
    /**
     * Executes the action to save a grammar with the specified ID to a given file.
     * This method retrieves the grammar by ID from the ListOfGrammars and saves it to the specified file.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id> <filename>");
            }
            int id = Integer.parseInt(tokens[1]);
            String fileName = tokens[2];
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            ContextFreeGrammar grammar = grammars.getGrammarById(id);
            grammar.save(grammar.getUniqueId(), fileName);
        } catch (NumberFormatException f) {
            try {
                throw new MenuException("Wrong Id");
            } catch (MenuException xe) {
                System.out.println(xe.getMessage());
            }
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }
}
