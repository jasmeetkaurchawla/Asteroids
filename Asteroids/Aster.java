package asteroids;

import java.util.Random;

public class Aster extends Polygon{
	

	public Aster(Point pos) {
		super(astShape(),pos, setRot(pos));
	}
	public void reset(){
		this.position = Aster.pos();
		this.rotation = setRot(this.position);
	}
	//creates a random asteroid
	public static Point[] astShape(){
		Random random = new Random();
		Point[] randAsteroid = {
				new Point(0,0),
				new Point(random.nextInt(11-5)+5, -(random.nextInt(16-10)+10)),
				new Point(random.nextInt(21-15)+15, -(random.nextInt(5))),
				new Point(random.nextInt(31-25)+25, (random.nextInt(5-(-10)-10))),
				new Point(random.nextInt(21-15)+15, (random.nextInt(21-15)+15)),
				new Point(random.nextInt(0-(-5))-5, (random.nextInt(15-10)+10))
		};
		return randAsteroid;
	}
	
	//still working on this method to put asteroids in random places
	public static  Point pos(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		//acts as a 4 sided coin flip
		//this is to decide which side the asteroid will appear
		int pos = random.nextInt(4);
		//west
		if(pos == 0){
			x = 0;
			y = random.nextInt(601);
		}
		//north
		else if(pos == 1){
			x = random.nextInt(801);
			y = 0;
		}
		//east
		else if(pos == 2){
			x = 800;
			y = random.nextInt(601);
		}
		//
		else if(pos == 3){
			x = random.nextInt(801);
			y = 600;
		}
		Point p = new Point(x,y);
		return p;
	}
	
	//method: rotation is set depending on the position
	public static  double setRot(Point position){
		Random random = new Random();
		//the canvas gets divided into 4 parts
		//west side
		if(position.x < 400){
			//north west
			if(position.y < 300){
				return random.nextInt(61-30) + 30;
			}
			//south west
			else{
				return random.nextInt(331-300) + 300;
			}
		}
		//east side
		else{
			//north east
			if(position.y < 300){
				return random.nextInt(151-120) + 120;
			}
			//south east
			else{
				return random.nextInt(241-210) + 210;
			}
		}
	}
	//method for creating array of asteroids
	public static Aster[] arrAst(int n){
		Aster[] arrAst = new Aster[n];
		for(int i = 0; i < n; i++){
			arrAst[i] = new Aster(Aster.pos());
		}
		return arrAst;
	}
	
	
	public void move(){
		double prevPosX = position.x;
		double prevPosY = position.y;
		
		position = new Point(position.x + (2*Math.cos(Math.toRadians(rotation))),
				position.y + (2* Math.sin(Math.toRadians(rotation))));
		if(position.x > 800 && prevPosX < position.x){
			position = new Point(-10,position.y);
		}
		else if(position.x < 0 && prevPosX > position.x){
			position = new Point(810,position.y);
		}
		if(position.y > 600 && prevPosY < position.y){
			position = new Point(position.x, -10);
		}
		else if(position.y < 0 && prevPosY > position.y){
			position = new Point(position.x, 610);
		}
	}

}
