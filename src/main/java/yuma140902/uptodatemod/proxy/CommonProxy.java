package yuma140902.uptodatemod.proxy;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.EntityBoatAcacia;
import yuma140902.uptodatemod.entity.item.EntityBoatBirch;
import yuma140902.uptodatemod.entity.item.EntityBoatDarkOak;
import yuma140902.uptodatemod.entity.item.EntityBoatJungle;
import yuma140902.uptodatemod.entity.item.EntityBoatSpruce;
import yuma140902.uptodatemod.entity.item.EntityFallingConcretePowderBlock;
import yuma140902.uptodatemod.event_handlers.CommonEventHandler;

public class CommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
	}
	
	private int id = 0;
	public void registerEntities() {
		EntityRegistry.registerModEntity(EntityBoatAcacia.class, "boat_acacia", id++, ModUpToDateMod.INSTANCE, 128, 5, true);
		EntityRegistry.registerModEntity(EntityBoatBirch.class, "boat_birch", id++, ModUpToDateMod.INSTANCE, 128, 5, true);
		EntityRegistry.registerModEntity(EntityBoatDarkOak.class, "boat_dark_oak", id++, ModUpToDateMod.INSTANCE, 128, 5, true);
		EntityRegistry.registerModEntity(EntityBoatJungle.class, "boat_jungle", id++, ModUpToDateMod.INSTANCE, 128, 5, true);
		EntityRegistry.registerModEntity(EntityBoatSpruce.class, "boat_spruce", id++, ModUpToDateMod.INSTANCE, 128, 5, true);
		EntityRegistry.registerModEntity(EntityFallingConcretePowderBlock.class, "falling_concrete_powder_block", id++, ModUpToDateMod.INSTANCE, 512, 1, true);
	}
	
	public void registerRenderers() { }
}
