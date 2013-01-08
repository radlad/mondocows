package mondocows.nodes;

import mondocows.data.Data;
import mondocows.methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.net.GeItem;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class Looting extends Node {
	
	public static Filter<GroundItem> lootFilter = new Filter<GroundItem>(){
		@Override
		public boolean accept(GroundItem n) {
			for(Integer i : Data.loot) {
				return n != null && n.getLocation().canReach() && 
						Data.loot.contains(n.getId()) && Data.CowArea.contains(n.getLocation()) && Methods.isLootNear(n) && Calculations.distanceTo(n) < 6;				
			}
			return false;
		}		
	};

	@Override
	public boolean activate() {
		GroundItem item = GroundItems.getNearest(lootFilter);
		return item != null && Players.getLocal().getInteracting() == null && 
				!Players.getLocal().isMoving() && !Inventory.isFull() && Data.doBanking;
	}

	@Override
	public void execute() {
		int currentMouseX = Mouse.getX();
        int currentMouseY = Mouse.getY();
        int currentPitch = Camera.getPitch();
        int currentAngle = Camera.getYaw();
		GroundItem item = GroundItems.getNearest(lootFilter);
		Data.cowhides = Inventory.getCount(Data.cowhideID) + Data.bankedHides;
		
		if (!Data.itemPriceMap.containsKey(item.getGroundItem().getName())) {
				GeItem itemGE = GeItem.lookup(item.getId());					
				if (itemGE != null) {
					System.out.println("Finding price of " + item.getGroundItem().getName() + " | ID: " + item.getId() + " | Stack size: " + item.getGroundItem().getStackSize());
					Data.itemPriceMap.put(item.getGroundItem().getName(), itemGE.getPrice());
					Data.itemPriceMapInt.put(item.getId(), itemGE.getPrice());
				}
		}
		
		if (item != null) {
			if (!Methods.isViewable(item)) {
				System.out.println("Turning to item.");
				Camera.setPitch(Random.nextInt(10, 70));
				Camera.turnTo(item);
			} else if (!Inventory.isFull() && !Data.lootTimer.isRunning()){	
				if (Calculations.distanceTo(item.getLocation()) > 10) {
					Walking.walk(item.getLocation());
					Task.sleep(Random.nextInt(500, 800));
				} else {					
					Data.status = "Picking up " + item.getGroundItem().getName() + ".";				
					if(item.click(false) && Menu.isOpen() && Menu.contains("Take")) {
						System.out.println("Picking up " + item.getGroundItem().getName() + ".");
						Menu.select("Take", item.getGroundItem().getName());	
						if (!Data.lootListMap.containsKey(item.getGroundItem().getName())) {
							Data.lootListMap.put(item.getGroundItem().getName(), item.getGroundItem().getStackSize());
							Data.lootListMapInt.put(item.getId(), item.getGroundItem().getStackSize());
						} else {
							Data.lootListMap.put(item.getGroundItem().getName(), Data.lootListMap.get(item.getGroundItem().getName())+item.getGroundItem().getStackSize());
							Data.lootListMapInt.put(item.getId(), Data.lootListMapInt.get(item.getId())+item.getGroundItem().getStackSize());
						}						
						Methods.getProfit();
						Data.lootTimer.setEndIn(Random.nextInt(750,1500));
					}				
					Mouse.move(currentMouseX + Random.nextInt(-20, 20), currentMouseY + Random.nextInt(-20, 20));
					Camera.setPitch(currentPitch + Random.nextInt(-30, 70));
			        Camera.setAngle(currentAngle + Random.nextInt(-30, 30));
				}
			}		
		}
	}

}