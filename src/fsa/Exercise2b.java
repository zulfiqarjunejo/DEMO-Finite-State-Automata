package fsa;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

public class Exercise2b {

    public static void checkExtendedAutomata(String grammar, String input, String[] expectedResult) {
        try {
            ExtendedAutomata automata = new ExtendedAutomata(grammar);
            String[] result = automata.findMatches(input);
            Assertions.assertArrayEquals(expectedResult, result, "Your solution returned " + Arrays.toString(result)
                    + " but " + Arrays.toString(expectedResult) + " was expected.");
            System.out.println("Test successfully completed.");
        } catch (AssertionFailedError e) {
            System.err.println(e);
            throw e;
        } catch (Throwable e) {
            System.err.println("Your solution caused an unexpected error:");
            throw e;
        }
    }

    public static void main(String[] args) {
        String donkeyGrammar = "State\ta\ti\th\tI\t!\n0\t7\t2\t7\t1\t7\n1\t4\t2\t3\t7\t7\n2\t4\t2\t3\t7\t7\n3\t4\t7\t3\t7\t7\n4:\t4\t7\t5\t7\t6\n5:\t7\t7\t5\t7\t6\n6:\t7\t7\t7\t7\t7\n7\t7\t7\t7\t7\t7";
        checkExtendedAutomata(donkeyGrammar,
                "Ia! IIiahh!! IiIaaa. My donkey says Iihah!",
//                new String[]{"Ia!", "Iiahh!", "Iaaa", "Iihah!"});
                new String[]{"Ia", "Ia!", "Iia", "Iiah", "Iiahh", "Iiahh!", "ia", "iah", "iahh", "iahh!", "Ia", "Iaa", "Iaaa", "Iiha", "Iihah", 
                    "Iihah!", "iha", "ihah", "ihah!"});
    }
}
