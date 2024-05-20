package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The StarKleeneGrammar class is responsible for creating a new context-free grammar
 * that represents the Kleene star (iteration) of an original grammar.
 */
public class StarKleeneGrammar {
    /**
     * Creates a new grammar that is an iteration (Kleene star) of the original grammar with the given ID.
     *
     * @param id The unique identifier of the original grammar.
     * @return The new grammar representing the iteration (Kleene star) of the original grammar.
     */
    public ContextFreeGrammar iter(int id) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar originalGrammar = grammars.getGrammarById(id);
        ContextFreeGrammar resultGrammar = new ContextFreeGrammar();
        grammars.addGrammar(resultGrammar);

        int newId = resultGrammar.generateID();
        resultGrammar.setUniqueId(newId);

        resultGrammar.getTerminals().addAll(originalGrammar.getTerminals());
        resultGrammar.getNonTerminals().addAll(originalGrammar.getNonTerminals());

        Set<Character> nonTerminalsCopy = new HashSet<>(originalGrammar.getNonTerminals());
        for (char nonTerminal : nonTerminalsCopy) {
            List<String> originalRules = new ArrayList<>(originalGrammar.getRules(nonTerminal));
            for (String rule : originalRules) {
                int arrowIndex = rule.indexOf("->");
                if (arrowIndex != -1 && arrowIndex < rule.length() - 2) {
                    String production = rule.substring(arrowIndex + 2).trim();
                    resultGrammar.addRule(newId, production);
                }
            }

            for (String rule : originalRules) {
                char firstSymbol = rule.charAt(rule.indexOf("->") + 2);
                if (firstSymbol != 'e') {
                    String Rule1 = nonTerminal + "->" + firstSymbol + nonTerminal;
                    String Rule2 = nonTerminal + "->" + firstSymbol;

                    resultGrammar.addRule(newId, Rule1.trim());
                    resultGrammar.addRule(newId, Rule2.trim());
                }
            }

            resultGrammar.addRule(newId, nonTerminal + "->e");
        }
        return resultGrammar;
    }
}