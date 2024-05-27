package bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.Rule;

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
