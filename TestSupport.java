import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/** Shared reporting; assertions work without the JVM -ea flag. */
public final class TestSupport {
    private static int passed, failed;
    private TestSupport() {}
    public static void reset() { passed = 0; failed = 0; }
    public static void check(String name, Object expected, Supplier<?> test) {
        try {
            Object actual = test.get();
            report(name, expected, actual, Objects.deepEquals(expected, actual));
        } catch (Throwable error) {
            report(name, expected, error.getClass().getSimpleName(), false);
        }
    }
    /** Characterization checks document existing invalid-input behavior. */
    public static void throwsType(String name, Class<? extends Throwable> expected, Runnable test) {
        try {
            test.run();
            report(name, expected.getSimpleName(), "no exception", false);
        } catch (Throwable error) {
            report(name, expected.getSimpleName(), error.getClass().getSimpleName(),
                    expected.isInstance(error));
        }
    }
    private static String text(Object value) {
        return value instanceof int[] ? Arrays.toString((int[]) value) : String.valueOf(value);
    }
    private static void report(String name, Object expected, Object actual, boolean ok) {
        if (ok) passed++; else failed++;
        System.out.printf("%-55s | %-22s | %-22s | %s%n",
                name, text(expected), text(actual), ok ? "PASS" : "FAIL");
    }
    public static int finish() {
        System.out.printf("%nTOTAL: %d  PASSED: %d  FAILED: %d%n", passed + failed, passed, failed);
        return failed;
    }
}
