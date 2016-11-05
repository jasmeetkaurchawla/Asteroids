package asteroids;

//import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Ship extends Polygon implements KeyListener {
	static int Rotation = 0;
	static int speed = 0;
	boolean shift = false;
	boolean space = false;
	boolean WKey = false;
	boolean DKey = false;
	boolean AKey = false;
	boolean UpKey = false;
	boolean leftKey = false;
	boolean rightKey = false;
	boolean enterKey = false;
	boolean otherKey = false;
	static Point[] ship = { new Point(30,15), new Point(0,30),new Point(7.5,15),new Point(0,0),new Point(30,15)};
	static Point[] Speed3X = {new Point(30,15),new Point(-10,30),
			new Point(0,22.5),new Point(-10,15),new Point(0,7.5),
			new Point(-10,0),new Point(30,15)};
	


	static Point Position = new Point(390, 290);
	static boolean prevPos = false;
	static int gravity = 0;
	Polygon NewPos = new Polygon(Speed3X, new Point(Position.x -15,Position.y), Rotation);


	public Ship() {
		super(ship, Position, Rotation);
	}


	public static Ship[] lives(int n) {
		Ship[] output = new Ship[n];
		for (int i = 0; i < n; i++) {
			output[i] = new Ship();
		}
		return output;
	}

	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		switch (id) {
		case 16:
			shift= true;
		case 65:
			AKey = true;
			break;
		case 87:
			WKey=true;
			break;
		case 68:
			DKey=true;
		case 32:
			space = true;
			break;
		case 37:
			leftKey = true;
			break;
		case 38:
			UpKey = true;
			break;
		case 39:
			rightKey = true;
			break;
		case 10:
			enterKey = true;
			break;
		
	
		default:
			otherKey = true;

		}

	}

	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
		switch (id) {
		case 16:
			shift=false;
		case 65:
			AKey = false;
			break;
		case 87:
			WKey=false;
			break;
		case 68:
			DKey=false;
		case 32:
			space = false;
			break;
		case 37:
			leftKey = false;
			break;
		case 38:
			UpKey = false;
			break;
		case 39:
			rightKey = false;
			break;
		case 10:
			enterKey = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void move() {
		// added to track which direction it is going
		double prevPosX = position.x;
		double prevPosY = position.y;
		int speed = 0;
		if (WKey||UpKey) {
			if (shift){
				speed = 5;
			}
			if (space) {
				speed = 3;
			} else {
				speed = 2;
			}
			position = new Point(position.x
					+ (speed * Math.cos(Math.toRadians(rotation))), position.y
					+ (speed * Math.sin(Math.toRadians(rotation))));
			NewPos.position = position;
							
			if (position.x > 800 && prevPosX < position.x) {
				position = new Point(-10, position.y);
			} else if (position.x < 0 && prevPosX > position.x) {
				position = new Point(810, position.y);
			}
			if (position.y > 600 && prevPosY < position.y) {
				position = new Point(position.x, -10);
			} else if (position.y < 0 && prevPosY > position.y) {
				position = new Point(position.x, 610);
			}

		}
	
		if (leftKey||DKey) {
			rotation = rotation - 3;
			NewPos.rotation = rotation;
		}
		if (rightKey||AKey) {
			rotation = rotation + 3;
			NewPos.rotation = rotation;
		}
	}
	public void reset() {
		this.rotation = 0;
		this.position = new Point(390, 290);
		this.NewPos = new Polygon(Speed3X, new Point(Position.x -15,Position.y), Rotation);
	}

}