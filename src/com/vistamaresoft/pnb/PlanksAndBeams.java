/****************************
	P l a n k s A n d B e a m  s  -  A Rising World Java plug-in generating non-standard planks and beams.

	PlanksAndBeams.java - The main plug-in class

	Created by : Maurizio M. Gavioli 2016-10-22

	(C) Maurizio M. Gavioli (a.k.a. Miwarre), 2016-18
	Licensed under the GNU General Public License v3.0

*****************************/

package com.vistamaresoft.pnb;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import net.risingworld.api.Plugin;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerCommandEvent;
import net.risingworld.api.objects.Inventory;
import net.risingworld.api.objects.Item;
import net.risingworld.api.objects.Player;

public class PlanksAndBeams extends Plugin implements Listener
{
	// SETTINGS with their default values

	/**
	 *
	 */
	public static final String	LocaleLanguageDef	= "en";

	public static		String	commandPrefix		= "/pnb";
	public static		int		costPerItem			= 1;
	public static		int		windowResMult		= 5;
	public static		boolean	freeForAdmin		= false;
	public static		boolean	freeForCreative		= false;
	public static		Locale	locale;

	// CONSTANTS
	public static final String	VERSION				= "0.5.0";
	public static final	String	publicName			= "Planks 'n Beams";
	public static final int		NUM_OF_QUICKSLOTS	= 5;
	public static final int		NUM_OF_INVSLOTS		= 32;
	public static final short	ORE_ID				= 309;
	public static final short	LUMBER_ID			= 265;
	public static final short	PLANK_ID			= 760;
	public static final short	BEAM_ID				= 761;
	public static final short	WINDOW1_ID			= 771;	// frame
	public static final short	WINDOW2_ID			= 772;	// frame + vert.
	public static final short	WINDOW3_ID			= 773;	// frame + vert. + high bar
	public static final short	WINDOW4_ID			= 773;	// frame + vert. + middle bar
	public static final short	PLANKTRI_ID			= 763;
	public static final short	LOG_ID				= 762;
	public static final short	IRONINGOT_ID		= 311;
	public static final short	COPPERINGOT_ID		= 310;
	public static final short	DIRT_VAR			= 1;
	public static final short	STONE_VAR			= 3;
	public static final short	GRAVEL_VAR			= 4;
	public static final short	SAND_VAR			= 9;
	public static final short	SANDSTONE_VAR		= 11;

	// not a real constant, cached for performance, as it is going to be used a lot!
	protected static final 		Inventory.SlotType[]	slotTypeValues	= Inventory.SlotType.values();

	// MATERIAL & RESOURCES
	public static final	short[]	resourceId			=		// the type ID of each used resource
		{ORE_ID,   ORE_ID,       ORE_ID,  LUMBER_ID,COPPERINGOT_ID,IRONINGOT_ID,ORE_ID,		ORE_ID};
	public static final	short[]	resourceVar			=		// the type variation of each used resource
		{STONE_VAR,SANDSTONE_VAR,DIRT_VAR,0,        0,             0,			GRAVEL_VAR, SAND_VAR};
	// the range of known textures
	public static final	short	firstVariation		= 21;
	public static final	short	lastVariation		= 218;
	// the index (into resourceId and resourceVar) of the needed resource for each texture
	public static final short[]	resourcePerVariation	=
	// 0: stone			5: iron ingot
	// 1: sandstone		6: gravel
	// 2: dirt			7: sand
	// 3: lumber
	// 4: copper ingot
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	// 21-32   stone - stone bricks: stone
		  1, 1, 1, 1,							// 33-36   sandstone: sandstone
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	// 37-48   cobblestone: stone
		  2, 2, 2, 2,							// 49-52   loam: dirt
		  0, 0, 0, 0, 0, 0, 0, 0,				// 53-60   marble: stone
		  3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,		// 61-71   wood block: lumber
		  3, 3, 3, 3, 3, 3, 3, 3, 3,			// 72-80   wood plank: lumber
		  0, 0, 0, 0,							// 81-84   stone tiles: stone
		  3, 3, 3, 3, 3, 3, 3, 3, 3,			// 85-93   addit. wood block: lumber
		  3, 3, 3, 3, 3, 3, 3,					// 94-100  addit. wood plank: lumber
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	// 101-112 addit. cobble stone: stone
		  0, 0, 0, 0, 0, 0, 0, 0, 0,			// 113-121 addit. stone-stone bricks: stone
		  0, 0, 0,								// 122-124 asphalt: stone
		  0, 0, 0, 0, 0, 0,						// 125-130 concrete: stone
		  0, 0, 0, 0, 0, 0,						// 131-136 concrete plates: stone
		  0, 0, 0, 0,							// 137-140 reinforced concrete: stone
		  0, 0, 0, 0, 0, 0, 0, 0,				// 141-148 plaster: stone
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,			// 149-158 tiles: stone
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	// 159-170 marble tiles: stone
		  4, 4, 4, 4,							// 171-174 copper: copper ingot
		  5, 5, 5, 5, 5, 5, 5, 5, 5, 5,			// 175-184 metal: iron ingot
		  5, 5, 5, 5, 5, 5, 5, 5, 5,			// 185-193 metal plates: iron ingot
		  5, 5, 5, 5, 5,						// 194-198 recycled metal: iron ingot
		  0, 0, 0, 0, 0, 0,						// 199-204 addit. plaster: stone
		  0, 0, 0, 0, 0, 0, 0, 0,				// 205-212 ornamental: stone
		  2, 2, 0, 6, 2, 7						// 213-218 natural: various
		};

	// RETURN CODES
	public static final int		ERR_SUCCESS			= 0;
	public static final int		ERR_INVALID_PARAM	= -1;
	public static final int		ERR_NO_RESOURCES	= -2;
	public static final int		ERR_GENERIC			= -3;

	// FIELDS
	//
	protected static	PlanksAndBeams		plugin;
	protected static	String				pluginPath;

	//****************************
	//	EVENTS
	//*****************************

	@Override
	public void onEnable()
	{
		plugin		= this;
		pluginPath	= getPath();
		initSettings();
		Msgs.init(pluginPath, locale);
		System.out.println("Planks 'n Beams "+VERSION+" loaded successfully!");
		registerEventListener(this);
	}

	@Override
	public void onDisable()
	{
		unregisterEventListener(this);
		System.out.println("Planks 'n Beams "+VERSION+" unloaded successfully!");
	}

	/**	Called when the player issues a command ("/...") in the chat window
	
		@param event	the command event
	*/
	@EventMethod
	public void onPlayerCommand(PlayerCommandEvent event)
	{
		String[]	cmd		= event.getCommand().split(" ");

		if (cmd[0].equals(commandPrefix) )
			mainGui(event.getPlayer());
	}

	//****************************
	//	PUBLIC Plug-in Central METHODS
	//*****************************

	public String getPublicName()
	{
		return publicName;
	}

	/**
		Displays the GPS control panel for the given player.

		@param	player	the player
	*/
	public void mainGui(Player player)
	{
		Gui	gui		= new Gui(player);
		gui.show(player);
	}

	//****************************
	//	PUBLIC METHODS
	//*****************************

	/**
		Gives the player the required items in exchange for resources.
		Checks the player has enough resources in the inventory.

		@param	player		the target player
		@param	type		the type of the items (PLANK_ID, BEAM_ID or PLANKTRI_ID)
		@param	variation	the item variation (texture: 21 - 212)
		@param	quantity	the quantity of the items	(1 - 64)
		@return	one of the ERR_ return values
	*/
	public int buy(Player player, int type, int variation, int quantity)
	{
		if (variation < firstVariation || variation > lastVariation)
			return ERR_INVALID_PARAM;

		// retrieve the needed resource
		int		itemId		= resourceId[resourcePerVariation[variation-firstVariation]];
		int		itemVar		= resourceVar[resourcePerVariation[variation-firstVariation]];
		Boolean	freeCreative= (Boolean)player.getPermissionValue("creative_freecrafting");
		int		cost		= player.isAdmin() && PlanksAndBeams.freeForAdmin ||
					player.isCreativeModeEnabled() && freeCreative && PlanksAndBeams.freeForCreative ? 0
					: quantity * costPerItem;
		// scan the inventory to collect the total number of resources and the slots where they are
		Item	item;
		Inventory	inv	= player.getInventory();
		int		resources	= 0;					// the number of resources available in the player inventory
		ArrayList<Integer>	sourceSlots	= new ArrayList<>();
		if (cost > 0)
			for (int invType = 0; invType < slotTypeValues.length; invType++)
			{
				Inventory.SlotType	slotType	= slotTypeValues[invType];
				for (int j = 0; j < inv.getSlotCount(slotType); j++)
				{
					if ( (item	= inv.getItem(j, slotType)) == null)
						continue;
					if (item.getTypeID() == itemId && item.getVariation() == itemVar)
					{
						resources	+= item.getStacksize();
						sourceSlots.add( (invType << 16) + j);
					}
				}
			}
		// does the player have enough resources to pay for the items?
		if (resources < cost)
			return ERR_NO_RESOURCES;
		// give the items to the player
		item	= inv.insertNewItem((short)type, variation, quantity);
		if (item == null)
			return ERR_GENERIC;
		// scan the collected slots to withdraw the corresponding number of resources
		int	size;
		for (Integer i : sourceSlots)
		{
			item	= inv.getItem( (i & 0xFFFF), slotTypeValues[(i>>16)]);
			size	= item.getStacksize();
			if (size <= cost)			// if item does not -- or barely -- fulfil the cost, remove it entirely
			{
				inv.removeItem( (i & 0xFFFF), slotTypeValues[(i>>16)]);
				cost	-= size;
			}
			else						// if item fulfils or exceeds the cost, remove only needed resources
			{
				inv.removeItem( (i & 0xFFFF), slotTypeValues[(i>>16)], cost);
				cost	= 0;
			}
			if (cost <= 0)				// when all resources are payed, stop
				break;
		}
		return ERR_SUCCESS;
	}

	//****************************
	//	PRIVATE HELPER METHODS
	//*****************************

	/**
		Initialises settings from settings file.
	*/
	private void initSettings()
	{
		// create and load settings from disk
		Properties settings	= new Properties();
		// NOTE : use getResourcesAsStream() if the setting file is included in the distrib. .jar)
		FileInputStream in;
		try {
			in = new FileInputStream(getPath() + "/settings.properties");
			settings.load(in);
			in.close();
			// fill global values
			commandPrefix	= "/" + settings.getProperty("command", commandPrefix);
			costPerItem		= toInteger(settings.getProperty("costPerItem"), costPerItem);
			freeForAdmin	= (toInteger(settings.getProperty("freeForAdmin"), 0) != 0);
			freeForCreative	= (toInteger(settings.getProperty("freeForCreative"), 0) != 0);
			// locale is a bit more complex
			String		strLocale		= settings.getProperty("locale", LocaleLanguageDef);
			String[]	localeParams	= strLocale.split("-");
			if (localeParams.length > 0)
			{
				if (localeParams.length > 1 && localeParams[2].length() > 0)
				{
					if (localeParams.length > 2 && localeParams[2].length() > 0)
						locale	= new Locale(localeParams[0], localeParams[1], localeParams[2]);
					else
						locale	= new Locale(localeParams[0], localeParams[1]);
				}
				else
					locale	= new Locale(localeParams[0]);
			}
			else
				locale	= new Locale(LocaleLanguageDef);
		} catch (IOException e) {
			locale	= new Locale(LocaleLanguageDef);
//			return;					// settings are init'ed anyway: on exception, do nothing
		}
	}

	/**
		Returns txt as an integer number if it can be interpreted as one or defValue if it cannot.

 		@param	txt			the String to interpret as an int.
 		@param	defValue	the value to return on error.
 		@return	the equivalent int or defValue if txt cannot represent an integer.
	*/
	static protected int toInteger(String txt, int defValue)
	{
		if (txt == null)
			return defValue;
		int val;
		try {
			val = Integer.decode(txt);
		} catch (NumberFormatException e) {		// txt cannot be parsed as a number
			return defValue;
		}
		return val;
	}

}
