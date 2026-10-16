public class Key extends GameItem {
    private String color;
    
    public Key(String color,String symbol){
        super(color+"Key",symbol,"collectible");
        this.color=color;
    }
    
    public String getColor(){
        return color;
    }
}