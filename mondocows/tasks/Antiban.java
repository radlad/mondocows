package mondocows.tasks;

import java.awt.Point;

import mondocows.combat.Combat;
import mondocows.data.Data;
import mondocows.methods.Methods;
import mondocows.nodes.DoBanking;

import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Antiban extends LoopTask {
	
    @Override
	public int loop() {
    	
    if (Players.getLocal() != null && Data.CowArea.contains(Players.getLocal()) && !Inventory.isFull()) {
			int randomAntiban = Random.nextInt(0, 6);
			int randomTab = Random.nextInt(0, 5);
			System.out.println("Doing antiban.");
	        switch(randomAntiban) {
	        case 0:
	            int randomSkill = Random.nextInt(0, 24);
	            System.out.println("Checking random skill.");
	            Tabs.STATS.open();
	            WidgetChild randStat = Skills.getWidgetChild(randomSkill);
	            Point randStatPoint = randStat.getAbsoluteLocation();
	            randStatPoint.x += Random.nextInt(-8, 8);
	            randStatPoint.y += Random.nextInt(-8, 8);
	            Task.sleep(Random.nextInt(200,300));
	            Mouse.move(randStatPoint);
	            Task.sleep(Random.nextInt(600,800));
	            break;
	        case 1:
	        	if(Players.getLocal().getInteracting() != null) {
	        		System.out.println("Moving mouse around.");
	                int currentMouseX = Mouse.getX();
	                int currentMouseY = Mouse.getY();
	                Mouse.move(currentMouseX + Random.nextInt(Random.nextInt(Random.nextInt(-250, 0), 0), Random.nextInt(0, Random.nextInt(0, 250))),
	                		currentMouseY + Random.nextInt(Random.nextInt(Random.nextInt(-250, 0), 0), Random.nextInt(0, Random.nextInt(0, 250))));
		        } 
		        break;
	        case 2: 
	            default:
	                    if(Players.getLocal().getInteracting() != null) {
	                    	System.out.println("Turning camera to what we're doing.");
	                            Camera.turnTo(Players.getLocal().getInteracting());
	                            int currentPitch = Camera.getPitch();
	                            int currentYaw = Camera.getYaw();
	                            Camera.setPitch(currentPitch + Random.nextInt(Random.nextInt(-50, 0), Random.nextInt(0, 50)));
	                            Camera.setAngle(currentYaw + Random.nextInt(Random.nextInt(-75, 0), Random.nextInt(0, 75)));
	                    } else {
	                    	System.out.println("Moving camera a bit.");
	                            int currentPitch = Camera.getPitch();
	                            int currentYaw = Camera.getYaw();
	                            Camera.setPitch(currentPitch + Random.nextInt(Random.nextInt(-50, 0), Random.nextInt(0, 50)));
	                            Camera.setAngle(currentYaw + Random.nextInt(Random.nextInt(-250, 0), Random.nextInt(0, 250)));
	                    }
	                    break;
	        case 3:
	        	if(Players.getLocal() != null) {
	                System.out.println("Checking random useful tab.");
	                switch(randomTab) {                            
	                case 1:                	
	            	default:
	                		Tabs.FRIENDS_CHAT.open();
	                		Task.sleep(Random.nextInt(800,1200));
	                		break;
	                case 2:
	                		Tabs.ATTACK.open();
	                		Task.sleep(Random.nextInt(800,1200));
	                		break;
	                case 3:
	                		Tabs.FRIENDS.open();
	                		Task.sleep(Random.nextInt(800,1200));
	                		break;
	                case 4:
	                		Tabs.CLAN_CHAT.open();
	                		Task.sleep(Random.nextInt(800,1200));
	                		break;
	                case 5:
	                		Tabs.STATS.open();
	                		Task.sleep(Random.nextInt(800,1200));
	                		break;                
	                }                
	        	}
	        	break;	             
	                
	        case 4:
	        	if(Players.getLocal() != null) {
	                System.out.println("Moving camera a bit.");
	                int currentPitch = Camera.getPitch();
	                int currentYaw = Camera.getYaw();
	                Camera.setPitch(currentPitch + Random.nextInt(Random.nextInt(-50, 0), Random.nextInt(0, 50)));
	                Camera.setAngle(currentYaw + Random.nextInt(Random.nextInt(-300, 0), Random.nextInt(0, 300)));
	        	}
	        	break;
	        case 5:
	        	if(Players.getLocal() != null) {
	        		System.out.println("Hovering npc.");
	        		NPC npc = NPCs.getNearest(Combat.cowFilter);
	        		if (npc != null && Methods.isViewable(npc)) {
	        			npc.hover();
	        		}
	        	}
	        	break;
	        case 6:
	        	if(Players.getLocal() != null) {
	        		System.out.println("Hovering interaction.");
	        		Entity interaction = Players.getLocal().getInteracting();
	        		if (interaction != null) {
	        			interaction.hover();
	        		}
	        	}
	        	break;
	        }
		}
	return Random.nextInt(Random.nextInt(7, 20)*1000, Random.nextInt(17, 60)*1000);
    }
    
}
