package mondocows.nodes;

import mondocows.data.Data;
import mondocows.methods.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class DoBanking extends Node {

	@Override
	public boolean activate() {
		return Data.BankArea.contains(Players.getLocal()) && Inventory.isFull();
	}

	@Override
	public void execute() {
		NPC banker = NPCs.getNearest(Data.bankerID);	

		if (!Methods.isViewable(banker)) {
			System.out.println("Turning to banker.");
			Data.status = "Finding banker...";
			Camera.turnTo(banker, Random.nextGaussian(-25, 25, 12, 12));
		} else {
			System.out.println("Deposting items in bank.");
			Data.status = "Banking...";
			Bank.open();
			Data.bankedHides+=Inventory.getCount(Data.cowhideID);
			Bank.depositInventory();
			Data.trips+=1;
			Bank.close();
		}
		
	}

}
