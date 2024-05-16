package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.CYKAlgorithm;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class CYK implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        int id = Integer.parseInt(tokens[1]);
        String check = tokens[2];
        CYKAlgorithm grammar = new CYKAlgorithm(grammars.getGrammarById(id));
        System.out.println(grammar.parse(check));
    }
}
