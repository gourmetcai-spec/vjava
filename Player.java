public class Player {
    private int row;
    private int col;
    private boolean hasKey;
    private boolean hasRedKey;
    private boolean hasBlueKey;
    private boolean passedRedDoor;
    private boolean passedBlueDoor;
    private int health;
    
    
    public Player(int startRow, int startCol){
        row=startRow;
        col=startCol;
        hasKey=false;
        hasRedKey=false;
        hasBlueKey=false;
        passedRedDoor=false;
        health=100;
    }


public int getRow()
{
    return row;
}

public int getCol(){
    return col;
}

public void move(int newRow, int newCol){
    row=newRow;
    col=newCol;
}
public boolean hasKey(){
    return hasKey;
}

public void collectKey(){
    hasKey=true;
}

public int getHealth(){
    return health;
}

public void heal(int amount){
    health=health+amount;
}

public void heal(){
    health=health+25;
}

public void takeDamage(int amount){
    health=health-amount;
}


public void collectRedKey(){
    hasRedKey=true;

}

public void collectBlueKey(){
    hasBlueKey=true;
    
}

public boolean hasRedKey(){
    return hasRedKey;
}

public boolean hasBlueKey(){
    return hasBlueKey;
}

public boolean hasPassedRedDoor(){
    return passedRedDoor;
}

public void passRedDoor(){
    passedRedDoor=true;
}

public boolean hasPassedBlueDoor(){
    return passedBlueDoor;
}

public void passBlueDoor(){
    passedBlueDoor=true;
}
}