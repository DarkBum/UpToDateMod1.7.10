package yuma140902.uptodatemod;

import java.io.File;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.blocks.Stone;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.worldgen.MyMinableGenerator;

@Mod(modid = ModUpToDateMod.MOD_ID, useMetadata = true)
public class ModUpToDateMod {
	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.Instance
	public static ModUpToDateMod INSTANCE;
	
	public static final String MOD_ID = "uptodate";
	public static final String MOD_NAME = "UpToDateMod";
	public static final String MOD_VERSION = "mc1.7.10_0.3.1";
	public static final String CONFIG_FILE_NAME = "config\\" + MOD_NAME + ".cfg";
	
	public boolean config_worldGen_genStones;
	public boolean config_recipeRemove_oldFenceRecipe;
	public boolean config_enable_observer;
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = MOD_ID;
		modMetadata.name = MOD_NAME;
		modMetadata.version = MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "1.7.10にマイクラ1.8以降のバニラの要素を追加するMODです";
		modMetadata.url = "https://minecraft.curseforge.com/projects/uptodatemod";
		modMetadata.autogenerated = false;
	}
	
	private void loadConfig() {
		Configuration cfg = new Configuration(new File(CONFIG_FILE_NAME));
		try {
			cfg.load();
			config_worldGen_genStones = cfg.getBoolean("genStones", "worldgen", true, "Generate Granite, Diorite, Andesite in Overworld or not | 花崗岩、閃緑岩、安山岩をワールドに生成するか否か");
			config_recipeRemove_oldFenceRecipe = cfg.getBoolean("removeOldFenceRecipe", "recipe", false, "Delete the recipe from 6 sticks to 2 fences | 棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)");
			config_enable_observer = cfg.getBoolean("enableObserver", "experimental", false, "Enable observer(note: Observer has bugs) | オブザーバーを有効にするか否か【オブザーバーは未実装機能・バグ多数につき無効にしておくことを推奨】");
			EntityModBoatBase.boatCrashWhenCollide = cfg.getBoolean("boatCrashWhenCollide", "entity", false, "Boat added by this mod will crash when collision | このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)");
		}
		finally {
			cfg.save();
		}
	}
	
	private void tweakVanilla() {
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		BlockTrapDoor.disableValidation = true;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		loadModMetadata(modMetadata);
		loadConfig();
		
		tweakVanilla();
		MyBlocks.register();
		MyItems.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Recipes.removeVanillaRecipes();
		Recipes.register();
		MyEntities.register();
		MyMinableGenerator.Config stoneConfig = new MyMinableGenerator.Config(config_worldGen_genStones, 33, 10, 0, 80);
		
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, Stone.META_GRANITE, stoneConfig);
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, Stone.META_DIORITE, stoneConfig);
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, Stone.META_ANDESITE, stoneConfig);
		WorldGenerators.register();
	}
}
