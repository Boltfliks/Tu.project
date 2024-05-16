package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class Concat implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        int id1 = Integer.parseInt(tokens[1]);
        int id2 = Integer.parseInt(tokens[2]);
        ContextFreeGrammar contextFreeGrammar = grammars.concat(id1,id2);
        System.out.println("Id " + contextFreeGrammar.getUniqueId());
    }
}
