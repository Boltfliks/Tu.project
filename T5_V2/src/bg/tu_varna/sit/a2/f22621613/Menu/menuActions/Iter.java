package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class Iter implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        int id = Integer.parseInt(tokens[1]);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar = grammars.getGrammarById(id);
        grammar.iter(grammar.getUniqueId());
    }
}
