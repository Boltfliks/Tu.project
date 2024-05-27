package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Empty class implements the Action interface to check if a specified context-free grammar is empty.
 */
public class Empty implements Action {
    /**
     * Executes the action to check if the grammar with the specified ID is empty.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens The array of command tokens, where tokens[1] is the ID of the grammar.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id>");
            }

            int id = Integer.parseInt(tokens[1]);
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            ContextFreeGrammar grammar = grammars.getGrammarById(id);
            System.out.println(grammar.empty(grammar));
        } catch (NumberFormatException e) {
            try {
                throw new MenuException("Wrong ID");
            } catch (MenuException me) {
                System.out.println("Error: " + me.getMessage());
            }
        } catch (MenuException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

