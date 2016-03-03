package src;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public  class Runner {
	
	
public static void main(String[] args)
	{
	    //pour l'ouverture de l'ecran
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // load native library of opencv
			JFrame jframe = new JFrame("Dection d'objet en couleur");
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel vidpanel = new JLabel();
			jframe.setContentPane(vidpanel);
			jframe.setSize(640, 480);
			jframe.setVisible(true); 
			
	    //variable pour le camera et ouverture du camera
			Mat frame= new Mat();	
			VideoCapture capture = null;
		    capture =new VideoCapture(0);
		     
		      
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
			//lire les image 
			capture.read(frame);		//recuperation de flux de video pendant le macth
			if(controller.IsReady())
			{
				System.out.println("Ready!");
				controller.CheckReadyTime(); //a silly autotrigger for the match.
			}
			
			//Do image recognition
			or.DoImageRecognition(frame);
			
			//Update robot's position.
			int[] newRobotPosition = or.GetUpdatedRobotPosition();
			bookkeeper.GetObjectByName("CRIGRobot").xPos = newRobotPosition[0];
			bookkeeper.GetObjectByName("CRIGRobot").yPos = newRobotPosition[1];
			
			//Get all objects found in image.
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
			
			//affichage de chaque image
			   ImageIcon image = new ImageIcon(or.Mat2bufferedImage(frame));
		       vidpanel.setIcon(image);
		       vidpanel.repaint(); 
			
		}
		
		//Disposing of everything after game
		or.Dispose();
	
		System.out.println("Finished!");
	}
	
  
}
