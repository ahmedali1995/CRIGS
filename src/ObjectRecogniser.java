// an interface so that we can always swap out recognition methods.
public interface ObjectRecogniser {
	void Initialise();
	EuroBotObject[] GetObjects();
	int[] GetUpdatedRobotPosition();
	void DoImageRecognition();
	void Dispose();
}
