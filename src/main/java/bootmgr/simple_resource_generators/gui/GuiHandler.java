package bootmgr.simple_resource_generators.gui;

import bootmgr.simple_resource_generators.utils.GeneratorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GeneratorType _type = GeneratorType.getFromId(ID);
        BlockPos blockPos = new BlockPos(x, y, z);
        if (_type != null) {
            return switch (_type) {
                case ORE_GENERATOR -> new ContainerGenerators(player, blockPos);
            };
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GeneratorType _type = GeneratorType.getFromId(ID);
        BlockPos blockPos = new BlockPos(x, y, z);
        if (_type != null) {
            return switch (_type) {
                case ORE_GENERATOR -> new GuiGenerators(new ContainerGenerators(player, blockPos), _type);
            };
        }
        return null;
    }
}
