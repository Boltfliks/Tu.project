package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class RemoveRule implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        if (tokens.length < 3) {
            System.out.println("Filename missing.");
            return;
        }
        int id =  Integer.parseInt(tokens[1]);
        int rule =  Integer.parseInt(tokens[2]);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        grammars.getGrammarById(id).removeRule(id,rule);
    }
}
