package asteroids;

/*
CLASS: Asteroids

DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 Game Features
 * Has an introduction and results in the end of the game
 * 3 lives are given
 * Speed increases 3x when Space is pressed
 * Score increases 3x faster when Space is pressed
 * Ship changes shape when Space is pressed
 * Scores and High Scores are saved and compared
 * Game Resets automatically after game is over
 */

import java.awt.*;

class Asteroids extends Game {
	//Start= To indicate that the game has started
	static int Start = 0;
	//Score= To record the Score
	static int Score = 0;
	//HighScore= To record the Highest Score
	static int HighScore=0;
	//To assign lives(3 lives are assigned)
	Ship[] ships = Ship.lives(4);
	int life = 0;
	//Creates Asteroids for levels
	AsteroidCollison[] Easy = AsteroidCollison.Asteroids(5);
	AsteroidCollison[] Intermediate = AsteroidCollison.Asteroids(10);
	AsteroidCollison[] Hard = AsteroidCollison.Asteroids(15);
	AsteroidCollison[] MegaLevel = AsteroidCollison.Asteroids(20);





	public Asteroids() {
		//Adding Keylistener to all the ships
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		for(int i = 0; i < ships.length; i++){
			this.addKeyListener(ships[i]);
		}
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		brush.setColor(Color.white);

		//The game has an introduction and Starts once you press space
		if(Start < 1){
			Font myFont = new Font ("Courier New", 1, 15);
			brush.setFont (myFont);
			brush.setColor(Color.blue);
			brush.drawString("IN A GALAXY FAR FAR AWAY....",20, 270);
			brush.drawString("AFTER TWO YEARS OF TRAVELLING... YOU'RE ONE STEP AWAY FROM REACHING BACK TO EARTH",50, 290);
			brush.drawString("BUT OH WAIT...WHAT ARE THOSE?!?!?!?",250, 310);
			brush.drawString("Press enter to continue",275, 350);
			if(ships[0].enterKey){
				brush.setColor(Color.white);
				Start = 1;
			}
		}
		//counter starts along with the paint of ship and asteroids
		else{
			//as long as you have a life, keep on playing
			if(life+1 < ships.length ){
				
				//Score increases 3x if you press space, since the speed is increasing 3x too
				if(ships[life].space){
					Score = Score + 3;
					if(ships[life].WKey||ships[life].UpKey){
						//NewPosition is called if the player presses either W or Up key
						ships[life].move();
						ships[life].NewPos.paint(brush);
					} 

					else{
						ships[life].move();
						ships[life].NewPos.paint(brush);
					}
				}
				
				else{
					ships[life].move();
					ships[life].paint(brush);
					Score++;
				}
				brush.drawString("Score:" + Score,10,20);
				brush.drawString("lives " + (ships.length - life-1
						),10,40);
				//Levels of Game
				if(Score < 2500){
					brush.drawString("Level: 1(EASY)",10,60);
					//5 asteroids are part of Level 1
					for(int i = 0; i < Easy.length;i++){
						Easy[i].paint(brush);
						Easy[i].move();
						if(ships[life].intersection(Easy[i])){
							life++;
						}
					}
				}
				//Intermediate Level
				else if(Score >2500&& Score<6000){
					brush.drawString("Level: Intermediate ",10,60);
					for(int i = 0; i < Intermediate.length;i++){
						if(ships[life].intersection(Intermediate[i])){
							life++;
						}
						Intermediate[i].paint(brush);
						Intermediate[i].move();
					}
				}
				//Hard Level
				else if(Score>6000&&Score<10000){
					brush.drawString("Level: Hard",10,60);
					for(int i = 0; i < Hard.length;i++){
						if(ships[life].intersection(Hard[i])){
							life++;
						}
						Hard[i].paint(brush);
						Hard[i].move();
					}
				}
				//MEGA LEVEL 
				else{
					for(int i = 0; i < MegaLevel.length;i++){
						brush.drawString("Level: MegaLevel",10,60);
						if(ships[life].intersection(MegaLevel[i])){
							life++;
						}
						MegaLevel[i].paint(brush);
						MegaLevel[i].move();
					}
				}
			}

			else{
				brush.setColor(Color.white);
				Font myFont = new Font ("Courier New", 1, 20);
				brush.setFont (myFont);
				brush.drawString("GAME",360, 260);
				brush.drawString("OVER",360, 280);
				brush.drawString("Score:" + Score,320, 300);
				if(Score > HighScore){
					brush.drawString("New High Score:" + Score,320, 320);
				}
				else{
					brush.drawString("High Score:" + HighScore,250,360);
				}
				brush.drawString("Press ENTER to start again" ,290, 340);
				//To reset the Game
				if(ships[0].enterKey){
					HighScore = Score;
					life = 0;
					Score = 0;
					Start = 0;
					for(int i = 0; i < ships.length; i++){
						ships[i].reset();
					}

					for(int j = 0; j < Easy.length; j++){
						Easy[j].reset();
					}
				}
				
				}
			}
		}
	



	public static void main (String[] args) {
		Asteroids NewGame = new Asteroids();
		NewGame.repaint();



	}
}