package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exit implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        menuMap.setFilename(null);
        File blackFile = new File( menuMap.getMenu().getBlackFile() + ".xml");
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
