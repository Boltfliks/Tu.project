package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.ConcatGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Concat class implements the Action interface to perform the concatenation
 * of two context-free grammars identified by their unique IDs.
 */
public class Concat implements Action {
    /**
     * Executes the concatenation action on the specified grammars.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens The array of command tokens, where tokens[1] and tokens[2] are the IDs of the grammars to be concatenated.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id> <id>");
            }
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            int id1 = Integer.parseInt(tokens[1]);
            int id2 = Integer.parseInt(tokens[2]);

            ConcatGrammar concatGrammar = new ConcatGrammar();
            concatGrammar.concat(grammars.getGrammarById(id1), grammars.getGrammarById(id2));
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
