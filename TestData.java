/** Edit inputs AND independent expected values here, then recompile and rerun.
 * Fixed board coordinates in DungeonTest/ComputerPlayerTest can also be edited.
 * Each test creates its own fixtures; no interactive input or third-party library.
 */
public final class TestData {
    private TestData() {}
    // name, symbol, type; includes ordinary, empty and null metadata.
    public static final String[][] ITEMS = {
        {"Coin", "C", "collectible"}, {"", "", ""}, {null, null, null}
    };
    public static final String[] COLORS = {"Red", "Blue"};
    // initial timer count, expected isDone (1=true, 0=false).
    public static final int[][] TIMERS = {{25,0}, {1,0}, {0,1}, {-2,1}};
    // start row, start column (Player itself does not validate board bounds).
    public static final int[][] POSITIONS = {{1,1}, {3,4}, {0,0}, {-1,20}};
    public static final int[] EFFECTS = {25, 0, -5};
    public static final int RANDOM_SAMPLES = 50;
}
