package bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;

/**
 * The AddRemoveGrammar interface provides methods for adding and removing context-free grammars.
 */
public interface AddRemoveGrammar {

    /**
     * Adds a context-free grammar to the collection.
     *
     * @param contextFreeGrammar The context-free grammar to be added.
     */
    void addGrammar(ContextFreeGrammar contextFreeGrammar);

    /**
     * Removes a context-free grammar from the collection.
     *
     * @param contextFreeGrammar The context-free grammar to be removed.
     */
    void removeGrammar(ContextFreeGrammar contextFreeGrammar);
}