package bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * The Menu class implements FileFunctionality and provides methods for managing grammar files,
 * including opening, saving, and extracting content.
 */
public class Menu implements FileFunctionality {
    private String filename;
    private String blackFile = "black";

    /**
     * Sets the filename for the menu.
     *
     * @param filename The name of the file.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Returns the current filename.
     *
     * @return The filename.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns the name of the black file used for temporary storage.
     *
     * @return The black file name.
     */
    public String getBlackFile() {
        return blackFile;
    }

    /**
     * Opens a file with the specified filename and loads its content into the menu.
     *
     * @param filename The name of the file to open.
     * @param menu     The Menu instance to load the content into.
     */
    @Override
    public void openFile(String filename, Menu menu) {
        try {
            File file = new File(filename + ".xml");
            if (!file.exists()) {
                file.createNewFile();
                menu.setFilename(filename);
                System.out.println("File '" + filename + ".xml' created successfully.");
                return;
            }

            File blackFile = new File("black.xml");
            File targetFile = new File(filename + ".xml");
            menu.setFilename(filename);
            Files.copy(targetFile.toPath(), blackFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            this.filename = filename;

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
                for (String rule : rules) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(rule.substring(3));
                    grammar.addRule(grammar.getUniqueId(), sb.toString());
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error occurred while opening the file.");
            e.printStackTrace();
        }
    }

    /**
     * Extracts and returns a set of characters enclosed by a specified tag from the given content.
     *
     * @param content The content of the file from which to extract characters.
     * @param tag     The tag enclosing the characters to extract.
     * @return A set of characters enclosed by the specified tag in the content.
     */
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

    /**
     * Finds and returns a list of rules from the given file content.
     *
     * @param content The content of the file from which to extract rules.
     * @return A list of rules found in the content.
     */
    @Override
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

    /**
     * Saves the current state of the menu to a specified file.
     *
     * @param filename The name of the file to save the menu content.
     * @param menu     The Menu instance to save.
     */
    @Override
    public void saveFileAs(String filename, Menu menu) {
        File blackFile = new File(menu.getBlackFile() + ".xml");
        File targetFile = new File(filename + ".xml");

        try {
            Files.copy(blackFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved as " + filename + ".xml");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    /**
     * Saves the current state of the menu to its associated file.
     *
     * @param menu The Menu instance to save.
     */
    @Override
    public void saveFile(Menu menu) {
        File blackFile = new File(menu.getBlackFile() + ".xml");
        File targetFile = new File(menu.getFilename() + ".xml");

        try {
            Files.copy(blackFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved " + menu.getFilename() + ".xml");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    /**
     * Displays help information about the available commands and their usage.
     */
    @Override
    public void help() {
        System.out.println("The following commands are supported:");
        System.out.println("open        <file>         ");
        System.out.println("new                        ");
        System.out.println("list                       ");
        System.out.println("empty       <id>           ");
        System.out.println("print       <id>           ");
        System.out.println("save        <id> <filename>");
        System.out.println("saveas      <filename>     ");
        System.out.println("savechanges                ");
        System.out.println("addrule     <id> <rule>    ");
        System.out.println("removerule  <id> <number>  ");
        System.out.println("chomsky     <id>           ");
        System.out.println("chomskify   <id>           ");
        System.out.println("union       <id> <id>      ");
        System.out.println("concat      <id> <id>      ");
        System.out.println("iter        <id>           ");
        System.out.println("cyk         <id>           ");
        System.out.println("help                       ");
        System.out.println("close                      ");
        System.out.println("exit                       ");
    }
}