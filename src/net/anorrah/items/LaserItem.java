package net.anorrah.items;

import net.anorrah.items.bonus.fireballBonus;
import net.anorrah.items.bonus.laserBonus;

public class LaserItem extends RangedWeaponItem{

	public LaserItem(int levelBonus) {
		super(levelBonus);
		hasCharges = true;
		charges = 7;
		damage = (1*enchantment)+10;
		myBonus.add(new laserBonus((int) damage));
		
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
