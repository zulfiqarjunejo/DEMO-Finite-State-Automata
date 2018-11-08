package fsa;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

/**
 *
 * @author Zulfiqar
 */
public class Exercise2a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String grammar1 = "State\ta\tb\t!\n0\t5\t1\t5\n1\t2\t5\t5\n2\t3\t5\t5\n3\t3\t5\t4\n4:\t5\t5\t5\n5\t5\t5\t5";

        checkAutomata(grammar1, "baa!", true);
        checkAutomata(grammar1, "aa!", false);
        checkAutomata(grammar1, "bbaa!", false);
        checkAutomata(grammar1, "baaa", false);
        checkAutomata(grammar1, "baaa!", true);
        checkAutomata(grammar1, "baaa!!!", false);
        checkAutomata(grammar1, "!aab", false);
        checkAutomata(grammar1, "xyz", false);
    }

    /**
     * A simple check whether an instance of the Automata class initialized with
     * the given grammar would accept the given input.
     */
    public static void checkAutomata(String grammar, String input, boolean expectedResult) {
        try {
            Automata automata = new Automata(grammar);
            if (expectedResult) {
                Assertions.assertTrue(automata.acceptsString(input),
                        "Your automata rejected \"" + input + "\" but it should have accepted it.");
            } else {
                Assertions.assertFalse(automata.acceptsString(input),
                        "Your automata accepted \"" + input + "\" but it should have rejected it.");
            }
            System.out.println("Test successfully completed.");
        } catch (AssertionFailedError e) {
            System.err.println(e);
            throw e;
        }
    }
}
