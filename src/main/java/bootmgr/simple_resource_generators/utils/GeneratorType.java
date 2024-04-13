package bootmgr.simple_resource_generators.utils;

import bootmgr.simple_resource_generators.blocks.BlockList;
import net.minecraft.block.Block;

public enum GeneratorType {
    ORE_GENERATOR(0, BlockList.oreGeneratorBlock);

    public final int id;
    public final Block block;

    GeneratorType(int id, Block block) {
        this.id = id;
        this.block = block;
    }

    public static GeneratorType getFromId(int id) {
        for (GeneratorType type : GeneratorType.values()) {
            if (type.id == id) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public Block getBlock() {
        return block;
    }
}
