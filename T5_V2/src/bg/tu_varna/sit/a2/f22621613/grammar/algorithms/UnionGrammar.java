package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The UnionGrammar class provides a method to create a new context-free grammar
 * that is the union of two existing context-free grammars identified by their unique IDs.
 */
public class UnionGrammar {


    /**
     * Creates the union of two context-free grammars.
     *
     * @param grammar1 The first context-free grammar.
     * @param grammar2 The second context-free grammar.
     * @return A new context-free grammar that represents the union of the two grammars.
     */
    public ContextFreeGrammar union(ContextFreeGrammar grammar1, ContextFreeGrammar grammar2) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();

        ContextFreeGrammar unionGrammar = new ContextFreeGrammar();
        grammars.addGrammar(unionGrammar);

        unionGrammar.generateID();

        unionGrammar.getTerminals().addAll(grammar1.getTerminals());
        unionGrammar.getTerminals().retainAll(grammar2.getTerminals());

        Set<Character> unionNonTerminals = new HashSet<>(grammar1.getNonTerminals());
        unionNonTerminals.remove('S');
        unionGrammar.getNonTerminals().addAll(unionNonTerminals);
        unionGrammar.getNonTerminals().retainAll(grammar2.getNonTerminals());
        Set<Rule> commonRules = new HashSet<>();

        for (Rule rule1 : grammar1.getRules()) {
            for (Rule rule2 : grammar2.getRules()) {
                if (rule1.equals(rule2)) {
                    commonRules.add(rule1);
                }
            }
        }

        for (Rule rule : commonRules) {
            unionGrammar.addRule(unionGrammar, rule);
        }

        if (unionGrammar.getTerminals().size() == 1 && unionGrammar.getTerminals().contains('e')) {
            unionGrammar.getTerminals().clear();
        }

        if (unionGrammar.empty(unionGrammar)) {
            grammars.removeGrammar(unionGrammar);
        }
        unionGrammar.getNonTerminals().add('S');
        System.out.println(unionGrammar.getUniqueId());
        return unionGrammar;
    }

}
