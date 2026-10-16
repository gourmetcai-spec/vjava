public class Door extends GameItem{
    private String color;
    public Door(String color,String symbol){
        super(color+" Door",symbol,"door");
        this.color=color;
    }
    
    public String getColor(){
        return color;
    }
    
    public boolean matchesKey(Key key){
        return key.getColor().equals(color);
    }
    
    
    
    }