package mondocows.combat;

import java.awt.Point;

import mondocows.methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.interactive.Player;

import mondocows.data.Data;

public class Combat extends Node {
	
	public static Filter<NPC> cowFilter = new Filter<NPC>(){		
		@Override
		public boolean accept(NPC n) {
			for(int i : Data.cows) {
				return n != null && n.getHealthPercent() >= 1 && n.getLocation().canReach() && Data.CowArea.contains(n.getLocation())  &&
						Data.cows.contains(n.getId()) && !n.getLocation().equals(Players.getLocal().getLocation()) &&
						!n.isInCombat();
			}
			return false;
		}		
	};
	
	Point m = Mouse.getLocation();

	@Override
	public boolean activate() {
		NPC cow = NPCs.getNearest(cowFilter);
		return Players.getLocal() != null && Players.getLocal().getInteracting() == null && cow != null;
	}

	@Override
	public void execute() {
		final NPC npc = NPCs.getNearest(cowFilter);
		final Player p = Players.getLocal();
		Data.oldKills = Data.killCounter;
		int rand = Random.nextInt(0, 10);
		Data.tileReset = true;
		
		if (Walking.getEnergy() > Random.nextInt(30, 70)) {
			Walking.setRun(true);
		}
		
		if (npc == null) {
			Data.status = "Waiting for NPC...";
			Task.sleep(50);
		} else {		
			if (Methods.isViewable(npc)) {
				if (p.getInteracting() == null && !p.isMoving()) {
					npc.interact("Attack");					
					Data.status = "Fighting " + npc.getName() + "!";
					System.out.println("Attacking monster.");
					Task.sleep(Random.nextInt(100, 800));
					if (rand > 3) { 
						Mouse.move(Mouse.getX() + Random.nextInt(Random.nextInt(Random.nextInt(-250, 0), 0), Random.nextInt(0, Random.nextInt(0, 250))),
	                		Mouse.getY() + Random.nextInt(Random.nextInt(Random.nextInt(-250, 0), 0), Random.nextInt(0, Random.nextInt(0, 250)))); 
					}
				}
			} else if (!Methods.isViewable(npc)) {
				Camera.setPitch(Random.nextInt(1, 25));
				Camera.turnTo(npc, Random.nextInt(-75, 75));				
				Data.status = "Finding Cow!";
				System.out.println("Finding monster.");
			}
		}
	}

}
