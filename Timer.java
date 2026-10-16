public class Timer {
   private int movesRemaining;
   public Timer(int startingMoves){
       movesRemaining=startingMoves;
   }
   
   public void decrease(){
       movesRemaining--;
   }
   
   public int getMovesRemaining(){
       return movesRemaining;
   }
   
   
   public boolean isDone(){
       return movesRemaining<=0;
   }
   
   public void addMoves(int amount){
       movesRemaining=movesRemaining+amount;
   }
}