package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

/**
 * The Help class implements the Action interface to provide help information for available commands.
 */
public class Help implements Action {
    /**
     * Executes the help action to display help information for available commands.
     * This method calls the help method of the Menu class associated with the MenuMap.
     *
     * @param menuMap The MenuMap instance managing available actions.
     * @param tokens The array of command tokens. This action does not require any additional tokens.
     */
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        menuMap.getMenu().help();
    }
}
