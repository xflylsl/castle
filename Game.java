package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers=new HashMap<String, Handler>();
    
    private class Handlergo extends Handler{

		@Override
		public void docmd(String str) {
			goRoom(str);
		}
    	
    }
    
    public Game() 
    {
        handlers.put("go", new Handlergo());
        handlers.put("help",new Handlerhelp());
        handlers.put("bye", new Handlerbye());
        handlers.put("roomnum", new Handlernum());
        
    	createRooms();
    }   

    public void createRooms()
    {
        Room outside,lobby,pub,study,bedroom,upstairshall,basement,gym,restroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        upstairshall=new Room("楼上大厅");
        basement=new Room("地下室");
        gym=new Room("健身房");
        restroom=new Room("休息室");
        
        
        //	初始化房间的出口
        outside.setExits("east", lobby);
        outside.setExits("south", study);
        outside.setExits("west", pub);
        lobby.setExits("west", outside);
        lobby.setExits("upper", upstairshall);
        lobby.setExits("down", basement);
        pub.setExits("east", outside);
        study.setExits("north", outside);
        study.setExits("east", bedroom);
        bedroom.setExits("west", study);
        upstairshall.setExits("down", lobby);
        upstairshall.setExits("east", gym);
        upstairshall.setExits("west", restroom);
        basement.setExits("upper", lobby);
        restroom.setExits("east", upstairshall);
        gym.setExits("west", upstairshall);
        currentRoom = outside;  //	从城堡门外开始
    }
   
    
    public void prompt(Room room)
    {
    	System.out.println("现在你在" + room);
        System.out.print("出口有：");
        room.retExits();
        System.out.println();
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        prompt(currentRoom);
        
    }

    // 以下为用户命令

    
    public void goRoom(String direction) 
    {       
        if (currentRoom.selExit(direction) == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = currentRoom.selExit(direction);
            prompt(currentRoom);
        }
    }
	
    public void start()
    {
    	Scanner in = new Scanner(System.in);    	
    	printWelcome();
	  while ( true ) 
    	{
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler=handlers.get(words[0]);
    		String value="";
    		if(words.length>1)
    			value=words[1];
    		if(handler!=null)
    		{
    			handler.docmd(value);
    			if(handler.isbye())
    				break;
    		}
    		else
    		{
    			System.out.println("请输入正确的命令！");
    			continue;
    		}
    			
    	}
    	System.out.println("感谢您的光临。再见！");
        in.close();
    }
    
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
