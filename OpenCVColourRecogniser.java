package src;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

//OpenCV wrapper.
public class OpenCVColourRecogniser implements ObjectRecogniser{
    static  {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

	@Override
	public void Initialise() {
	}
	@Override
	public EuroBotObject[] GetObjects() {
		// TODO Auto-generated method stub
		return new EuroBotObject[] {new EuroBotObject()};
	}

	@Override
	public void Dispose() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public int[] GetUpdatedRobotPosition() {
		
		return new int[] {0, 0};
	}

	@Override
	public void DoImageRecognition(Mat frame) {
		// TODO Auto-generated method stub
		 int  x=0;
         int  y=0;
		 Rect r= analyse(frame,96, 63,  103,  193, 255, 255);//contient Frame,hsm(min) et hsv(max) couleur bleu
		 if(r!=null){
		 Core.rectangle(frame,r.tl(),r.br(),new Scalar(255,0,0),1);
		 x=(int)(r.width);
         y=(int)(r.height);
         System.out.println("x="+(double)(x*0.02646)+", y="+(double)(y*0.02646)+" cm");//conversition pixelle en cm
		 }
		 //la hauteur et la largeur de l'objet
		 
		 
	}
	
	//il analyse l'objet et retoure le conour
	 public    Rect analyse(Mat frame,int Hmin, int Vmin, int Smin,int Hmax, int Vmax , int Smax){
    	 
	  	  //declaration des variables et matrice
	        Mat hsv_image = new Mat();
	        Mat thresholded = new Mat();
	    	   //valeur hsv de la couleur
	         Scalar hsv_min_vert  = new Scalar(Hmin ,Vmin, Smin, 0);
	         Scalar hsv_max_vert  = new Scalar(Hmax,Vmax,Smax, 0);
	         //conversion HSV en BGR utilisé par opencv
	         Imgproc.cvtColor(frame, hsv_image, Imgproc.COLOR_BGR2HSV);
	         Core.inRange(hsv_image, hsv_min_vert,hsv_max_vert,thresholded);
	         return detect_couleur(thresholded);//return le rectancle trouvé
	    	
	    }
	 
	//pour detecler la couleur de l'objet
    public   Rect detect_couleur(Mat outmat) {

	      Mat v = new Mat();
	      List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	      Imgproc.findContours(outmat, contours, v, Imgproc.RETR_LIST,
	              Imgproc.CHAIN_APPROX_SIMPLE);
	      double maxArea = -1;
	      int maxAreaIdx = -1;
	      Rect r = null;
	      for (int idx = 0; idx < contours.size(); idx++) {
	          Mat contour = contours.get(idx);
	          double contourarea = Imgproc.contourArea(contour);
	          if (contourarea > maxArea) {
	              maxArea = contourarea;
	              maxAreaIdx = idx;
	              r = Imgproc.boundingRect(contours.get(maxAreaIdx));
	          }
	      }
	      v.release();
	      return r;
	   }

	
	
	//fonction pur voir a l'ecran
	public  BufferedImage Mat2bufferedImage(Mat image) {
		MatOfByte bytemat = new MatOfByte();
		Highgui.imencode(".jpg", image, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage img = null;
		try {
		  img = ImageIO.read(in);
		} catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		}
		return img;
	}



}
