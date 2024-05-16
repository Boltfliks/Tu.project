package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.Menu.Menu;

public class List implements Action {
    @Override
    public void action(MenuMap menuMap,String[] tokens) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        System.out.println(grammars.list());
    }
}