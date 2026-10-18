public class KeyTest {
    public static void runTests() {
        for (String color: TestData.COLORS) {
            Key key=new Key(color,"K");
            TestSupport.check("Key "+color+" getColor",color,key::getColor);
            TestSupport.check("Key "+color+" getName",color+"Key",key::getName);
            TestSupport.check("Key "+color+" getSymbol","K",key::getSymbol);
            TestSupport.check("Key "+color+" getType","collectible",key::getType);
        }
    }
}
