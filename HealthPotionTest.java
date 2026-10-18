public class HealthPotionTest {
    public static void runTests() {
        HealthPotion p=new HealthPotion();
        TestSupport.check("HealthPotion name","Health potion",p::getName);
        TestSupport.check("HealthPotion symbol","💗",p::getSymbol);
        TestSupport.check("HealthPotion type","health",p::getType);
        TestSupport.check("HealthPotion effect",25,p::getEffectAmount);
    }
}
