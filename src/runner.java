import java.util.*;

public class runner {

	
public static void main(String[] args)
	{
		//This is to keep track of things on the table
		List<EuroBotObject> all_things = new ArrayList<EuroBotObject>();
		
		//Initialise the Robot
		CRIGRobot robot = new CRIGRobot(Integer.parseInt(/*args[0]*/ "0"));
		all_things.add(robot);
		
		//Initialise object recognition
		ObjectRecogniser or = (ObjectRecogniser) new OpenCVColourRecogniser();
		or.Initialise();
		
		//Get all images in file
		EuroBotObject[] foundObjects = or.GetObjects();
		
		//Insert them in list of objects. 
		//LATER: might need to find a way to determine whether an object is already listed.
		for(int i = 0; i<foundObjects.length; i++)
		{
			all_things.add(foundObjects[i]);
		}
		
		int[] newXYPos = DecideWhereToGo(all_things);
		
		robot.Go(newXYPos[0], newXYPos[1]);
		
		System.out.println("Finished!");
	}
	
	static int[] DecideWhereToGo(List<EuroBotObject> all_things)
	{
		int[] newXYPos = new int[] {0, 0};
		return newXYPos;
	}
}
