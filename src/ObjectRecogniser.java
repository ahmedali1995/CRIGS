package src;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

// an interface so that we can always swap out recognition methods.
public interface ObjectRecogniser {
	void Initialise();
	EuroBotObject[] GetObjects();
	int[] GetUpdatedRobotPosition();
	void DoImageRecognition(Mat frame);
	void Dispose();
    BufferedImage Mat2bufferedImage(Mat image);
}
