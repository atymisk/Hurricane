package net.anorrah;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import org.newdawn.slick.Input;

public class InputManager implements KeyListener, MouseListener
{
//Key Events-------------------------------------------------------------------------------------------------
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_W:
			if(Core.inGame && !Core.player.isMoving)
			{
				Core.bW = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_A:
			if(Core.inGame && !Core.player.isMoving)
			{
				Core.bA = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_S:
			if(Core.inGame && !Core.player.isMoving)
			{
				Core.bS = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_D:
			if(Core.inGame && !Core.player.isMoving)
			{
				Core.bD = true;
				//Core.t.WaitForPlayer=false;
				Core.t.setWaitForPlayerToFalse();
			}
			break;
			
		case KeyEvent.VK_SPACE:
			//Core.player.attack(null);
			if(Core.running)
			{
				System.out.println("About to attack");
				int playerx = Core.player.getlocationX();
				int playery = Core.player.getlocationY();
				for(EnemyEntities bad : Core.level.enemies)
				{
					if (bad.getlocationX() == playerx && bad.getlocationY()== playery+1)
					{	
						Core.player.attack(bad);
					//	bad.takeDamage(Core.player.weapon);
					}
					
					
				}
				
			}
			Core.t.setWaitForPlayerToFalse();
			break;
			
		case KeyEvent.VK_ESCAPE:
			Core.inGame = !Core.inGame;
			if(Core.inGame)
				System.out.println("unPaused");
			else
				System.out.println("Paused");
			break;
			
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}
//--------------------------------------------------------------------------------------------------------
//Mouse Events--------------------------------------------------------------------------------------------
//these need to be here
	public void mouseClicked(MouseEvent e)//whenever a button has been pressed and Released 
	{	
		Core.t.setWaitForPlayerToFalse();
		//If you are facing an enemy, you should be able to deal damage
		Core.player.attack(Core.player.getlocationX(),Core.player.getlocationY());
		//System.out.println("Attack at X: " + Math.floor(e.getPoint().getX()/Tile.size) + " Y: " + Math.floor(e.getPoint().getY()/Tile.size));
	}

	public void mouseEntered(MouseEvent e)//whenever cursor enters a component
	{
		
	}

	public void mouseExited(MouseEvent e)//when cursor exits a component
	{
		
	}

	public void mousePressed(MouseEvent e)//only when a mouse-button is pressed 
	{
		
		/*
		 * To check for the mouse buttons
		 * 
		 * SwingUtilities.isLeftMouseButton(MouseEvent e)
		 * SwingUtilities.isRightMouseButton(MouseEvent e)
		 * SwingUtilities.isMiddleMouseButton(MouseEvent e)
		 */
	}

	public void mouseReleased(MouseEvent e)//only when a mouse-button is released after pressing
	{
		
	}	
	
}
