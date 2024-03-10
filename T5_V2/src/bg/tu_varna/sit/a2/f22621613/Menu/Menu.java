package bg.tu_varna.sit.a2.f22621613.Menu;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Menu implements SaveFile,Help,Exit{
    protected String filename;
    protected StringBuilder sb;


    @Override
    public void SaveFile(ContextFreeGrammar contextFreeGrammar) {  // Instead of contextFreeGrammar it has to be id and filename
        try (FileWriter writer = new FileWriter(filename + ".xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<ContextFreeGrammar>\n");

            // Write unique ID
            writer.write("  <UniqueId>" + contextFreeGrammar.getUniqueId() + "</UniqueId>\n");

            // Write terminals
            writer.write("  <Terminals>\n");
            for (char terminal : contextFreeGrammar.getTerminals()) {
                writer.write("    <Terminal>" + terminal + "</Terminal>\n");
            }
            writer.write("  </Terminals>\n");

            // Write non-terminals
            writer.write("  <NonTerminals>\n");
            for (char nonTerminal : contextFreeGrammar.getNonTerminals()) {
                writer.write("    <NonTerminal>" + nonTerminal + "</NonTerminal>\n");
            }
            writer.write("  </NonTerminals>\n");

            // Write rules
            writer.write("  <Rules>\n");
            for (char nonTerminal : contextFreeGrammar.getNonTerminals()) {
                List<String> ruleList = contextFreeGrammar.getRules(nonTerminal);
                for (String rule : ruleList) {
                    writer.write("    <Rule nonTerminal=\"" + nonTerminal + "\">" + rule + "</Rule>\n");
                }
            }
            writer.write("  </Rules>\n");

            writer.write("</ContextFreeGrammar>");
        } catch (IOException e) {
        }
    }
        @Override
        public void Help(){
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
        public void Exit() {
            System.out.println("Exiting the program...");
            System.exit(0);
        }
}

