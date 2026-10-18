public class DungeonTest {
    public static void runTests() {
        Dungeon d=new Dungeon(); Player p=new Player(1,1);
        // row, col, expected legality (1=true). Edit table for new map cases.
        int[][] moves={{-1,1,0},{20,1,0},{1,-1,0},{1,20,0},
                {0,1,0},{1,0,0},{19,1,0},{1,19,0},{1,2,1},
                {2,4,1},{2,8,1},{17,9,1},{17,18,1}};
        for (int[] m:moves) TestSupport.check("Dungeon legal ["+m[0]+","+m[1]+"]",
                m[2]==1,() -> d.isValidMove(m[0],m[1],p));
        TestSupport.check("Dungeon initial player","🧍",() -> d.getTile(1,1));
        TestSupport.check("Dungeon initial wall","⬛",() -> d.getTile(0,0));
        TestSupport.check("Dungeon initial exit","🏁",() -> d.getTile(17,18));
        d.setTile(1,2,"X");
        TestSupport.check("Dungeon tile round trip","X",() -> d.getTile(1,2));
        TestSupport.check("Dungeon neighbor unchanged","⬜",() -> d.getTile(1,3));
        TestSupport.throwsType("Dungeon invalid getTile (current)",
                ArrayIndexOutOfBoundsException.class,() -> d.getTile(-1,0));
        TestSupport.check("Dungeon red door locked",false,() -> d.isValidMove(17,15,p));
        TestSupport.check("Dungeon blue door locked",false,() -> d.isValidMove(17,17,p));
        p.collectRedKey();
        TestSupport.check("Dungeon red door unlocked",true,() -> d.isValidMove(17,15,p));
        TestSupport.check("Dungeon red key cannot open blue",false,() -> d.isValidMove(17,17,p));
        Player blue=new Player(1,1); blue.collectBlueKey();
        TestSupport.check("Dungeon blue door unlocked",true,() -> d.isValidMove(17,17,blue));
        TestSupport.check("Dungeon blue key cannot open red",false,() -> d.isValidMove(17,15,blue));
        p.collectBlueKey();
        TestSupport.check("Dungeon both keys blue door",true,() -> d.isValidMove(17,17,p));
        TestSupport.check("Dungeon find red",new int[]{9,13},() -> d.findTile("🔴"));
        TestSupport.check("Dungeon find blue",new int[]{17,16},() -> d.findTile("🔵"));
        TestSupport.check("Dungeon find exit",new int[]{17,18},() -> d.findTile("🏁"));
        TestSupport.check("Dungeon first floor",new int[]{1,3},() -> d.findTile("⬜"));
        TestSupport.check("Dungeon absent tile",null,() -> d.findTile("missing"));
        TestSupport.check("Dungeon null search",null,() -> d.findTile(null));
        d.setTile(9,13,"⬜");
        TestSupport.check("Dungeon removed red key",null,() -> d.findTile("🔴"));
    }
}
