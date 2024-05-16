package bg.tu_varna.sit.a2.f22621613.Menu.menuActions;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.Menu.Menu;
import bg.tu_varna.sit.a2.f22621613.Menu.MenuException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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