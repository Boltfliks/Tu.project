package bg.tu_varna.sit.a2.f22621613.Grammar.Grammer_Singleton;

import bg.tu_varna.sit.a2.f22621613.Grammar.ContextFreeGrammar;

import java.util.*;

public class CYKAlgorithm {
    private static final char START_SYMBOL = 'S';
    private ContextFreeGrammar grammar;

    public CYKAlgorithm(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }

    public boolean parse(String input) {
        int n = input.length();
        Map<Character, List<Character>>[][] table = new Map[n][n];

        // Initialize table
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = new HashMap<>();
                if (i == j) {
                    table[i][j].computeIfAbsent(START_SYMBOL, l -> new ArrayList<>()).add(START_SYMBOL);
                }
            }
        }

// Fill table for substrings of length 1
        for (int i = 0; i < n; i++) {
            char symbol = input.charAt(i);
            for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
                char nonTerminal = entry.getKey();
                List<String> productions = entry.getValue();
                for (String production : productions) {
                    int arrowIndex = production.indexOf("->");
                    if (arrowIndex != -1 && arrowIndex < production.length() - 2) {
                        char left = production.charAt(arrowIndex - 1); // Extract the symbol before the arrow
                        String rhs = production.substring(arrowIndex + 2).trim(); // Extract the entire RHS string
                        // Check each character of the RHS individually
                        for (char rhsChar : rhs.toCharArray()) {
                            if (rhsChar == symbol) {
                                table[i][i].computeIfAbsent(nonTerminal, k -> new ArrayList<>()).add(left);
                                break; // Break the loop if a match is found
                            }
                        }
                    }
                }
            }
        }

        // Fill table for substrings of length greater than 1
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {
                    for (Map.Entry<Character, List<String>> entry : grammar.getRules().entrySet()) {
                        char nonTerminal = entry.getKey();
                        List<String> productions = entry.getValue();
                        for (String production : productions) {
                            int arrowIndex = production.indexOf("->");
                            if (arrowIndex != -1 && arrowIndex < production.length() - 2) {
                                char left = production.charAt(arrowIndex - 1); // Extract the symbol before the arrow
                                String rhs = production.substring(arrowIndex + 2).trim(); // Extract the entire RHS string
                                for (int r = 0; r < rhs.length(); r++) {
                                    char right = rhs.charAt(r); // Extract each character individually from the RHS
                                    if (table[i][k].containsKey(left) && table[k + 1][j].containsKey(right)) {
                                        table[i][j].computeIfAbsent(nonTerminal, l -> new ArrayList<>()).add(nonTerminal);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Check if the start symbol is present in the top right cell (0, n-1)
        return table[0][n - 1].containsKey(START_SYMBOL);
    }
}

