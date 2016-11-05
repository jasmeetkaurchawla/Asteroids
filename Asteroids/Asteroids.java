package asteroids;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
*/
/**Game Features
 * 1.Speed change when space bar is pressed
 * 2.Score increases faster when space bar is pressed
 * 3.Added levels, levels increase amount of asteroids displayed. 
 * Levels change when score reaches certain number
 * 4.reset game after game over.
 * 5.added high score... might add txt file to keep old scores 
 * 6. when increased speed changes to show that its going faster.
 * All other features coded by Josue Rojas
 */
import java.awt.*;
import java.awt.event.*;

class Asteroids extends Game {
	static int Start = 0;
	static int Score = 0;
	static int TopScore=0;
	static int rot = 0;
	//you have 3 lives, this will go from 0-2, meaning 0 then 1 then 2
	//still trying to fix the lives. so far it gives me 2 lives
	Ship[] ships = Ship.lives(4);
	int live = 0;
	Aster[] asArr = Aster.arrAst(1000);


	

  public Asteroids() {
    super("Asteroids!",800,600);
    this.setFocusable(true);
	this.requestFocus();
	//add keyListener to all ships
	for(int i = 0; i < ships.length; i++){
		this.addKeyListener(ships[i]);
	}
	//this.addKeyListener(thrust);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	brush.setColor(Color.white);
    	
    	//start the game by pressing down
    	if(Start < 1){
    		brush.drawString("ASTEROIDS!!!!",360, 270);
    		brush.drawString("Avoid collision...",350, 290);
    		brush.drawString("Press down key to begin",330, 310);
    		if(ships[0].downKey){
    			Start = 1;
    		}
 	
    	}
    	//counter starts along with the paint of ship and asteroids
    	else{
    		//as long as you have lives, keep on playing
    		if(live + 1 < ships.length ){
    			//since it is dangerous traveling super fast, i increased the points.
    			if(ships[live].space){
    				Score = Score + 5;
    				if(ships[live].upKey){
    					ships[live].move();
    					ships[live].thrust.paint(brush);
    				}
    				else{
    					ships[live].move();
            			ships[live].paint(brush);
    				}
    			}
    			else{
    				ships[live].move();
        			ships[live].paint(brush);
    				Score++;
    			}
    			brush.drawString("Score:" + Score,10,20);
    			brush.drawString("Lives " + (ships.length - live-1),10,40);
    			//loop for showing asteroids
    			//start with 5 asteroids
    			if(Score < 2501){
    				brush.drawString("Level: 1",10,60);
    				for(int i = 0; i < 5;i++){
    					asArr[i].paint(brush);
    					asArr[i].move();
    					if(ships[live].intersection(asArr[i])){
    						live++;
    					}
        			}
    			}
    			//increase asteroids until it reaches max
    		else if(Score < 00000 && Score > 2501){
    			brush.drawString("Level: " + (Score/1000) ,10,60);
    			for(int i = 0; i < Score/500;i++){
    				if(ships[live].intersection(asArr[i])){
						live++;
					}
        			asArr[i].paint(brush);
        			asArr[i].move();
        			}
    		}
    		//max asteroids displayed
    		else{
    			for(int i = 0; i < asArr.length;i++){
    				brush.drawString("Level: Impossible",10,60);
    				if(ships[live].intersection(asArr[i])){
						live++;
					}
    				asArr[i].paint(brush);
    				asArr[i].move();
    				}
    		}
    	}
    		else{
    			brush.drawString("GAME",360, 260);
        		brush.drawString("OVER",360, 280);
        		brush.drawString("Score:" + Score,350, 300);
        		brush.drawString("Press any key to start again" ,300, 340);
        		if(Score > TopScore){
        			brush.drawString("New High Score:" + Score,330, 320);
        		}
        		else{
        			brush.drawString("High Score:" + TopScore,330, 320);
        		}
        		//reset game
        		if(ships[0].otherKey){
        			TopScore = Score;
        			live = 0;
        			Score = 0;
        			Start = 0;
        			for(int i = 0; i < ships.length; i++){
        				ships[i].reset();
        			}
        			
        			for(int j = 0; j < asArr.length; j++){
        				asArr[j].reset();
        			}
        			
    
        		}
        		
        		
    		}
    	}
    }
 
  
	public static void main (String[] args) {
   		Asteroids a = new Asteroids();
		a.repaint();
		
		
		
  }
}