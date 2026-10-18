public class TimerTest {
    public static void runTests() {
        for (int[] data:TestData.TIMERS) {
            Timer t=new Timer(data[0]);
            TestSupport.check("Timer initial count "+data[0],data[0],t::getMovesRemaining);
            TestSupport.check("Timer isDone "+data[0],data[1]==1,t::isDone);
        }
        Timer t=new Timer(1); t.decrease();
        TestSupport.check("Timer last move count",0,t::getMovesRemaining);
        TestSupport.check("Timer last move done",true,t::isDone);
        t.decrease();
        TestSupport.check("Timer below zero count",-1,t::getMovesRemaining);
        TestSupport.check("Timer below zero done",true,t::isDone);
        t.addMoves(6);
        TestSupport.check("Timer add moves",5,t::getMovesRemaining);
        TestSupport.check("Timer revived",false,t::isDone);
        t.addMoves(0);
        TestSupport.check("Timer add zero",5,t::getMovesRemaining);
        t.addMoves(-5);
        TestSupport.check("Timer negative addition",0,t::getMovesRemaining);
        TestSupport.check("Timer negative addition done",true,t::isDone);
    }
}
