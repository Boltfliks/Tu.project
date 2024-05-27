package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

/**
 * The NewGrammar class implements the Action interface to create a new context-free grammar.
 */
public class NewGrammar implements Action {
    /**
     * Executes the action to create a new context-free grammar.
     * This method generates a new ContextFreeGrammar, assigns it a unique ID, and adds it to the ListOfGrammars.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 1) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <filename>");
            }
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            ContextFreeGrammar grammar = new ContextFreeGrammar();
            grammar.setUniqueId(grammar.generateID());
            grammars.addGrammar(grammar);
            System.out.println(grammar.getUniqueId());
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }
}
