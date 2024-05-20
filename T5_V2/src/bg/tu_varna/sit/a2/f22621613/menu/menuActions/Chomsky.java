package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.ChomskyGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Chomsky class implements the Action interface to check if a given grammar
 * is in Chomsky Normal Form (CNF).
 */
public class Chomsky implements Action {

    /**
     * Executes the action of checking if a grammar is in Chomsky Normal Form based on the provided tokens.
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
            System.out.println(grammar.chomsky());
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
