public class PlayerTest {
    public static void runTests() {
        for (int[] xy:TestData.POSITIONS) {
            Player p=new Player(xy[0],xy[1]);
            TestSupport.check("Player initial row "+xy[0],xy[0],p::getRow);
            TestSupport.check("Player initial col "+xy[1],xy[1],p::getCol);
        }
        Player p=new Player(1,1);
        TestSupport.check("Player initial health",100,p::getHealth);
        TestSupport.check("Player initial general key",false,p::hasKey);
        TestSupport.check("Player initial red key",false,p::hasRedKey);
        TestSupport.check("Player initial blue key",false,p::hasBlueKey);
        TestSupport.check("Player initial red passage",false,p::hasPassedRedDoor);
        TestSupport.check("Player initial blue passage",false,p::hasPassedBlueDoor);
        p.move(3,4);
        TestSupport.check("Player moved row",3,p::getRow);
        TestSupport.check("Player moved col",4,p::getCol);
        p.collectKey();
        TestSupport.check("Player collected general key",true,p::hasKey);
        TestSupport.check("General key is not red key",false,p::hasRedKey);
        p.collectRedKey();
        TestSupport.check("Player collected red key",true,p::hasRedKey);
        TestSupport.check("Red key is not blue key",false,p::hasBlueKey);
        p.collectBlueKey();
        TestSupport.check("Player collected blue key",true,p::hasBlueKey);
        p.passRedDoor();
        TestSupport.check("Player passed red door",true,p::hasPassedRedDoor);
        TestSupport.check("Red passage is not blue passage",false,p::hasPassedBlueDoor);
        p.passBlueDoor();
        TestSupport.check("Player passed blue door",true,p::hasPassedBlueDoor);
        p.takeDamage(40);
        TestSupport.check("Player damaged",60,p::getHealth);
        p.heal(10);
        TestSupport.check("Player explicit heal",70,p::getHealth);
        p.heal();
        TestSupport.check("Player default heal",95,p::getHealth);
        p.heal(30);
        TestSupport.check("Player health not capped (current)",125,p::getHealth);
        p.takeDamage(150);
        TestSupport.check("Player negative health (current)",-25,p::getHealth);
        Player colored=new Player(1,1); colored.collectRedKey(); colored.collectBlueKey();
        TestSupport.check("Colored keys do not set general flag",false,colored::hasKey);
    }
}
