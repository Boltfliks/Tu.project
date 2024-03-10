package bg.tu_varna.sit.a2.f22621613.Grammar;

import java.util.ArrayList;
import java.util.List;

public class ListOfGrammars {
    private static ListOfGrammars GrammarListInstance;

    private List<ContextFreeGrammar> grammars = new ArrayList<>();

    private ListOfGrammars() {}

    public static ListOfGrammars getBankInstance() {
        if(GrammarListInstance == null){
            GrammarListInstance = new ListOfGrammars();
        }
        return GrammarListInstance;
    }
}
