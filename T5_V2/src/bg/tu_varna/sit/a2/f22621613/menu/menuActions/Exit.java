package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Exit class implements the Action interface to handle the termination of the program.
 */
public class Exit implements Action {
    /**
     * Executes the action to terminate the program. This method clears the filename from the MenuMap,
     * empties the contents of the specified black file, and then exits the program.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens The array of command tokens. This action does not require any additional tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        menuMap.setFilename(null);
        File blackFile = new File(menuMap.getMenu().getBlackFile() + ".xml");
        try (FileWriter writer = new FileWriter(blackFile, false)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error occurred while clearing black.xml.");
            e.printStackTrace();
        }
        System.out.println("Exiting...");
        System.exit(0);
    }
}
