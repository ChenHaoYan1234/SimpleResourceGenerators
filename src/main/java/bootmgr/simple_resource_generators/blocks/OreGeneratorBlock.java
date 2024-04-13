package bootmgr.simple_resource_generators.blocks;

import bootmgr.simple_resource_generators.SimpleResourceGenerators;
import bootmgr.simple_resource_generators.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class OreGeneratorBlock extends Block implements IHasModel {


    public OreGeneratorBlock() {
        super(Material.ROCK);
        setTranslationKey("ore_generator");
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new OreGeneratorTileEntity();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        final TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof OreGeneratorTileEntity) {
            final ItemStackHandler inv = ((OreGeneratorTileEntity)tile).inventory;
            for (int i = 0; i < inv.getSlots(); i++) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), inv.getStackInSlot(i));
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public void registerModel() {
        SimpleResourceGenerators.proxy.registerRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public static final class OreGeneratorTileEntity extends TileEntity implements ITickable {
        private final ItemStackHandler inventory = new ItemStackHandler(1);
        private int ticks = 0;

        @Override
        public void update() {
            if (ticks != 10){
                ticks++;
                return;
            }else{
                ticks = 0;
            }

            World world = getWorld();
            BlockPos blockPos = getPos().up();

            if (world.isRemote) return;

            IBlockState aboveBlockState = world.getBlockState(blockPos);
            Block aboveBlock = aboveBlockState.getBlock();
            ItemStack aboveItemStack = new ItemStack(aboveBlock);

            if (aboveItemStack.getItem() == Items.AIR) return;

            int[] blockIDs = OreDictionary.getOreIDs(aboveItemStack);
            String[] blockOreDicts = new String[blockIDs.length];

            for (int i = 0; i < blockIDs.length; i++) {
                blockOreDicts[i] = OreDictionary.getOreName(blockIDs[i]);
            }

            boolean isOre = false;
            for (String oreDict : blockOreDicts) {
                if (oreDict.startsWith("ore")) {
                    isOre = true;
                    break;
                }
            }

            if (!isOre) return;

            SimpleResourceGenerators.LOGGER.info("Above block is "+aboveBlock.getRegistryName());
            if (inventory.getStackInSlot(0).getItem() == Items.AIR) {
                inventory.insertItem(0, new ItemStack(Item.getItemFromBlock(aboveBlock)), false);
            } else if (inventory.getStackInSlot(0).getItem() == Item.getItemFromBlock(aboveBlock)) {
                inventory.getStackInSlot(0).setCount(1 + inventory.getStackInSlot(0).getCount());
            }
        }

        @Override
        public boolean hasCapability(Capability<?> cap, EnumFacing side) {
            return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
        }

        @Nullable
        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
            if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new IItemHandler() {
                    @Override
                    public int getSlots() {
                        return inventory.getSlots();
                    }

                    @Nonnull
                    @Override
                    public ItemStack getStackInSlot(int slot) {
                        return inventory.getStackInSlot(slot);
                    }

                    @Nonnull
                    @Override
                    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                        return stack;
                    }

                    @Nonnull
                    @Override
                    public ItemStack extractItem(int slot, int amount, boolean simulate) {
                        return inventory.extractItem(slot, amount, simulate);
                    }

                    @Override
                    public int getSlotLimit(int slot) {
                        return inventory.getSlotLimit(slot);
                    }
                });
            }else {
                return super.getCapability(capability, facing);
            }
        }

        @Override
        public void readFromNBT(NBTTagCompound compound) {
            super.readFromNBT(compound);
            compound.getInteger("ticks");
            inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        }

        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound compound) {
            compound.setInteger("ticks", ticks);
            compound.setTag("inventory", inventory.serializeNBT());
            return super.writeToNBT(compound);
        }
    }
}
