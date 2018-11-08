package fsa;

import java.util.ArrayList;
import java.util.HashMap;

public class Automata {

    HashMap<Integer, ArrayList<Integer>> transitionTable;
    HashMap<String, Integer> characterIndexMapping;
    ArrayList<Integer> endStates = new ArrayList<>();
    Integer errorState = 0;
    Integer currentState = 0;

    /**
     * Constructor taking the grammar String which defines the behavior of this
     * automata.
     *
     * @param grammarDef
     */
    public Automata(String grammarDef) {
        parseGrammar(grammarDef);
    }

    int parseState(String state) {
        Integer parsedState = 0;
        if (state.contains(":")) {
            state = state.replace(":", "");
            endStates.add(Integer.valueOf(state));
        }
        parsedState = Integer.valueOf(state);
        if (errorState < parsedState) {
            errorState = parsedState;
        }
        return parsedState;
    }

    /**
     * An internal method that parses the given grammar String.
     */
    protected void parseGrammar(String grammarDef) {
        transitionTable = new HashMap<>();
        characterIndexMapping = new HashMap<>();
        String[] lines = grammarDef.split("\n");
        String[] headline = lines[0].split("\t");
        for (int i = 1; i < headline.length; i++) {
            characterIndexMapping.put(headline[i], i - 1);
        }

        for (int i = 1; i < lines.length; i++) {
            String[] lineBreakup = lines[i].split("\t");
            Integer state = parseState(lineBreakup[0]);
            ArrayList<Integer> inputs = new ArrayList<>();
            for (int j = 1; j < lineBreakup.length; j++) {
                inputs.add(Integer.valueOf(lineBreakup[j]));
            }
            transitionTable.put(state, inputs);
        }

//        System.out.println("----");
//        System.out.println("End States -> " + endStates.toString());
//        System.out.println("Error State -> " + errorState);
//        System.out.println(transitionTable.toString());
//        System.out.println(characterIndexMapping.toString());
//        System.out.println("----");

    }

    /**
     * This method should return true if the complete given text is accepted by
     * the FSA. If this is not the case, false should be returned.
     *
     * @param text
     * @return
     */
    public boolean acceptsString(String text) {
        currentState = 0;
//        System.out.println("----");
//        System.out.print(text + "     " + currentState);
        boolean accepted = true;
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            Integer index;
            if (characterIndexMapping.containsKey("" + character)) {
                index = characterIndexMapping.get("" + character);
            } else {
                accepted = false;
                break;
            }
            ArrayList<Integer> transition = transitionTable.get(currentState);
            Integer nextState = transition.get(index);
//            System.out.print(" -> " + nextState);
            if (errorState.equals(nextState)) {
                accepted = false;
                break;
            }
            currentState = nextState;
        }
//        System.out.println();

        if (!endStates.contains(currentState)) {
            accepted = false;
        }

        return accepted;
    }
}
