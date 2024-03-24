package bg.tu_varna.sit.a2.f22621613.Grammar;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ContextFreeGrammar implements print  {
    private int uniqueId;
    private Set<Character> terminals;
    private Set<Character> nonTerminals;
    private Map<Character, List<String>> rules;

    public ContextFreeGrammar() {
        terminals = new HashSet<>();
        nonTerminals = new HashSet<>();
        rules = new HashMap<>();
    }

    public Set<Character> getTerminals() {
        return terminals;
    }

    public Set<Character> getNonTerminals() {
        return nonTerminals;
    }

    public Map<Character, List<String>> getRules() {
        return rules;
    }

    public List<String> getRules(char nonTerminal) {
        return rules.getOrDefault(nonTerminal, Collections.emptyList());
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void addTerminal(char terminal) {
        terminals.add(terminal);
    }

    public void addNonTerminal(char nonTerminal) {
        nonTerminals.add(nonTerminal);
    }

    public void addRule(char nonTerminal, String rule) {
        if (!rules.containsKey(nonTerminal)) {
            rules.put(nonTerminal, new ArrayList<>());
        }
        rules.get(nonTerminal).add(rule);
    }
    public int generateID(){
        Random random = new Random();
        int id =random.nextInt(100) + 1;
            this.uniqueId = id;
            return uniqueId;
    }
    public void setUniqueId(int id){
        this.uniqueId = id;
    }


    @Override
    public void printGrammar(int id){
        if (id != uniqueId) {
            System.out.println("Grammar with ID " + id + " not found.");
            return;
        }
        System.out.println(id);

        System.out.println("Terminals:");
        for (char terminal : terminals) {
            System.out.println(terminal);
        }

        System.out.println("Non-terminals:");
        for (char nonTerminal : nonTerminals) {
            System.out.println(nonTerminal);
        }

        System.out.println("Rules:");
        for (char nonTerminal : nonTerminals) {
            List<String> ruleList = rules.getOrDefault(nonTerminal, Collections.emptyList());
            for (String rule : ruleList) {
                System.out.println(nonTerminal + " -> " + rule);
            }
        }

    }
}
