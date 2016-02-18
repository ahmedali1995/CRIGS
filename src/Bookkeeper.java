import java.util.ArrayList;
import java.util.List;


public class Bookkeeper {
	
	//This is to keep track of things on the table
	List<EuroBotObject> all_things; 

	public Bookkeeper()
	{
		all_things = new ArrayList<EuroBotObject>();
	}
	
	public void Add(EuroBotObject o)
	{
		all_things.add(o);
	}
	
	public void AddIfNew(EuroBotObject o)
	{
		boolean is_New = true;
		for(EuroBotObject bot : all_things)
		{
			if(o.IsSameAs(bot))
			{
				is_New = false;
			}
		}
		if(is_New)
		{
			Add(o);
		}
		
	}
	
	public EuroBotObject[] GetAllObjects()
	{
		EuroBotObject[] a = new EuroBotObject[all_things.size()];
		all_things.toArray(a);
		return a;
	}
	
}
