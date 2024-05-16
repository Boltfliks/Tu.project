package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class NewGrammar implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar = new ContextFreeGrammar();
        grammar.setUniqueId(grammar.generateID());
        grammars.addGrammar(grammar);
    }
}
