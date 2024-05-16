package bg.tu_varna.sit.a2.f22621613.Menu;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;


public class Menu implements FileFunctionality{
    private String filename;
    private String blackFile = "black";

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getBlackFile() {
        return blackFile;
    }

    @Override
    public void openFile(String filename,Menu menu) {
        try {
            File file = new File(filename + ".xml");
            File blackFile = new File( "black.xml");
            File targetFile = new File( filename+".xml");
            menu.setFilename(filename);


            // Copy the blackFile to the targetFile
            Files.copy(targetFile.toPath(), blackFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            if (!file.exists()) {
                // If the file doesn't exist, create a new file
                file.createNewFile();
                menu.setFilename(filename);
                System.out.println("File '" + filename + ".xml' created successfully.");

                return;
            }
            setFilename(filename);

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
                ContextFreeGrammar grammar = new ContextFreeGrammar();
                grammarList.addGrammar(grammar);

                int Id = grammar.generateID();
                Set<Character> terminals = extractCharacters(grammarContent, "<Terminal>");
                Set<Character> nonTerminals = extractCharacters(grammarContent, "<NonTerminal>");
                List<String> rules = findRules(grammarContent);

                for (char terminal : terminals) {
                    grammar.addTerminal(terminal);
                }
                for (char nonTerminal : nonTerminals) {
                    grammar.addNonTerminal(nonTerminal);
                }

                // Extract rules from the XML format
                for (String rule : rules) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(rule.substring(3));
                    grammar.addRule(grammar.getUniqueId(), sb.toString());
                }

            }
        } catch (IOException e) {
            // Handle IOException
        }
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
    public List<String> findRules(String content) {
        List<String> rules = new ArrayList<>();
        int startIndex = content.indexOf("<Rule>");
        while (startIndex != -1) {
            startIndex += "<Rule>".length();
            int endIndex = content.indexOf("</Rule>", startIndex);
            if (endIndex != -1) {
                rules.add(content.substring(startIndex, endIndex));
            }
            startIndex = content.indexOf("<Rule>", endIndex);
        }
        return rules;
    }

    @Override
    public void closeFile() {
        filename = null;

        System.out.println("Successfully closed file.");
    }

    @Override
    public void saveFileAs(String filename) {

    }

    public void saveFile() {


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

