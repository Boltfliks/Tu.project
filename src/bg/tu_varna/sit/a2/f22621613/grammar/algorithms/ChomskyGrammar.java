package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.*;

/**
 * This class represents a grammar in Chomsky Normal Form.
 */
public class ChomskyGrammar {
    private ContextFreeGrammar originalGrammar;

    /**
     * Constructor for ChomskyGrammar.
     *
     * @param grammar The context-free grammar.
     */
    public ChomskyGrammar(ContextFreeGrammar grammar) {
        this.originalGrammar = grammar;
    }

    /**
     * Returns the context-free grammar.
     *
     * @return The context-free grammar.
     */
    public ContextFreeGrammar getGrammar() {
        return originalGrammar;
    }

    /**
     * Checks if the grammar is in Chomsky Normal Form.
     *
     * @return True if the grammar is in Chomsky Normal Form, otherwise false.
     */
    public boolean chomsky() {
        Set<Rule> rules = originalGrammar.getRules();
        for (Rule rule : rules) {
            if (rule.getRight().length() < 2)
                continue;
            String left = rule.getLeft();
            String right = rule.getRight();
            boolean hasTerminal = false;

            if (!isNonTerminal(left)) {
                return false;
            }
            for (Character s : originalGrammar.getTerminals()) {
                if (right.contains(s.toString()))
                {
                    hasTerminal = true;
                    break;
                }
            }
            for (Character s : originalGrammar.getNonTerminals()) {
                if (right.contains(s.toString()) && hasTerminal)
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if a symbol is a non-terminal.
     *
     * @param symbol The symbol to check.
     * @return True if the symbol is a non-terminal, otherwise false.
     */
    private boolean isNonTerminal(String symbol) {
        return symbol.matches("[A-Z]");
    }

    /**
     * Converts a grammar to Chomsky Normal Form.
     *
     * @param id The identifier of the grammar.
     * @return A new context-free grammar in Chomsky Normal Form or null if the grammar is already in Chomsky Normal Form.
     */
    public ContextFreeGrammar chomskify(int id) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ChomskyGrammar grammar = new ChomskyGrammar(originalGrammar);
        if (grammar == null) {
            System.out.println("Grammar with ID " + id + " not found.");
            return null;
        }
        if (!grammar.chomsky()) {
            ContextFreeGrammar grammar1 = new ContextFreeGrammar();
            grammars.addGrammar(grammar1);
            Set<Rule> originalRules = grammar.getGrammar().getRules();
            Set<Rule> newRules = new HashSet<>();
            for (Rule originalRule : originalRules) {
                Rule newRule = new Rule(originalRule.getNumber(), originalRule.getLeft(), originalRule.getRight());
                newRules.add(newRule);
            }
            grammar1.setRules(newRules);
            grammar1.generateID();
            grammar1.setTerminals(new HashSet<>(grammar.getGrammar().getTerminals()));
            grammar1.setNonTerminals(new HashSet<>(grammar.getGrammar().getNonTerminals()));
            grammar1.setRuleCount(grammar.getGrammar().getRuleCount());
            convertLongProductions(grammar1);
            convertEpsilonProductions(grammar1);
            return grammar1;
        }
        return null;
    }

    /**
     * Converts long productions into Chomsky Normal Form.
     *
     * @param grammar The context-free grammar.
     */
    private void convertLongProductions(ContextFreeGrammar grammar) {
        Set<Rule> rules = new HashSet<>(grammar.getRules());
        for (Rule rule : rules) {
            String right = rule.getRight();
            if (containsTerminalsAndNonTerminals(right)) {
                StringBuilder newProduction = new StringBuilder();
                for (char c : right.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        String characterC = String.valueOf(c);
                        Set<Rule> nonTerminalRules = grammar.getRules();
                        for (Rule nonTerminalRule : nonTerminalRules) {
                            if (nonTerminalRule.getLeft().contains(characterC)) {
                                newProduction.append(nonTerminalRule.getRight());
                                break;
                            }
                        }
                    } else {
                        newProduction.append(c);
                    }
                }
                grammar.removeRule(grammar, rule.getNumber());
                grammar.addRule(grammar, new Rule(rule.getNumber(), rule.getLeft(), newProduction.toString()));
            }
        }
    }

    /**
     * Converts epsilon productions into Chomsky Normal Form.
     *
     * @param grammar The context-free grammar.
     */
    private void convertEpsilonProductions(ContextFreeGrammar grammar) {
        Set<Rule> rules = new HashSet<>(grammar.getRules());
        for (Rule rule : rules) {
            String right = rule.getRight();
            if (right.equals("e")) {
                grammar.removeRule(grammar, rule.getNumber());
                if (grammar.getTerminals().contains("e"))
                    grammar.getTerminals().remove("e");
            }
        }
    }

    /**
     * Checks if a string contains both terminals and non-terminals.
     *
     * @param input The string to check.
     * @return True if the string contains both terminals and non-terminals, otherwise false.
     */
    private boolean containsTerminalsAndNonTerminals(String input) {
        boolean containsTerminals = false;
        boolean containsNonTerminals = false;
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c) || c == 'e') {
                if (Character.isLowerCase(c) || Character.isDigit(c)) {
                    containsTerminals = true;
                } else if (Character.isUpperCase(c)) {
                    containsNonTerminals = true;
                }
            }
        }
        return containsTerminals && containsNonTerminals;
    }
}
