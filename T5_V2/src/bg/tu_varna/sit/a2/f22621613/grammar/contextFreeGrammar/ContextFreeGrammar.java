package bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar;

import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.io.*;
import java.util.*;

/**
 * Represents a context-free grammar with a unique identifier, terminals, non-terminals, and production rules.
 */
public class ContextFreeGrammar implements Print {
    private int uniqueId;
    private Set<Character> terminals;
    private Set<Character> nonTerminals;
    private Map<Character, List<String>> rules;

    /**
     * Constructs a new ContextFreeGrammar with empty sets of terminals, non-terminals, and rules.
     */
    public ContextFreeGrammar() {
        terminals = new HashSet<>();
        nonTerminals = new HashSet<>();
        rules = new LinkedHashMap<>();
    }

    /**
     * Returns the set of terminal symbols in the grammar.
     *
     * @return The set of terminal symbols.
     */
    public Set<Character> getTerminals() {
        return terminals;
    }

    /**
     * Returns the set of non-terminal symbols in the grammar.
     *
     * @return The set of non-terminal symbols.
     */
    public Set<Character> getNonTerminals() {
        return nonTerminals;
    }

    /**
     * Returns the map of production rules in the grammar.
     *
     * @return The map of production rules.
     */
    public Map<Character, List<String>> getRules() {
        return rules;
    }

    /**
     * Returns the list of production rules for a given non-terminal symbol.
     *
     * @param nonTerminal The non-terminal symbol.
     * @return The list of production rules for the non-terminal symbol.
     */
    public List<String> getRules(char nonTerminal) {
        return rules.getOrDefault(nonTerminal, Collections.emptyList());
    }

    /**
     * Returns the unique identifier of the grammar.
     *
     * @return The unique identifier.
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets the set of terminal symbols in the grammar.
     *
     * @param terminals The set of terminal symbols.
     */
    public void setTerminals(Set<Character> terminals) {
        this.terminals = terminals;
    }

    /**
     * Sets the set of non-terminal symbols in the grammar.
     *
     * @param nonTerminals The set of non-terminal symbols.
     */
    public void setNonTerminals(Set<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    /**
     * Sets the map of production rules in the grammar.
     *
     * @param rules The map of production rules.
     */
    public void setRules(Map<Character, List<String>> rules) {
        this.rules = rules;
    }

    /**
     * Adds a terminal symbol to the grammar.
     *
     * @param terminal The terminal symbol to add.
     */
    public void addTerminal(char terminal) {
        if (!terminals.contains(terminal)) {
            terminals.add(terminal);
        }
    }

    /**
     * Adds a non-terminal symbol to the grammar.
     *
     * @param nonTerminal The non-terminal symbol to add.
     */
    public void addNonTerminal(char nonTerminal) {
        if (!nonTerminals.contains(nonTerminal)) {
            nonTerminals.add(nonTerminal);
        }
    }

    /**
     * Adds a production rule to the grammar.
     *
     * @param id The unique identifier of the grammar.
     * @param rule The production rule to add.
     */
    public void addRule(int id, String rule) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        for (ContextFreeGrammar grammar : grammars.getGrammars()) {
            if (grammar.getUniqueId() == id) {
                int arrowIndex = rule.indexOf("->");
                if (arrowIndex != -1 && arrowIndex < rule.length() - 2) {
                    char nonTerminal = rule.charAt(0);
                    String production = rule.substring(arrowIndex + 2).trim();

                    if (!grammar.getNonTerminals().contains(nonTerminal)) {
                        grammar.addNonTerminal(nonTerminal);
                    }

                    for (char symbol : production.toCharArray()) {
                        if ((symbol >= '0' && symbol <= '9') || (symbol >= 'a' && symbol <= 'z') || symbol == 'e') {
                            grammar.addTerminal(symbol);
                        } else {
                            grammar.addNonTerminal(symbol);
                        }
                    }

                    List<String> nonTerminalRules = grammar.getRules().get(nonTerminal);
                    if (nonTerminalRules == null) {
                        nonTerminalRules = new ArrayList<>();
                        grammar.getRules().put(nonTerminal, nonTerminalRules);
                    }
                    nonTerminalRules.add("0. " + rule);

                    Map<Character, List<String>> numberedRules = new LinkedHashMap<>();
                    int ruleNumber = 1;
                    for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
                        char nt = entry.getKey();
                        List<String> rules = entry.getValue();
                        List<String> updatedRules = new ArrayList<>();
                        for (String rul : rules) {
                            updatedRules.add(ruleNumber + ". " + rul.substring(3).trim());
                            ruleNumber++;
                        }
                        numberedRules.put(nt, updatedRules);
                    }
                    grammar.setRules(numberedRules);
                }
            }
        }
    }

    /**
     * Removes a production rule from the grammar.
     *
     * @param id The unique identifier of the grammar.
     * @param n The position of the rule to remove (1-based index).
     */
    public void removeRule(int id, int n) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        for (ContextFreeGrammar grammar : grammars.getGrammars()) {
            if (grammar.getUniqueId() == id) {
                List<String> allRules = new ArrayList<>();
                for (List<String> rules : grammar.getRules().values()) {
                    allRules.addAll(rules);
                }

                if (n <= allRules.size()) {
                    String removedRule = allRules.remove(n - 1);

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

                    Set<Character> usedNonTerminals = new HashSet<>();
                    for (String rule : allRules) {
                        usedNonTerminals.add(rule.charAt(3));
                    }

                    Set<Character> usedTerminals = new HashSet<>();
                    for (String rule : allRules) {
                        usedNonTerminals.add(rule.charAt(3));
                        for (char c : rule.substring(3).toCharArray()) {
                            if (Character.isLetterOrDigit(c) || c == 'e') {
                                usedTerminals.add(c);
                            }
                        }
                    }

                    grammar.getNonTerminals().retainAll(usedNonTerminals);
                    grammar.getTerminals().retainAll(usedTerminals);

                    char removedNonTerminal = removedRule.charAt(3);
                    if (!usedNonTerminals.contains(removedNonTerminal)) {
                        grammar.getNonTerminals().remove(removedNonTerminal);
                    }

                    return;
                }
            }
        }
    }

    /**
     * Checks if the grammar with the given ID is empty (no terminals, non-terminals, or rules).
     *
     * @param id The unique identifier of the grammar.
     * @return True if the grammar is empty, false otherwise.
     */
    public boolean empty(int id) {
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        ContextFreeGrammar grammar = grammars.getGrammarById(id);
        if (grammar == null) {
            return false;
        }

        return grammar.getRules().isEmpty() && grammar.getTerminals().isEmpty() && grammar.getNonTerminals().isEmpty();
    }

    /**
     * Generates a unique identifier for the grammar.
     *
     * @return The unique identifier.
     */
    public int generateID() {
        Random random = new Random();
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
        int id = random.nextInt(100) + 1;
        while (true) {
            if (grammars.getGrammarById(id) == null) {
                this.uniqueId = id;
                break;
            } else {
                id = random.nextInt(100) + 1;
            }
        }
        return uniqueId;
    }

    /**
     * Sets the unique identifier for the grammar.
     *
     * @param id The unique identifier.
     */
    public void setUniqueId(int id) {
        this.uniqueId = id;
    }

    /**
     * Prints the details of the grammar including terminals, non-terminals, and rules.
     *
     * @param id The unique identifier of the grammar to print.
     */
    @Override
    public void printGrammar(int id) {
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
            List<String> ruleList = entry.getValue();
            for (String rule : ruleList) {
                System.out.println(rule);
            }
        }
    }

    /**
     * Saves the grammar with the given ID to an XML file.
     *
     * @param id The unique identifier of the grammar to save.
     * @param filename The name of the file to save the grammar.
     */
    public void save(int id, String filename) {
        File blackFile = new File("black.xml");

        try (FileWriter writer = new FileWriter(blackFile, true)) {
            if (!blackFile.exists()) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<List>\n");
            } else {
                RandomAccessFile raf = new RandomAccessFile(blackFile, "rw");
                long length = raf.length();
                raf.setLength(length - "</List>\n".length());
                raf.close();
            }

            writer.write("  <ContextFreeGrammar> \n");
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

            writer.write("</List>\n");

            System.out.println("Grammar with ID " + id + " appended to black.xml");
        } catch (IOException e) {
            System.out.println("Error occurred while appending the grammar to black.xml");
            e.printStackTrace();
        }
    }



}
