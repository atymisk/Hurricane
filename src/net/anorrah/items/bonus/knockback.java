package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class knockback extends bonus {

	// knockback moves enemy away one sqaure only if they are next to character.
	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		
		int x = user.getlocationX();
		int y = user.getlocationY();
		int xSpeed =enemy.getlocationX()-x;
		xSpeed =(int) Math.signum(xSpeed);
		int ySpeed =enemy.getlocationY()-y;
		ySpeed=(int) Math.signum(ySpeed);
		
		if(!outOfBounds(x+xSpeed,y+ySpeed) &&canMove(x+xSpeed,y+ySpeed))
		{
			enemy.move(x+xSpeed,y+ySpeed );
		}
		
		/*int vectorX = (int) (enemy.getX()-Core.player.getlocationX());
		int vectorY = (int) (enemy.getY()-Core.player.getlocationY());
		System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
		if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
		{
			enemy.move(vectorX, vectorY);
		}*/
			

	}
	public String description()
	{
		return "Move melee attackers away from the wearer one space when they attack the wearer. If the attackers would be moved into a wall or another enemy, they are unaffected by the  armor�s power.";
			
	} 
}
