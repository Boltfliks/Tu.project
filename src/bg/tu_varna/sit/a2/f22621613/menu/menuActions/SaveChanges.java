package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.menu.menuFunctionality.Menu;

/**
 * The SaveChanges class implements the Action interface to save the current changes in the menu.
 */
public class SaveChanges implements Action {
    /**
     * Executes the action to save the current changes in the menu.
     * This method saves the current state of the menu.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens  The array of command tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        Menu menu = menuMap.getMenu();
        menu.saveFile(menu);
    }
}
