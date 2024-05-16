package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

public class Union implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        if(tokens.length < 3){
            System.out.println("Too");
        }
        int id1 = Integer.parseInt(tokens[1]);
        int id2 = Integer.parseInt(tokens[2]);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        grammars.union(id1,id2);
    }
}
