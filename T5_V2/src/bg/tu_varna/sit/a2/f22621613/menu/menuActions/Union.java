package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.algorithms.UnionGrammar;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The Union class implements the Action interface to perform the union operation on two grammars.
 */
public class Union implements Action {
    /**
     * Executes the action to perform the union operation on two grammars.
     * This method expects two grammar IDs and unions the corresponding grammars.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id1> <id2>");
            }
            int id1 = Integer.parseInt(tokens[1]);
            int id2 = Integer.parseInt(tokens[2]);
            UnionGrammar unionGrammar = new UnionGrammar();
            unionGrammar.union(id1, id2);
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
