
package javafxapplication2;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    /**
     * Plays a text-based game
     * @param args
     */
    
    public static void main(String[] args) {
       int[][] box = new int[10][10];
       int x;
       int y;
       int level;
       int trap;
       int prize;
       boolean gameOver;
       int stamina;
       char move;
       String condition;
       int score;
       Scanner scanner = new Scanner(System.in);
       
       score = 0;
       x = 9;
       y = 9;
       level = 1;
       trap = level + 4; // if (trap > 1 + prize level -> abourt increament
       prize = 10;
       stamina = 30;
       gameOver = false;
       
       
       //setting frame 0 as empty
       setBox(box);
       //setting prize as 1
       setPrize(box,prize);
       //setting traps as 2
       setTrap(box,trap);
       //setting person as 9
       box[x][y] = 9;
       
       System.out.print("\nPress key up left right down to play   q to quit\nPress p to start\n");
       scanner.next().charAt(0);
       while(!gameOver){
           displayBox(box);
           System.out.print("\nYour Score: " + score + " |||||    Stamine: " + stamina + "\n");
            
            move = scanner.next().charAt(0);
            if(move == 'q'){
                gameOver = true;
                continue;
            }
            condition = movePlayer(box,move,x,y);
            System.out.print(condition);
            if(condition.contains("dead")){
                gameOver = true;
                continue;
            }
            if(condition.contains("fail")) {
                continue;
            }
            if(condition.contains("prize")){
                score = score + 1;
                stamina = stamina -1; 
            }
            if(condition.contains("move")){
                stamina = stamina -1; 
            }
            x = positionX(condition) + x;
            y = positionY(condition) + y;
            
            
            
        }
       System.out.print("\n your score " + score);
        
        
        
    
    }
    public static int random(){
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        
        return randomNumber;
    }
    public static int frame(){
       return 0; 
    }
    public static void setBox(int[][] box){
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
            box[i][j]= 0;
            System.out.print(box[i][j]);
            System.out.print(" ");

            }
            System.out.print("\n");

        }
    }
    public static void displayBox(int[][] box){
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
            
                switch (box[i][j]) {
                    case 0 -> System.out.print(" _ ");
                    case 1 -> System.out.print(" P ");
                    case 2 -> System.out.print(" T ");
                    case 9 -> System.out.print(" X ");
                }
            
            
            }
            System.out.print("\n");
        }
    } 
    public static void setTrap(int[][] box, int trap){
        for( int i = 0 ; i < trap ; i++){
            int x = random();
            int y = random();
            if(box[x][y] == 0 ){
                box[x][y] = 2;
            }
            else{
                //decreasing loop for recall
                i--;
            }
        }
    }
    public static void setPrize(int[][] box,int prize){
       for( int i = 0 ; i < prize ; i++){
            int x = random();
            int y = random();
            if(box[x][y] == 0 ){
                box[x][y] = 1;
            }
            else{
                //decreasing loop for recall
                i--;
            }
        } 
    }
    public static String movePlayer(int[][] box,char move,int x , int y){
        System.out.print("move: "+move + " " + "x: " + x + " y : " + y);
        switch (move) {
                    case 'a' -> {
                        
                        if( y <= 0){
                            return "fail";
                        }
                        else{
                            if(box[x][y-1] == 2){
                                return "dead";
                            }
                            if(box[x][y-1] == 1){
                                box[x][y] = 0;
                                box[x][y-1] = 9;
                                return "prize left";
                            }
                            box[x][y] = 0;
                            box[x][y-1] = 9;
                            return "left move";
                        }   
                    }
                    case 'd' -> {
                        if( y >= 9){
                            return "fail";
                        }
                        else{
                            if(box[x][y+1] == 2){
                                return "dead";
                            }
                            if(box[x][y+1] == 1){
                                box[x][y] = 0;
                                box[x][y+1] = 9;
                                return "prize right";
                            }
                            box[x][y] = 0;
                            box[x][y+1] = 9;
                            return "right move";
                        }   
                    }
                    case 's' -> {
                        if( x >=  9){
                            return "fail";
                        }
                        else{
                            if(box[x+1][y] == 2){
                                return "dead";
                            }
                            if(box[x+1][y] == 1){
                                box[x][y] = 0;
                                box[x + 1][y] = 9;
                                return "prize down";
                            }
                            box[x][y] = 0;
                            box[x + 1][y] = 9;
                            return "down move";
                        }   
                    }
                    
                    case 'w' -> {
                        if( x <= 0){
                            return "fail";
                        }
                        
                        else{
                            if(box[x-1][y] == 2){
                                return "dead";
                            }
                            if(box[x][y] == 1){
                                box[x][y] = 0;
                                box[x-1][y] = 9;
                                return "prize up";
                            }
                            box[x][y] = 0;
                            box[x-1][y] = 9;
                            return "up move";
                        }   
                    }
                    
                    
                    
                }
        return "switch";
    }
    public static int positionX(String condition){
        if(condition.contains("up")){
            return -1;
            
        }
        if(condition.contains("down")){
            return 1;
            
        }
       
        return 0;
    }
    public static int positionY(String condition){
        if(condition.contains("left")){
            return -1;
            
        }
        if(condition.contains("right")){
            return 1;
            
        }
       
        return 0;
    }
    
    
    
}




