package src;

public class Mastermind {

	long startTime;
	long readyTime; //Hopefully we'll replace readytime with a real trigger
	CRIGRobot robot;
	public enum TeamColour {
		Red, Blue
	}
	enum Match_Status {Ready, OnGoing, Finished } ;
	public Match_Status status;
	TeamColour colour;
	
	public Mastermind(CRIGRobot robot, int teamInt)
	{
		//Tell mastermind that this is the robot to control
		this.robot = robot;
		
		//Tell robot that this is the mastermind to obey.
		robot.controller = this;
		
		//This is to tell which side of the table we'll be starting on... (maybe useless?)
		if(teamInt == 0)
		{
			colour = TeamColour.Blue;
		}
		else
		{
			colour = TeamColour.Red;
		}
		
		//Temporary game trigger
		readyTime = System.currentTimeMillis();
		
		status = Match_Status.Ready;
	}
	
	//Status checks for main runner loop
	public boolean IsReady()
	{
		if(status == Match_Status.Ready)
		{
			return true;
		}
		else {return false;}
	}	
	public boolean IsMatchOngoing()
	{
		if(status == Match_Status.OnGoing)
		{
			return true;
		}
		else { return false;}
	}
	
	//This is when the trigger arrives from robot to say game has started.
	public void StartMatch()
	{
		//Measure time at start of match. This is to make it stop in 90s.
		startTime = System.currentTimeMillis();
		
		//Start controlling the Robot that it's time to go!
		status = Match_Status.OnGoing;
	}
	
	//Loads of stuff needs to go here.
	public int[] DecideWhereToGo(EuroBotObject[] all_things)
	{
		int[] newXYPos = new int[] {0, 0};
		return newXYPos;
	}
	
	//This is to check if gametime is up.
	public void CheckStartTime()
	{
		 if((System.currentTimeMillis()-startTime)<5000)
		 {
			 
		 } else { status = Match_Status.Finished; }
	}
	//Bogus autotrigger. Remove for final version.
	public void CheckReadyTime()
	{
		 if((System.currentTimeMillis()-readyTime)>1000)
		 {
			 StartMatch();
		 } 
	}
}
