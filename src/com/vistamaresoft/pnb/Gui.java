/****************************
	P l a n k s A n d B e a m  s  -  A Rising World Java plug-in distributing non-standard planks and beams.

	Gui.java - The plug-in GUI.

	Created by : Maurizio M. Gavioli 2016-10-22

	(C) Maurizio M. Gavioli (a.k.a. Miwarre), 2016
	Licensed under the Creative Commons by-sa 3.0 license (see http://creativecommons.org/licenses/by-sa/3.0/ for details)

*****************************/

package com.vistamaresoft.pnb;

import java.io.IOException;
import com.vistamaresoft.rwgui.GuiDialogueBox;
import com.vistamaresoft.rwgui.GuiLayout;
import com.vistamaresoft.rwgui.GuiMessageBox;
//import com.vistamaresoft.rwgui.GuiTitleBar;
//import com.vistamaresoft.rwgui.GuiUsersMenu;
import com.vistamaresoft.rwgui.RWGui;
import com.vistamaresoft.rwgui.RWGui.RWGuiCallback;
//import net.risingworld.api.gui.GuiElement;
import net.risingworld.api.gui.GuiImage;
import net.risingworld.api.gui.GuiLabel;
//import net.risingworld.api.gui.GuiPanel;
//import net.risingworld.api.gui.PivotPosition;
//import net.risingworld.api.objects.Inventory;
//import net.risingworld.api.objects.Item;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.ImageInformation;

public class Gui extends GuiDialogueBox
{
	// CONSTANTS
	//
	private static final	int		NUM_OF_IMG_ROWS	= 6;
	private static final	int		NUM_OF_IMG_COLS	= 8;
	private static final	int		IMAGE_HEIGHT	= 64;
	private static final	int		IMAGE_WIDTH		= 64;
	// The default position (relative to screen size) and size (absolute) of the GUI panel
	// Position centred in the screen
	// Size has been determined by trials and errors to fit all the required text.
//	private static final	float	PANEL_XPOS		= 0.5f;
//	private static final	float	PANEL_YPOS		= 0.5f;
//	private static final	int		PANEL_WIDTH		= IMAGE_WIDTH*NUM_OF_IMG_COLS + RWGui.BUTTON_SIZE + RWGui.BORDER*11;
//	private static final	int		PANEL_HEIGHT	=
//		GuiTitleBar.TITLEBAR_HEIGHT + RWGui.ITEM_SIZE*5 + RWGui.BUTTON_SIZE*2 + IMAGE_HEIGHT*NUM_OF_IMG_ROWS + RWGui.BORDER*10;
	// The IMAGE controls
//	private static final	int		IMAGE_BORDERCOL	= RWGui.ACTIVE_COLOUR;
//	private static final	int		TEXTURELBL_XPOS	= RWGui.BORDER;
//	private static final	int		TEXTURELBL_YPOS	= PANEL_HEIGHT - (RWGui.BORDER + GuiTitleBar.TITLEBAR_HEIGHT);
//	private static final	int		IMAGE0_XPOS		= RWGui.BORDER;
//	private static final	int		IMAGE0_YPOS		= TEXTURELBL_YPOS - (RWGui.ITEM_SIZE+RWGui.BORDER);
//	private static final	int		PGUPBUTT_XPOS	= PANEL_WIDTH - (RWGui.BUTTON_SIZE + RWGui.BORDER);
//	private static final	int		PGUPBUTT_YPOS	= IMAGE0_YPOS;
//	private static final	int		PGDNBUTT_XPOS	= PGUPBUTT_XPOS;
//	private static final	int		PGDNBUTT_YPOS	= IMAGE0_YPOS - IMAGE_HEIGHT*NUM_OF_IMG_ROWS
//			- RWGui.BORDER * (NUM_OF_IMG_ROWS-1) + RWGui.BUTTON_SIZE;
	// The TYPE controls
//	private static final	int		TYPELBL_XPOS	= RWGui.BORDER;
//	private static final	int		TYPELBL_YPOS	= IMAGE0_YPOS - (IMAGE_HEIGHT + RWGui.BORDER) * NUM_OF_IMG_ROWS;
//	private static final	int		PLANKCHK_XPOS	= RWGui.BORDER*2 + IMAGE_WIDTH;
//	private static final	int		PLANKLBL_XPOS	= PLANKCHK_XPOS + RWGui.BUTTON_SIZE + RWGui.BORDER;
//	private static final	int		BEAMCHK_XPOS	= RWGui.BORDER*4 + IMAGE_WIDTH*3;
//	private static final	int		BEAMLBL_XPOS	= BEAMCHK_XPOS + RWGui.BUTTON_SIZE + RWGui.BORDER;
	// The QUANTITY controls
//	private static final	int		QUANTLBL_XPOS	= RWGui.BORDER;
//	private static final	int		QUANTLBL_YPOS	= TYPELBL_YPOS - (RWGui.BUTTON_SIZE + RWGui.BORDER);
//	private static final	int		MINUS_XPOS		= PLANKCHK_XPOS;
//	private static final	int		PLUS_XPOS		= IMAGE_WIDTH*2 + RWGui.BORDER*3;
//	private static final	int		QUANTTXT_XPOS	= (MINUS_XPOS + PLUS_XPOS + RWGui.BUTTON_SIZE) / 2 ;
//	private static final	int		RESLBL_XPOS		= IMAGE_WIDTH*4 + RWGui.BORDER*5;
//	private static final	int		RESLBL_YPOS		= QUANTLBL_YPOS;
	// The BUY button
//	private static final	int		DO_XPOS			= (PANEL_WIDTH / 2);
//	private static final	int		DO_YPOS			= RWGui.ITEM_SIZE + RWGui.BORDER*2;
	// Constants
	private static final	int		NUM_OF_IMAGES	= NUM_OF_IMG_ROWS * NUM_OF_IMG_COLS;
	private static final	int		NUM_OF_TEXTURES	= 192;
//	private static final	int		TYPE_PLANK		= 0;
//	private static final	int		TYPE_BEAM		= 0;

	private static final	int		PGUPBUTT_ID		= (NUM_OF_IMG_COLS * NUM_OF_IMG_ROWS) + 1;
	private static final	int		PGDNBUTT_ID		= PGUPBUTT_ID + 1;
	private static final	int		PLANKBUTT_ID	= PGDNBUTT_ID + 1;
	private static final	int		BEAMBUTT_ID		= PLANKBUTT_ID + 1;
	private static final	int		PLANK3BUTT_ID	= BEAMBUTT_ID + 1;
	private static final	int		MINUSBUTT_ID	= PLANK3BUTT_ID + 1;
	private static final	int		PLUSBUTT_ID		= MINUSBUTT_ID + 1;
	private static final	int		DOBUTT_ID		= PLUSBUTT_ID + 1;

	// FIELDS
	//
	private	int						textureFirst;	// the index of the first texture shown
	private	int						textureFirstOld;
	private	int						imageSel;		// the index of the selected image
	private	int						imageSelOld;
	private GuiImage[]				images;			// the texture images
	private	GuiImage				pgUpButt;
	private GuiImage				pgDnButt;
//	private	GuiLabel				textureLabel;
//	private	GuiTitleBar				titleBar;
	private	int						type;
//	private	GuiLabel				typeLabel;
	private	GuiImage				plankCheck;
//	private GuiLabel				plankLabel;
	private	GuiImage				beamCheck;
//	private GuiLabel				beamLabel;
	private	GuiImage				plank3Check;
	private int						quant;
	private GuiImage				minusButt;
//	private GuiLabel				quantLabel;
	private GuiLabel				quantText;
	private GuiImage				plusButt;
	private GuiLabel				resourcesLabel;
//	private GuiLabel				doButt;
	// to convert an image index into a variation number
	private short[]					image2variation	=
		{21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,113,114,115,116,117,118,119,120,121,	//stone - stone bricks: stone
		 33, 34, 35, 36,							// sandstone: sandstone
		 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,101,102,103,104,105,106,107,108,109,110,111,112,	// cobblestone: stone
		 49, 50, 51, 52,							// loam: dirt
		 53, 54, 55, 56, 57, 58, 59, 60,			// marble: stone
		 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 85, 86, 87, 88, 89, 90, 91, 92, 93,		// wood block: log
		 72, 73, 74, 75, 76, 77, 78, 79, 80, 94, 95, 96, 97, 98, 99,100,						// wood plank: log
		 81, 82, 83, 84,							// stone tiles: stone
		 122,123,124,								// asphalt: stone
		 125,126,127,128,129,130,					// concrete: stone
		 131,132,133,134,135,136,					// concrete plates: stone
		 137,138,139,140,							// reinforced concrete: stone
		 141,142,143,144,145,146,147,148,199,200,201,202,203,204,		// plaster: stone
		 149,150,151,152,153,154,155,156,157,158,						// tiles: stone
		 159,160,161,162,163,164,165,166,167,168,169,170,				// marble tiles: stone
		 171,172,173,174,							// copper: copper ingot
		 175,176,177,178,179,180,181,182,183,184,	// metal: iron ingot
		 185,186,187,188,189,190,191,192,193,		// metal plates: iron ingot
		 194,195,196,197,198,						// recycled metal: iron ingot
		 205,206,207,208,209,210,211,212			// ornamental: stone
		};
	// to convert an image index into a texture name
	private	short[]					image2texture	=
		{ 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 92, 93, 94, 95, 96, 97, 98, 99,100,	//stone - stone bricks
		 12, 13, 14, 15,							// sandstone 
		 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91,	// cobblestone
		 28, 29, 30, 31,							// loam
		 32, 33, 34, 35, 36, 37, 38, 39,			// marble
		 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 64, 65, 66, 67, 68, 69, 70, 71, 72,		// wood block
		 51, 52, 53, 54, 55, 56, 57, 58, 59, 73, 74, 75, 76, 77, 78, 79,						// wood plank
		 60, 61, 62, 63,							// stone tiles
		 101,102,103,								// asphalt
		 104,105,106,107,108,109,					// concrete
		 110,111,112,113,114,115,					// concrete plates
		 116,117,118,119,							// reinforced concrete
		 120,121,122,123,124,125,126,127,178,179,180,181,182,183,		// plaster
		 128,129,130,131,132,133,134,135,136,137,						// tiles
		 138,139,140,141,142,143,144,145,146,147,148,149,				// marble tiles
		 150,151,152,153,							// copper
		 154,155,156,157,158,159,160,161,162,163,	// metal
		 164,165,166,167,168,169,170,171,172,		// metal plates
		 173,174,185,176,177,						// recycled metal
		 184,185,186,187,188,189,190,192			// ornamental
		};

	public Gui(Player player)
	{
		super(PlanksAndBeams.plugin, Msgs.msg[Msgs.gui_title], RWGui.LAYOUT_VERT, null);
		setCallback(new DlgHandler());
		// The TEXTURES
		addChild(new GuiLabel(Msgs.msg[Msgs.gui_texture], 0, 0, false));
		textureFirst		= 0;
		textureFirstOld		= -1;		// impossible index to force initial reloading of images
		imageSel			= 0;
		imageSelOld			= 0;
		images				= new GuiImage[NUM_OF_IMAGES];
		GuiLayout	layout	= addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_TOP);
		GuiLayout	layout2	= layout.addNewTableLayoutChild(NUM_OF_IMG_COLS, NUM_OF_IMG_ROWS, 0);
		for (int i = 0; i < NUM_OF_IMAGES; i++)
		{
			images[i]	= new GuiImage(0, 0, false, IMAGE_WIDTH, IMAGE_HEIGHT, false);
			layout2.addChild(images[i], i+1);
		}
		// The PGUP and PGDWON buttons
		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_VERT, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_SPREAD);
		pgUpButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		layout2.addChild(pgUpButt, PGUPBUTT_ID);
		RWGui.setImage(pgUpButt, RWGui.ICN_ARROW_UP);
		pgDnButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(pgDnButt, RWGui.ICN_ARROW_DOWN);
		layout2.addChild(pgDnButt, PGDNBUTT_ID);

		// The TYPE
		type		= PlanksAndBeams.PLANK_ID;
		layout	= addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_SPREAD | RWGui.LAYOUT_V_TOP);
		layout.addChild(new GuiLabel(Msgs.msg[Msgs.gui_type], 0, 0, false));

		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_TOP);
		plankCheck	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		layout2.addChild(plankCheck, PLANKBUTT_ID);
		RWGui.setImage(plankCheck, RWGui.ICN_CHECK);
		layout2.addChild(new GuiLabel(Msgs.msg[Msgs.gui_typePlank], 0, 0, false));

		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_TOP);
		beamCheck	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		layout2.addChild(plankCheck, BEAMBUTT_ID);
		RWGui.setImage(plankCheck, RWGui.ICN_UNCHECK);
		layout2.addChild(new GuiLabel(Msgs.msg[Msgs.gui_typeBeam], 0, 0, false));

		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_TOP);
		plank3Check	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		layout2.addChild(plank3Check, PLANK3BUTT_ID);
		RWGui.setImage(plankCheck, RWGui.ICN_UNCHECK);
		layout2.addChild(new GuiLabel(Msgs.msg[Msgs.gui_typePlank3], 0, 0, false));

		// The QUANTITY
		quant	= 1;
		layout	= addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_MIDDLE);
		layout.addChild(new GuiLabel(Msgs.msg[Msgs.gui_quantity], 0, 0, false));
		minusButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(minusButt, RWGui.ICN_MINUS);
		layout.addChild(minusButt, MINUSBUTT_ID);
		quantText	= new GuiLabel("1", 0, 0, false);
		layout.addChild(quantText);
		plusButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(plusButt, RWGui.ICN_PLUS);
		layout.addChild(plusButt, PLUSBUTT_ID);
		resourcesLabel	= new GuiLabel(0, 0, false);
		layout.addChild(resourcesLabel);

		// The BUY button
		layout	= addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_CENTRE | RWGui.LAYOUT_V_TOP);
		GuiLabel lbl	= new GuiLabel(Msgs.msg[Msgs.gui_buy], 0, 0, false);
		lbl.setColor(RWGui.ACTIVE_COLOUR);
		layout.addChild(lbl, DOBUTT_ID);
//		setVisible(false);
		updateImages();

/*
		// create a panel centred in the screen.
		super(PANEL_XPOS, PANEL_YPOS, true, PANEL_WIDTH, PANEL_HEIGHT, false);
		setPivot(PivotPosition.Center);
		setBorderColor(RWGui.BORDER_COLOUR);
		setBorderThickness(RWGui.BORDER_THICKNESS, false);
		setColor(RWGui.PANEL_COLOUR);
		// The TITLE BAR
		titleBar	= new GuiTitleBar(this, Msgs.msg[Msgs.gui_title], true);
		titleBar.relayout();
		// The TEXTURES
		textureLabel	= new GuiLabel(Msgs.msg[Msgs.gui_texture], TEXTURELBL_XPOS, TEXTURELBL_YPOS, false);
		textureLabel.setPivot(PivotPosition.TopLeft);
		textureLabel.setFontSize(RWGui.ITEM_SIZE);
		addChild(textureLabel);
		textureFirst		= 0;
		textureFirstOld	= -1;		// impossible index to force initial reloading of images
		imageSel		= 0;
		imageSelOld		= 0;
		images			= new GuiImage[NUM_OF_IMAGES];
		int			x	= IMAGE0_XPOS;
		int			y	= IMAGE0_YPOS;
		for (int i = 0; i < NUM_OF_IMAGES; i++)
		{
			images[i]	= new GuiImage(x, y, false, IMAGE_WIDTH, IMAGE_HEIGHT, false);
			images[i].setPivot(PivotPosition.TopLeft);
			images[i].setBorderColor(IMAGE_BORDERCOL);
			images[i].setClickable(true);
			addChild(images[i]);
			x	+= IMAGE_WIDTH + RWGui.BORDER;
			if (x > PANEL_WIDTH - (IMAGE_WIDTH + RWGui.BORDER) )
			{
				x	=  IMAGE0_XPOS;
				y	-= IMAGE_HEIGHT + RWGui.BORDER;
			}
		}
		// The PGUP and PGDWON buttons
		pgUpButt	= new GuiImage(PGUPBUTT_XPOS, PGUPBUTT_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		pgUpButt.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(pgUpButt, RWGui.ICN_ARROW_UP);
		pgUpButt.setClickable(true);
		pgDnButt	= new GuiImage(PGDNBUTT_XPOS, PGDNBUTT_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		pgDnButt.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(pgDnButt, RWGui.ICN_ARROW_DOWN);
		pgDnButt.setClickable(true);
		addChild(pgUpButt);
		addChild(pgDnButt);
		// The TYPE
		type		= PlanksAndBeams.PLANK_ID;
		typeLabel	= new GuiLabel(Msgs.msg[Msgs.gui_type], TYPELBL_XPOS, TYPELBL_YPOS, false);
		typeLabel.setPivot(PivotPosition.TopLeft);
		typeLabel.setFontSize(RWGui.ITEM_SIZE);
		plankCheck	= new GuiImage(PLANKCHK_XPOS, TYPELBL_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		plankCheck.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(plankCheck, RWGui.ICN_CHECK);
		plankCheck.setClickable(true);
		plankLabel	= new GuiLabel(Msgs.msg[Msgs.gui_typePlank], PLANKLBL_XPOS, TYPELBL_YPOS, false);
		plankLabel.setPivot(PivotPosition.TopLeft);
		plankLabel.setFontSize(RWGui.ITEM_SIZE);
		beamCheck	= new GuiImage(BEAMCHK_XPOS, TYPELBL_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		beamCheck.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(beamCheck, RWGui.ICN_UNCHECK);
		beamCheck.setClickable(true);
		beamLabel	= new GuiLabel(Msgs.msg[Msgs.gui_typeBeam], BEAMLBL_XPOS, TYPELBL_YPOS, false);
		beamLabel.setPivot(PivotPosition.TopLeft);
		beamLabel.setFontSize(RWGui.ITEM_SIZE);
		addChild(typeLabel);
		addChild(plankCheck);
		addChild(plankLabel);
		addChild(beamCheck);
		addChild(beamLabel);
		// The QUANTITY
		quant		= 1;
		quantLabel	= new GuiLabel(Msgs.msg[Msgs.gui_quantity], QUANTLBL_XPOS, QUANTLBL_YPOS, false);
		quantLabel.setPivot(PivotPosition.TopLeft);
		quantLabel.setFontSize(RWGui.ITEM_SIZE);
		minusButt	= new GuiImage(MINUS_XPOS, QUANTLBL_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		minusButt.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(minusButt, RWGui.ICN_MINUS);
		minusButt.setClickable(true);
		quantText	= new GuiLabel("1", QUANTTXT_XPOS, QUANTLBL_YPOS - RWGui.ITEM_SIZE/2, false);
		quantText.setPivot(PivotPosition.Center);
		quantText.setFontSize(RWGui.ITEM_SIZE);
		plusButt	= new GuiImage(PLUS_XPOS, QUANTLBL_YPOS, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		plusButt.setPivot(PivotPosition.TopLeft);
		RWGui.setImage(plusButt, RWGui.ICN_PLUS);
		plusButt.setClickable(true);
		resourcesLabel	= new GuiLabel(RESLBL_XPOS, RESLBL_YPOS, false);
		resourcesLabel.setPivot(PivotPosition.TopLeft);
		resourcesLabel.setFontSize(RWGui.ITEM_SIZE);
		addChild(quantLabel);
		addChild(minusButt);
		addChild(quantText);
		addChild(plusButt);
		addChild(resourcesLabel);
		// The BUY button
		doButt	= new GuiLabel(Msgs.msg[Msgs.gui_buy], DO_XPOS, DO_YPOS, false);
		doButt.setPivot(PivotPosition.Center);
		doButt.setFontSize(RWGui.ITEM_SIZE);
		doButt.setColor(RWGui.ACTIVE_COLOUR);
		doButt.setClickable(true);
		addChild(doButt);
		setVisible(false);
		updateImages();*/
	}

	/**
		Shows the GUI panel for the Player @a player.
		The panel is filled with appropriate texts and made visible.
		@param player	the player to which this panel refers
	*/
/*	public void show(Player player)
	{
		titleBar.addToPlayer(player);
		player.addGuiElement(textureLabel);
		player.addGuiElement(pgUpButt);
		player.addGuiElement(pgDnButt);
		player.addGuiElement(typeLabel);
		player.addGuiElement(plankCheck);
		player.addGuiElement(plankLabel);
		player.addGuiElement(beamCheck);
		player.addGuiElement(beamLabel);
		player.addGuiElement(minusButt);
		player.addGuiElement(quantLabel);
		player.addGuiElement(quantText);
		player.addGuiElement(plusButt);
		player.addGuiElement(resourcesLabel);
		player.addGuiElement(doButt);
		for (int i = 0; i < NUM_OF_IMAGES; i++)
			player.addGuiElement(images[i]);
		player.addGuiElement(this);
		setVisible(true);
		player.setMouseCursorVisible(true);
	}
*/
/*	public void hide(Player player)
	{
		setVisible(false);
		titleBar.removeFromPlayer(player);
		player.removeGuiElement(textureLabel);
		player.removeGuiElement(pgUpButt);
		player.removeGuiElement(pgDnButt);
		player.removeGuiElement(typeLabel);
		player.removeGuiElement(plankCheck);
		player.removeGuiElement(plankLabel);
		player.removeGuiElement(beamCheck);
		player.removeGuiElement(beamLabel);
		player.removeGuiElement(minusButt);
		player.removeGuiElement(quantLabel);
		player.removeGuiElement(quantText);
		player.removeGuiElement(plusButt);
		player.removeGuiElement(resourcesLabel);
		player.removeGuiElement(doButt);
		for (int i = 0; i < NUM_OF_IMAGES; i++)
			player.removeGuiElement(images[i]);
		player.removeGuiElement(this);
		player.setMouseCursorVisible(false);
	}*/
	/**
		Process a mouse click on the child GUI element @a element. 
	
		@param	element	the element which has been clicked
		@param	player	the player this panel refers to
	*/
/*	public void click(GuiElement element, Player player)
	{
		// check for the clicked 'control'
		if (titleBar.isCancelButton(element))		// CLOSE the 'dialogue box'
			hide(player);
		else if (element == plankCheck)
		{
			type	= PlanksAndBeams.PLANK_ID;
			RWGui.setImage(plankCheck, RWGui.ICN_CHECK);
			RWGui.setImage(beamCheck, RWGui.ICN_UNCHECK);
		}
		else if (element == beamCheck)
		{
			type	= PlanksAndBeams.BEAM_ID;
			RWGui.setImage(plankCheck, RWGui.ICN_UNCHECK);
			RWGui.setImage(beamCheck, RWGui.ICN_CHECK);
		}
		else if (element == pgUpButt)
		{
			if (textureFirst <= 0)
				return;
			textureFirst	-= NUM_OF_IMAGES;
			if (textureFirst <= 0)
				pgUpButt.setVisible(false);
			pgDnButt.setVisible(true);
			updateImages();
		}
		else if (element == pgDnButt)
		{
			if (textureFirst+NUM_OF_IMAGES >= NUM_OF_TEXTURES)
				return;
			textureFirst	+= NUM_OF_IMAGES;
			if (textureFirst+NUM_OF_IMAGES >= NUM_OF_TEXTURES)
				pgDnButt.setVisible(false);
			pgUpButt.setVisible(true);
			updateImages();
		}
		else if (element == minusButt)
		{
			quant--;
			if (quant <= 1)
			{
				quant = 1;
				minusButt.setVisible(false);
			}
			if (quant == 63)
				plusButt.setVisible(true);
			quantText.setText(Integer.toString(quant));
			updateResources();
		}
		else if (element == plusButt)
		{
			quant++;
			if (quant >= 64)
			{
				quant = 64;
				plusButt.setVisible(false);
			}
			if (quant == 2)
				minusButt.setVisible(true);
			quantText.setText(Integer.toString(quant));
			updateResources();
		}
		else if (element == doButt)
		{
			hide(player);
			PlanksAndBeams.plugin.buy(player, type, image2variation[textureFirst+imageSel], quant);
		}
		else
			for (int i = 0; i < NUM_OF_IMAGES; i++)
				if (element == images[i] && textureFirst + i < NUM_OF_TEXTURES)
				{
					imageSel	= i;
					updateSelected();
					break;
				}
	}*/

	//********************
	// HANDLERS
	//********************

	private class DlgHandler implements RWGuiCallback
	{
		@Override
		public void onCall(Player player, int id, Object data)
		{
			if (id >= 1 && id <= NUM_OF_IMAGES)
			{
				imageSel		= id - 1;
				updateSelected();
				return;
			}
			switch (id)
			{
			case RWGui.ABORT_ID:
				return;
			case PGUPBUTT_ID:
				if (textureFirst <= 0)
					return;
				textureFirst	-= NUM_OF_IMAGES;
				if (textureFirst <= 0)
					pgUpButt.setVisible(false);
				pgDnButt.setVisible(true);
				updateImages();
				break;
			case PGDNBUTT_ID:
				if (textureFirst+NUM_OF_IMAGES >= NUM_OF_TEXTURES)
					return;
				textureFirst	+= NUM_OF_IMAGES;
				if (textureFirst+NUM_OF_IMAGES >= NUM_OF_TEXTURES)
					pgDnButt.setVisible(false);
				pgUpButt.setVisible(true);
				updateImages();
				break;
			case PLANKBUTT_ID:
				type	= PlanksAndBeams.PLANK_ID;
				RWGui.setImage(plankCheck, RWGui.ICN_CHECK);
				RWGui.setImage(beamCheck, RWGui.ICN_UNCHECK);
				RWGui.setImage(plank3Check, RWGui.ICN_UNCHECK);
				break;
			case BEAMBUTT_ID:
				type	= PlanksAndBeams.BEAM_ID;
				RWGui.setImage(plankCheck, RWGui.ICN_UNCHECK);
				RWGui.setImage(beamCheck, RWGui.ICN_CHECK);
				RWGui.setImage(plank3Check, RWGui.ICN_UNCHECK);
				break;
			case PLANK3BUTT_ID:
				type	= PlanksAndBeams.PLANKTRI_ID;
				RWGui.setImage(plankCheck, RWGui.ICN_UNCHECK);
				RWGui.setImage(beamCheck, RWGui.ICN_UNCHECK);
				RWGui.setImage(plank3Check, RWGui.ICN_CHECK);
				break;
			case MINUSBUTT_ID:
				quant--;
				if (quant <= 1)
				{
					quant = 1;
					minusButt.setVisible(false);
				}
				if (quant == 63)
					plusButt.setVisible(true);
				quantText.setText(Integer.toString(quant));
				updateResources();
				break;
			case PLUSBUTT_ID:
				quant++;
				if (quant >= 64)
				{
					quant = 64;
					plusButt.setVisible(false);
				}
				if (quant == 2)
					minusButt.setVisible(true);
				quantText.setText(Integer.toString(quant));
				updateResources();
				break;
			case DOBUTT_ID:
				close(player);
				int	variation	= image2variation[textureFirst+imageSel];
				int retVal = PlanksAndBeams.plugin.buy(player, type, variation, quant);
				String[]	texts	= new String[1];
				switch (retVal)
				{
				case PlanksAndBeams.ERR_NO_RESOURCES:
					texts[0]	= Msgs.msg[Msgs.txt_no_resources];
					break;
				case PlanksAndBeams.ERR_GENERIC:
					texts[0]	= Msgs.msg[Msgs.txt_newitem_failed];
					break;
				case PlanksAndBeams.ERR_SUCCESS:
					texts[0]	= String.format(Msgs.msg[Msgs.txt_items_added], quant,
							type == PlanksAndBeams.PLANK_ID ? Msgs.msg[Msgs.gui_typePlank] :
							(type == PlanksAndBeams.PLANK_ID ? Msgs.msg[Msgs.gui_typeBeam] :
								Msgs.msg[Msgs.gui_typePlank3]),
							variation);
					break;
				default:
					texts[0]	= Msgs.msg[Msgs.txt_newitem_failed];
					break;
				}
				new GuiMessageBox(PlanksAndBeams.plugin, player, Msgs.msg[Msgs.gui_title], texts, 0);
				if (retVal == PlanksAndBeams.ERR_SUCCESS)
					free();
				else
					show(player);
				break;
			}
		}
	}

	//********************
	// PRIVATE UTILITY METHODS
	//********************

	private void updateImages()
	{
		if (textureFirst != textureFirstOld)
		{
			for (int i = 0; i < NUM_OF_IMAGES; i++)
			{
				if (textureFirst + i >= NUM_OF_TEXTURES)
				{
					for (int j = i; j < NUM_OF_IMAGES; j++)
					{
						images[j].setImage(null);
						images[j].setColor(RWGui.PANEL_COLOUR);
					}
					break;
				}
				ImageInformation ii;
				try
				{
					ii = new ImageInformation(PlanksAndBeams.pluginPath + "/assets/"+image2texture[textureFirst+i]+".png");
//					URL url = PlanksAndBeams.plugin.getClass().getResource("/assets/"+image2texture[textureFirst+i]+".png");
//					URI	uri	= url.toURI();
//					File file	= new File(uri);
//					ii = new ImageInformation(file);
					images[i].setImage(ii);
				} catch (IOException /*| URISyntaxException*/ e)
				{
					e.printStackTrace();
				}
			}
			textureFirstOld	= textureFirst;
			updateSelected();
		}
	}

	private void updateSelected()
	{
		images[imageSelOld].setBorderThickness(0, false);
		images[imageSel].setBorderThickness(RWGui.BORDER_THICKNESS*2, false);
		imageSelOld			= imageSel;
		updateResources();
	}

	private void updateResources()
	{
		int		resIndex	= PlanksAndBeams.resourcePerVariation
				[image2variation[textureFirst+imageSel]-PlanksAndBeams.firstVariation];
		String	resourceTxt	= String.format(Msgs.msg[Msgs.gui_resourcesFmt],
				Msgs.msg[Msgs.firstResName + resIndex], quant * PlanksAndBeams.costPerItem);
		resourcesLabel.setText(resourceTxt);
	}

}
