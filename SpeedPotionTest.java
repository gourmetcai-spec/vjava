public class SpeedPotionTest {
    public static void runTests() {
        SpeedPotion p=new SpeedPotion();
        TestSupport.check("SpeedPotion name","speed potion",p::getName);
        TestSupport.check("SpeedPotion symbol","⚡",p::getSymbol);
        TestSupport.check("SpeedPotion type","speed",p::getType);
        TestSupport.check("SpeedPotion effect",5,p::getEffectAmount);
    }
}
