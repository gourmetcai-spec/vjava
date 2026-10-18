public class GameItemTest {
    public static void runTests() {
        for (int i=0; i<TestData.ITEMS.length; i++) {
            String[] data=TestData.ITEMS[i];
            GameItem item=new GameItem(data[0],data[1],data[2]);
            String id="GameItem["+i+"] ";
            TestSupport.check(id+"getName",data[0],item::getName);
            TestSupport.check(id+"getSymbol",data[1],item::getSymbol);
            TestSupport.check(id+"getType",data[2],item::getType);
        }
    }
}
