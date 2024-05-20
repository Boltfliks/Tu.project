package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
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
     * Creates a new context-free grammar that is the union of two existing grammars.
     * The union grammar contains common non-terminals and rules from both grammars.
     *
     * @param id1 The unique ID of the first grammar.
     * @param id2 The unique ID of the second grammar.
     * @return The new context-free grammar representing the union of the two grammars.
     */
    public ContextFreeGrammar union(int id1, int id2) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar1 = grammars.getGrammarById(id1);
        ContextFreeGrammar grammar2 = grammars.getGrammarById(id2);

        ContextFreeGrammar unionGrammar = new ContextFreeGrammar();
        grammars.addGrammar(unionGrammar);
        int newId = unionGrammar.generateID();
        unionGrammar.setUniqueId(newId);

        unionGrammar.getTerminals().addAll(grammar1.getTerminals());
        unionGrammar.getTerminals().retainAll(grammar2.getTerminals());

        Set<Character> unionNonTerminals = new HashSet<>(grammar1.getNonTerminals());
        unionNonTerminals.remove('S');
        unionGrammar.getNonTerminals().addAll(unionNonTerminals);
        unionGrammar.getNonTerminals().retainAll(grammar2.getNonTerminals());
        List<String> commonRules = new ArrayList<>();

        for (char nonTerminal : grammar1.getNonTerminals()) {
            if (grammar2.getNonTerminals().contains(nonTerminal)) {
                List<String> rules1 = grammar1.getRules(nonTerminal);
                List<String> rules2 = grammar2.getRules(nonTerminal);

                for (String rule1 : rules1) {
                    String ruleWithoutNumbering1 = rule1.substring((rule1.indexOf(".") + 1)).trim();

                    for (String rule2 : rules2) {
                        String ruleWithoutNumbering2 = rule2.substring((rule2.indexOf(".") + 1)).trim();
                        if (ruleWithoutNumbering1.equals(ruleWithoutNumbering2)) {
                            commonRules.add(ruleWithoutNumbering1);
                            break;
                        }
                    }
                }
            }
        }

        for (String rule : commonRules) {
            unionGrammar.addRule(unionGrammar.getUniqueId(), rule);
        }

        if (unionGrammar.getTerminals().size() == 1 && unionGrammar.getTerminals().contains("e")) {
            unionGrammar.getTerminals().clear();
        }

        if (unionGrammar.empty(unionGrammar.getUniqueId())) {
            grammars.removeGrammar(unionGrammar);
        }

        return unionGrammar;
    }
}
