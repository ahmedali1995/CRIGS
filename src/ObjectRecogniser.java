// an interface so that we can always swap out recognition methods.
public interface ObjectRecogniser {
	void Initialise();
	EuroBotObject[] GetObjects();
	void Dispose();
}
