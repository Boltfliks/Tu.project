package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;
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
    private ContextFreeGrammar grammar;

    public StarKleeneGrammar(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }

    public ContextFreeGrammar getGrammar() {
        return grammar;
    }

    /**
     * Iterates (applies the Kleene star operation to) a context-free grammar.
     *
     * @param originalGrammar The original context-free grammar.
     * @return A new context-free grammar that represents the iteration (Kleene star) of the original grammar.
     */
    public ContextFreeGrammar iter(ContextFreeGrammar originalGrammar) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar resultGrammar = new ContextFreeGrammar();
        grammars.addGrammar(resultGrammar);

        resultGrammar.generateID();

        resultGrammar.getTerminals().addAll(originalGrammar.getTerminals());
        resultGrammar.getNonTerminals().addAll(originalGrammar.getNonTerminals());

        Set<Rule> rules = originalGrammar.getRules();
        for (Rule rule : rules) {
            if (!rule.getRight().equals("e")) {
                Rule one = new Rule(0, rule.getLeft(), rule.getRight().concat(rule.getLeft()));
                Rule two = new Rule(0, rule.getLeft(), "e");
                resultGrammar.addRule(resultGrammar, one);
                resultGrammar.addRule(resultGrammar, two);
            }
        }
        System.out.println(resultGrammar.getUniqueId());
        return resultGrammar;
    }
}