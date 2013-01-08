package mondocows.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

import mondocows.combat.Combat;
import mondocows.data.Data;
import mondocows.methods.Methods;
import mondocows.nodes.Looting;

public class Paint {
	
	public static RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    public static Color color1 = new Color(0, 0, 0, 230);
    public static Color color2 = new Color(0, 0, 0, 250);
    public static Color color3 = new Color(0, 0, 0);
    public static Color color4 = new Color(52, 183, 255, 225);
    public static Color color5 = new Color(0, 235, 174, 225);
    public static Color color6 = new Color(211, 239, 255, 225);
    public static Color color7 = new Color(216, 251, 255, 202);
    public static Color color_skill = new Color(0, 0, 0, 0);
    public static Color color_npc = new Color(135, 206, 250, 225);
    public static Color color_npc2 = new Color(135, 206, 250, 100);
    public static Color color_item = new Color(0, 235, 174, 225);
    public static Color color_item2 = new Color(0, 235, 174, 100);

    public static BasicStroke stroke1 = new BasicStroke(1);
    public static BasicStroke stroke2 = new BasicStroke(2);

    public static Font font1 = new Font("Myriad Pro", 1, 16);
    public static Font font2 = new Font("Myriad Pro", 0, 9);
    public static Font font3 = new Font("Myriad Pro", 0, 10);
    public static Font font4 = new Font("Myriad Pro", 0, 12);
    
    public static Color color1_setup = new Color(0, 0, 0, 215);
    public static Color color2_setup = new Color(0, 0, 0);
    public static Color color3_setup = new Color(0, 219, 252, 225);
    public static Color color4_setup = new Color(255, 255, 255, 225);
    public static Color color5_setup = new Color(19, 230, 255, 202);
    public static Color color6_setup = new Color(241, 0, 0, 225);

    public static BasicStroke stroke1_setup = new BasicStroke(3);
    public static BasicStroke stroke2_setup = new BasicStroke(2);

    public static Font font1_setup = new Font("Myriad Pro", 1, 32);
    public static Font font2_setup = new Font("Myriad Pro", 2, 20);
    public static Font font3_setup = new Font("Myriad Pro", 2, 16);
    public static Font font4_setup = new Font("Myriad Pro", 0, 12);
    public static Font font5_setup = new Font("Myriad Pro", 2, 12);

    
    private static class MouseTrail {
    	private final static int SIZE = 25;
    	private Point[] points;
    	private int index;
    	public MouseTrail() {
	    	points = new Point[SIZE];
	    	index = 0;
    	}
    	public void add(Point p) {
	    	points[index++] = p;
	    	index %= SIZE;
    	}
    	public void draw(Graphics g1) {
	    	double alpha = 0;
	    	for (int i = index; i != (index == 0 ? SIZE - 1 : index - 1); i = (i + 1)
	    		 % SIZE) {
		    	if (points[i] != null && points[(i + 1) % SIZE] != null) {
		    		 Color trailColor = new Color(0, Random.nextInt(150, 210), Random.nextInt(50, 255), (int) alpha);
		    		 BasicStroke stroke2 = new BasicStroke(2);
		    		 ((Graphics2D) g1).setStroke(stroke2);
		    		 g1.setColor(trailColor);
		    		 g1.drawLine(points[i].x, points[i].y, points[(i + 1) % SIZE].x, points[(i + 1) % SIZE].y);
		    		 alpha += (255.0 / SIZE);
		    	}
	    	}
    	}
    }

    static MouseTrail trail = new MouseTrail();
	
	public static void onRepaint(Graphics g1) {
		
	    Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(antialiasing);
        
        if (Data.setup) {
            g.setColor(color1_setup);
            g.fillRect(0, 0, (int) Game.getDimensions().getWidth(), (int) Game.getDimensions().getHeight());
            g.setFont(font1_setup);
            g.setColor(color2_setup);
            g.drawString("MondoCows", 320, 171);
            g.setColor(color3_setup);
            g.drawString("MondoCows", 319, 170);
            g.setFont(font2_setup);
            g.setColor(color2_setup);
            g.drawString("Getting Started", 345, 198);
            g.setColor(color4_setup);
            g.drawString("Getting Started", 344, 197);
            g.setFont(font3_setup);
            g.drawString("Requirements:", 248, 230);
            g.setFont(font4_setup);
            g.drawString("• Your weapon (and ammo) of choice", 359, 229);
            g.drawString("• The relevant skills placed on your action bar", 359, 246);
            g.drawString("• Have Edgeville and Lumbridge lodestones unlocked", 359, 263);
            g.drawString("• If using Rejuvenate, have a shield in inventory", 360, 281);
            g.setFont(font3_setup);
            g.drawString("Settings:", 248, 310);
            g.setFont(font4_setup);
            g.drawString("Banking", 370, 316);
            g.drawString("Use Rejuvenate", 473, 316);
            g.drawString("Use Melee", 307, 343);
            g.drawString("Use Range", 407, 343);
            g.drawString("Use Magic", 505, 343);
            g.setColor(color5_setup);
            g.setStroke(stroke1_setup);
            g.drawLine(158, 206, 658, 206);
            g.drawLine(158, 362, 658, 362);
            g.setStroke(stroke2_setup);
            g.drawRect(341, 305, 15, 15);
            g.drawRect(439, 305, 15, 15);
            g.drawRect(274, 333, 15, 15);
            g.drawRect(376, 333, 15, 15);
            g.drawRect(475, 333, 15, 15);
            g.setColor(color6_setup);
            g.setColor(Data.doBanking ? Color.GREEN.brighter() : Color.RED.brighter());
            g.fillRect(345, 309, 8, 7);
            g.setColor(Data.useRejuvenate ? Color.GREEN.brighter() : Color.RED.brighter());
            g.fillRect(443, 309, 8, 7);
            g.setColor(Data.useMelee ? Color.GREEN.brighter() : Color.RED.brighter());
            g.fillRect(278, 337, 8, 7);
            g.setColor(Data.useRange ? Color.GREEN.brighter() : Color.RED.brighter());
            g.fillRect(380, 337, 8, 7);
            g.setColor(Data.useMagic ? Color.GREEN.brighter() : Color.RED.brighter());
            g.fillRect(479, 337, 8, 7);
            g.setFont(font5_setup);
            g.setColor(color4_setup);
            g.drawString("Click anywhere outside of the center to start with selected settings.", 260, 387);
            g.drawString("Default Settings: No banking, no skills, no rejuvenate.", 286, 405);
            g.drawString("This setup will automatically start with selected settings in " + (Data.setupTimer.getRemaining()/1000) + " seconds.", 240, 481);	        
        } else {        
	        if (Data.showPaint){   
	        	
		        Graphics2D spinG = (Graphics2D) g.create();
		        
		        trail.add(Mouse.getLocation());
		        trail.draw(g);
		        
		        g.setStroke(stroke2);
		        g.setColor(Mouse.isPressed() ? Color.RED : color6);
		        spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2 * Math.PI / 180.0, Mouse.getX(), Mouse.getY());
		        spinG.drawLine(Mouse.getX()-3, Mouse.getY()-3, Mouse.getX()+3, Mouse.getY()+3);
		        spinG.drawLine(Mouse.getX()+3, Mouse.getY()-3, Mouse.getX()-3, Mouse.getY()+3);
	        	
		        g.setColor(color1);
		        g.fillRect(6, 394, 490, 18);
		        g.setColor(color2);
		        g.setStroke(stroke1);
		        g.drawRect(6, 394, 490, 18);	        
		        g.drawRect(353, 397, 34, 12);
		        g.drawRect(396, 397, 34, 12);
		        g.drawRect(440, 397, 34, 12);
		        g.setFont(font1);
		        g.setColor(color3);
		        g.drawString("MondoCows", 13, 410);
		        g.setColor(color4);
		        g.drawString("MondoCows", 12, 409);
		       // g.setFont(font2);
		       // g.setColor(color5);
		       // g.drawString("" + Data.version, 101, 409);
		        g.setFont(font3);
		        g.setColor(color4);
		        g.drawString("Run Time:", 124, 407);
		        g.setColor(color6);
		        g.drawString("" + Data.runTime.toElapsedString(), 172, 407);
		        g.drawString("info", 362, 407);
		        g.drawString("loot", 405, 407);
		        g.drawString("stats", 447, 407);
		        g.drawString("" + Data.status, 257, 407);
		        g.setColor(color4);
		        g.drawString("Status: ", 219, 407);
		        g.setColor(color7);
		        g.drawLine(484, 400, 490, 406);
		        g.drawLine(490, 400, 484, 406);
		        
		        
		        if (Data.showLoot) {
		        	g.setColor(color2);
		            g.drawLine(415, 415, 415, 432);
		            g.setColor(color1);
		            g.fillRect(180, 435, 250, 15);
		            g.setColor(color2);
		            g.drawRect(180, 435, 250, 15);
		            g.setColor(color6);
		            g.drawString(Data.cowhides + " (" + Methods.getPerHour(Data.cowhides) + " p/h)", 239, 447);
		            g.drawString(Methods.numFormat(Data.totalProfit) + " (" + Methods.numFormat(Methods.getPerHour(Data.totalProfit)) + " p/h)", 337, 447);
		            g.setColor(color2);
		            g.drawLine(415, 415, 415, 432);
		            g.setColor(color4);
		            g.drawString("Cowhides: ", 186, 447);
		            g.drawString("Profit: ", 306, 447);
		        }
		        
		        if (Data.showInfo) {
		            g.setColor(color2);
		            g.drawLine(372, 415, 372, 423);
		            g.drawLine(372, 423, 177, 423);
		        	g.setColor(color1);
		            g.fillRect(11, 417, 161, 85);
		            g.setColor(color2);
		            g.drawRect(11, 417, 161, 85);
		            g.setFont(font4);
		            g.setColor(color4);
		            g.drawString("Information", 16, 431);
		            g.setFont(font3);
		            g.drawString("Cows killed: ", 16, 449);
		            g.drawString("version 0.1 | Updated 01/06/2013", 21, 498);
		            g.drawString("Trips made:", 16, 463);
		            g.drawString("Bank", 38, 481);
		            g.drawString("Use Rejuvenate", 94, 481);
		            g.setColor(color7);
		            g.drawLine(14, 435, 169, 435);
		            g.setColor(Data.doBanking ? Color.GREEN.brighter() : Color.RED.brighter());
		            g.fillRect(22, 474, 7, 7);
		            g.setColor(Data.useRejuvenate ? Color.GREEN.brighter() : Color.RED.brighter());
		            g.fillRect(77, 474, 7, 7);
		            g.setColor(color6);
		            g.drawString(Data.killCounter + " (" + Methods.getPerHour(Data.killCounter) + " p/h)", 71, 449);
		            g.drawString(Data.trips + " (" + Methods.getPerHour(Data.trips) + " p/h)", 71, 463);
		            g.setColor(color2);
		            g.drawRect(19, 471, 12, 12);
		            g.drawRect(74, 471, 12, 12);
		        }
		        
		        if (Data.showStats) {
		        	 g.setColor(color1);
		             g.fillRect(180, 455, 310, 47);
		             g.setColor(color2);
		             g.drawRect(180, 455, 310, 47);
		             g.drawRect(191, 481, 287, 15);
		             g.setColor(color_skill);
		             g.fillRect(192, 482, 286, 14);
		             g.setColor(color2);
		             g.drawLine(459, 415, 459, 451);
		             g.setFont(font4);
		             g.setColor(color4);
		             g.drawString("Statistics", 184, 470);
		             g.setFont(font3);
		             g.drawString("Skills to use:", 256, 469);
		             g.drawString("Melee", 334, 469);
		             g.drawString("Range", 390, 469);
		             g.drawString("Magic", 447, 469);
		             g.setColor(color7);
		             g.drawLine(183, 475, 486, 475);
		             g.setColor(color6);
		             g.drawString("[ " + Data.skillString + ": " + Skills.getRealLevel(Data.selectedSkill) + " (+" + (Skills.getRealLevel(Data.selectedSkill) - Data.startLevel) + ") | " + Methods.getExpPercent(Data.selectedSkill) + "% | " + Methods.numFormat(Methods.getXpHr(Data.selectedSkill)) + " xp/h | TTL: " + Methods.getTTNL(Data.selectedSkill) + " ]", 221, 493);
		             g.setColor(color4);
		             g.setColor(Data.useMelee ? Color.GREEN.brighter() : Color.RED.brighter());
		             g.fillRect(319, 462, 7, 7);
		             g.setColor(Data.useRange ? Color.GREEN.brighter() : Color.RED.brighter());
		             g.fillRect(374, 462, 7, 7);
		             g.setColor(Data.useMagic ? Color.GREEN.brighter() : Color.RED.brighter());
		             g.fillRect(431, 462, 7, 7);
		             g.setColor(color2);
		             g.drawRect(316, 459, 12, 12);
		             g.drawRect(371, 459, 12, 12);
		             g.drawRect(428, 459, 12, 12);
		        }
			}
	        g.setColor(color2);
	        g.drawRect(482, 492, 10, 10);
        }         
    }


}
