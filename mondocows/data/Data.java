package mondocows.data;

import java.util.ArrayList;
import java.util.HashMap;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Data {
	
	public static Tile[] ToBank = new Tile[] { new Tile(3068, 3505, 0), new Tile(3073, 3503, 0), new Tile(3077, 3500, 0), 
			new Tile(3082, 3500, 0), new Tile(3087, 3500, 0), new Tile(3092, 3501, 0), 
			new Tile(3094, 3496, 0) };
	
	public static Tile[] ToCows = new Tile[] { new Tile(3232, 3219, 0), new Tile(3236, 3222, 0), new Tile(3239, 3226, 0), 
			new Tile(3244, 3225, 0), new Tile(3249, 3225, 0), new Tile(3254, 3225, 0), 
			new Tile(3258, 3228, 0), new Tile(3259, 3233, 0), new Tile(3259, 3238, 0), 
			new Tile(3258, 3242, 0), new Tile(3255, 3246, 0), new Tile(3252, 3250, 0), 
			new Tile(3249, 3254, 0), new Tile(3248, 3259, 0), new Tile(3250, 3264, 0), 
			new Tile(3254, 3267, 0), new Tile(3257, 3270, 0), new Tile(3258, 3275, 0), 
			new Tile(3256, 3280, 0), new Tile(3256, 3285, 0) };
	
	public static Tile[] RoamPath = new Tile[] { new Tile(3250, 3290, 0), new Tile(3253, 3286, 0), new Tile(3256, 3281, 0), 
			new Tile(3258, 3276, 0), new Tile(3259, 3271, 0), new Tile(3259, 3266, 0), 
			new Tile(3259, 3261, 0) };
	
	public static Area BankPathArea = new Area(new Tile[] { new Tile(3058, 3511, 0), new Tile(3074, 3511, 0), new Tile(3074, 3506, 0), 
			new Tile(3100, 3506, 0), new Tile(3100, 3487, 0), new Tile(3089, 3487, 0), 
			new Tile(3089, 3489, 0), new Tile(3081, 3489, 0), new Tile(3081, 3496, 0), 
			new Tile(3058, 3496, 0) });
	
	public static Area ToCowsArea = new Area(new Tile[] { new Tile(3223, 3233, 0), new Tile(3242, 3233, 0), new Tile(3242, 3272, 0), 
			new Tile(3252, 3272, 0), new Tile(3252, 3254, 0), new Tile(3266, 3254, 0), 
			new Tile(3266, 3222, 0), new Tile(3239, 3222, 0), new Tile(3239, 3216, 0), 
			new Tile(3223, 3216, 0) });
	
	public static Area BankArea = new Area(new Tile[] { new Tile(3087, 3502, 0), new Tile(3100, 3502, 0), new Tile(3100, 3485, 0), 
			new Tile(3087, 3485, 0) });
	
	public static Area CowArea = new Area(new Tile[] { new Tile(3239, 3300, 0), new Tile(3271, 3300, 0), new Tile(3271, 3294, 0), 
			new Tile(3268, 3286, 0), new Tile(3268, 3276, 0), new Tile(3273, 3266, 0), 
			new Tile(3271, 3259, 0), new Tile(3269, 3256, 0), new Tile(3269, 3254, 0), 
			new Tile(3252, 3254, 0), new Tile(3252, 3272, 0), new Tile(3250, 3274, 0), 
			new Tile(3250, 3276, 0), new Tile(3248, 3278, 0), new Tile(3245, 3278, 0), 
			new Tile(3243, 3280, 0), new Tile(3243, 3281, 0), new Tile(3239, 3285, 0), 
			new Tile(3239, 3286, 0), new Tile(3241, 3288, 0), new Tile(3241, 3294, 0), 
			new Tile(3239, 3296, 0) });
	
	public static boolean paused;
	public static Timer runTime = new Timer(0);
	public static long startTime;
	public static String status = "Waiting...";
	public static String revision = "r001 - 7/01/2013 3:43PM";	
	public static double version = 0.1;
	
	public static boolean showLoot = false;
	public static boolean showInfo = false;
	public static boolean showStats = false;
	public static boolean showPaint = false;
	public static boolean setup = true;
	public static Timer setupTimer = new Timer(45*1000);
	
	public static int startLevel;
	public static int startExp;
	public static int startAtkLvl;
	public static int startStrLvl;
	public static int startDefLvl;
	public static int startConstLvl;
	public static int startRngLvl;
	public static int startMagLvl;
	public static int startAtkExp;
	public static int startStrExp;
	public static int startDefExp;
	public static int startConstExp;
	public static int startRngExp;
	public static int startMagExp;
	
	public static int oldKills;
	public static int killCounter;
	
	public static int totalProfit = 0;
	public static int cowhides = 0;
	public static int cowsKilled = 0;
	public static int trips = 0;
	public static boolean useRejuvenate = false;
	public static boolean doBanking = false;
	public static boolean useMelee = false;
	public static boolean useRange = false;
	public static boolean useMagic = false;
	public static int skillNum = 1;
	public static int selectedSkill;
	public static String skillString;
	public static ArrayList<Integer> loot = new ArrayList<Integer>();
	public static HashMap<String, Integer> itemPriceMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> lootListMap = new HashMap<String, Integer>();
	public static HashMap<Integer, Integer> itemPriceMapInt = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> lootListMapInt = new HashMap<Integer, Integer>();
	public static Timer lootTimer = new Timer(1);
	public static Tile lootTile;
	public static boolean tileReset = false;
	public static ArrayList<Tile> lootTiles = new ArrayList<Tile>();
	public static int bankedHides = 0;
	
	public static ArrayList<Integer> cows = new ArrayList<Integer>();
	public static int[] cowIDs = {12362, 12363, 12364, 12365, 12366};
	public static int cowhideID = 1739;	
	public static Tile inside = new Tile(3259+Random.nextInt(-2, 2), 3274+Random.nextInt(-2, 2), 0);
	
	public static int bankerID = 2759;
	public static WidgetChild teleportTabButton = Widgets.get(275, 46);
	public static WidgetChild homeTeleButton = Widgets.get(275, 15);
	public static WidgetChild edgevilleLodeButton = Widgets.get(1092, 45);
	public static WidgetChild lumbridgeLodeButton = Widgets.get(1092, 47);
	
	public static Timer waitTimer = new Timer(1);
	public static Timer hoverTimer = new Timer(1);
	
}
