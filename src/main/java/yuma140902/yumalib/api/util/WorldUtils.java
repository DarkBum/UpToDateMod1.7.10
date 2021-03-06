package yuma140902.yumalib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.yumalib.api.McConst.Meta;

public class WorldUtils {
	public static Block getBlock(World world, BlockPos pos) {
		return world.getBlock(pos.x(), pos.y(), pos.z());
	}
	
	public static boolean isAir(World world, BlockPos pos) {
		return world.isAirBlock(pos.x(), pos.y(), pos.z());
	}
	
	public static int getMeta(World world, BlockPos pos) {
		return world.getBlockMetadata(pos.x(), pos.y(), pos.z());
	}
	
	// デフォルトの{@link World#isSideSolid()}だと下置きハーフブロックの下面の判定などができなかったため作成
	public static boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection direction, boolean dflt) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		// 下置きハーフブロック
		if(direction == ForgeDirection.DOWN && block instanceof BlockSlab) {
			return (Meta.IS_SLAB_UPPER & meta) != Meta.IS_SLAB_UPPER;
		}
		// 階段
		else if(direction == ForgeDirection.DOWN && block instanceof BlockStairs) {
			return (Meta.IS_STAIRS_UPSIDE_DOWN & meta) != Meta.IS_STAIRS_UPSIDE_DOWN;
		}
		return world.isSideSolid(x, y, z, direction, dflt);
	}
}
