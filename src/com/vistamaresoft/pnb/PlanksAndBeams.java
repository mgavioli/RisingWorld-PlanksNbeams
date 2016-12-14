/****************************
	P l a n k s A n d B e a m  s  -  A Rising World Java plug-in distributing non-standard planks and beams.

	PlanksAndBeams.java - The main plug-in class

	Created by : Maurizio M. Gavioli 2016-10-22

	(C) Maurizio M. Gavioli (a.k.a. Miwarre), 2016
	Licensed under the Creative Commons by-sa 3.0 license (see http://creativecommons.org/licenses/by-sa/3.0/ for details)

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
import net.risingworld.api.events.player.gui.PlayerGuiElementClickEvent;
import net.risingworld.api.objects.Inventory;
import net.risingworld.api.objects.Item;
import net.risingworld.api.objects.Player;

public class PlanksAndBeams extends Plugin implements Listener
{
	// SETTINGS with their default values
	public static final String	localeLanguageDef	= "en";

	public static		String	commandPrefix		= "/pnb";
	public static		int		costPerItem			= 1;
	public static		Locale	locale;

	// attribute keys
	public static final String	key_gui				= "com.vms.pnbGUI";

	// CONSTANTS
	public static final String	VERSION				= "0.2.0";
	public static final	String	publicName			= "Planks 'n Beams";
	public static final int		NUM_OF_QUICKSLOTS	= 5;
	public static final int		NUM_OF_INVSLOTS		= 32;
	public static final short	ORE_ID				= 100;
	public static final short	LUMBER_ID			= 102;
	public static final short	PLANK_ID			= 113;
	public static final short	BEAM_ID				= 114;
	public static final short	PLANKTRI_ID			= 124;
	public static final short	IRONINGOT_ID		= 171;
	public static final short	COPPERINGOT_ID		= 172;
	public static final short	DIRT_VAR			= 1;
	public static final short	STONE_VAR			= 3;
	public static final short	SANDSTONE_VAR		= 11;

	// not a real constant, cached for performance, as it is going to be used a lot!
	protected static final 		Inventory.SlotType[]	slotTypeValues	= Inventory.SlotType.values();

	// MATERIAL & RESOURCES
	public static final	short[]	resourceId			=		// the type ID of each used resource
		{ORE_ID,   ORE_ID,       ORE_ID,  LUMBER_ID,COPPERINGOT_ID,IRONINGOT_ID};
	public static final	short[]	resourceVar			=		// the type variation of each used resource
		{STONE_VAR,SANDSTONE_VAR,DIRT_VAR,0,        0,             0};
	// the range of known textures
	public static final	short	firstVariation		= 21;
	public static final	short	lastVariation		= 212;
	// the index (into resourceId and resourceVar) of the needed resource for each texture
	public static final short[]	resourcePerVariation	=
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

	//------------------
	// EVENTS
	//------------------

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

	/**	Called when the player clicks on the GUI

		@param	event	the click event
	*/
	@EventMethod
	public void onPlayerClick(PlayerGuiElementClickEvent event)
	{
		Player	player	= event.getPlayer();
		Gui		gui		= (Gui)player.getAttribute(key_gui);
		if (gui != null)
			gui.click(event.getGuiElement(), player);
	}

	//------------------
	// PUBLIC METHODS
	//------------------

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
		Gui		gui;
		if (!player.hasAttribute(key_gui))
			gui	= createGui(player);
		else
			gui	= (Gui)player.getAttribute(key_gui);
		if (gui != null)
			gui.show(player);
	}

	/**
		Gives the player the required items in exchange for resources.
		Checks the player has enough resources in the inventory.

		@param	player		the target player
		@param	type		the type of the items (PLANK_ID or BEAM_ID)
		@param	variation	the item variation (texture: 121 - 212)
		@param	quantity	the quantity of the items
		@return	one of the ERR_ return values
	*/
	public int buy(Player player, int type, int variation, int quantity)
	{
		if (variation < firstVariation || variation > lastVariation)
			return ERR_INVALID_PARAM;

		// retrieve the needed resource
		int		itemId		= resourceId[resourcePerVariation[variation-firstVariation]];
		int		itemVar		= resourceVar[resourcePerVariation[variation-firstVariation]];
		Item	item;
		Inventory	inv	= player.getInventory();
		int		cost		= quantity * costPerItem;
		// scan the inventory to collect the total number of resources and the slots where they are
		int		resources	= 0;					// the number of resources available in the player inventory
		ArrayList<Integer>	sourceSlots	= new ArrayList<Integer>();
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
		{
			player.sendTextMessage(Msgs.msg[Msgs.txt_no_resources]);
			return ERR_NO_RESOURCES;
		}
		// give the items to the player
		item	= inv.insertNewItem((short)type, variation, quantity);
		if (item == null)
		{
			player.sendTextMessage(Msgs.msg[Msgs.txt_newitem_failed]);
			return ERR_GENERIC;
		}
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
			else						// if item fulfils or exceeds the cost, remove only needed stones
			{
				inv.removeItem( (i & 0xFFFF), slotTypeValues[(i>>16)], cost);
				cost	= 0;
			}
			if (cost <= 0)				// when all resources are payed, stop
				break;
		}
		player.sendTextMessage(String.format(Msgs.msg[Msgs.txt_items_added], quantity,
				type == PLANK_ID ? Msgs.msg[Msgs.gui_typePlank] : Msgs.msg[Msgs.gui_typeBeam],
				variation));
		return ERR_SUCCESS;
	}

	//------------------
	// STATIC METHODS EXTERNALLY ACCESSIBLE
	//------------------

	//------------------
	// UTILITY PRIVATE METHODS
	//------------------

	private Gui createGui(Player player)
	{
		// the GPS GUI panel
		Gui	gui		= new Gui(player);
		player.setAttribute(key_gui, gui);
		return gui;
	}

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
			Integer	temp;
			commandPrefix	= "/" + settings.getProperty("command", commandPrefix);
			temp			= toInteger(settings.getProperty("costPerItem", Integer.toString(costPerItem)));
			costPerItem		= temp != null ? temp : costPerItem;
			// locale is a bit more complex
			String		strLocale		= settings.getProperty("locale", localeLanguageDef);
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
				locale	= new Locale(localeLanguageDef);
		} catch (IOException e) {
			locale	= new Locale(localeLanguageDef);
			return;					// settings are init'ed anyway: on exception, do nothing
		}
	}

	/**
		Returns txt as an integer number if it can be interpreted as one or 0 if it cannot.

 		@param	txt	the String to interpret as an int
 		@return	the equivalent int or null if txt cannot represent an integer.
	*/
	static protected Integer toInteger(String txt)
	{
		if (txt == null)
			return null;
		int val;
		try {
			val = Integer.decode(txt);
		} catch (NumberFormatException e) {		// txt cannot be parsed as a number
			return null;
		}
		return val;
	}

}
