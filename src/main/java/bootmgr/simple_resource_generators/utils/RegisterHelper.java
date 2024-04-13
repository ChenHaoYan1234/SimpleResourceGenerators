package bootmgr.simple_resource_generators.utils;

import bootmgr.simple_resource_generators.blocks.BlockList;
import bootmgr.simple_resource_generators.blocks.OreGeneratorBlock;
import bootmgr.simple_resource_generators.items.ItemList;
import bootmgr.simple_resource_generators.Tags;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class RegisterHelper {
    public static final CreativeTabs SRG_CREATIVE_TAB = new CreativeTabs("simple_resource_generators") {
        // 获得用作标签图标的 ItemStack。你大可以往里面塞各种奇奇怪怪的数据。
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(ItemList.oreGeneratorItem);
        }
    };

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(BlockList.oreGeneratorBlock);

        GameRegistry.registerTileEntity(OreGeneratorBlock.OreGeneratorTileEntity.class, Objects.requireNonNull(BlockList.oreGeneratorBlock.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        event.getRegistry().register(ItemList.oreGeneratorItem);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        ((IHasModel)BlockList.oreGeneratorBlock).registerModel();
    }

    public static Item genItemBlock(Block block){
        return new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }
}
