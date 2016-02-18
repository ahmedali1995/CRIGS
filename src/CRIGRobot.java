
public class CRIGRobot extends EuroBotObject {
	public Mastermind controller;
	
	public enum RobotState {
		Idle, Running, Stopped
	}
	RobotState state;
	

	
	public CRIGRobot()
	{
		this.name = "CRIGRobot";
		this.is_enemy = false;
		this.is_fixed = false;

		initialiseRobot();

	}

	private void initialiseRobot()
	{
		state = RobotState.Idle;
	}
	
	public void Go(int xPos, int yPos)
	{
		//Ignore command if robot is busy... will have to revisit later.
		if(state == RobotState.Idle)
		{
			//Insert code for moving towards a position here.
		}
	}
	
	
	//This is for when the start trigger comes from pulling on a string on robot. Unused at the moment.
	public void StartMatch()
	{
		controller.StartMatch();
	}


	
}
