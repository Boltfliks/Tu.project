package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.StarKleeneGrammar;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Iter class implements the Action interface to apply the Kleene star operation
 * on a context-free grammar identified by its unique ID.
 */
public class Iter implements Action {
    /**
     * Executes the iteration action on a context-free grammar.
     * This method applies the Kleene star operation on the specified grammar.
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
            ContextFreeGrammar grammar = grammars.getGrammarById(id);
            StarKleeneGrammar kleeneGrammar = new StarKleeneGrammar(grammar);
            kleeneGrammar.iter(grammar);
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
