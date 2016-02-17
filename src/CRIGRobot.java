
public class CRIGRobot extends EuroBotObject {
	
	public enum RobotState {
		Starting, Idle, Running, Finishing, Stopped
	}
	RobotState state;
	
	public enum TeamColour {
		Red, Blue
	}
	TeamColour colour;
	
	public CRIGRobot(int teamInt)
	{
		this.is_enemy = false;
		this.is_fixed = false;
		if(teamInt == 0)
		{
			colour = TeamColour.Blue;
		}
		else
		{
			colour = TeamColour.Red;
		}

		initialiseRobot(colour);

	}

	private void initialiseRobot(TeamColour colour)
	{
		state = RobotState.Starting;
		
		state = RobotState.Idle;
	}
	
	public void Go(int xPos, int yPos)
	{
		//Insert code for moving towards a position here.
	}
	
	
	
}
