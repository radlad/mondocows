package mondocows.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.Player;

import abilities.sk.action.ActionBar;
import abilities.sk.action.book.BookAbility;
import mondocows.data.Data;

public class UseAbilities extends Node {

	@Override
	public boolean activate() {		
		return Players.getLocal().getInteracting() != null && (Data.useMagic || Data.useMelee || Data.useRange);
	}

	@Override
	public void execute() {
		final Player p = Players.getLocal();		
		
		if (p.getInteracting() != null) {
			System.out.println("Using ability.");
			if (Data.useRejuvenate) {	
				if (p.getHealthPercent() < 60) {
					ActionBar.useAbility(BookAbility.REJUVENATE);
				}
			} 
			if (Data.useMagic) {
				ActionBar.useAbility(BookAbility.OMNIPOWER);					
				ActionBar.useAbility(BookAbility.WRACK);
				ActionBar.useAbility(BookAbility.COMBUST);
				if (p.getHealthPercent() > 60) {
					ActionBar.useAbility(BookAbility.WILD_MAGIC);
					ActionBar.useAbility(BookAbility.ASPHYXIATE);
				}
			} else if (Data.useMelee) {
				if (p.getHealthPercent() > 60) {
					ActionBar.useAbility(BookAbility.OVERPOWER);
					ActionBar.useAbility(BookAbility.ASSAULT);
				}
				ActionBar.useAbility(BookAbility.SLICE);
				ActionBar.useAbility(BookAbility.BACKHAND);				
				ActionBar.useAbility(BookAbility.SEVER);
				ActionBar.useAbility(BookAbility.FURY);
				ActionBar.useAbility(BookAbility.DISMEMBER);					
			} else if (Data.useRange) {
				if (p.getHealthPercent() > 60) {
					ActionBar.useAbility(BookAbility.DEADSHOT);
					ActionBar.useAbility(BookAbility.SNAP_SHOT);
					ActionBar.useAbility(BookAbility.RAPID_FIRE);
					ActionBar.useAbility(BookAbility.PIERCING_SHOT);
				}
				ActionBar.useAbility(BookAbility.SNIPE);
				ActionBar.useAbility(BookAbility.FRAGMENTATION_SHOT);					
			}				
			
		}		
	}
}
