package bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;

import java.util.*;

public class ChomskyGrammar {
    private ContextFreeGrammar grammar;

    public ChomskyGrammar(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }

    public ContextFreeGrammar getGrammar() {
        return grammar;
    }

    public boolean chomsky(){
        for (char nonTerminal : grammar.getNonTerminals()) {
            if (grammar.getNonTerminals().contains(nonTerminal)) {
                List<String> rules = grammar.getRules(nonTerminal);
                for(String rule : rules){
                    nonTerminal = rule.charAt(3);
                    String production =rule.substring(rule.indexOf("->") + 2).trim();
                    int terminals = 0;
                    int nonTerminals = 0;
                    char firstSymbol = production.charAt(0);
                    for (char symbol : production.toCharArray()) {
                      if(grammar.getTerminals().contains(firstSymbol)) {
                          if (grammar.getTerminals().contains(symbol))
                              terminals++;
                          else
                              terminals--;
                      }
                      else{
                          if(grammar.getNonTerminals().contains(symbol))
                              nonTerminals++;
                          else
                              nonTerminals--;
                      }

                    }
                    if(grammar.getTerminals().contains(firstSymbol)) {
                        if (terminals != production.length())
                            return false;
                    }
                    else{
                        if (nonTerminals != production.length())
                            return false;
                    }
                }
            }


        }


        return true;
    }
    public ContextFreeGrammar chomskify(int id) {
        // Ensure the grammar with the given ID exists
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ChomskyGrammar grammar = new ChomskyGrammar(grammars.getGrammarById(id));
        if (grammar == null) {
            System.out.println("Grammar with ID " + id + " not found.");
            return null;
        }
        if(!grammar.chomsky()) {

            ContextFreeGrammar grammar1 = new ContextFreeGrammar();
            grammars.addGrammar(grammar1);
            grammar1.setRules(grammar.getGrammar().getRules());
            grammar1.setUniqueId(grammar1.generateID());
            grammar1.setTerminals(grammar.getGrammar().getTerminals());
            grammar1.setNonTerminals(grammar.getGrammar().getNonTerminals());
            // Convert productions to Chomsky normal form
            convertLongProductions(grammar1);
            convertEpsilonProductions(grammar1);

            return grammar1;
        }
        return null;
    }

    private void convertLongProductions(ContextFreeGrammar grammar) {
        Set<String> processedRules = new HashSet<>(); // To keep track of processed rules
        for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
            char nonTerminal = entry.getKey();
            List<String> rules = new ArrayList<>(entry.getValue()); // Create a copy to avoid concurrent modification
            for (String rule : rules) {
                String[] parts = rule.split("\\. "); // Split the rule by ". "

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
                        // Replace the original rule with the modified production
                        grammar.removeRule(grammar.getUniqueId(), Integer.parseInt(parts[0]));
                        grammar.addRule(grammar.getUniqueId(), nonTerminal + "->" + newProduction);
                    }
                }
            }
        }
    }

    private void convertEpsilonProductions(ContextFreeGrammar modifiedGrammar) {
        // Iterate over the rules in the grammar
        for (Map.Entry<Character, List<String>> entry : modifiedGrammar.getRules().entrySet()) {
            char nonTerminal = entry.getKey();
            List<String> rules = new ArrayList<>(entry.getValue()); // Create a copy to avoid concurrent modification
            for (String rule : rules) {
                String[] parts = rule.split("\\. "); // Split the rule by ". "

                if (parts.length == 2) {
                    String production = parts[1].substring(parts[1].indexOf("->") + 2).trim();
                    if(production.equals("ε")){
                        modifiedGrammar.removeRule(modifiedGrammar.getUniqueId(), Integer.parseInt(parts[0]));
                    }
                }
            }
        }
    }

    private boolean containsTerminalsAndNonTerminals(String input) {
        boolean containsTerminals = false;
        boolean containsNonTerminals = false;

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c) || c == 'ε') {
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
