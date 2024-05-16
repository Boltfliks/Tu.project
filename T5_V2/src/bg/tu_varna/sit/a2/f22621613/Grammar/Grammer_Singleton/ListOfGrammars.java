package bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;

import java.util.*;

public class ListOfGrammars implements AddRemoveGrammar,ShowIds {
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

    @Override
    public void removeGrammar(ContextFreeGrammar contextFreeGrammar) {
        grammars.remove(contextFreeGrammar);
    }

    public void displayGrammars() {
       for (ContextFreeGrammar grammar : grammars) {
        grammar.printGrammar(grammar.getUniqueId());

        }
    }



    @Override
    public String list() {
        StringBuilder sb = new StringBuilder();
        for(ContextFreeGrammar c: grammars){
            sb.append(c.getUniqueId()).append(" ");
        }
        return sb.toString();
    }

    public ContextFreeGrammar getGrammarById(int id) {
        for (ContextFreeGrammar grammar : grammars) {
            if(grammar == null)
                return null;
            if (grammar.getUniqueId() == id) {
                return grammar;
            }
        }
        return null;
    }

    public ContextFreeGrammar union(int id1, int id2) {
        ContextFreeGrammar grammar1 = getGrammarById(id1);
        ContextFreeGrammar grammar2 = getGrammarById(id2);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        // Create a new grammar for the union
        ContextFreeGrammar unionGrammar = new ContextFreeGrammar();
        grammars.addGrammar(unionGrammar);
        int newId = unionGrammar.generateID(); // Assuming you have a method to generate unique IDs
        unionGrammar.setUniqueId(newId);

        // Retain only terminals common to both grammars
        unionGrammar.getTerminals().addAll(grammar1.getTerminals());
        unionGrammar.getTerminals().retainAll(grammar2.getTerminals());

        // Retain only non-terminals common to both grammars
        Set<Character> unionNonTerminals = new HashSet<>(grammar1.getNonTerminals());
        unionNonTerminals.remove('S');
        unionGrammar.getNonTerminals().addAll(unionNonTerminals);
        unionGrammar.getNonTerminals().retainAll(grammar2.getNonTerminals());
        List<String> commonRules = new ArrayList<>();
        // Combine rules
        for (char nonTerminal : grammar1.getNonTerminals()) {
            if (grammar2.getNonTerminals().contains(nonTerminal)) {
                List<String> rules1 = grammar1.getRules(nonTerminal);
                List<String> rules2 = grammar2.getRules(nonTerminal);


                // Find common rules from grammar1
                for (String rule1 : rules1) {
                    // Extract the rule part after "->"
                    String ruleWithoutNumbering1 = rule1.substring((rule1.indexOf(".")+1)).trim();

                    // Check if rule1 exists in rules2 (without numbering)
                    for (String rule2 : rules2) {
                        String ruleWithoutNumbering2 = rule2.substring((rule2.indexOf(".")+1)).trim();
                        if (ruleWithoutNumbering1.equals(ruleWithoutNumbering2)) {
                            commonRules.add(ruleWithoutNumbering1); // Add rule1 (with numbering) to the commonRules list
                            break; // Break the inner
                        }
                    }
                }
            }
        }
        for (String rule : commonRules) {
            unionGrammar.addRule(unionGrammar.getUniqueId(), rule);
        }
        if(unionGrammar.getTerminals().size() == 1)
            if(unionGrammar.getTerminals().contains("ε"))
                unionGrammar.getTerminals().clear();
        if(unionGrammar.empty(unionGrammar.getUniqueId()))
            grammars.removeGrammar(unionGrammar);

        return unionGrammar;
    }
    public ContextFreeGrammar concat(int id1, int id2) {
        ContextFreeGrammar grammar1 = getGrammarById(id1);
        ContextFreeGrammar grammar2 = getGrammarById(id2);
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        if(grammar1 == null || grammar2 == null)
            return null;

        // Create a new grammar for the concatenation
        ContextFreeGrammar concatGrammar = new ContextFreeGrammar();
        int newId = concatGrammar.generateID(); // Assuming you have a method to generate unique IDs
        concatGrammar.setUniqueId(newId);

        // Retain all terminals from both grammars
        concatGrammar.getTerminals().addAll(grammar1.getTerminals());
        concatGrammar.getTerminals().addAll(grammar2.getTerminals());

        // Retain all non-terminals from both grammars
        concatGrammar.getNonTerminals().addAll(grammar1.getNonTerminals());
        concatGrammar.getNonTerminals().addAll(grammar2.getNonTerminals());

        // Combine rules from both grammars
        Map<Character, List<String>> concatRules = concatGrammar.getRules();
        int ruleNumber = 1;

// Combine rules from grammar1
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

// Combine rules from grammar2
        for (char nonTerminal : grammar2.getNonTerminals()) {
            List<String> rules2 = grammar2.getRules(nonTerminal);
            if (!concatRules.containsKey(nonTerminal)) {
                concatRules.put(nonTerminal, new ArrayList<>());

            }
            for (String rule : rules2) {
                concatRules.get(nonTerminal).add(ruleNumber + ". " + rule.substring(3));
                ruleNumber++;
            }
        }
        concatGrammar.setRulesCount(ruleNumber);

        if(!concatGrammar.empty(concatGrammar.getUniqueId()))
            grammars.addGrammar(concatGrammar);

        return concatGrammar;
    }




}
