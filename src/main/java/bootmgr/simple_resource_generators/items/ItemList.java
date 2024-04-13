package bootmgr.simple_resource_generators.items;

import bootmgr.simple_resource_generators.blocks.BlockList;
import bootmgr.simple_resource_generators.utils.RegisterHelper;
import net.minecraft.item.Item;

public class ItemList {
    public static final Item oreGeneratorItem = RegisterHelper.genItemBlock(BlockList.oreGeneratorBlock);
}
