package net.anorrah.items;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.bonus.bonus;

public abstract class ItemObject {
	
	public int[] id;
	public String name="NONE";
	public String itemDescription = "";
	protected int enchantment;
	protected ArrayList<bonus> possibleBonuses;
	protected  ArrayList<bonus> myBonus; 
	protected int charges;
	protected boolean hasCharges = false;
	protected bonus onlyForRegen = null;
	// only for rendering junk
	protected bonus generatedBonus = null;
	
	public ItemObject(int currentLevel)
	{
		enchantment = generateBonus(currentLevel); 	
		myBonus = new ArrayList<bonus>();
		possibleBonuses = new ArrayList<bonus>();
		
	}
	
	public boolean hasCharges()
	{
		return hasCharges;
	}
	
	// only for rendering junk
	public bonus getBonus()
	{
		return generatedBonus;
	}
	
	public int charges()
	{
		return charges;
	}
	
	public String description(){
		if(generatedBonus !=null)
		{
			return itemDescription + " " + generatedBonus.description(); 
		}
		else
		{
			return itemDescription;
		}
	}
	
	public int generateBonus(int currentLevel) 	
	{
		int bonusSeed = ((int) (Math.random()*100))%10;
		switch(bonusSeed)
		{
			default:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				return currentLevel;
				
			case 5:
			case 6:
			case 7:
				if( ((int) (Math.random()*100))%2 ==0)
				{
					return currentLevel +1; 
				}
				else
				{
					if(currentLevel -1 >0)
					{	
						return currentLevel -1;
					}
					else
					{
						return 0;
					}
				}
				
			case 8:
			case 9:
				
				if( ((int) (Math.random()*100))%2 ==0)
				{
					return currentLevel +2; 
				}
				else
				{
					if(currentLevel -2 >0)
					{	
						return currentLevel -2;
					}
					else
					{
						return 0;
					}
				}
		}

			
	}
	
	public Object generateBonus()
	{
		 if( ((int) (Math.random()*100)) <50)
		 {
			 if(possibleBonuses.size()>0)
			 {
				 int idx = new Random().nextInt(possibleBonuses.size());
				 generatedBonus = possibleBonuses.get(idx);
				 myBonus.add(generatedBonus);
				 System.out.println("bonus " + generatedBonus.getClass());
				 
			 }
		 }
		 return null;
	}
	
	public void onEquip(Entity user)
	{
		if(myBonus !=null)
		{
			for(bonus b:myBonus)
			{
				b.onEquipped(user);
				Core.player.addToList(b);
			}
		}
	}
	
	public void onUnequip(Entity user)
	{
		if(myBonus !=null)
		{
			for(bonus b:myBonus)
			{
				Core.player.removeFromList(b);
				b.onUnequipped(user);
			}
		}
	}
	
	public void onUseOnSelf(Entity user)
	{
		if(myBonus!=null){
			
			use();
			//for(bonus b:myBonus)
			{	
				user.addToList(onlyForRegen);
		//		b.onUseOnSelf(user);				
			}
		//	
			charges--;
			if(hasCharges && charges <=0)
			{
				assert(Core.player.getUsableItem() == this);
				
				Core.player.setUsableItem(new NoItem(0));
			}
		}
	}
	protected void use()
	{
		
	}
	
	public String getName(){
		return name;
	}
}
