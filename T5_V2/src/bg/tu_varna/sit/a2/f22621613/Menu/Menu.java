package bg.tu_varna.sit.a2.f22621613.Menu;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.Menu.openFile.OpenFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Menu implements OpenFile, SaveFile,Help,Exit{
    protected String filename;
    protected StringBuilder sb;

    @Override
    public void openFile(String filename) {
        try {
            FileReader fileReader = new FileReader(filename + ".xml");
            StringBuilder content = new StringBuilder();
            ListOfGrammars grammarList = ListOfGrammars.getGrammarListInstanceInstance();
            int character;
            while ((character = fileReader.read()) != -1) {
                content.append((char) character);
            }

            String[] grammarContents = content.toString().split("<ContextFreeGrammar>");


            for (int i = 1; i < grammarContents.length; i++) {
                String grammarContent = grammarContents[i].trim();
                if (grammarContent.isEmpty()) {
                    continue;
                }

                int Id = findUniqueId(grammarContent);
                Set<Character> terminals = extractCharacters(grammarContent, "<Terminal>");
                Set<Character> nonTerminals = extractCharacters(grammarContent, "<NonTerminal>");
                List<String> rules = findRules(grammarContent);

                ContextFreeGrammar grammar = new ContextFreeGrammar();
                grammar.setUniqueId(Id);
                grammarList.addGrammar(grammar);
                for (char terminal : terminals) {
                    grammar.addTerminal(terminal);
                }
                for (char nonTerminal : nonTerminals) {
                    grammar.addNonTerminal(nonTerminal);
                }
                for (String rule : rules) {
                    char nonTerminal = rule.charAt(0);
                    System.out.println(nonTerminal + "->"+rule.substring(2));
                    grammar.addRule(Id, nonTerminal + "->" + rule.substring(2));
                }


            }
        } catch (IOException e) {

        }
    }
    @Override
    public int findUniqueId(String content) {
        int startIndex = content.indexOf("<UniqueId>") + "<UniqueId>".length();
        int endIndex = content.indexOf("</UniqueId>");
        String idString = content.substring(startIndex, endIndex);
        return Integer.parseInt(idString.trim());
    }
    @Override
    public Set<Character> extractCharacters(String content, String tag) {
        Set<Character> characters = new HashSet<>();
        int startIndex = content.indexOf(tag);
        while (startIndex != -1) {
            startIndex += tag.length();
            int endIndex = content.indexOf("</" + tag.substring(1), startIndex);
            if (endIndex != -1) {
                characters.add(content.charAt(startIndex));
            }
            startIndex = content.indexOf(tag, endIndex);
        }
        return characters;
    }
    @Override
    public List<String> findRules(String content) {
        List<String> rules = new ArrayList<>();
        int startIndex = content.indexOf("<Rule nonTerminal=");
        while (startIndex != -1) {
            int nonTerminalIndex = content.indexOf("\"", startIndex) + 1;
            char nonTerminal = content.charAt(nonTerminalIndex);
            startIndex = content.indexOf(">", startIndex) + 1;
            int endIndex = content.indexOf("</Rule>", startIndex);
            if (endIndex != -1) {
                String rule = nonTerminal + " " + content.substring(startIndex, endIndex);
                rules.add(rule);
            }
            startIndex = content.indexOf("<Rule", endIndex);
        }
        return rules;
    }

    public void saveFile(String filename) {
        ListOfGrammars grammarListInstance = ListOfGrammars.getGrammarListInstanceInstance();
        List<ContextFreeGrammar> grammars = grammarListInstance.getGrammars();
        try (FileWriter writer = new FileWriter(filename + ".xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<List>\n"); // Changed the root element to List

            for (ContextFreeGrammar contextFreeGrammar : grammars) {
                writer.write("  <ContextFreeGrammar>\n");

                // Write unique ID
                writer.write("    <UniqueId>" + contextFreeGrammar.getUniqueId() + "</UniqueId>\n");

                // Write terminals
                writer.write("    <Terminals>\n");
                for (char terminal : contextFreeGrammar.getTerminals()) {
                    writer.write("      <Terminal>" + terminal + "</Terminal>\n");
                }
                writer.write("    </Terminals>\n");

                // Write non-terminals
                writer.write("    <NonTerminals>\n");
                for (char nonTerminal : contextFreeGrammar.getNonTerminals()) {
                    writer.write("      <NonTerminal>" + nonTerminal + "</NonTerminal>\n");
                }
                writer.write("    </NonTerminals>\n");

                // Write rules
                writer.write("    <Rules>\n");
                for (char nonTerminal : contextFreeGrammar.getNonTerminals()) {
                    List<String> ruleList = contextFreeGrammar.getRules(nonTerminal);
                    for (String rule : ruleList) {
                        writer.write("      <Rule nonTerminal=\"" + nonTerminal + "\">" + rule + "</Rule>\n");
                    }
                }
                writer.write("    </Rules>\n");

                writer.write("  </ContextFreeGrammar>\n");
            }
            writer.write("</List>");
        } catch (IOException e) {
            // Handle IOException
        }
    }
        @Override
        public void help(){
            System.out.println("The following commands are supported:");
            System.out.println("open <file>     Opens <file>");
            System.out.println("opens <file>    Opens <file>");
            System.out.println("close           Closes currently opened file");
            System.out.println("save            Saves the currently opened file");
            System.out.println("saveAs <file>   Saves the currently opened file in <file>");
            System.out.println("help            Prints this information");
            System.out.println("exit            Exits the program");
        }

        @Override
        public void exit() {
            System.out.println("Exiting the program...");
            System.exit(0);
        }


}

