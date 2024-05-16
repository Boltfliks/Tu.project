package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Menu.Menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SaveAs implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        Menu menu = menuMap.getMenu();
        String filename = tokens[1];
        File blackFile = new File( menu.getBlackFile() +".xml");
        File targetFile = new File(filename + ".xml");

        try {
            // Copy the blackFile to the targetFile
            Files.copy(blackFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File cloned and renamed to " + filename + ".xml");
        } catch (IOException e) {
            System.out.println("Error occurred while cloning and renaming the file.");
            e.printStackTrace();
        }
    }
}
