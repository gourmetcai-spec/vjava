public class PotionTest {
    public static void runTests() {
        for (int effect:TestData.EFFECTS) {
            Potion p=new Potion("Test","P","health",effect);
            TestSupport.check("Potion effect "+effect,effect,p::getEffectAmount);
            TestSupport.check("Potion name "+effect,"Test",p::getName);
            TestSupport.check("Potion symbol "+effect,"P",p::getSymbol);
            TestSupport.check("Potion type "+effect,"health",p::getType);
        }
    }
}
