public class Potion extends GameItem {
    private int effectAmount;
    public Potion(String name, String symbol,String type,int effectAmount){
        super(name,symbol,type);
        this.effectAmount=effectAmount;
    }
   
    
    public int getEffectAmount(){
        return effectAmount;
    }
}