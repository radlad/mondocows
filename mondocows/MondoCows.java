package mondocows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mondocows.combat.Combat;
import mondocows.data.Data;
import mondocows.nodes.DoBanking;
import mondocows.nodes.Looting;
import mondocows.nodes.ToBank;
import mondocows.nodes.ToCows;
import mondocows.nodes.UseAbilities;
import mondocows.paint.Paint;
import mondocows.tasks.Antiban;
import mondocows.tasks.CombatHelper;
import mondocows.tasks.DataHelper;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Lobby;
import org.powerbot.game.api.util.Random;

@Manifest(authors = { "nootz" }, name = "MondoCows", description = "Fights Cows and banks their drops, start at the Lumbridge cow fields.", version = 1.0 )
public class MondoCows extends ActiveScript implements PaintListener, MouseListener, MessageListener {
	
	@Override
	public void onRepaint(Graphics g1) {	
		if (Players.getLocal() != null && Game.isLoggedIn() && !Lobby.isOpen()) {
			Paint.onRepaint(g1);
		}	
	}
	
    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;

    public synchronized final void provide(final Node... jobs) {
            for (final Node job : jobs) {
                    if(!jobsCollection.contains(job)) {
                            jobsCollection.add(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public synchronized final void revoke(final Node... jobs) {
            for (final Node job : jobs) {
                    if(jobsCollection.contains(job)) {
                            jobsCollection.remove(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public final void submit(final Job... jobs) {
            for (final Job job : jobs) {
                    getContainer().submit(job);
            }
    }

	@Override
	public int loop() {
		
		if (Data.paused || Data.setup) {
			Task.sleep(50);
		} else {
			if (jobContainer != null) {
	            final Node job = jobContainer.state();
	            if (job != null) {
	                    jobContainer.set(job);
	                    getContainer().submit(job);
	                    job.join();
	            }
			}	
		}	
		if (Players.getLocal().getInteracting() != null && (Players.getLocal().getInteracting().getHealthPercent() <= 0 || Players.getLocal().getInteracting().getAnimation() == 244 || Players.getLocal().getInteracting().getAnimation() == 5331)) {
			if ((Data.killCounter - Data.oldKills) == 0) {
			Data.killCounter+=1;
			}
		}		
		if (Data.setup && !Data.setupTimer.isRunning()) {
			Data.setup = false;
			Data.showPaint = true;
		}
		
		return Random.nextInt(50, 100);
		
	}
	
	@Override
	public void onStart() {	
		
		provide(new DoBanking(), new ToBank(), new ToCows(), new Looting(), new UseAbilities(), new Combat());
		submit(new DataHelper(), new CombatHelper(), new Antiban());
		
		Mouse.setSpeed(Mouse.Speed.FAST);
		Data.paused = false;		
		Data.startTime = System.currentTimeMillis();
		Data.startAtkLvl = Skills.getRealLevel(Skills.ATTACK);
		Data.startStrLvl = Skills.getRealLevel(Skills.STRENGTH);
		Data.startDefLvl = Skills.getRealLevel(Skills.DEFENSE);
		Data.startConstLvl = Skills.getRealLevel(Skills.CONSTITUTION);
		Data.startRngLvl = Skills.getRealLevel(Skills.RANGE);
		Data.startMagLvl = Skills.getRealLevel(Skills.MAGIC);
		Data.startAtkExp = Skills.getExperience(Skills.ATTACK);
		Data.startStrExp = Skills.getExperience(Skills.STRENGTH);
		Data.startDefExp = Skills.getExperience(Skills.DEFENSE);
		Data.startConstExp = Skills.getExperience(Skills.CONSTITUTION);
		Data.startRngExp = Skills.getExperience(Skills.RANGE);
		Data.startMagExp = Skills.getExperience(Skills.MAGIC);
		
		Data.oldKills = 0;
		Data.killCounter = 0;
		
		for (int i : Data.cowIDs) {
			Data.cows.add(i);
		}
		
		Data.loot.add(Data.cowhideID);
	}

	@Override
	public void messageReceived(MessageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent p) {
		final Rectangle showInfo = new Rectangle(353, 397, 34, 12);
		final Rectangle showLoot = new Rectangle(396, 397, 34, 12);		
		final Rectangle showStats = new Rectangle(440, 397, 34, 12);	
		final Rectangle showPaint = new Rectangle(481, 397, 12, 12);
		final Rectangle doBank = new Rectangle(19, 471, 12, 12);
		final Rectangle useRejuvenate = new Rectangle(74, 471, 12, 12);
		final Rectangle useMelee = new Rectangle(316, 459, 12, 12);
		final Rectangle useRange = new Rectangle(371, 459, 12, 12);
		final Rectangle useMagic = new Rectangle(428, 459, 12, 12);
		final Rectangle skillToggle = new Rectangle(191, 481, 287, 15);
		final Rectangle setupBank = new Rectangle(341, 305, 15, 15);
		final Rectangle setupRejuvenate = new Rectangle(439, 305, 15, 15);
		final Rectangle setupMelee = new Rectangle(274, 333, 15, 15);
		final Rectangle setupRange = new Rectangle(376, 333, 15, 15);
		final Rectangle setupMagic = new Rectangle(475, 333, 15, 15);
		final Rectangle setupExit = new Rectangle(151, 200, 503, 167);
		
		if (Data.setup) {
			if (!setupExit.contains(p.getPoint())) {
				Data.setup = false;
				Data.showPaint = true;
			}
			if (setupBank.contains(p.getPoint())) {
				Data.doBanking = !Data.doBanking;
			} else if (setupRejuvenate.contains(p.getPoint())) {
				Data.useRejuvenate = !Data.useRejuvenate;
			} else if (setupMelee.contains(p.getPoint())) {
				Data.useMelee = true;
				Data.useRange = false;
				Data.useMagic = false;
			} else if (setupRange.contains(p.getPoint())) {
				Data.useMelee = false;
				Data.useRange = true;
				Data.useMagic = false;
			} else if (setupMagic.contains(p.getPoint())) {
				Data.useMelee = false;
				Data.useRange = false;
				Data.useMagic = true;
			}			
		} else {		
			if (showPaint.contains(p.getPoint())) {
				Data.showPaint = !Data.showPaint;
			}		
			if (Data.showPaint) {		
				if(showInfo.contains(p.getPoint())) {
					Data.showInfo = !Data.showInfo;	
		        } else if(showLoot.contains(p.getPoint())) {
		        	System.out.println("Random Gaussian (-30, 30, 10, 10): " + Random.nextGaussian(-30, 30, 10, 10));
		        	System.out.println("Random Gaussian (-30, 30, 15, 15): " + Random.nextGaussian(-30, 30, 15, 15));
		        	System.out.println("Random Gaussian (-25, 25, 4, 12): " + Random.nextGaussian(-30, 30, 4, 12));
		        	System.out.println("Random Gaussian (-25, 25, 12, 12): " + Random.nextGaussian(-30, 30, 12, 12));
		        	Data.showLoot = !Data.showLoot;
		        } else if (showStats.contains(p.getPoint())) {
					Data.showStats = !Data.showStats;
				}
				
				if (Data.showInfo) {
					if (doBank.contains(p.getPoint()) && Data.showInfo) {
						Data.doBanking = !Data.doBanking;
					} else if (useRejuvenate.contains(p.getPoint()) && Data.showInfo) {
						Data.useRejuvenate = !Data.useRejuvenate;
					}
				}
				
				if (Data.showStats) {
					if (useMelee.contains(p.getPoint())) {
						Data.useMelee = true;
						Data.useRange = false;
						Data.useMagic = false;
					} else if (useRange.contains(p.getPoint())) {
						Data.useMelee = false;
						Data.useRange = true;
						Data.useMagic = false;
					} else if (useMagic.contains(p.getPoint())) {
						Data.useMelee = false;
						Data.useRange = false;
						Data.useMagic = true;
					} else if (skillToggle.contains(p.getPoint())) {
						if (Data.skillNum < 6) {
							Data.skillNum+=1;
						} else {
							Data.skillNum = 1;
						}
					}
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
