package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.CYKAlgorithm;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The CYK class implements the Action interface to perform the CYK parsing algorithm
 * on a given string using a specified context-free grammar identified by its unique ID.
 */
public class CYK implements Action {
    /**
     * Executes the CYK parsing action using the specified grammar ID and input string.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens The array of command tokens, where tokens[1] is the ID of the grammar and tokens[2] is the string to be parsed.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id> <string>");
            }
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            int id = Integer.parseInt(tokens[1]);
            String check = tokens[2];
            CYKAlgorithm grammar = new CYKAlgorithm(grammars.getGrammarById(id));
            System.out.println(grammar.parse(check));
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
