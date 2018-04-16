package castle;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> roomlist=new HashMap<>();

    public Room(String description) 
    {
        this.description = description;
    }

    public void setExits(String dir,Room room) 
    {
        roomlist.put(dir, room);
    }

    public void retExits()
    {
    	for(String s:roomlist.keySet())
    	{
    		System.out.print(s);
    		System.out.print(" ");
    	}		
    }
    
    public Room selExit(String dir)
    {
    	return roomlist.get(dir);	
    }
    
    @Override
    public String toString()
    {
        return description;
    }
}
