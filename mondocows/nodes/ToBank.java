package mondocows.nodes;

import java.util.EnumSet;

import mondocows.data.Data;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class ToBank extends Node {

	@Override
	public boolean activate() {		
		return !Data.BankArea.contains(Players.getLocal()) && Inventory.isFull() && Data.doBanking;
	}

	@Override
	public void execute() {		
		WidgetChild teleportTabButton = Widgets.get(275, 46);
		WidgetChild homeTeleButton = Widgets.get(275, 16).getChildren()[155];
		WidgetChild edgevilleLodeButton = Widgets.get(1092, 45);
		
		if (Players.getLocal() != null) {
			if (Players.getLocal().getAnimation() == -1) {
				if (!Data.BankPathArea.contains(Players.getLocal())) {
					Data.status = "Teleporting...";
					System.out.println("Opening ability book (going to bank).");
					Tabs.ABILITY_BOOK.open();
					if (!homeTeleButton.visible()) {
						System.out.println("Opening home teleport interface (going to bank).");
						teleportTabButton.click(true);
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
					} else {
						System.out.println("Clicking home tele button (going to bank).");
						homeTeleButton.click(true);
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
						if (edgevilleLodeButton.validate()) {
							System.out.println("Clicking Edgeville lodestone button (going to bank).");
							edgevilleLodeButton.click(true);
							Task.sleep(Random.nextGaussian(800, 2000, 1400));
						}
					}
				} else {
					if (!Data.BankArea.contains(Players.getLocal())) {
						Data.status = "Walking to bank...";
						Walking.newTilePath(Data.ToBank).traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
						Task.sleep(Random.nextGaussian(800, 2000, 1400));
					}				
				}
			}
		}		
	}
}
