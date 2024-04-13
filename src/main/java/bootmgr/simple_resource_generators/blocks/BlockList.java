package bootmgr.simple_resource_generators.blocks;

import bootmgr.simple_resource_generators.Tags;
import bootmgr.simple_resource_generators.utils.RegisterHelper;
import net.minecraft.block.Block;

public class BlockList {
    public static Block oreGeneratorBlock = new OreGeneratorBlock().setRegistryName(Tags.MOD_ID, "ore_generator").setCreativeTab(RegisterHelper.SRG_CREATIVE_TAB);
}
