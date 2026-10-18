public class DoorTest {
    public static void runTests() {
        for (String color: TestData.COLORS) {
            Door door=new Door(color,"D");
            TestSupport.check("Door "+color+" getColor",color,door::getColor);
            TestSupport.check("Door "+color+" getName",color+" Door",door::getName);
            TestSupport.check("Door "+color+" getSymbol","D",door::getSymbol);
            TestSupport.check("Door "+color+" getType","door",door::getType);
            TestSupport.check("Door "+color+" matching key",true,
                    () -> door.matchesKey(new Key(color,"K")));
            TestSupport.check("Door "+color+" wrong key",false,
                    () -> door.matchesKey(new Key("Other","K")));
            TestSupport.check("Door "+color+" case-sensitive",false,
                    () -> door.matchesKey(new Key(color.toLowerCase(),"K")));
            TestSupport.throwsType("Door "+color+" null key (current behavior)",
                    NullPointerException.class, () -> door.matchesKey(null));
        }
    }
}
