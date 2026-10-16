import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

    //human player chooses between 2 computer difficulty lvls
    //difficulty 1: easy and uses random mvmt
    //difficulty 2: considers computer player's situation 
    public class ComputerPlayer extends Player{

        private int difficulty;
        private Random random;

    
    
    public ComputerPlayer(int startRow, int startCol, int difficulty){
        super(startRow,startCol);
        this.difficulty=difficulty;
        random=new Random();
    }
    //computer randomly chooses one of the 4 mvmt directions: wasd. makes the computer less strategic
    public String chooseRandomMove(Dungeon dungeon){
        ArrayList<String> validMoves=new ArrayList<>();
        if(dungeon.isValidMove(getRow()-1,getCol(),this)){
            validMoves.add("W");
        }
        
        if (dungeon.isValidMove(getRow()+1,getCol(),this)){
            validMoves.add("S");
        }
        
        if(dungeon.isValidMove(getRow(),getCol()-1,this)){
            validMoves.add("A");
        }
        
        if (dungeon.isValidMove(getRow(),getCol()+1,this)){
            validMoves.add("D");
        }
        
        return validMoves.get(random.nextInt(validMoves.size()));
    }
    
    
    public String chooseMove(Dungeon dungeon){
        if (difficulty==1){
            return chooseRandomMove(dungeon);
    }
    return chooseStrategicMove(dungeon);
    
}
        
    public String chooseStrategicMove(Dungeon dungeon){
        int targetRow;
        int targetCol;
        if (!hasRedKey()){
            int[] keyLocation=dungeon.findTile("🔴");
            targetRow=keyLocation[0];
            targetCol=keyLocation[1];
        }
        
        else if (!hasPassedRedDoor()){
            int[] doorLocation=dungeon.findTile("🟥");
            targetRow=doorLocation[0];
            targetCol=doorLocation[1];
            
        }
        
        else if (!hasBlueKey()){
            int[] keyLocation=dungeon.findTile("🔵");
            targetRow=keyLocation[0];
            targetCol=keyLocation[1];
            
            
        }
        
        else if (!hasPassedBlueDoor()){
            int[] doorLocation=dungeon.findTile("🟦");
            targetRow=doorLocation[0];
            targetCol=doorLocation[1];
        }
        else{
            targetRow=17;
            targetCol=18;
        }
        int currentRow=getRow();
        int currentCol=getCol();
        
        String[] moves={"W","S","A","D"};
        int[] rowChanges={-1,1,0,0};
        int[] colChanges={0,0,-1,1};
        
        int bestDistance=Integer.MAX_VALUE;
        String bestMove=null;
        for (int i=0;i<moves.length;i++){
            int newRow=currentRow+rowChanges[i];
            int newCol=currentCol+colChanges[i];
            
            
            if (dungeon.isValidMove(newRow,newCol,this)){
                int distance=Math.abs(targetRow-newRow)+Math.abs(targetCol-newCol);
                
                if (distance<bestDistance){
                    bestDistance=distance;
                    bestMove=moves[i];
                }
            }
        }
        if (bestMove==null){
            return chooseRandomMove(dungeon);
        }
        return bestMove;
    }
    
    public String findPath(Dungeon dungeon,int targetRow,int targetCol){
        int startRow=getRow();
        int startCol=getCol();
        Queue<int[]> queue=new LinkedList<>();
        ArrayList<String> paths=new ArrayList<>();
        queue.add(new int[]{startRow,startCol});
        paths.add("");
        boolean[][] visited=new boolean[20][20];
        visited[startRow][startCol]=true;
        
        String[] moves={"W","S","A","D"};
        int[] rowChanges={-1,1,0,0};
        int[] colChanges={0,0,-1,1};
        
        while (!queue.isEmpty()){
            int[] current=queue.remove();
            String currentPath=paths.remove(0);
            int currentRow=current[0];
            int currentCol=current[1];
            if (currentRow==targetRow&&currentCol==targetCol){
                if (currentPath.length()>0){
                    return currentPath.substring(0,1);
                }
                return null;
            }
            for (int i=0;i<moves.length;i++){
                int newRow=currentRow+rowChanges[i];
                int newCol=currentCol+colChanges[i];
                
                if (newRow>=0&&newRow<20&&newCol>=0&&newCol<20&&!visited[newRow][newCol]&&dungeon.isValidMove(newRow,newCol,this)){
                    visited[newRow][newCol]=true;
                    queue.add(new int[]{newRow,newCol});
                    paths.add(currentPath+moves[i]);
                }
            }
        }
        return null;
    }
    }