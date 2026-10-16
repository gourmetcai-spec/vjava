public class Dungeon {
    private String[][] board;
    private Key redKey;
    private Key blueKey;
    private Potion potion;
    private Potion speedPotion;
    private Door redDoor;
    private Door blueDoor;
    public Dungeon(){
    redKey=new Key("Red","🔴");
    blueKey=new Key("Blue","🔵");
    potion=new HealthPotion();
    speedPotion=new SpeedPotion();
    redDoor=new Door("Red","🟥");
    blueDoor=new Door("Blue","🟦");
    board=new String[][]{
            {"⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛"},
            {"⬛","🧍","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬜","⬜","⬛"},
            {"⬛","⬜","⬜","⬜","🪤","⬜","⬛","⬜","👾","⬜","⬜","⬜","⬛","⬜","⬜","🪤","⬜","⬜","⬜","⬛"},
            {"⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬜","⬜","⬜","⬛","⬛","⬛","⬜","⬜","⬜","⬛","⬛","⬜","⬛"},
{"⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛"},
{"⬛","⬛","⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬜","⬛","⬜","⬛","⬜","⬛","⬜","⬛","⬛","⬛","⬛"},
            {"⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛"},
            {"⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛"},
            {"⬛","⬛","⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬜","⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬛","⬛","⬛"},
            {"⬛","⬜","⬜","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","🔴","⬛","⬜","⬜","⬜","⬜","⬛"},            {"⬛","⬜","⬛","⬛","⬛","⬛","⬛","⬜","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬜","⬛","⬛","⬜","⬛"},
            {"⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛"},
{"⬛","⬛","⬛","⬛","⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬛","⬜","⬛","⬛","⬜","⬛","⬛","⬛","⬛"},
{"⬛","⬜","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛","⬜","⬜","⬛","⬜","⬜","⬜","⬜","⬛"},
            {"⬛","⬜","⬛","⬛","⬛","⬜","⬛","⬜","⬛","⬛","⬜","⬜","⬛","⬜","⬛","⬜","⬛","⬛","⬜","⬛"},
            {"⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬜","⬛","🧪","⬜","⬜","⬛","⬜","⬜","⬜","⬛","⬜","⬜","⬛"},
{"⬛","⬜","⬛","⬜","⬛","⬛","⬛","⬛","⬛","⬛","🪤","⬛","⬛","⬜","⬛","⬜","⬛","⬛","⬛","⬛"},
{"⬛","⬜","⬜","⬜","⬜","⬜","⬜","⬜","⬜","💰","⬜","⬜","⬜","⬜","⚡","🟥","🔵","🟦","🏁","⬛"},
{"⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛"},
        {"⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛","⬛"}
    };
}
    
    
    
    public String getTile(int row, int col){
        return board[row][col];
    }
    
    public void setTile(int row, int col,String tile){
        board[row][col]=tile;
    }
    
    public boolean isValidMove(int row,int col, Player player){
        if (row<0||row>=board.length){
            return false;
        }
        
        if (col<0||col>=board[row].length){
            return false;
        }
        
        if (board[row][col].equals("⬛")){
            return false;
        }
        
        if (board[row][col].equals("🟥")&&!player.hasRedKey()){
            return false;
        }
        if (board[row][col].equals("🟦")&&!player.hasBlueKey()){
            return false;
        }
        
        return true;
    }
    
    public void displayBoard(){
        for (int row=0;row<board.length;row++){
            for (int col=0;col<board[row].length;col++){
                System.out.print(board[row][col]+" ");
            }
            
            System.out.println();
        }
    }
    
public void checkTile(Player player, Timer timer){
    String tile=getTile(player.getRow(),player.getCol());
    if (tile.equals("🔴")){
        player.collectRedKey();
        System.out.println("you picked up the "+redKey.getName());
    }
    if (tile.equals("🔵")){
        player.collectBlueKey();
        System.out.println("you picked up the "+blueKey.getName());
    }
    
    if (tile.equals("🧪")){
        player.heal(potion.getEffectAmount());
            System.out.println("u drank a "+potion.getName()+" and so u healed some health! congratulatoins");

    }
    
    if (tile.equals("⚡")){
        timer.addMoves(speedPotion.getEffectAmount());
        System.out.println("u drank a "+speedPotion.getName()+" and gained "+speedPotion.getEffectAmount()+" extra moves");
    }
    
    if (tile.equals("🪤")){
        player.takeDamage(20);
        System.out.println("bc u steped on a trap, u lost 20 health womp wom :(");
        System.out.println("health remaining: "+player.getHealth());
    }
    
    if (tile.equals("🟥")){
        player.passRedDoor();
        System.out.println("you unlocked & went through the red door");
    }
    
    if (tile.equals("🟦")){
        player.passBlueDoor();
        System.out.println("you unlocked & went through the blue door");
    }
    
}
    
    public int[] findTile(String target){
        for (int row=0;row<board.length;row++){
            for (int col=0;col<board[row].length;col++){
                if (board[row][col].equals(target)){
                    return new int[]{row,col};
                }
            }
        }
        return null;
    }
    
    public void displayVisibleBoard(Player player){
        int playerRow=player.getRow();
        int playerCol=player.getCol();
        
        for (int row=0;col<board[row].length;col++){
            if (Math.abs(row-playerRow)<=2&&Math.abs(col=playerCol)<=2){
                System.out.print(board[row][col]+" ");
            }
            else{
                System.out.print("❓" );
            }
        }
        System.out.println();
    }
    
}