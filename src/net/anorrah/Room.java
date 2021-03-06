package net.anorrah;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Room {
	public int levelNum = 0;
	public int roomid = -1;
	private double chanceEmpty = 85;
	private double chanceEnemy = 80;
	private double chanceBlocked = 65;
	
	public boolean hasExit = false;
	public boolean isStart = false;
	public boolean hasPlayer = false;
	public boolean cleared = true;
	public int r =0;
	public Item item = null;
	public ArrayList<EnemyEntities> enemies= new ArrayList<EnemyEntities>();
	private  ArrayList<EnemyEntities> toBeRemovedEnemies = new ArrayList<EnemyEntities>();
	public ArrayList<Solid> blocks = new ArrayList<Solid>();
	public int left=0, right=0, up=0, down=0;//adjacent rooms
	//private int chanceTrap = 100; not needed
	
	public EnemyEntities enemy;
	
	public Room(int levelnumber, int roomid)
	{
		levelNum = levelnumber;
		this.roomid = roomid;
		if (levelNum > 30)//cap to 30
			levelNum = 30;
		
		makeRoom();
	}
	
	public void addToRemoveList(EnemyEntities enemyToRemove)
	{
		toBeRemovedEnemies.add(enemyToRemove);
	}
	
	public void cleanup()
	{
		for(EnemyEntities b: toBeRemovedEnemies)
		{
			enemies.remove(b);
		}
		toBeRemovedEnemies.clear();
	}
//-----------------------------------------Room content generation-------------------------------------------
	public void makeRoom()
	{
		//assign left, right, up, down if they exist
		
		for(int x = 2; x < Level.width-2; x++)
		{
			for(int y = 2; y < Level.height-2; y++)
			{
				int dice = (int) (Math.random()*100)+1;
				if(x != Level.center_w && y != Level.center_h)
				{
					if(dice > chanceEmpty-levelNum)
					{
						//place an empty tile at [x,y] 
					}
					else if(dice > chanceEnemy-Math.ceil(levelNum/2))
					{
						int roll = (int)(Math.random()*5);
						Solid block;
						if(roll == 0)
						{
							enemy = new EnemyEntities(null, Tile.axis_LEFT, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						else if(roll == 1)
						{
							enemy = new EnemyEntities(null, Tile.cannibal_DOWN, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						else if(roll == 2)
						{
							enemy = new EnemyEntities(null, Tile.charger_DOWN, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						else if(roll == 3)
						{
							enemy = new EnemyEntities(null, Tile.mage_DOWN, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						else if(roll == 4)
						{
							enemy = new EnemyEntities(null, Tile.theif_DOWN, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						else
						{
							enemy = new EnemyEntities(null, Tile.rat_DOWN, x*Tile.size, y*Tile.size, Tile.size, Tile.size);
						}
						
						enemies.add(enemy);
					}
					else if(dice > chanceBlocked-Math.ceil(levelNum/2))
					{

						int flip = (int)(Math.random()*2);
						Solid block;
						if(flip == 0)
						{
							block = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.pit);
						}
						else
						{
							block = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.boulder);
						}
						blocks.add(block);
					}
					else {
						//place a trap tile at [x,y]
						
					}
				}
				else if(roomid != 1 && x == Level.center_w && y == Level.center_h)//no items in room 1
				{
					//determine if an item is to be placed in the room
					float chance = ((float)levelNum/10)+1;
					chance = 1/chance;
					if(Math.random() < chance)
					{
						item = new Item(new Rectangle(Level.center_w*Tile.size, Level.center_h*Tile.size, Tile.size, Tile.size),Level.center_w,Level.center_h,Tile.chest_closed);
					}
				}
			}
			
		}
		System.out.println("There are "+enemies.size()+" enemies in Room #"+roomid);
		System.out.println("There are "+blocks.size()+" blocks in Room #"+roomid);
	}
	
	public void render(Graphics g)
	{
		
	}

}
