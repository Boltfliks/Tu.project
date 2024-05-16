package bg.tu_varna.sit.a2.f22621613.Grammar;

import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

import java.io.*;
import java.util.*;

public class ContextFreeGrammar implements print  {
    private int uniqueId;
    private Set<Character> terminals;
    private Set<Character> nonTerminals;
    private Map<Character, List<String>> rules;
    private int rulesCount;

    public ContextFreeGrammar() {
        terminals = new HashSet<>();
        nonTerminals = new HashSet<>();
        rules = new LinkedHashMap<>();
        rulesCount = 0;
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

    public void setTerminals(Set<Character> terminals) {
        this.terminals = terminals;
    }

    public void setNonTerminals(Set<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public void setRulesCount(int rulesCount) {
        this.rulesCount = rulesCount;
    }

    public void setRules(Map<Character, List<String>> rules) {
        this.rules = rules;
    }

    public void addTerminal(char terminal) {
        if(!terminals.contains(terminal))
        terminals.add(terminal);
    }

    public void addNonTerminal(char nonTerminal) {
        if(!nonTerminals.contains(nonTerminal))
        nonTerminals.add(nonTerminal);
    }

    public void addRule(int id, String rule) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        for (ContextFreeGrammar grammar : grammars.getGrammars()) {
            if (grammar.getUniqueId() == id) {
                int arrowIndex = rule.indexOf("->");
                if (arrowIndex != -1 && arrowIndex < rule.length() - 2) {
                    char nonTerminal = rule.charAt(0);
                    String production = rule.substring(arrowIndex + 2).trim();

                    // Add the non-terminal to the grammar if it's not already present
                    if (!grammar.getNonTerminals().contains(nonTerminal)) {
                        grammar.addNonTerminal(nonTerminal);
                    }

                    // Add terminals from the production to the grammar
                    for (char symbol : production.toCharArray()) {
                        if ((symbol >= '0' && symbol <= '9') || (symbol >= 'a' && symbol <= 'z') || symbol == 'ε') {
                            grammar.addTerminal(symbol);
                        } else {
                            grammar.addNonTerminal(symbol);
                        }
                    }

                    // Add the rule with original numbering
                    List<String> nonTerminalRules = grammar.getRules().get(nonTerminal);
                    if (nonTerminalRules == null) {
                        nonTerminalRules = new ArrayList<>();
                        grammar.getRules().put(nonTerminal, nonTerminalRules);
                    }
                    nonTerminalRules.add(rulesCount + ". " + rule);

                    // Remove the numbering from existing rules and renumber them
                    Map<Character, List<String>> numberedRules = new LinkedHashMap<>();
                    int ruleNumber = 1;
                    for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
                        char nt = entry.getKey();
                        List<String> rules = entry.getValue();
                        List<String> updatedRules = new ArrayList<>();
                        for (String rul : rules) {
                            updatedRules.add(ruleNumber + ". " + rul.substring(3).trim()); // Remove numbering
                            ruleNumber++;
                        }
                        numberedRules.put(nt, updatedRules);
                    }

                    // Update the rules map and rules count
                    grammar.setRules(numberedRules);
                    grammar.setRulesCount(ruleNumber);
                }
            }
        }
    }


    public void removeRule(int id, int n) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        for (ContextFreeGrammar grammar : grammars.getGrammars()) {
            if (grammar.getUniqueId() == id) {
                List<String> allRules = new ArrayList<>();
                for (List<String> rules : grammar.getRules().values()) {
                    allRules.addAll(rules);
                }

                if (n <= allRules.size()) {
                    // Remove the rule
                    String removedRule = allRules.remove(n - 1);

                    // Update the remaining rules and renumber them
                    int index = 1;
                    Map<Character, List<String>> renumberedRules = new LinkedHashMap<>();
                    for (String rule : allRules) {
                        char nonTerminal = rule.charAt(2);
                        if (!renumberedRules.containsKey(nonTerminal)) {
                            renumberedRules.put(nonTerminal, new ArrayList<>());
                        }
                        renumberedRules.get(nonTerminal).add(index + ". " + rule.substring(3));
                        index++;
                    }
                    grammar.setRules(renumberedRules);

                    // Track used non-terminals before removing the rule
                    Set<Character> usedNonTerminals = new HashSet<>();
                    for (String rule : allRules) {
                        usedNonTerminals.add(rule.charAt(3));
                    }



                    Set<Character> usedTerminals = new HashSet<>();
                    for (String rule : allRules) {
                        usedNonTerminals.add(rule.charAt(3));
                        for (char c : rule.substring(3).toCharArray()) {
                            if (Character.isLetterOrDigit(c) || c == 'ε') {
                                usedTerminals.add(c);
                            }
                        }
                    }

                    // Remove non-terminals not present in used set
                    grammar.getNonTerminals().retainAll(usedNonTerminals);

                    grammar.getTerminals().retainAll(usedTerminals);

                    // Remove the non-terminal of the removed rule if not used in other rules
                    char removedNonTerminal = removedRule.charAt(3);

                    if (!usedNonTerminals.contains(removedNonTerminal)) {
                        grammar.getNonTerminals().remove(removedNonTerminal);
                    }

                    // Exit loop after processing the rule
                    return;
                }
            }
        }
    }

    public boolean empty(int id) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar = grammars.getGrammarById(id);
        if (grammar == null) {
            return false;
        }

        return grammar.getRules().isEmpty() && grammar.getTerminals().isEmpty() && grammar.getNonTerminals().isEmpty();
    }

    public int generateID(){
        Random random = new Random();
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        int id =random.nextInt(100) + 1;
        while(true) {
            if(grammars.getGrammarById(id) == null) {
                this.uniqueId = id;
                break;
            }
            else
                id = random.nextInt(100)+1;
        }
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
        for (Map.Entry<Character, List<String>> entry : rules.entrySet()) {
            char nonTerminal = entry.getKey();
            List<String> ruleList = entry.getValue();
            for (String rule : ruleList) {
                System.out.println(rule);
            }
        }

    }

    public ContextFreeGrammar iter(int id) {
        // Create a new grammar for the result
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar originalGrammar = grammars.getGrammarById(id);
        ContextFreeGrammar resultGrammar = new ContextFreeGrammar();
        grammars.addGrammar(resultGrammar);

        // Generate a unique ID for the new grammar
        int newId = resultGrammar.generateID();
        resultGrammar.setUniqueId(newId);

        // Copy the terminals and non-terminals from the original grammar
        resultGrammar.getTerminals().addAll(originalGrammar.getTerminals());
        resultGrammar.getNonTerminals().addAll(originalGrammar.getNonTerminals());

        // Create a copy of the non-terminals set to avoid concurrent modification
        Set<Character> nonTerminalsCopy = new HashSet<>(originalGrammar.getNonTerminals());
        for (char nonTerminal : nonTerminalsCopy) {
            // Add the original rules to the result grammar
            List<String> originalRules = new ArrayList<>(originalGrammar.getRules(nonTerminal));
            // Add the original rules to the result grammar
            for (String rule : originalRules) {
                int arrowIndex = rule.indexOf("->");
                if (arrowIndex != -1 && arrowIndex < rule.length() - 2) {
                    String production = rule.substring(arrowIndex + 2).trim();
                    resultGrammar.addRule(newId, production);
                }
            }


            // Add rules for the Kleene star operation
            for (String rule : originalRules) {
                // Extract the symbol after the arrow symbol '->'
                char firstSymbol = rule.charAt(rule.indexOf("->") + 2);
                //System.out.println(firstSymbol);
                // Generate new rules where the original production is repeated one or more times
                if (firstSymbol != 'ε') {
                String Rule1 = nonTerminal + "->" + firstSymbol + nonTerminal;
                String Rule2 = nonTerminal + "->" + firstSymbol;

                resultGrammar.addRule(newId, Rule1.trim());
                resultGrammar.addRule(newId, Rule2.trim());
                }
            }

            // Add a rule to accept epsilon for the current non-terminal
            resultGrammar.addRule(newId, nonTerminal + "->ε");
        }

        // Add a new rule for the original start symbol to accept epsilon
         // Assuming 'S' is the start symbol

        return resultGrammar;
    }

    public void save(int id, String filename) {
        try {
            File file = new File( "black.xml");
            boolean isNewFile = !file.exists();

            FileWriter writer = new FileWriter(file, true); // Append mode
            if (isNewFile) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<List>\n");
            } else {
                // Move the cursor back before the </List> tag
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                long length = raf.length();
                raf.setLength(length - "</List>\n".length());
                raf.close();
            }

            // Write the grammar details
            writer.write("  <ContextFreeGrammar>\n");
            writer.write("    <Terminals>\n");
            for (char terminal : terminals) {
                writer.write("      <Terminal>" + terminal + "</Terminal>\n");
            }
            writer.write("    </Terminals>\n");
            writer.write("    <NonTerminals>\n");
            for (char nonTerminal : nonTerminals) {
                writer.write("      <NonTerminal>" + nonTerminal + "</NonTerminal>\n");
            }
            writer.write("    </NonTerminals>\n");
            writer.write("    <Rules>\n");
            for (Map.Entry<Character, List<String>> entry : rules.entrySet()) {
                char nonTerminal = entry.getKey();
                List<String> ruleList = entry.getValue();
                for (String rule : ruleList) {
                    writer.write("      <Rule>" + rule + "</Rule>\n");
                }
            }
            writer.write("    </Rules>\n");
            writer.write("  </ContextFreeGrammar>\n");

            // Close the </List> tag
            writer.write("</List>\n");

            writer.close(); // Close the writer
            System.out.println("Grammar with ID " + id + " appended to " + filename);
        } catch (IOException e) {
            System.out.println("Error occurred while appending the grammar to " + filename);
            e.printStackTrace();
        }
    }





















}
