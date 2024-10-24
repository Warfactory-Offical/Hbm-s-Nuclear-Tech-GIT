package api.hbm.material;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import api.hbm.material.NTMMaterial.SmeltingBehavior;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;
import static api.hbm.material.MaterialShapes.*;

/* with every new rewrite, optimization and improvement, the code becomes more gregian */

/**
 * Defines materials that wrap around DictFrames to more accurately describe that material.
 * Direct uses are the crucible and possibly item auto-gen, depending on what traits are set.
 * @author hbm
 */
public class Mats {

	public static List<NTMMaterial> orderedList = new ArrayList();
	public static HashMap<String, MaterialShapes> prefixByName = new HashMap();
	public static HashMap<String, NTMMaterial> matByName = new HashMap();
	public static HashMap<ComparableStack, List<MaterialStack>> materialEntries = new HashMap();
	public static HashMap<String, List<MaterialStack>> materialOreEntries = new HashMap();

	/*
	 * ItemStacks are saved with their metadata being truncated to a short, so the max meta is 32767
	 * Format for elements: Atomic number *100, plus the last two digits of the mass number. Mass number is 0 for generic/undefined/mixed materials.
	 * Vanilla numbers are in vanilla space (0-29), basic alloys use alloy space (30-99)
	 */

//	/* Vanilla Space, up to 30 materials, */
//	public static final int _VS = 0;
//	/* Alloy Space, up to 70 materials. Use >20_000 as an extension.*/
//	public static final int _AS = 30;

	//Vanilla and vanilla-like

//	public static final NTMMaterial MAT_WOOD			= makeNonSmeltable("wood",		WOOD,				0x896727, 0x281E0B, 0x896727).setShapes(STOCK, GRIP).n();
//	public static final NTMMaterial MAT_IVORY			= makeNonSmeltable("ivory",	BONE,				0xFFFEEE, 0x797870, 0xEDEBCA).setShapes(GRIP).n();
	public static final NTMMaterial MAT_STONE			= makeSmeltable("stone",		df("Stone"),	0x7F7F7F, 0x353535, 0x4D2F23).n();
	public static final NTMMaterial MAT_CARBON			= makeAdditive(	"carbon",		CARBON,		0x363636, 0x030303, 0x404040).setShapes(WIRE, INGOT, BLOCK).n();
	public static final NTMMaterial MAT_COAL			= makeNonSmeltable("coal",			COAL, 				0x363636, 0x030303, 0x404040).setConversion(MAT_CARBON,  2, 1).setShapes(FRAGMENT).n();
	public static final NTMMaterial MAT_LIGNITE			= makeNonSmeltable(	"lignite",			LIGNITE,			0x542D0F, 0x261508, 0x472913).setConversion(MAT_CARBON,  3, 1).setShapes(FRAGMENT).n();
	public static final NTMMaterial MAT_COALCOKE		= make(			"coalcoke",			COALCOKE)			.setConversion(MAT_CARBON,  4, 3).n();
	public static final NTMMaterial MAT_PETCOKE			= make(			"petcoke",			PETCOKE)			.setConversion(MAT_CARBON,  4, 3).n();
	public static final NTMMaterial MAT_LIGCOKE			= make(			"ligcoke",			LIGCOKE)			.setConversion(MAT_CARBON,  4, 3).n();
	public static final NTMMaterial MAT_GRAPHITE		= make(			"graphite",			GRAPHITE)			.setConversion(MAT_CARBON,  1, 1).n();
	public static final NTMMaterial MAT_DIAMOND			= makeNonSmeltable("diamond",		DIAMOND,			0xFFFFFF, 0x1B7B6B, 0x8CF4E2).setConversion(MAT_CARBON,  1, 1).setShapes(FRAGMENT).n();
	public static final NTMMaterial MAT_IRON			= makeSmeltable("iron",			IRON,				0xFFFFFF, 0x353535, 0xFFA259).setShapes(FRAGMENT, INGOT, DUST, PIPE, CASTPLATE, WELDEDPLATE, BLOCK).m();
	public static final NTMMaterial MAT_GOLD			= makeSmeltable("gold",			GOLD,				0xFFFF8B, 0xC26E00, 0xE8D754).setShapes(FRAGMENT, WIRE, NUGGET, INGOT, DUST, DENSEWIRE, CASTPLATE, BLOCK).m();
	public static final NTMMaterial MAT_REDSTONE		= makeSmeltable("redstone",		REDSTONE,			0xE3260C, 0x700E06, 0xFF1000).setShapes(FRAGMENT).n();
	public static final NTMMaterial MAT_OBSIDIAN		= makeSmeltable("obsidian",		df("Obsidian"),		0x3D234D).n();
	public static final NTMMaterial MAT_HEMATITE		= makeAdditive(	"hematite",			HEMATITE,			0xDFB7AE, 0x5F372E, 0x6E463D).m();
	public static final NTMMaterial MAT_WROUGHTIRON		= makeSmeltable("wroughiron",			df("WroughtIron"),	0xFAAB89).m();
	public static final NTMMaterial MAT_PIGIRON			= makeSmeltable("pigiron",			df("PigIron"),		0xFF8B59).m();
	public static final NTMMaterial MAT_METEORICIRON	= makeSmeltable("meteoriciron",			df("MeteoricIron"),	0x715347).m();
	public static final NTMMaterial MAT_MALACHITE		= makeAdditive(	"malachite",			MALACHITE,			0xA2F0C8, 0x227048, 0x61AF87).m();

	//Radioactive
	public static final NTMMaterial MAT_URANIUM		= makeSmeltable(	"uranium",    	U,			0xC1C7BD, 0x2B3227, 0x9AA196).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_U233		= makeSmeltable(	"u233",       	U233,		0xC1C7BD, 0x2B3227, 0x9AA196).setShapes(NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_U235		= makeSmeltable(	"u235",       	U235,		0xC1C7BD, 0x2B3227, 0x9AA196).setShapes(NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_U238		= makeSmeltable(	"u238",       	U238,		0xC1C7BD, 0x2B3227, 0x9AA196).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_THORIUM		= makeSmeltable(	"thorium",    	TH232,		0xBF825F, 0x1C0000, 0xBF825F).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_PLUTONIUM	= makeSmeltable(	"plutonium",  	PU,			0x9AA3A0, 0x111A17, 0x78817E).setShapes(NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_RGP			= makeSmeltable(	"rgp",        	PURG,		0x9AA3A0, 0x111A17, 0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_PU238		= makeSmeltable(	"pu238",      	PU238,		0xFFBC59, 0xFF8E2B, 0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_PU239		= makeSmeltable(	"pu239",      	PU239,		0x9AA3A0, 0x111A17, 0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_PU240		= makeSmeltable(	"pu240",      	PU240,		0x9AA3A0, 0x111A17, 0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_PU241		= makeSmeltable(	"pu241",      	PU241,		0x9AA3A0, 0x111A17, 0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_RGA			= makeSmeltable(	"rga",        	AMRG,		0xCEB3B9, 0x3A1C21, 0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_AM241		= makeSmeltable(	"am241",      	AM241,		0xCEB3B9, 0x3A1C21, 0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_AM242		= makeSmeltable(	"am242",     	AM242,		0xCEB3B9, 0x3A1C21, 0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_NEPTUNIUM	= makeSmeltable(	"neptunium", 	NP237,		0xA6B2A6, 0x030F03, 0x647064).setShapes(NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_POLONIUM	= makeSmeltable(	"polonium",  	PO210,		0x968779, 0x3D1509, 0x715E4A).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_TECHNETIUM	= makeSmeltable(	"technetium",	TC99,		0xFAFFFF, 0x576C6C, 0xCADFDF).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_RADIUM		= makeSmeltable(	"radium",    	RA226,		0xFCFCFC, 0xADBFBA, 0xE9FAF6).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_ACTINIUM	= makeSmeltable(	"actinium",  	AC227,		0xECE0E0, 0x221616, 0x958989).setShapes(NUGGET, BILLET, INGOT).m();
	public static final NTMMaterial MAT_CO60		= makeSmeltable(	"co60",      	CO60,		0xC2D1EE, 0x353554, 0x8F72AE).setShapes(NUGGET, BILLET, INGOT, DUST).m();
	public static final NTMMaterial MAT_AU198		= makeSmeltable(	"au198",     	AU198,		0xFFFF8B, 0xC26E00, 0xE8D754).setShapes(NUGGET, BILLET, INGOT, DUST).m();
	public static final NTMMaterial MAT_PB209		= makeSmeltable(	"pb209",     	PB209,		0xB38A94, 0x12020E, 0x7B535D).setShapes(NUGGET, BILLET, INGOT, DUST).m();
	public static final NTMMaterial MAT_SCHRABIDIUM	= makeSmeltable(	"schrabidium",	SA326,		0x32FFFF, 0x005C5C, 0x32FFFF).setShapes(NUGGET, WIRE, BILLET, INGOT, DUST, DENSEWIRE, PLATE, CASTPLATE, BLOCK).m();
	public static final NTMMaterial MAT_SOLINIUM	= makeSmeltable(	"solinium",  	SA327,		0xA2E6E0, 0x00433D, 0x72B6B0).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_SCHRABIDATE	= makeSmeltable(	"schrabidate",	SBD,		0x77C0D7, 0x39005E, 0x6589B4).setShapes(INGOT, DUST, DENSEWIRE, CASTPLATE, BLOCK).m();
	public static final NTMMaterial MAT_SCHRARANIUM	= makeSmeltable(	"schraranium",	SRN,		0x2B3227, 0x2B3227, 0x24AFAC).setShapes(INGOT, BLOCK).m();
	public static final NTMMaterial MAT_GHIORSIUM	= makeSmeltable(	"ghiorsium", 	GH336,		0xF4EFE1, 0x2A3306, 0xC6C6A1).setShapes(NUGGET, BILLET, INGOT, BLOCK).m();

	//Base metals
	public static final NTMMaterial MAT_TITANIUM		=    makeSmeltable("titanium",     		TI,				0xF7F3F2, 0x4F4C4B, 0xA99E79).setShapes(FRAGMENT, INGOT, DUST, PLATE, CASTPLATE, WELDEDPLATE, SHELL, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_COPPER			=    makeSmeltable("copper",       		CU,				0xFDCA88, 0x601E0D, 0xC18336).setShapes(FRAGMENT, WIRE, INGOT, DUST, PLATE, CASTPLATE, WELDEDPLATE, SHELL, PIPE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_TUNGSTEN		=    makeSmeltable("tungsten",     		W,				0x868686, 0x000000, 0x977474).setShapes(FRAGMENT, WIRE, BOLT, INGOT, DUST, DENSEWIRE, CASTPLATE, WELDEDPLATE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_ALUMINIUM		=    makeSmeltable("aluminium",    		AL,				0xFFFFFF, 0x344550, 0xD0B8EB).setShapes(FRAGMENT, WIRE, INGOT, DUST, PLATE, CASTPLATE, WELDEDPLATE, SHELL, PIPE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_LEAD			=    makeSmeltable("lead",         		PB,				0xA6A6B2, 0x03030F, 0x646470).setShapes(FRAGMENT, NUGGET, WIRE, INGOT, DUST, PLATE, CASTPLATE, PIPE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_BISMUTH			=    makeSmeltable("bismuth",      		BI, 			0xB200FF, 0xB200FF, 0xB200FF).setShapes(FRAGMENT, NUGGET, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_ARSENIC			=    makeSmeltable("arsenic",      		AS,				0x6CBABA, 0x242525, 0x558080).setShapes(NUGGET, INGOT).m();
	public static final NTMMaterial MAT_TANTALIUM		=    makeSmeltable("tantalium",    		TA,				0xFFFFFF, 0x1D1D36, 0xA89B74).setShapes(NUGGET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_NEODYMIUM		=    makeSmeltable("neodymium",    		ND,				0xE6E6B6, 0x1C1C00, 0x8F8F5F).setShapes(FRAGMENT, NUGGET, DUSTTINY, INGOT, DUST, DENSEWIRE, BLOCK).m();
	public static final NTMMaterial MAT_NIOBIUM			=    makeSmeltable("niobium",      		NB,				0xB76EC9, 0x2F2D42, 0xD576B1).setShapes(FRAGMENT, NUGGET, DUSTTINY, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_BERYLLIUM		=    makeSmeltable("beryllium",    	    BE,				0xB2B2A6, 0x0F0F03, 0xAE9572).setShapes(FRAGMENT, NUGGET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_EMERALD			= makeNonSmeltable("emerald",      	    EMERALD,		0xBAFFD4, 0x003900, 0x17DD62).setConversion(MAT_BERYLLIUM, 4, 3).setShapes(FRAGMENT, DUST, GEM, BLOCK).n();
	public static final NTMMaterial MAT_COBALT			=    makeSmeltable("cobalt",       		CO,				0xC2D1EE, 0x353554, 0x8F72AE).setShapes(FRAGMENT, NUGGET, DUSTTINY, BILLET, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_BORON			=    makeSmeltable("boron",        	    B,				0xBDC8D2, 0x29343E, 0xAD72AE).setShapes(FRAGMENT, DUSTTINY, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_BORAX			=    makeSmeltable("borax",        	    BORAX,			0xFFFFFF, 0x946E23, 0xFFECC6).setShapes(FRAGMENT, DUST).n();
	public static final NTMMaterial MAT_LANTHANIUM		=    makeSmeltable("lanthanium",   		LA,				0xC8E0E0, 0x3B5353, 0xA1B9B9).setShapes(FRAGMENT, INGOT, BLOCK).m();
	public static final NTMMaterial MAT_ZIRCONIUM		=    makeSmeltable("zirconium",    		ZR,				0xE3DCBE, 0x3E3719, 0xADA688).setShapes(FRAGMENT, NUGGET, WIRE, DUSTTINY, BILLET, INGOT, DUST, CASTPLATE, WELDEDPLATE, BLOCK).m();
	public static final NTMMaterial MAT_SODIUM			=    makeSmeltable("sodium",       		NA,				0xD3BF9E, 0x3A5A6B, 0x7E9493).setShapes(FRAGMENT, DUST).m();
	public static final NTMMaterial MAT_SODALITE		= makeNonSmeltable("sodalite",     	    SODALITE,		0xDCE5F6, 0x4927B4, 0x96A7E6).setShapes(FRAGMENT, GEM).n();
	public static final NTMMaterial MAT_STRONTIUM		=    makeSmeltable("strontium",    		SR,				0xF1E8BA, 0x271E00, 0xCAC193).setShapes(FRAGMENT, DUST).m();
	public static final NTMMaterial MAT_CALCIUM			=    makeSmeltable("calcium",      		CA,				0xCFCFA6, 0x747F6E, 0xB7B784).setShapes(INGOT, DUST).m();
	public static final NTMMaterial MAT_LITHIUM			=    makeSmeltable("lithium",      	    LI,				0xFFFFFF, 0x818181, 0xD6D6D6).setShapes(FRAGMENT, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_SULFUR			= makeNonSmeltable("sulfur",       	    S,				0xFCEE80, 0xBDA022, 0xF1DF68).setShapes(FRAGMENT, DUST, BLOCK).n();
	public static final NTMMaterial MAT_KNO				= makeNonSmeltable("kno",          	    KNO,			0xD4D4D4, 0x969696, 0xC9C9C9).setShapes(FRAGMENT, DUST, BLOCK).n();
	public static final NTMMaterial MAT_FLUORITE		= makeNonSmeltable("fluorite",     	    F,				0xFFFFFF, 0xB0A192, 0xE1DBD4).setShapes(FRAGMENT, DUST, BLOCK).n();
	public static final NTMMaterial MAT_PHOSPHORUS		= makeNonSmeltable("phosphorus",   	    P_RED,			0xCB0213, 0x600006, 0xBA0615).setShapes(FRAGMENT, DUST, BLOCK).n();
	public static final NTMMaterial MAT_CHLOROCALCITE	= makeNonSmeltable("chlorocalcite",     	CHLOROCALCITE,	0xF7E761, 0x475B46, 0xB8B963).setShapes(FRAGMENT, DUST).n();
	public static final NTMMaterial MAT_MOLYSITE		= makeNonSmeltable("molysite",          	MOLYSITE, 		0xF9E97B, 0x216E00, 0xD0D264).setShapes(FRAGMENT, DUST).n();
	public static final NTMMaterial MAT_CINNABAR		= makeNonSmeltable("cinnabar",         	CINNABAR,		0xD87070, 0x993030, 0xBF4E4E).setShapes(FRAGMENT, GEM).n();
	public static final NTMMaterial MAT_CADMIUM			=    makeSmeltable("cadmium",      		CD,				0xFFFADE, 0x350000, 0xA85600).setShapes(INGOT, DUST).m();
	public static final NTMMaterial MAT_SILICON			=    makeSmeltable("silicon",      		SI,				0xD1D7DF, 0x1A1A3D, 0x878B9E).setShapes(FRAGMENT, NUGGET, BILLET, INGOT).m();
	public static final NTMMaterial MAT_ASBESTOS		=    makeSmeltable("asbestos",     		ASBESTOS,		0xD8D9CF, 0x616258, 0xB0B3A8).setShapes(FRAGMENT, INGOT, BLOCK).n();
	public static final NTMMaterial MAT_OSMIRIDIUM		=    makeSmeltable("osmiridium",   		OSMIRIDIUM, 	0xDBE3EF, 0x7891BE, 0xACBDD9).setShapes(NUGGET, INGOT, CASTPLATE, WELDEDPLATE).m();

	//Alloys
	public static final NTMMaterial MAT_STEEL		= makeSmeltable("steel",      	STEEL,			0xAFAFAF, 0x0F0F0F, 0x4A4A4A).setShapes(DUSTTINY, BOLT, WIRE, INGOT, DUST, PLATE, CASTPLATE, WELDEDPLATE, SHELL, PIPE, BLOCK, HEAVY_COMPONENT, LIGHTBARREL, HEAVYBARREL, LIGHTRECEIVER, GRIP).m();
	public static final NTMMaterial MAT_MINGRADE	= makeSmeltable("mingrade",   	MINGRADE,		0xFFBA7D, 0xAF1700, 0xE44C0F).setShapes(WIRE, INGOT, DUST, BLOCK).m();
	public static final NTMMaterial MAT_ALLOY		= makeSmeltable("alloy",      	ALLOY,			0xFF8330, 0x700000, 0xFF7318).setShapes(WIRE, INGOT, DUST, DENSEWIRE, PLATE, CASTPLATE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_DURASTEEL		= makeSmeltable("dura",       	DURA,			0x183039, 0x030B0B, 0x376373).setShapes(BOLT, INGOT, DUST, PIPE, BLOCK, LIGHTBARREL, HEAVYBARREL, LIGHTRECEIVER, HEAVYRECEIVER, GRIP).m();
	public static final NTMMaterial MAT_DESH		= makeSmeltable("desh",       	DESH,			0xFF6D6D, 0x720000, 0xF22929).setShapes(INGOT, DUST, CASTPLATE, BLOCK, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_STAR		= makeSmeltable("star",       	STAR,			0xCCCCEA, 0x11111A, 0xA5A5D3).setShapes(INGOT, DUST, DENSEWIRE, BLOCK).m();
	public static final NTMMaterial MAT_FERRO		= makeSmeltable("ferro",      	FERRO,			0xB7B7C9, 0x101022, 0x6B6B8B).setShapes(INGOT).m();
	public static final NTMMaterial MAT_TCALLOY		= makeSmeltable("tcalloy",    	TCALLOY,		0xD4D6D6, 0x323D3D, 0x9CA6A6).setShapes(INGOT, DUST, CASTPLATE, WELDEDPLATE, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_CDALLOY		= makeSmeltable("cdalloy",    	CDALLOY,		0xF7DF8F, 0x604308, 0xFBD368).setShapes(INGOT, CASTPLATE, WELDEDPLATE, HEAVY_COMPONENT).m();
	public static final NTMMaterial MAT_BBRONZE		= makeSmeltable("bbronze",    	BBRONZE,		0xE19A69, 0x485353, 0x987D65).setShapes(INGOT, CASTPLATE).m();
	public static final NTMMaterial MAT_ABRONZE		= makeSmeltable("abronze",    	ABRONZE,		0xDB9462, 0x203331, 0x77644D).setShapes(INGOT, CASTPLATE).m();
	public static final NTMMaterial MAT_BSCCO		= makeSmeltable("bscco",      	BSCCO,			0x767BF1, 0x000000, 0x5E62C0).setShapes(INGOT, DENSEWIRE).m();
	public static final NTMMaterial MAT_MAGTUNG		= makeSmeltable("magtung",    	MAGTUNG,		0x22A2A2, 0x0F0F0F, 0x22A2A2).setShapes(WIRE, INGOT, DUST, DENSEWIRE, BLOCK).m();
	public static final NTMMaterial MAT_CMB			= makeSmeltable("cmb",        	CMB,			0x6F6FB4, 0x000011, 0x6F6FB4).setShapes(INGOT, DUST, PLATE, CASTPLATE, WELDEDPLATE, BLOCK).m();
	public static final NTMMaterial MAT_DNT			= makeSmeltable("dnt",        	DNT,			0x7582B9, 0x16000E, 0x455289).setShapes(INGOT, DUST, DENSEWIRE, BLOCK).m();
	public static final NTMMaterial MAT_FLUX		= makeAdditive("flux",     df("Flux"),	0xF1E0BB, 0x6F6256, 0xDECCAD).setShapes(DUST).n();
	public static final NTMMaterial MAT_SLAG		= makeSmeltable("slag",       	SLAG,			0x554940, 0x34281F, 0x6C6562).setShapes(BLOCK).n();
	public static final NTMMaterial MAT_MUD			= makeSmeltable("mud",        	MUD,			0xBCB5A9, 0x481213, 0x96783B).setShapes(INGOT).n();
	public static final NTMMaterial MAT_GUNMETAL	= makeSmeltable("gunmetal",   	GUNMETAL,		0xFFEF3F, 0xAD3600, 0xF9C62C).setShapes(INGOT, LIGHTBARREL, HEAVYBARREL, LIGHTRECEIVER, HEAVYRECEIVER, MECHANISM, STOCK, GRIP).n();
	public static final NTMMaterial MAT_WEAPONSTEEL	= makeSmeltable("weaponsteel",	WEAPONSTEEL,	0xA0A0A0, 0x000000, 0x808080).setShapes(INGOT, LIGHTBARREL, HEAVYBARREL, LIGHTRECEIVER, HEAVYRECEIVER, MECHANISM, STOCK, GRIP).n();
	public static final NTMMaterial MAT_SATURN		= makeSmeltable("saturn",     	BIGMT,			0x3AC4DA, 0x09282C, 0x30A4B7).setShapes(INGOT, PLATE, CASTPLATE, BLOCK, LIGHTBARREL, HEAVYBARREL, LIGHTRECEIVER, HEAVYRECEIVER, MECHANISM, STOCK, GRIP).m();

	public static NTMMaterial make(String name, DictFrame dict) {
		return new NTMMaterial(name, dict);
	}

	public static NTMMaterial makeSmeltable(String name, DictFrame dict, int color) { return makeSmeltable(name, dict, color, color, color); }

	public static NTMMaterial makeSmeltable(String name, DictFrame dict, int solidColorLight, int solidColorDark, int moltenColor) {
		return new NTMMaterial(name, dict).smeltable(SmeltingBehavior.SMELTABLE).setSolidColor(solidColorLight, solidColorDark).setMoltenColor(moltenColor);
	}
    // TODO: add multicolor
	public static NTMMaterial makeAdditive(String name, DictFrame dict, int solidColorLight, int solidColorDark, int moltenColor) {
		return new NTMMaterial(name, dict).smeltable(SmeltingBehavior.ADDITIVE).setSolidColor(solidColorLight, solidColorDark).setMoltenColor(moltenColor);
	}

	public static NTMMaterial makeNonSmeltable(String name, DictFrame dict, int solidColorLight, int solidColorDark, int moltenColor) {
		return new NTMMaterial(name, dict).smeltable(SmeltingBehavior.NOT_SMELTABLE).setSolidColor(solidColorLight, solidColorDark).setMoltenColor(moltenColor);
	}

	public static DictFrame df(String string) {
		return new DictFrame(string);
	}

	/** will not respect stacksizes - all stacks will be treated as a singular */
	public static List<MaterialStack> getMaterialsFromItem(ItemStack stack) {
		List<MaterialStack> list = new ArrayList();
		List<String> names = ItemStackUtil.getOreDictNames(stack);

		if(!names.isEmpty()) {
			outer:
			for(String name : names) {

				List<MaterialStack> oreEntries = materialOreEntries.get(name);

				if(oreEntries != null) {
					list.addAll(oreEntries);
					break outer;
				}

				for(Entry<String, MaterialShapes> prefixEntry : prefixByName.entrySet()) {
					String prefix = prefixEntry.getKey();

					if(name.startsWith(prefix)) {
						String materialName = name.substring(prefix.length());
						NTMMaterial material = matByName.get(materialName);

						if(material != null) {
							list.add(new MaterialStack(material, prefixEntry.getValue().q(1)));
							break outer;
						}
					}
				}
			}
		}

		List<MaterialStack> entries = materialEntries.get(ItemStackUtil.comparableStackFrom(stack).makeSingular());

		if(entries != null) {
			list.addAll(entries);
		}
		/*
		  for when crucible becomes real
		 
		if(stack.getItem() == ModItems.scraps) {
			list.add(ItemScraps.getMats(stack));
		}*/

		return list;
	}

	public static List<MaterialStack> getSmeltingMaterialsFromItem(ItemStack stack) {
		List<MaterialStack> baseMats = getMaterialsFromItem(stack);
		List<MaterialStack> smelting = new ArrayList();
		baseMats.forEach(x -> smelting.add(new MaterialStack(x.material.smeltsInto, (int) (x.amount * x.material.convOut / x.material.convIn))));
		return smelting;
	}

	public static void init() {
		MainRegistry.logger.info("test2");
	}

	public static class MaterialStack {
		//final fields to prevent accidental changing
		public final NTMMaterial material;
		public int amount;

		public MaterialStack(NTMMaterial material, int amount) {
			this.material = material;
			this.amount = amount;
		}

		public MaterialStack copy() {
			return new MaterialStack(material, amount);
		}
	}

	public static String formatAmount(int amount, boolean showInMb) {

		if(showInMb) {
			return (amount * 2) + "mB";
		}

		String format = "";

		int blocks = amount / BLOCK.q(1);
		amount -= BLOCK.q(blocks);
		int ingots = amount / INGOT.q(1);
		amount -= INGOT.q(ingots);
		int nuggets = amount / NUGGET.q(1);
		amount -= NUGGET.q(nuggets);
		int quanta = amount;

		if(blocks > 0) format += (blocks == 1 ? I18nUtil.resolveKey("matshape.block", blocks) : I18nUtil.resolveKey("matshape.blocks", blocks)) + " ";
		if(ingots > 0) format += (ingots == 1 ? I18nUtil.resolveKey("matshape.ingot", ingots) : I18nUtil.resolveKey("matshape.ingots", ingots)) + " ";
		if(nuggets > 0) format += (nuggets == 1 ? I18nUtil.resolveKey("matshape.nugget", nuggets) : I18nUtil.resolveKey("matshape.nuggets", nuggets)) + " ";
		if(quanta > 0) format += (quanta == 1 ? I18nUtil.resolveKey("matshape.quantum", quanta) : I18nUtil.resolveKey("matshape.quanta", quanta)) + " ";

		return format.trim();
	}
}