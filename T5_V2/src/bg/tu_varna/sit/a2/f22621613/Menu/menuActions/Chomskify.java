package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ChomskyGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class Chomskify implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        if(tokens.length < 2){
            System.out.println("Too long");
        }
        int id = Integer.parseInt(tokens[1]);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ChomskyGrammar grammar = new ChomskyGrammar(grammars.getGrammarById(id));
        ContextFreeGrammar contextFreeGrammar = grammar.chomskify(id);
        System.out.println("Id: " + contextFreeGrammar.getUniqueId());

    }
}
