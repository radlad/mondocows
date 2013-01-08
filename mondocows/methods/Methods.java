package mondocows.methods;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import mondocows.data.Data;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.SkillData.Rate;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class Methods {
	
	
	public static NumberFormat k = new DecimalFormat("###,###,###");
	public static NumberFormat z = new DecimalFormat("#");
	
	public static String numFormat(final int num) {
		if (num < 1000000) {
		return num / 1000 + "." + (num % 1000) / 100 + "K";
		} else {
			return num / 1000000 + "." + (num % 1000000) / 10000 + "M";
		}
	}
	
	public static int getAdrPercent() {		
		return Settings.get(679) / 10;		
	}
	
	public static int getXpHr(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.experience(Rate.HOUR, skill);		
	}

	public static long getTTNL(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.timeToLevel(Rate.HOUR, skill);
	}
	
	public static double getExpBarLength(int skill, int maxlength) {
		int xpGained = Skills.getExperience(skill);
        int xpCurrent = Skills.getExperienceRequired(Skills.getRealLevel(skill));
        int xpNext = Skills.getExperienceRequired(Skills.getRealLevel(skill) + 1);        
        //int xpBarLength = (xpCurrent / xpNext)*maxlength;
        double xpBarLength = ((xpGained - xpNext) / (xpNext - xpCurrent)) * maxlength;
        if (Skills.getRealLevel(skill) == 99) {
        	return 1 * maxlength;
        } else {
        	return xpBarLength;   
        }
	}
	
	public static double getExpPercent(int skill) {
		int xpGained = Skills.getExperience(skill);
        int xpCurrent = Skills.getExperienceRequired(Skills.getRealLevel(skill));
        int xpNext = Skills.getExperienceRequired(Skills.getRealLevel(skill) + 1);        
        //int xpBarLength = (xpCurrent / xpNext)*maxlength;
        double xpBarLength = ((xpGained - xpNext) / (xpNext - xpCurrent));
        return xpBarLength;   
        
	}
	
	public static int getPerHour(int integer) {
		int perHour = (int) (integer * 3600000D / (System.currentTimeMillis() - Data.startTime));
		return perHour;		
	}
	
    public static void drawTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.drawPolygon(p1);
                        }
        }
    }
    
    public static void fillTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.fillPolygon(p1);
                        }
        }
    }
	
	public static int getProfit() {
		Data.totalProfit = 0;
		for (int i : Data.loot) {
			if (Data.lootListMapInt.containsKey(i) && Data.itemPriceMapInt.containsKey(i)) {
				Data.totalProfit+=Data.lootListMapInt.get(i)*Data.itemPriceMapInt.get(i);		
			}		
		}
		return Data.totalProfit;
	}
	
	public static boolean isViewable(Entity n) {
		final Rectangle view = new Rectangle(0, 50, 513, 258);
		int x = n.getCentralPoint().x;
		int y = n.getCentralPoint().y;
		return x >= 0 && y >= 0 && x < view.getWidth() && y < view.getHeight();
	}
	
	public static boolean isLootNear(GroundItem i) {
		if (i != null) {
			for (Tile tile : Data.lootTiles) {
				if (i.getLocation().distance(tile) < 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean containsMouse(Entity e) {	    
	     Polygon[] polygons = e.getBounds();  
	     Point m = Mouse.getLocation();
	     if (e != null) {
		     for (Polygon p : polygons) {
		          if (p.contains(m)) {
		               return true;
		          }
		     }
	     }
	     return false;
	}

}
