import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
public class MyProgram
{
    public static void main(String[] args)
    {
        try {
            System.out.println(Files.readString(Path.of("instruction.txt")));
        }
        catch (IOException e) {
            System.err.println("Could not read instruction.txt. Run the game from the project directory.");
        }
        Player player;
        Dungeon dungeon=new Dungeon();
        Timer timer=new Timer(25);
        Scanner input=new Scanner(System.in);
        System.out.println("how do you want to play?");
        System.out.println("human player");
        System.out.println("computer player");
        
        /* COMPUTER PLAYER - DIFFICULTY
         * The human chooses the player before the turn loop: exact input "2"
         * opens the easy/hard menu and constructs a ComputerPlayer at (1,1).
         * Any other player-choice string creates a human Player. Difficulty
         * input is parsed as an integer; nonnumeric input is not handled here.
         * The selected number is passed to ComputerPlayer's strategy dispatcher.
         */
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
    /* COMPUTER PLAYER - EXECUTION
     * Runs once per loop iteration after displaying the board and move budget,
     * before converting a direction into coordinates or applying tile effects.
     * The runtime player type determines whose turn-input path is used.
     * Printing "Computer chose:" makes autonomous execution visible in play.
     */
    if (player instanceof ComputerPlayer){
        ComputerPlayer computer=(ComputerPlayer) player;
        /* COMPUTER PLAYER - MOVES
         * Requests a W/S/A/D direction from the selected computer strategy.
         * No Scanner read occurs on this path: after initial menu selection,
         * the computer supplies each move without further human input.
         * The else branch below reads a direction only for a human player.
         */
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
    
    /* COMPUTER PLAYER - MOVES
     * Human and computer directions share the same movement rules. After
     * W/S/A/D is converted to a candidate coordinate, Dungeon checks legality.
     * A legal move clears the old tile, updates position, spends one move,
     * applies the destination effect, then redraws the player marker.
     * Invalid directions or blocked destinations leave the move budget intact.
     */
    if (dungeon.isValidMove(newRow,newCol,player)){
        String tile=dungeon.getTile(newRow,newCol);
        dungeon.setTile(player.getRow(),player.getCol(),"⬜");
        player.move(newRow,newCol);
        timer.decrease();
        dungeon.checkTile(player,timer);
        dungeon.setTile(player.getRow(),player.getCol(),"🧍");
        /* COMPUTER PLAYER - EXECUTION
         * End conditions follow the completed move for either player type.
         * The saved destination tile detects escape even after it is replaced
         * by the player marker. Escape takes priority; otherwise an exhausted
         * timer is checked before depleted health. Setting playing=false stops
         * further computer decisions and returns control out of the loop.
         */
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
