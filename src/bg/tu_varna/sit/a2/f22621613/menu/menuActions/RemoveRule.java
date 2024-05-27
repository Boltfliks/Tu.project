package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The RemoveRule class implements the Action interface to remove a specific rule from a grammar.
 */
public class RemoveRule implements Action {
    /**
     * Executes the action to remove a rule from the grammar with the specified ID.
     * This method retrieves the grammar by ID from the ListOfGrammars and removes the rule with the given rule number.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id> <number>");
            }
            int id = Integer.parseInt(tokens[1]);
            int rule = Integer.parseInt(tokens[2]);
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            grammars.getGrammarById(id).removeRule(grammars.getGrammarById(id), rule);
        } catch (NumberFormatException e) {
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
