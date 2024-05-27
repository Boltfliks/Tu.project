package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.ChomskyGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Chomskify class implements the Action interface to convert a given grammar
 * to Chomsky Normal Form (CNF).
 */
public class Chomskify implements Action {

    /**
     * Executes the action of converting a grammar to Chomsky Normal Form based on the provided tokens.
     *
     * @param menuMap The MenuMap instance used to manage the action.
     * @param tokens  The tokens representing the command and grammar ID.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {

        try {
            if (tokens.length != 2) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id>");
            }
            int id = Integer.parseInt(tokens[1]);
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            ChomskyGrammar grammar = new ChomskyGrammar(grammars.getGrammarById(id));
            ContextFreeGrammar contextFreeGrammar = grammar.chomskify(id);
            System.out.println("Id: " + contextFreeGrammar.getUniqueId());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format. Please enter a valid integer ID.");
        } catch (MenuException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
