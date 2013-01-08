package mondocows.tasks;

import mondocows.combat.Combat;
import mondocows.data.Data;
import mondocows.methods.Methods;
import mondocows.nodes.Looting;

import org.powerbot.core.script.job.LoopTask;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class CombatHelper extends LoopTask {

	@Override
	public int loop() {
		
		if (Players.getLocal().getInteracting() != null && Data.CowArea.contains(Players.getLocal())) {			
			GroundItem item = GroundItems.getNearest(Looting.lootFilter);
			NPC npc = NPCs.getNearest(Combat.cowFilter);
			if (item != null) {
				if (!Methods.isViewable(item)) {
					System.out.println("Turning to item (CombatHelper).");
					Camera.turnTo(item, Random.nextGaussian(-25, 25, 12, 12));
				} else if (!Methods.containsMouse(item) && !Data.hoverTimer.isRunning() && !Players.getLocal().isMoving()) {
					System.out.println("Hovering item (CombatHelper).");
					item.hover();
					Data.hoverTimer.setEndIn(Random.nextGaussian(400, 1500, 900));
				}
			} else if (item == null && npc != null) {
				if (!Methods.isViewable(npc)) {
					System.out.println("Turning to npc (CombatHelper).");
					Camera.turnTo(npc, Random.nextGaussian(-30, 30, 15, 15));
				} else {
					if (!Methods.containsMouse(npc) && !Data.hoverTimer.isRunning() && !Players.getLocal().isMoving()) {
						System.out.println("Hovering npc (CombatHelper).");
						npc.hover();
						Data.hoverTimer.setEndIn(Random.nextGaussian(400, 1500, 900));
					}
				}
			}
		}
		return Random.nextInt(50, 100);
	}

}
