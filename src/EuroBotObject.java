
public class EuroBotObject {

	/* A class for objects that are on the table during the game
	 x/y is the short/long axis of the table (Euclidian coordinates from the perspective of a robot in the starting position) 
	 positions are in mm.
	 */
	String name;
	int xPos, yPos;
	boolean is_fixed, is_enemy;
	
	public EuroBotObject()
	{
		xPos = 0;
		yPos = 0;
		is_fixed = true;
		is_enemy = false;
		name = "dummy";
	}
	
	public EuroBotObject(int xPos, int yPos, boolean is_fixed, boolean is_enemy, String name)
	{
		this.xPos = 0;
		this.yPos = 0;
		this.is_fixed = true;
		this.is_enemy = false;
		this.name = "dummy";
	}
	
	public boolean IsSameAs(EuroBotObject otherObject)
	{
		//For the moment, consider that they are the same if they are in same place.
		if(this.xPos == otherObject.xPos && this.yPos == otherObject.yPos)
		{
			return true;
		}
		else {return false;}
	}

}
