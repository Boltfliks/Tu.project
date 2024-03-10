package bg.tu_varna.sit.a2.f22621613;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Menu.Menu;

public class Main {

    public static void main(String[] args) {
        ContextFreeGrammar cfg = new ContextFreeGrammar();
        Menu menu = new Menu();
        cfg.generateID();
        // Add terminals
        cfg.addTerminal('a');
        cfg.addTerminal('b');

        // Add non-terminals
        cfg.addNonTerminal('S');
        cfg.addNonTerminal('A');
        cfg.addNonTerminal('B');

        // Add rules
        cfg.addRule('S', "aAB");
        cfg.addRule('A', "aA");
        cfg.addRule('A', "ε"); // ε represents empty string (plan to change to _ for ε)
        cfg.addRule('B', "b");

        // print method test
        cfg.printGrammar(cfg.generateID());
       /*
       // Test with getters
        System.out.println("Terminals: " + cfg.getTerminals());
        System.out.println("Non-Terminals: " + cfg.getNonTerminals());
        System.out.println("Rules for A: " + cfg.getRules('A'));
*/
        // Menu saveFile
        menu.SaveFile(cfg);

        //Menu Exit
        menu.Exit();

    }
}
