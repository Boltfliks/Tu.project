package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

public class Help implements Action {
    @Override
    public void action(MenuMap menuMap, String[] tokens) {
        menuMap.getMenu().help();
    }
}
