package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Menu.Menu;
import bg.tu_varna.sit.a2.f22621613.Menu.MenuException;

import java.util.HashMap;
import java.util.Map;

public class MenuMap {
    private Map<String, Action> actionMap = new HashMap<>();
    private Menu menu;
    private String filename;

    public MenuMap() {
        menu = new Menu();
        // Initialize the action map also use enum FOR COMMAND
        actionMap.put("open", new Open());
        actionMap.put("new", new NewGrammar());
        actionMap.put("list", new List());
        actionMap.put("empty", new Empty());
        actionMap.put("print", new Print());
        actionMap.put("save", new Save());
        actionMap.put("saveas", new SaveAs());
        actionMap.put("savechanges", new SaveChanges());
        actionMap.put("addrule", new AddRule());
        actionMap.put("removerule", new RemoveRule());
        actionMap.put("chomsky", new Chomsky());
        actionMap.put("chomskify", new Chomskify());
        actionMap.put("union", new Union());
        actionMap.put("concat", new Concat());
        actionMap.put("iter", new Iter());
        actionMap.put("cyk", new CYK());
        actionMap.put("help", new Help());
        actionMap.put("close", new Close());
        actionMap.put("exit", new Exit());

    }

    public Menu getMenu() {
        return menu;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public boolean hasAction(String actionKey) {
        return actionMap.containsKey(actionKey);
    }
    public Action getAction(String command) {
        return actionMap.get(command);
    }

    public void executeAction(String actionKey, String[] tokens) {
        Action action = actionMap.get(actionKey);
        try {
            if (action != null) {
                action.action(this, tokens);
            } else {
                throw new MenuException("Invalid action: " + actionKey);
            }
        } catch (MenuException e) {
            System.out.println("MenuException: " + e.getMessage());
        }
    }
}
