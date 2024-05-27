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
    private Set<Rule> rules;
    private int ruleCount;

    /**
     * Constructs a new ContextFreeGrammar with empty sets of terminals, non-terminals, and rules.
     */
    public ContextFreeGrammar() {
        terminals = new HashSet<>();
        nonTerminals = new HashSet<>();
        rules = new LinkedHashSet<>();
        ruleCount = 0;
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
    public Set<Rule> getRules() {
        return rules;
    }

    public int getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(int ruleCount) {
        this.ruleCount = ruleCount;
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
    public void setRules(Set<Rule> rules) {
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
     * Adds a rule to the given context-free grammar and updates the rule count.
     *
     * @param grammar The context-free grammar to which the rule is added.
     * @param rule The rule to be added.
     */
    public void addRule(ContextFreeGrammar grammar, Rule rule) {
        ruleCount++;
        grammar.getRules().add(rule);
        grammar.addNonTerminal(rule.getLeft().charAt(0));
        for(char s : rule.getRight().toCharArray()){
            if(s >= 'A' && s <= 'Z')
                grammar.addNonTerminal(s);
            else if((s >= 'a' && s <= 'z') || s >= '0' && s<= '9')
                grammar.addTerminal(s);
        }
        int number = 1;
        for (Rule sortRule : rules) {
            sortRule.setNumber(number);
            number++;
        }

    }

    /**
     * Removes a rule by its number from the given context-free grammar and updates the rule count.
     *
     * @param grammar The context-free grammar from which the rule is removed.
     * @param number The number of the rule to be removed.
     */
    public void removeRule(ContextFreeGrammar grammar, int number) {
        for (Rule rule : rules) {
            if (rule.getNumber() == number) {
                rules.remove(rule);
                ruleCount--;
                break;
            }
        }
        int n = 1;
        for (Rule sortRule : rules) {
            sortRule.setNumber(n);
            n++;
        }
    }

    /**
     * Checks if the given context-free grammar is empty.
     *
     * @param grammar The context-free grammar to check.
     * @return True if the grammar has no rules, otherwise false.
     */
    public boolean empty(ContextFreeGrammar grammar) {
        return grammar.getRules().isEmpty();
    }

    /**
     * Generates a unique identifier for the context-free grammar.
     *
     * @return The generated unique identifier.
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
        List<Rule> sortedRules = new ArrayList<>(rules);
        sortedRules.sort((rule1, rule2) -> Integer.compare(rule1.getNumber(), rule2.getNumber()));

        for (Rule rule : sortedRules) {
            System.out.println(rule.getNumber() + " " + rule.getLeft() + "->" + rule.getRight());
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
            for (Rule rule : rules) {
                    writer.write("      <Rule>" + rule.getNumber() + ". " + rule.getLeft() +"->"+rule.getRight() + "</Rule>\n");
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
