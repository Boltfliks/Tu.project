package bg.tu_varna.sit.a2.f22621613;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;
import bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton.ListOfGrammars;
import bg.tu_varna.sit.a2.f22621613.Menu.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        ListOfGrammars grammars = ListOfGrammars.getGrammarListInstanceInstance();
/*
        ContextFreeGrammar cfg = new ContextFreeGrammar();
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

        ContextFreeGrammar cfg2 = new ContextFreeGrammar();
        cfg2.generateID();
        // Add terminals
        cfg2.addTerminal('c');
        cfg2.addTerminal('d');

        // Add non-terminals
        cfg2.addNonTerminal('S');
        cfg2.addNonTerminal('C');
        cfg2.addNonTerminal('D');

        // Add rules
        cfg2.addRule('S', "cCD");
        cfg2.addRule('C', "cC");
        cfg2.addRule('D', "ε"); // ε represents empty string (plan to change to _ for ε)
        cfg2.addRule('C', "d");

        // print method test
        cfg2.printGrammar(cfg.generateID());

        // add the two grammars to the singleton
        grammars.addGrammar(cfg);
        grammars.addGrammar(cfg2);

        //save the singleton on a file
        menu.saveFile("Grammars");
*/
        //open read the file
        menu.openFile("Grammars");

        grammars.displayGrammars();

    }
}
