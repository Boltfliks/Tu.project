package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * This class provides functionality to concatenate two context-free grammars.
 */
public class ConcatGrammar {

    /**
     * Concatenates two context-free grammars identified by their unique IDs.
     *
     * @param id1 The unique ID of the first grammar.
     * @param id2 The unique ID of the second grammar.
     * @return A new ContextFreeGrammar object representing the concatenated grammar,
     * or null if either of the grammars with the given IDs is not found.
     */
    public ContextFreeGrammar concat(int id1, int id2) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar1 = grammars.getGrammarById(id1);
        ContextFreeGrammar grammar2 = grammars.getGrammarById(id2);
        if (grammar1 == null || grammar2 == null)
            return null;

        ContextFreeGrammar concatGrammar = new ContextFreeGrammar();
        grammars.addGrammar(concatGrammar);
        concatGrammar.generateID();

        concatGrammar.getTerminals().addAll(grammar1.getTerminals());
        concatGrammar.getTerminals().addAll(grammar2.getTerminals());

        concatGrammar.getNonTerminals().addAll(grammar1.getNonTerminals());
        concatGrammar.getNonTerminals().addAll(grammar2.getNonTerminals());

        Map<Character, List<String>> concatRules = concatGrammar.getRules();
        int ruleNumber = 1;

        for (char nonTerminal : grammar1.getNonTerminals()) {
            List<String> rules1 = grammar1.getRules(nonTerminal);
            if (!concatRules.containsKey(nonTerminal)) {
                concatRules.put(nonTerminal, new ArrayList<>());
            }
            for (String rule : rules1) {
                concatRules.get(nonTerminal).add(ruleNumber + ". " + rule.substring(3));
                ruleNumber++;
            }
        }

        for (char nonTerminal : grammar2.getNonTerminals()) {
            List<String> rules2 = grammar2.getRules(nonTerminal);
            if (!concatRules.containsKey(nonTerminal)) {
                concatRules.put(nonTerminal, new ArrayList<>());

            }
            for (String rule : rules2) {
                concatRules.get(nonTerminal).add(ruleNumber + ". " + rule.substring(3));
                ruleNumber++;
            }
            Map<Character, List<String>> numberedRules = new LinkedHashMap<>();

            ruleNumber = 1;
            for (Map.Entry<Character, List<String>> entry : concatGrammar.getRules().entrySet()) {
                char nt = entry.getKey();
                List<String> rules = entry.getValue();
                List<String> updatedRules = new ArrayList<>();
                for (String rul : rules) {
                    updatedRules.add(ruleNumber + ". " + rul.substring(3).trim());
                    ruleNumber++;
                }
                numberedRules.put(nt, updatedRules);
            }
            concatGrammar.setRules(numberedRules);

            if (concatGrammar.empty(concatGrammar.getUniqueId()))
                grammars.removeGrammar(concatGrammar);

        }
        return concatGrammar;
    }
}
