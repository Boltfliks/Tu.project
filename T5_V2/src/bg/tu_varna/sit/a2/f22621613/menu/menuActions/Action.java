package bg.tu_varna.sit.a2.f22621613.menu.menuActions;

/**
 * The Action interface defines a method for performing an action on a menu map with specified tokens.
 */
public interface Action {
    /**
     * Performs an action based on the provided menu map and tokens.
     *
     * @param menuMap The menu map on which the action will be performed.
     * @param tokens  An array of tokens that provide additional information for the action.
     */
    void action(MenuMap menuMap, String[] tokens);
}