package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.*;

/**
 * This class provides functionality to concatenate two context-free grammars.
 */
public class ConcatGrammar {


    /**
     * Concatenates two context-free grammars.
     *
     * @param grammar1 The first context-free grammar.
     * @param grammar2 The second context-free grammar.
     * @return A new context-free grammar that is the concatenation of grammar1 and grammar2, or null if either grammar1 or grammar2 is null.
     */
    public ContextFreeGrammar concat(ContextFreeGrammar grammar1, ContextFreeGrammar grammar2) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        if (grammar1 == null || grammar2 == null) {
            return null;
        }

        ContextFreeGrammar concatGrammar = new ContextFreeGrammar();
        grammars.addGrammar(concatGrammar);
        concatGrammar.generateID();

        concatGrammar.getTerminals().addAll(grammar1.getTerminals());
        concatGrammar.getTerminals().addAll(grammar2.getTerminals());

        concatGrammar.getNonTerminals().addAll(grammar1.getNonTerminals());
        concatGrammar.getNonTerminals().addAll(grammar2.getNonTerminals());

        Set<Rule> concatRules = concatGrammar.getRules();
        concatRules.addAll(grammar1.getRules());
        concatRules.addAll(grammar2.getRules());
        concatGrammar.setRules(concatRules);
        int ruleNumber = 1;

        for(Rule rule: concatRules){
            rule.setNumber(ruleNumber);
            ruleNumber++;
        }
        System.out.println(concatGrammar.getUniqueId());
        return concatGrammar;
    }

}
