package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.Menu;

/**
 * The SaveAs class implements the Action interface to save the current menu state to a specified file.
 */
public class SaveAs implements Action {
    /**
     * Executes the action to save the current menu state to a specified file.
     * This method saves the menu state using the provided filename.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        Menu menu = menuMap.getMenu();
        String filename = tokens[1];
        menu.saveFileAs(filename, menu);
    }
}
