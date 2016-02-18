
public class Runner {
	
	
public static void main(String[] args)
	{
		//There will be a bookkeeper. It keeps track of all objects found on the table.
		Bookkeeper bookkeeper;
		bookkeeper = new Bookkeeper();
		
		
		//This is where OpenCV and all image recognition goes.
		ObjectRecogniser or;
		or = (ObjectRecogniser) new OpenCVColourRecogniser();
		or.Initialise();
		
		
		//An class to represent our robot
		CRIGRobot robot;
		robot = new CRIGRobot();
		bookkeeper.Add(robot);
		
		
		//This is where all the decisions are made.
		Mastermind controller;
		controller = new Mastermind(robot, Integer.parseInt("0"));

		//Mainloop. This keep going as long as the game is running.
		while(controller.IsReady() || controller.IsMatchOngoing())
		{	
			if(controller.IsReady())
			{
				System.out.println("Ready!");
				controller.CheckReadyTime(); //a silly autotrigger for the match.
			}
			//Get all objects in image.
			EuroBotObject[] foundObjects = or.GetObjects();
		
			//Insert new objects in bookkeeper's list. 			
			for(int i = 0; i<foundObjects.length; i++)
			{
				bookkeeper.AddIfNew(foundObjects[i]);
			}
			
			//Controller needs to decide where to go based on where things are.
			int[] newXYPos = controller.DecideWhereToGo(bookkeeper.GetAllObjects());
			
			//If the match has started, tell robot to move.
			if(controller.IsMatchOngoing())
			{
				System.out.println("Game on!");
				robot.Go(newXYPos[0], newXYPos[1]);
				controller.CheckStartTime();
			}


			
		}
		
		
		//Disposing of everything after game
		or.Dispose();
		
		System.out.println("Finished!");
	}
	


}
