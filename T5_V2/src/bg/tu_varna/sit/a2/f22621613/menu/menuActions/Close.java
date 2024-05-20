package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Close class implements the Action interface to close the currently opened file and clear its content.
 */
public class Close implements Action {

    /**
     * Executes the action of closing the currently opened file and clearing its content.
     *
     * @param menuMap The MenuMap instance used to manage the action.
     * @param tokens  The tokens representing the command.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (menuMap.getMenu().getFilename() == null) {
                throw new MenuException("You have not opened a file");
            }
            menuMap.setFilename(null);
            File blackFile = new File(menuMap.getMenu().getBlackFile() + ".xml");
            try (FileWriter writer = new FileWriter(blackFile, false)) {
                writer.write("");
            } catch (IOException e) {
                System.out.println("Error occurred while clearing black.xml.");
                e.printStackTrace();
            }
            ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
            grammars.getGrammars().clear();
            System.out.println("file closed!");
        } catch (MenuException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

