package bg.tu_varna.sit.a2.f22621613.grammar.grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar.ContextFreeGrammar;

import java.util.*;

/**
 * The ListOfGrammars class implements the AddRemoveGrammar and ShowIds interfaces.
 * It maintains a singleton instance of a list of context-free grammars.
 */
public class ListOfGrammars implements AddRemoveGrammar, ShowIds {

    /** The singleton instance of the ListOfGrammars. */
    private static ListOfGrammars GrammarListInstance;

    /** The list of context-free grammars. */
    private List<ContextFreeGrammar> grammars = new ArrayList<>();
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ListOfGrammars() {}

    /**
     * Returns the singleton instance of the ListOfGrammars.
     *
     * @return The singleton instance of the ListOfGrammars.
     */
    public static ListOfGrammars getGrammarListInstanceInstance() {
        if (GrammarListInstance == null) {
            GrammarListInstance = new ListOfGrammars();
        }
        return GrammarListInstance;
    }

    /**
     * Returns the list of context-free grammars.
     *
     * @return The list of context-free grammars.
     */
    public List<ContextFreeGrammar> getGrammars() {
        return grammars;
    }

    /**
     * Adds a context-free grammar to the list.
     *
     * @param contextFreeGrammar The context-free grammar to be added.
     */
    @Override
    public void addGrammar(ContextFreeGrammar contextFreeGrammar) {
        grammars.add(contextFreeGrammar);
    }

    /**
     * Removes a context-free grammar from the list.
     *
     * @param contextFreeGrammar The context-free grammar to be removed.
     */
    @Override
    public void removeGrammar(ContextFreeGrammar contextFreeGrammar) {
        grammars.remove(contextFreeGrammar);
    }

    /**
     * Lists the unique IDs of all context-free grammars in the list.
     *
     * @return A string containing the unique IDs of all context-free grammars.
     */
    @Override
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (ContextFreeGrammar c : grammars) {
            sb.append(c.getUniqueId()).append(" ");
        }
        return sb.toString();
    }

    /**
     * Retrieves a context-free grammar by its unique ID.
     *
     * @param id The unique ID of the context-free grammar to be retrieved.
     * @return The context-free grammar with the specified unique ID, or null if not found.
     */
    public ContextFreeGrammar getGrammarById(int id) {
        for (ContextFreeGrammar grammar : grammars) {
            if (grammar == null) {
                return null;
            }
            if (grammar.getUniqueId() == id) {
                return grammar;
            }
        }
        return null;
    }
}