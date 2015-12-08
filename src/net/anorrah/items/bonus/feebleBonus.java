package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class feebleBonus extends bonus{

	// more of a curse. Applied from EnfeeblementBonus. 
	
	public feebleBonus()
	{
		isTemp = true;
		turnsLeft = 1;
	}
	//changes this to work for enemies
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		if(damage.damage-10 >0)
		{
			damage.damage = damage.damage-10;
		}
		else
		{
			damage.damage=0;
		}
	}
	
}
