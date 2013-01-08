package mondocows.tasks;

import java.awt.Color;

import org.powerbot.core.script.job.LoopTask;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;

import mondocows.data.Data;
import mondocows.paint.Paint;

public class DataHelper extends LoopTask {
	
	@Override
	public int loop() {		
		
	if (Data.CowArea.contains(Players.getLocal()) && Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().getHealthPercent() < 10) {
		if (!Data.lootTiles.contains(Players.getLocal().getInteracting().getLocation())) {
			System.out.println("Adding tile to SmartLoot (DataHelper).");
			Data.lootTiles.add(Players.getLocal().getInteracting().getLocation());	
		}
	}
	
	if (Data.lootTiles.size() > 5) {
		System.out.println("Clearing SmartLoot tiles (DataHelper).");
		Data.lootTiles.clear();
	}
	
	if (Data.skillNum == 1 && Data.selectedSkill != 1) {
		Data.selectedSkill = 0;
		Data.skillString = "Attack";
		Data.startLevel = Data.startAtkLvl;
		Data.startExp = Data.startAtkExp;
		Paint.color_skill = new Color(178, 34, 34, 150);
	} else if (Data.skillNum == 2 && Data.selectedSkill != 2) {
		Data.selectedSkill = 2;
		Data.skillString = "Strength";
		Data.startLevel = Data.startStrLvl;
		Data.startExp = Data.startStrExp;
		Paint.color_skill = new Color(46, 139, 87, 150);
	} else if (Data.skillNum == 3 && Data.selectedSkill != 1) {
		Data.selectedSkill = 1;
		Data.skillString = "Defence";
		Data.startLevel = Data.startDefLvl;
		Data.startExp = Data.startDefExp;
		Paint.color_skill = new Color(70, 130, 180, 150);
	} else if (Data.skillNum == 4 && Data.selectedSkill != 3) {
		Data.selectedSkill = 3;
		Data.skillString = "Constitution";
		Data.startLevel = Data.startConstLvl;
		Data.startExp = Data.startConstExp;
		Paint.color_skill = new Color(205, 92, 92, 150);
	} else if (Data.skillNum == 5 && Data.selectedSkill != 4) {
		Data.selectedSkill = 4;
		Data.skillString = "Range";
		Data.startLevel = Data.startRngLvl;
		Data.startExp = Data.startRngExp;
		Paint.color_skill = new Color(205, 133, 63, 150);
	} else if (Data.skillNum == 6 && Data.selectedSkill != 6) {
		Data.selectedSkill = 6;
		Data.skillString = "Magic";
		Data.startLevel = Data.startMagLvl;
		Data.startExp = Data.startMagExp;
		Paint.color_skill = new Color(65, 105, 225, 150);
	}

		// TODO Auto-generated method stub
		return Random.nextInt(50, 100);
	}

}
