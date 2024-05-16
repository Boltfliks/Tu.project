package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class Save implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        if(tokens.length < 2){
            System.out.println("Too long");
        }
        int id = Integer.parseInt(tokens[1]);
        String fileName = tokens[2];
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar = grammars.getGrammarById(id);
        grammar.save(grammar.getUniqueId(), fileName);
    }
}
