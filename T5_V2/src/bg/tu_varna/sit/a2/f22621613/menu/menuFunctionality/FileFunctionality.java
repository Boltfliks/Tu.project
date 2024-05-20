package bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality;

import java.util.List;
import java.util.Set;

/**
 * The FileFunctionality interface defines methods for file operations and content manipulation
 * within the context of a menu-based grammar management system.
 */
public interface FileFunctionality {

    /**
     * Opens a file with the specified filename and loads its content into the given menu.
     *
     * @param filename The name of the file to open.
     * @param menu     The Menu instance into which the file content will be loaded.
     */
    void openFile(String filename, Menu menu);

    /**
     * Finds and returns a list of rules from the given file content.
     *
     * @param content The content of the file from which to extract rules.
     * @return A list of rules found in the content.
     */
    List<String> findRules(String content);

    /**
     * Extracts and returns a set of characters enclosed by a specified tag from the given content.
     *
     * @param content The content of the file from which to extract characters.
     * @param tag     The tag enclosing the characters to extract.
     * @return A set of characters enclosed by the specified tag in the content.
     */
    Set<Character> extractCharacters(String content, String tag);

    /**
     * Saves the current state of the given menu to its associated file.
     *
     * @param menu The Menu instance to save.
     */
    void saveFile(Menu menu);

    /**
     * Saves the current state of the given menu to a specified file.
     *
     * @param filename The name of the file to save the menu content.
     * @param menu     The Menu instance to save.
     */
    void saveFileAs(String filename, Menu menu);

    /**
     * Displays help information about the available commands and their usage.
     */
    void help();
}
