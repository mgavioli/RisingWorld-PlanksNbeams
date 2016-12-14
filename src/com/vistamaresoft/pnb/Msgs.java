/****************************
	P l a n k s A n d B e a m  s  -  A Rising World Java plug-in distributing non-standard planks and beams.

	Messages.java - The text internationalisation class.

	Created by : Maurizio M. Gavioli 2016-10-22

	(C) Maurizio M. Gavioli (a.k.a. Miwarre), 2016
	Licensed under the Creative Commons by-sa 3.0 license (see http://creativecommons.org/licenses/by-sa/3.0/ for details)

*****************************/

package com.vistamaresoft.pnb;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Msgs
{
	// TEXT IDENTIFIERS
	public static final int	err_invalidCmd		= 0;
	public static final	int	gui_title			= 1;
	public static final	int	gui_texture			= 2;
	public static final	int	gui_type			= 3;
	public static final	int	gui_typePlank		= 4;
	public static final	int	gui_typeBeam		= 5;
	public static final	int	gui_typePlank3		= 6;
	public static final	int	gui_quantity		= 7;
	public static final	int	gui_buy				= 8;
	public static final int	gui_resourcesFmt	= 9;
	public static final int	gui_stoneName		= 10;
	public static final int	gui_sandstoneName	= 11;
	public static final int	gui_dirtName		= 12;
	public static final int	gui_lumberName		= 13;
	public static final int	gui_copperName		= 14;
	public static final int	gui_ironName		= 15;
	public static final int		firstResName		= 10;
	public static final int		lastResName			= 15;
	// Messages
	public static final int	txt_no_resources	= 16;
	public static final int	txt_newitem_failed	= 17;
	public static final int	txt_items_added		= 18;
	
	private static final int	LAST_TEXT	= txt_items_added;

	public static		String[]	msg		=
	{
		"Invalid P'nB command: '",
		"Planks 'n Beams",
		"Texture:",
		"Type:",
		"Plank",
		"Beam",
		"Triangle",
		"Quantity:",
		"\n BUY! \n ",
		"Required: %1$s x %2$d",
		"stone",
		"sandstone",
		"dirt",
		"lumber",
		"copper ingot",
		"iron ingot",
		"PnB: [#FF0000]You do not have enough resources to buy those items!",
		"Pnb: [#FF0000]Could not add the required items! Inventory full?",
		"Pnb: [#FFFF00]%1$d x %2$s (var. %3$d) added to inventory",
	};

	private static final	String		BUNDLE_NAME	= "/locale/messages";

	public static boolean init(String path, Locale locale)
	{
		if (locale == null)
			return false;
		String		country		= locale.getCountry();
		String		variant		= locale.getVariant();
		String		fname		= BUNDLE_NAME + "_" + locale.getLanguage();
		if (country.length() > 0)	fname += "_" + country;
		if (variant.length() > 0)	fname += "_" + variant;
		fname	+= ".properties";
		Properties settings	= new Properties();
		// NOTE : use getResourcesAsStream() if the setting file is included in the distrib. .jar)
		FileInputStream in;
		try
		{
		in = new FileInputStream(path + fname);
		settings.load(in);
		in.close();
		} catch (IOException e) {
			System.out.println("** Planks 'n Beam plug-in ERROR: Property file '" + fname + "' for requested locale '"+ locale.toString() + "' not found. Defaulting to 'en'");
			return false;
		}
		// Load strings from localised bundle
		for (int i = 0; i <= LAST_TEXT; i++)
			msg[i]	= settings.getProperty(String.format("%03d", i) );
		// a few strings require additional steps
		msg[gui_buy]	= "\n " + msg[gui_buy] + " \n ";
		return true;
	}

}
