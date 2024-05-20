package bg.tu_varna.sit.a2.f22621613.grammar.algorithms;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.util.*;

/**
 * This class represents a grammar in Chomsky Normal Form.
 */
public class ChomskyGrammar {
    private ContextFreeGrammar grammar;

    /**
     * Constructor for ChomskyGrammar.
     *
     * @param grammar The context-free grammar.
     */
    public ChomskyGrammar(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }

    /**
     * Returns the context-free grammar.
     *
     * @return The context-free grammar.
     */
    public ContextFreeGrammar getGrammar() {
        return grammar;
    }

    /**
     * Checks if the grammar is in Chomsky Normal Form.
     *
     * @return True if the grammar is in Chomsky Normal Form, otherwise false.
     */
    public boolean chomsky() {
        for (char nonTerminal : grammar.getNonTerminals()) {
            if (grammar.getNonTerminals().contains(nonTerminal)) {
                List<String> rules = grammar.getRules(nonTerminal);
                for (String rule : rules) {
                    String production = rule.substring(rule.indexOf("->") + 2).trim();
                    int terminals = 0;
                    int nonTerminals = 0;
                    char firstSymbol = production.charAt(0);
                    for (char symbol : production.toCharArray()) {
                        if (grammar.getTerminals().contains(firstSymbol)) {
                            if (grammar.getTerminals().contains(symbol))
                                terminals++;
                            else
                                terminals--;
                        } else {
                            if (grammar.getNonTerminals().contains(symbol))
                                nonTerminals++;
                            else
                                nonTerminals--;
                        }
                    }
                    if (grammar.getTerminals().contains(firstSymbol)) {
                        if (terminals != production.length())
                            return false;
                    } else {
                        if (nonTerminals != production.length())
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Converts a grammar to Chomsky Normal Form.
     *
     * @param id The identifier of the grammar.
     * @return A new context-free grammar in Chomsky Normal Form or null if the grammar is already in Chomsky Normal Form.
     */
    public ContextFreeGrammar chomskify(int id) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ChomskyGrammar grammar = new ChomskyGrammar(grammars.getGrammarById(id));
        if (grammar == null) {
            System.out.println("Grammar with ID " + id + " not found.");
            return null;
        }
        if (!grammar.chomsky()) {
            ContextFreeGrammar grammar1 = new ContextFreeGrammar();
            grammars.addGrammar(grammar1);
            grammar1.setRules(grammar.getGrammar().getRules());
            grammar1.setUniqueId(grammar1.generateID());
            grammar1.setTerminals(grammar.getGrammar().getTerminals());
            grammar1.setNonTerminals(grammar.getGrammar().getNonTerminals());
            convertLongProductions(grammar1);
            convertEpsilonProductions(grammar1);
            return grammar1;
        }
        return null;
    }

    /**
     * Converts long productions in the grammar.
     *
     * @param grammar The context-free grammar.
     */
    private void convertLongProductions(ContextFreeGrammar grammar) {
        for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
            char nonTerminal = entry.getKey();
            List<String> rules = new ArrayList<>(entry.getValue());
            for (String rule : rules) {
                String[] parts = rule.split("\\. ");
                if (parts.length >= 2) {
                    String production = parts[1].substring(parts[1].indexOf("->") + 2).trim();
                    if (containsTerminalsAndNonTerminals(production)) {
                        StringBuilder newProduction = new StringBuilder();
                        for (char c : production.toCharArray()) {
                            if (grammar.getNonTerminals().contains(c)) {
                                List<String> nonTerminalRules = grammar.getRules(c);
                                if (!nonTerminalRules.isEmpty()) {
                                    String selectedRule = nonTerminalRules.get(new Random().nextInt(nonTerminalRules.size()));
                                    newProduction.append(selectedRule.substring(selectedRule.indexOf("->") + 2).trim());
                                }
                            } else {
                                newProduction.append(c);
                            }
                        }
                        grammar.removeRule(grammar.getUniqueId(), Integer.parseInt(parts[0]));
                        grammar.addRule(grammar.getUniqueId(), nonTerminal + "->" + newProduction);
                    }
                }
            }
        }
    }

    /**
     * Converts epsilon (ε) productions in the grammar.
     *
     * @param modifiedGrammar The modified context-free grammar.
     */
    private void convertEpsilonProductions(ContextFreeGrammar modifiedGrammar) {
        for (Map.Entry<Character, List<String>> entry : modifiedGrammar.getRules().entrySet()) {
            List<String> rules = new ArrayList<>(entry.getValue());
            for (String rule : rules) {
                String[] parts = rule.split("\\. ");
                if (parts.length == 2) {
                    String production = parts[1].substring(parts[1].indexOf("->") + 2).trim();
                    if (production.equals("e")) {
                        modifiedGrammar.removeRule(modifiedGrammar.getUniqueId(), Integer.parseInt(parts[0]));
                    }
                }
            }
        }
    }

    /**
     * Checks if a string contains both terminals and non-terminals.
     *
     * @param input The string to check.
     * @return True if the string contains both terminals and non-terminals.
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

