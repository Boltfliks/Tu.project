package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.Menu;
import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.MenuException;

public class Open implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new MenuException("Invalid number of arguments. Expected usage: <command> <filename>");
            }

            String filename = tokens[1];
            Menu menu = menuMap.getMenu();
            menu.openFile(filename,menu);
            menuMap.setFilename(filename);
            System.out.println("File " + filename + " read!");
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }
}