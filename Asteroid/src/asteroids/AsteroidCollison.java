package asteroids;

import java.util.Random;

public class AsteroidCollison extends Polygon{
	

	public AsteroidCollison(Point pos) {
		super(asteroidShape(),pos, Rotation(pos));
	}
	public void reset(){
		this.position = AsteroidCollison.Asterposition();
		this.rotation = Rotation(this.position);
	}
	//creates a random asteroid
	public static Point[] asteroidShape(){
		Point[] RandomAst= {
				new Point(36,25),
			    new Point(51,25),
			    new Point(75,36),
			    new Point(75,51),
			    new Point(51,75),
			    new Point(36,75),
			    new Point(25,51),
			    new Point(25,36),
			    new Point(36,25)
		};
		return RandomAst;
	}
	
	public static  Point Asterposition(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		int pos = random.nextInt(10);
		x=random.nextInt(600);
		y=random.nextInt(800);
		
		Point p = new Point(x,y);
		return p;
	}
		

	public static  double Rotation(Point position){
		Random random = new Random();
		if(position.x > 400){
			if(position.y > 300){
				return random.nextInt(70-35) + 30;
			}
			else{
				return random.nextInt(340-290) + 300;
			}
		}
		else{
			if(position.y < 300){
				return random.nextInt(140-110) + 120;
			}
			else{
				return random.nextInt(260-200) + 210;
			}
		}
	}
	//method for creating array of asteroids
	public static AsteroidCollison[] Asteroids(int n){
		AsteroidCollison[] Aster = new AsteroidCollison[n];
		for(int i = 0; i < n; i++){
			Aster[i] = new AsteroidCollison(AsteroidCollison.Asterposition());
		}
		return Aster;
	}
	
	
		

}
