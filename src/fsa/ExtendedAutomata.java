package fsa;

import java.util.ArrayList;

public class ExtendedAutomata extends Automata {

    public ExtendedAutomata(String grammarDef) {
        super(grammarDef);
    }

    /**
     * Searches for all matchings of the grammar from the given text.
     */
    public String[] findMatches(String text) {
        ArrayList<String> matches = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 1; j <= text.length() - i; j++) {
                String part = text.substring(i, i + j);
                if (acceptsString(part)) {
                    matches.add(part);
                }
            }
        }
        return matches.toArray(new String[0]);
    }
}
