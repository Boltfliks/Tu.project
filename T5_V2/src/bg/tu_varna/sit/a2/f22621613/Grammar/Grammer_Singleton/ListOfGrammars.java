package bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;

import java.util.ArrayList;
import java.util.List;

public class ListOfGrammars implements addGrammar {
    private static ListOfGrammars GrammarListInstance;

    private List<ContextFreeGrammar> grammars = new ArrayList<>();

    private ListOfGrammars() {}

    public static ListOfGrammars getGrammarListInstanceInstance() {
        if(GrammarListInstance == null){
            GrammarListInstance = new ListOfGrammars();
        }
        return GrammarListInstance;
    }

    public List<ContextFreeGrammar> getGrammars() {
        return grammars;
    }



    @Override
    public void addGrammar(ContextFreeGrammar contextFreeGrammar) {
        grammars.add(contextFreeGrammar);
    }

    public void displayGrammars() {
       for (ContextFreeGrammar grammar : grammars) {
        grammar.printGrammar(grammar.getUniqueId());

        }
    }

    public void displayFirstGrammar() {
        if (!grammars.isEmpty()) {
            ContextFreeGrammar firstGrammar = grammars.get(0);
            System.out.println("First Grammar:");
            System.out.println("Unique ID: " + firstGrammar.getUniqueId());
            System.out.println("Terminals: " + firstGrammar.getTerminals());
            System.out.println("Non-terminals: " + firstGrammar.getNonTerminals());
            System.out.println("Rules:");
            for (char nonTerminal : firstGrammar.getNonTerminals()) {
                List<String> rules = firstGrammar.getRules(nonTerminal);
                for (String rule : rules) {
                    System.out.println(nonTerminal + " -> " + rule);
                }
            }
        } else {
            System.out.println("No grammars available.");
        }
    }
}
