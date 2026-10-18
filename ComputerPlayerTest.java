public class ComputerPlayerTest {
    public static void runTests() {
        Dungeon d=new Dungeon(); ComputerPlayer easy=new ComputerPlayer(1,1,1);
        TestSupport.check("Computer initial row",1,easy::getRow);
        TestSupport.check("Computer initial col",1,easy::getCol);
        TestSupport.check("Computer inherited health",100,easy::getHealth);
        TestSupport.check("Random sample count must be positive",true,
                () -> TestData.RANDOM_SAMPLES > 0);
        // Membership, not a fixed random sequence or statistical distribution.
        TestSupport.check("Computer random legal samples",true,() -> {
            for (int i=0;i<TestData.RANDOM_SAMPLES;i++) {
                String move=easy.chooseRandomMove(d);
                if (!"S".equals(move) && !"D".equals(move)) return false;
            }
            return true;
        });
        Dungeon forced=new Dungeon(); forced.setTile(2,1,"⬛");
        TestSupport.check("Computer random single legal move","D",() -> easy.chooseRandomMove(forced));
        TestSupport.check("Computer easy dispatcher","D",() -> easy.chooseMove(forced));
        ComputerPlayer hard=new ComputerPlayer(1,1,2);
        TestSupport.check("Computer hard dispatcher","S",() -> hard.chooseMove(d));
        TestSupport.check("Computer greedy tie order","S",() -> hard.chooseStrategicMove(d));
        TestSupport.check("Computer other difficulty uses strategy","S",
                () -> new ComputerPlayer(1,1,3).chooseMove(d));
        ComputerPlayer red=new ComputerPlayer(9,12,2);
        TestSupport.check("Computer targets red key","D",() -> red.chooseStrategicMove(d));
        ComputerPlayer door=new ComputerPlayer(17,14,2); door.collectRedKey();
        TestSupport.check("Computer targets red door","D",() -> door.chooseStrategicMove(d));
        ComputerPlayer blue=new ComputerPlayer(17,15,2); blue.collectRedKey(); blue.passRedDoor();
        TestSupport.check("Computer targets blue key","D",() -> blue.chooseStrategicMove(d));
        blue.move(17,16); blue.collectBlueKey();
        TestSupport.check("Computer targets blue door","D",() -> blue.chooseStrategicMove(d));
        blue.move(17,17); blue.passBlueDoor();
        TestSupport.check("Computer targets exit","D",() -> blue.chooseStrategicMove(d));
        TestSupport.check("Computer path right","D",() -> hard.findPath(d,1,2));
        TestSupport.check("Computer path down","S",() -> hard.findPath(d,2,1));
        TestSupport.check("Computer path forced first step","D",() -> hard.findPath(forced,2,2));
        TestSupport.check("Computer path already there",null,() -> hard.findPath(d,1,1));
        TestSupport.check("Computer path wall target",null,() -> hard.findPath(d,0,0));
        TestSupport.check("Computer path locked door",null,() -> hard.findPath(d,17,15));
        TestSupport.check("Computer path unlocked door","D",() -> door.findPath(d,17,15));
        TestSupport.check("Computer path out of bounds",null,() -> hard.findPath(d,20,20));
        TestSupport.check("Computer path does not move row",1,hard::getRow);
        TestSupport.check("Computer path does not move col",1,hard::getCol);
        Dungeon isolated=new Dungeon(); isolated.setTile(2,1,"⬛"); isolated.setTile(1,2,"⬛");
        TestSupport.check("Computer path unreachable floor",null,() -> hard.findPath(isolated,2,2));
        TestSupport.throwsType("Computer trapped random (current)",IllegalArgumentException.class,
                () -> easy.chooseRandomMove(isolated));
        TestSupport.throwsType("Computer trapped strategy (current)",IllegalArgumentException.class,
                () -> hard.chooseStrategicMove(isolated));
        Dungeon missing=new Dungeon(); missing.setTile(9,13,"⬜");
        TestSupport.throwsType("Computer absent objective (current)",NullPointerException.class,
                () -> hard.chooseStrategicMove(missing));
    }
}
