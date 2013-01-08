package mondocows.nodes;

import java.util.EnumSet;

import mondocows.data.Data;
import mondocows.methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class ToCows extends Node {

	@Override
	public boolean activate() {		
		return !Inventory.isFull() && !Data.CowArea.contains(Players.getLocal());
	}

	@Override
	public void execute() {	
		WidgetChild teleportTabButton = Widgets.get(275, 46);
		WidgetChild homeTeleButton = Widgets.get(275, 16).getChildren()[155];
		WidgetChild lumbridgeLodeButton = Widgets.get(1092, 47);	
		SceneObject gate = SceneEntities.getNearest(45210);
		
		if (Players.getLocal() != null) {
			if (Players.getLocal().getAnimation() == -1) {			
				if (!Data.ToCowsArea.contains(Players.getLocal())) {	
					Data.status = "Teleporting...";
					System.out.println("Opening ability book (going to cows).");
					Tabs.ABILITY_BOOK.open();					
					if (!homeTeleButton.visible()) {
						System.out.println("Opening teleport tab (going to cows).");
						teleportTabButton.click(true);
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
					} else {
						System.out.println("Opening home teleport interface (going to cows).");
						homeTeleButton.click(true);
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
						if (lumbridgeLodeButton.validate()) {
							System.out.println("Clicking Lumbridge lodestone button (going to cows).");
							lumbridgeLodeButton.click(true);
							Task.sleep(Random.nextGaussian(800, 2000, 1400));
						}
					}				
				} else {
					if (gate != null) {
						if (Calculations.distanceTo(gate.getLocation()) < 4) {
							if (Methods.isViewable(gate)) {
								gate.interact("Open");
								Task.sleep(Random.nextGaussian(400, 1200, 800));
								Walking.walk(Data.inside);
								Task.sleep(Random.nextGaussian(400, 1200, 800));
							} else {
								Camera.turnTo(gate, Random.nextGaussian(-25, 25, 2));
							}
						} else {
							Data.status = "Walking to cows...";
							Walking.newTilePath(Data.ToCows).traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
							Task.sleep(Random.nextGaussian(800, 2000, 1400));
						}
					} else {
						Data.status = "Walking to cows...";
						Walking.newTilePath(Data.ToCows).traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
					}
				}
			}
		}
	}

}
