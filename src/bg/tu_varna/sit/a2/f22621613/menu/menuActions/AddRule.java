package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The AddRule class implements the Action interface to add a rule to a specific grammar.
 */
public class AddRule implements Action {

    /**
     * Executes the action of adding a rule to a grammar based on the provided tokens.
     *
     * @param menuMap The MenuMap instance used to manage the action.
     * @param tokens  The tokens representing the command, grammar ID, and rule.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 3) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <id> <rule>");
            }

            int id = Integer.parseInt(tokens[1]);
            String input = tokens[2];

            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            Rule rule = new Rule(0,input.substring(input.indexOf('.')+1,input.indexOf("->")).trim(),input.substring(input.indexOf("->")+2).trim());
            grammars.getGrammarById(id).addRule(grammars.getGrammarById(id), rule);
            System.out.println("Rule added");
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
