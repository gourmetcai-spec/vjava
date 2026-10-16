public class GameItem {
   private String name;
   private String symbol;
   private String type;
   
   public GameItem(String name, String symbol,String type){
       this.name=name;
       this.symbol=symbol;
       this.type=type;
   }
   
   public String getName(){
       return name;
   }
   
   public String getSymbol(){
       return symbol;
   }
   
   public String getType(){
       return type
       ;
   }
   
}