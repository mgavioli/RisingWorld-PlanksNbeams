/****************************
	P l a n k s A n d B e a m  s  -  A Rising World Java plug-in distributing non-standard planks and beams.

	Gui.java - The plug-in GUI.

	Created by : Maurizio M. Gavioli 2016-10-22

	(C) Maurizio M. Gavioli (a.k.a. Miwarre), 2016
	Licensed under the Creative Commons by-sa 3.0 license (see http://creativecommons.org/licenses/by-sa/3.0/ for details)

*****************************/

package com.vistamaresoft.pnb;

import com.vistamaresoft.rwgui.GuiCheckBox;
import com.vistamaresoft.rwgui.GuiDialogueBox;
import com.vistamaresoft.rwgui.GuiLayout;
import com.vistamaresoft.rwgui.GuiMessageBox;
import com.vistamaresoft.rwgui.GuiTableLayout;
import com.vistamaresoft.rwgui.RWGui;
import com.vistamaresoft.rwgui.RWGui.RWGuiCallback;
import net.risingworld.api.gui.GuiImage;
import net.risingworld.api.gui.GuiLabel;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.ImageInformation;

public class Gui extends GuiDialogueBox
{
	// CONSTANTS
	//
	private static final	int		NUM_OF_IMG_ROWS	= 6;
	private static final	int		NUM_OF_IMG_COLS	= 9;
	private static final	int		IMAGE_HEIGHT	= 64;
	private static final	int		IMAGE_WIDTH		= 64;
	private static final	int		IMAGE_BORDERCOL	= RWGui.ACTIVE_COLOUR;
	// Constants
	private static final	int		NUM_OF_IMAGES	= NUM_OF_IMG_ROWS * NUM_OF_IMG_COLS;
	private static final	int		NUM_OF_TEXTURES	= 198;

	private static final	int		PGUPBUTT_ID		= (NUM_OF_IMG_COLS * NUM_OF_IMG_ROWS) + 1;
	private static final	int		PGDNBUTT_ID		= PGUPBUTT_ID + 1;
	private static final	int		PLANKBUTT_ID	= PGDNBUTT_ID + 1;
	private static final	int		BEAMBUTT_ID		= PLANKBUTT_ID + 1;
	private static final	int		LOGBUTT_ID		= BEAMBUTT_ID + 1;
	private static final	int		PLANK3BUTT_ID	= LOGBUTT_ID + 1;
	private static final	int		WINDOW1BUTT_ID	= PLANK3BUTT_ID + 1;
	private static final	int		WINDOW2BUTT_ID	= WINDOW1BUTT_ID + 1;
	private static final	int		WINDOW3BUTT_ID	= WINDOW2BUTT_ID + 1;
	private static final	int		WINDOW4BUTT_ID	= WINDOW3BUTT_ID + 1;
	private static final	int		MINBUTT_ID		= WINDOW4BUTT_ID + 1;
	private static final	int		MINUSBUTT_ID	= MINBUTT_ID + 1;
	private static final	int		PLUSBUTT_ID		= MINUSBUTT_ID + 1;
	private static final	int		MAXBUTT_ID		= PLUSBUTT_ID + 1;
	private static final	int		DOBUTT_ID		= MAXBUTT_ID + 1;

	// FIELDS
	//
	private	int						textureFirst;	// the index of the first texture shown
	private	int						textureFirstOld;
	private	int						imageSel;		// the index of the selected image
	private	int						imageSelOld;
	private boolean					free;
	private GuiImage[]				images;			// the texture images
	private	GuiImage				pgUpButt;
	private GuiImage				pgDnButt;
	private	int						type;
	private	GuiCheckBox				plankCheck;
	private	GuiCheckBox				beamCheck;
	private	GuiCheckBox				logCheck;
	private	GuiCheckBox				plank3Check;
	private	GuiCheckBox				window1Check;
	private	GuiCheckBox				window2Check;
	private	GuiCheckBox				window3Check;
	private	GuiCheckBox				window4Check;
	private int						quant;
	private GuiLabel				minButt;
	private GuiImage				minusButt;
	private GuiLabel				quantText;
	private GuiImage				plusButt;
	private GuiLabel				maxButt;
	private GuiLabel				resourcesText;
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
		 205,206,207,208,209,210,211,212,			// ornamental: stone
		 213,214,215,216,217,218					// natural: various
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
		 184,185,186,187,188,189,190,191,			// ornamental
		 192,193,194,195,196,197					// natural
		};

	public Gui(Player player)
	{
		super(PlanksAndBeams.plugin, Msgs.msg[Msgs.gui_title], RWGui.LAYOUT_VERT, null);
		setCallback(new DlgHandler());
		Boolean	freeCreative	= (Boolean)player.getPermissionValue("creative_freecrafting");
		free = player.isAdmin() && PlanksAndBeams.freeForAdmin ||
				player.isCreativeModeEnabled() && freeCreative && PlanksAndBeams.freeForCreative;
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
			layout2.addChild(images[i], i+1);	images[i].setBorderColor(IMAGE_BORDERCOL);
		}
		// The PGUP and PGDOWN buttons
		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_VERT, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_SPREAD);
		pgUpButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		layout2.addChild(pgUpButt, PGUPBUTT_ID);
		RWGui.setImage(pgUpButt, RWGui.ICN_ARROW_UP);
		pgDnButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(pgDnButt, RWGui.ICN_ARROW_DOWN);
		layout2.addChild(pgDnButt, PGDNBUTT_ID);

		// The TABLE of settings
		layout	= addNewTableLayoutChild(2, 3, 0);

		// The TYPE
		type		= PlanksAndBeams.PLANK_ID;
		layout.addChild(new GuiLabel(Msgs.msg[Msgs.gui_type], 0, 0, false));
		// the sub-table of types
		layout2	= layout.addNewTableLayoutChild(4, 2, 0);
		plankCheck	= new GuiCheckBox(Msgs.msg[Msgs.gui_typePlank], GuiCheckBox.CHECKED, true, PLANKBUTT_ID, null);
		layout2.addChild(plankCheck);
		beamCheck	= new GuiCheckBox(Msgs.msg[Msgs.gui_typeBeam], GuiCheckBox.UNCHECKED, true, BEAMBUTT_ID, null);
		layout2.addChild(beamCheck);
		logCheck	= new GuiCheckBox(Msgs.msg[Msgs.gui_typeLog], GuiCheckBox.UNCHECKED, true, LOGBUTT_ID, null);
		layout2.addChild(logCheck);
		plank3Check	= new GuiCheckBox(Msgs.msg[Msgs.gui_typePlank3], GuiCheckBox.UNCHECKED, true, PLANK3BUTT_ID, null);
		layout2.addChild(plank3Check);
		window1Check= new GuiCheckBox(Msgs.msg[Msgs.gui_typeWindow1], GuiCheckBox.UNCHECKED, true, WINDOW1BUTT_ID, null);
		layout2.addChild(window1Check);
		window2Check= new GuiCheckBox(Msgs.msg[Msgs.gui_typeWindow2], GuiCheckBox.UNCHECKED, true, WINDOW2BUTT_ID, null);
		layout2.addChild(window2Check);
		window3Check= new GuiCheckBox(Msgs.msg[Msgs.gui_typeWindow3], GuiCheckBox.UNCHECKED, true, WINDOW3BUTT_ID, null);
		layout2.addChild(window3Check);
		window4Check= new GuiCheckBox(Msgs.msg[Msgs.gui_typeWindow4], GuiCheckBox.UNCHECKED, true, WINDOW4BUTT_ID, null);
		layout2.addChild(window4Check);

		// The QUANTITY
		quant	= 1;
		layout.addChild(new GuiLabel(Msgs.msg[Msgs.gui_quantity], 0, 0, false));
		layout2	= layout.addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_LEFT | RWGui.LAYOUT_V_MIDDLE);
		minButt		= new GuiLabel(Msgs.msg[Msgs.gui_minButt], 0, 0, false);
		minButt.setColor(RWGui.ACTIVE_COLOUR);
		layout2.addChild(minButt, MINBUTT_ID);
		minusButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(minusButt, RWGui.ICN_MINUS);
		layout2.addChild(minusButt, MINUSBUTT_ID);
		quantText	= new GuiLabel("1", 0, 0, false);
		layout2.addChild(quantText);
		plusButt	= new GuiImage(0, 0, false, RWGui.BUTTON_SIZE, RWGui.BUTTON_SIZE, false);
		RWGui.setImage(plusButt, RWGui.ICN_PLUS);
		layout2.addChild(plusButt, PLUSBUTT_ID);
		maxButt		= new GuiLabel(Msgs.msg[Msgs.gui_maxButt], 0, 0, false);
		maxButt.setColor(RWGui.ACTIVE_COLOUR);
		layout2.addChild(maxButt, MAXBUTT_ID);

		// The RESOURCES
		layout.addChild(new GuiLabel(Msgs.msg[Msgs.gui_resources], 0, 0, false));
		resourcesText	= new GuiLabel(0, 0, false);
		layout.addChild(resourcesText);

		((GuiTableLayout)layout).setColFlag(0, RWGui.LAYOUT_H_RIGHT);

		// The BUY button
		layout	= addNewLayoutChild(RWGui.LAYOUT_HORIZ, RWGui.LAYOUT_H_CENTRE | RWGui.LAYOUT_V_TOP);
		GuiLabel lbl	= new GuiLabel(Msgs.msg[Msgs.gui_buy], 0, 0, false);
		lbl.setColor(RWGui.ACTIVE_COLOUR);
		layout.addChild(lbl, DOBUTT_ID);
		updateImages();
	}

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
				free();
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
				break;
			case BEAMBUTT_ID:
				type	= PlanksAndBeams.BEAM_ID;
				break;
			case LOGBUTT_ID:
				type	= PlanksAndBeams.LOG_ID;
				break;
			case PLANK3BUTT_ID:
				type	= PlanksAndBeams.PLANKTRI_ID;
				break;
			case WINDOW1BUTT_ID:
				type	= PlanksAndBeams.WINDOW1_ID;
				break;
			case WINDOW2BUTT_ID:
				type	= PlanksAndBeams.WINDOW2_ID;
				break;
			case WINDOW3BUTT_ID:
				type	= PlanksAndBeams.WINDOW3_ID;
				break;
			case WINDOW4BUTT_ID:
				type	= PlanksAndBeams.WINDOW4_ID;
				break;
			case MINBUTT_ID:
				quant = 1;
				updateResources();
				break;
			case MINUSBUTT_ID:
				quant--;
				updateResources();
				break;
			case PLUSBUTT_ID:
				quant++;
				updateResources();
				break;
			case MAXBUTT_ID:
				quant = 64;
				updateResources();
				break;
			case DOBUTT_ID:
				int		variation	= image2variation[textureFirst+imageSel];
				int		retVal		= PlanksAndBeams.plugin.buy(player, type, variation, quant);
				switch (retVal)
				{
				case PlanksAndBeams.ERR_NO_RESOURCES:
					player.sendTextMessage(Msgs.msg[Msgs.txt_no_resources]);
					break;
//				case PlanksAndBeams.ERR_GENERIC:		// same as default
//					player.sendTextMessage(Msgs.msg[Msgs.txt_newitem_failed]);
//					break;
				case PlanksAndBeams.ERR_SUCCESS:
					String[]	texts		= new String[1];
					int			typeMsgId	=
							type == PlanksAndBeams.WINDOW4_ID ? Msgs.gui_typeWindow4 :
								(type == PlanksAndBeams.WINDOW3_ID ? Msgs.gui_typeWindow3 :
									(type == PlanksAndBeams.WINDOW2_ID ? Msgs.gui_typeWindow2 :
										(type == PlanksAndBeams.WINDOW1_ID ? Msgs.gui_typeWindow1 :
											(type == PlanksAndBeams.PLANKTRI_ID ? Msgs.gui_typePlank3 :
												(type == PlanksAndBeams.LOG_ID ? Msgs.gui_typeLog :
													(type == PlanksAndBeams.BEAM_ID ? Msgs.gui_typeBeam :
														Msgs.gui_typePlank
								))))));
					texts[0]	= String.format(Msgs.msg[Msgs.txt_items_added], quant, Msgs.msg[typeMsgId], variation);
					push(player, new GuiMessageBox(PlanksAndBeams.plugin, player, Msgs.msg[Msgs.gui_title],
							texts, 0));
					break;
				default:
					player.sendTextMessage(Msgs.msg[Msgs.txt_newitem_failed]);
					break;
				}
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
						images[j].setVisible(false);
					}
					break;
				}
				ImageInformation ii;
				ii = new ImageInformation(PlanksAndBeams.pluginPath + "/assets/"+image2texture[textureFirst+i]+".png");
				images[i].setImage(ii);
				images[i].setVisible(true);
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
		if (quant < 1)		quant = 1;
		minusButt.setVisible(quant > 1);
		if (quant > 64)		quant = 64;
		plusButt.setVisible(quant < 64);
		quantText.setText(Integer.toString(quant));
		int		resIndex	= PlanksAndBeams.resourcePerVariation
				[image2variation[textureFirst+imageSel]-PlanksAndBeams.firstVariation];
		String	text		= String.format(Msgs.msg[Msgs.gui_resourcesFmt],
				Msgs.msg[Msgs.firstResName + resIndex], free ? 0 : quant * PlanksAndBeams.costPerItem);
		resourcesText.setText(text);
	}

}
