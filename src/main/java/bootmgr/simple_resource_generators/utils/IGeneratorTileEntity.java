package bootmgr.simple_resource_generators.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public interface IGeneratorTileEntity extends ITickable {
    void update();
    boolean hasCapability(Capability<?> cap, EnumFacing side);
    <T> T getCapability(Capability<T> capability, EnumFacing facing);
    void readFromNBT(NBTTagCompound compound);
    NBTTagCompound writeToNBT(NBTTagCompound compound);
}
