/** Main entry point: javac -encoding UTF-8 -d test-classes *.java
 * Then: java -cp test-classes AllTests
 * No console input, assertions flag, or external dependencies required.
 */
public class AllTests {
    public static void main(String[] args) {
        runTests();
    }
    public static void runTests() {
        TestSupport.reset();
        System.out.printf("%-55s | %-22s | %-22s | %s%n",
                "Test", "Expected", "Actual", "Result");
        GameItemTest.runTests();
        KeyTest.runTests();
        DoorTest.runTests();
        PotionTest.runTests();
        HealthPotionTest.runTests();
        SpeedPotionTest.runTests();
        PlayerTest.runTests();
        TimerTest.runTests();
        DungeonTest.runTests();
        ComputerPlayerTest.runTests();
        if (TestSupport.finish()!=0) throw new AssertionError("Test suite failed");
    }
}
