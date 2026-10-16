import java.util.Scanner;
public class MyProgram
{
    public static void main(String[] args)
    {
        Player player;
        Dungeon dungeon=new Dungeon();
        Timer timer=new Timer(25);
        Scanner input=new Scanner(System.in);
        System.out.println("how do you want to play?");
        System.out.println("human player");
        System.out.println("computer player");
        
        String playerChoice=input.nextLine();
        if (playerChoice.equals("2")){
            System.out.println("choose computer difficulty");
            System.out.println("1. easy");
            System.out.println("2. hard");
            
            int difficulty=Integer.parseInt(input.nextLine());
            player=new ComputerPlayer(1,1,difficulty);
        }
        else{
            player=new Player(1,1);
        }
        boolean playing=true;
        while (playing){
        dungeon.displayBoard();
        System.out.println("you have "+timer.getMovesRemaining()+" moves remaining");
 
    String move;
    if (player instanceof ComputerPlayer){
        ComputerPlayer computer=(ComputerPlayer) player;
        move=computer.chooseMove(dungeon);
        System.out.println("Computer chose: "+move);
        
    }
    else{
        System.out.println("enter how to move");
        move=input.nextLine();
    }
    int newRow=player.getRow();
    int newCol=player.getCol();
            boolean validInput=false;

    if (move.equalsIgnoreCase("w")){
        newRow--;
        validInput=true;
    }
    
    else if (move.equalsIgnoreCase("S")){
        newRow++;
                validInput=true;

    }
    
    else if (move.equalsIgnoreCase("A")){
        newCol--;
                validInput=true;

    }
    
    else if (move.equalsIgnoreCase("D")){
        newCol++;
                validInput=true;

    }
    
    if (!validInput){
        System.out.println("u have to enter W, A, S, or D");
        continue;
    }
    
    if (dungeon.isValidMove(newRow,newCol,player)){
        String tile=dungeon.getTile(newRow,newCol);
        dungeon.setTile(player.getRow(),player.getCol(),"⬜");
        player.move(newRow,newCol);
        timer.decrease();
        dungeon.checkTile(player,timer);
        dungeon.setTile(player.getRow(),player.getCol(),"🧍");
        if (tile.equals("🏁")){
            System.out.println("u escaped the dungeon. congrats.");
            playing=false;
        }
       
        if (timer.isDone()&&playing){
            System.out.println("becuase the time went down to 0, you ran out of moves. you lost");
            playing=false;
        }
        
        if (player.getHealth()<=0&&playing){
            System.out.println("you ran out of health so u lost");
            playing=false;
        }
    }
    else{
        System.out.println("u can't move there");
    }
    
    
    // for (int row=0;row<board.length;row++){
    //     for (int col=0;col<board[row].length;col++){
    //         System.out.print(board[row][col]+" ");
            
    //     }
    //     System.out.println();
    // }

}}
}