
public class EuroBotObject {

	/* A class for objects that are on the table during the game
	 x/y is the short/long axis of the table (Euclidian coordinates from the perspective of a robot in the starting position) 
	 positions are in mm.
	 */
	int xPos, yPos;
	boolean is_fixed, is_enemy;
	
	public EuroBotObject()
	{
		xPos = 0;
		yPos = 0;
		is_fixed = true;
		is_enemy = false;
	}

}
