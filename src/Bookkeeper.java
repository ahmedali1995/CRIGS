import java.util.HashMap;
import java.util.Map;


public class Bookkeeper {
	
	//This is to keep track of things on the table
	Map<String, EuroBotObject> all_things; 

	public Bookkeeper()
	{
		all_things = new HashMap<String, EuroBotObject>();
	}
	
	public void Add(EuroBotObject o)
	{
		all_things.put(o.name, o);
	}
	
	public void AddIfNew(EuroBotObject o)
	{
		boolean is_New = true;
		for(Map.Entry<String, EuroBotObject> bot : all_things.entrySet())
		{
			if(o.IsSameAs(bot.getValue()))
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
		all_things.values().toArray(a);
		return a;
	}
	
	public EuroBotObject GetObjectByName(String name)
	{
		return all_things.get(name);
	}
	
}
