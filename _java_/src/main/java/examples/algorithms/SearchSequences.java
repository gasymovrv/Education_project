package examples.algorithms;

import examples.utils.TimeMeasurementUtil;

public class SearchSequences {

    public static void main(String[] args) throws Exception {
        long time = TimeMeasurementUtil.measureTime(() -> System.out.println(
                calculateSumOfSequentialRepeats(
                        "ABC",
                        // 8 repeats
                        "lkABCjhasfdlkhasdABCABCskngr;lejgr;ABCABCwegwegegwABCABCABCABC"
                )
        ));
        System.out.println("Execution time:" + time + " ms");

        time = TimeMeasurementUtil.measureTime(() -> System.out.println(
                calculateSumOfSequentialRepeats(
                        "ABC!",
                        // 5 repeats
                        "2ABC!5_ABC!ABC!123hgdABC!h_ABC!ABC!ABC!vcABC!jABC!".repeat(20000) // one million
                )
        ));
        System.out.println("Execution time:" + time + " ms");

        time = TimeMeasurementUtil.measureTime(() -> System.out.println(
                calculateSumOfSequentialRepeats(
                        "AA",
                        // 2 repeats
                        "2AA57AAA123dAB_AAAAA_CfhA"/*.repeat(100)*/
                )
        ));
        System.out.println("Execution time:" + time + " ms");
    }

    private static int calculateSumOfSequentialRepeats(String shortStr, String longStr) {
        if (shortStr.isBlank() || shortStr.isBlank()) {
            return 0;
        }

        var i = 0;
        final var shortStrSize = shortStr.length();
        var sequentialRepeats = 0;
        var firstInSequence = true;

        while (i < longStr.length()) {
            final var nextIndex = longStr.indexOf(shortStr, i);
            if (nextIndex == -1) {
                return sequentialRepeats;
            }

            if (nextIndex == i) {
                if (firstInSequence) {
                    sequentialRepeats++;
                }
                sequentialRepeats++;
                firstInSequence = false;
            } else {
                firstInSequence = true;
            }
            i = nextIndex + shortStrSize;
        }
        return sequentialRepeats;
    }
}
