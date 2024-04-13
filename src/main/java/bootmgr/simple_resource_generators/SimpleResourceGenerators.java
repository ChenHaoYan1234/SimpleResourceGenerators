package bootmgr.simple_resource_generators;

import bootmgr.simple_resource_generators.gui.GuiHandler;
import bootmgr.simple_resource_generators.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class SimpleResourceGenerators {
    @SidedProxy(serverSide = "bootmgr.simple_resource_generators.proxy.CommonProxy", clientSide = "bootmgr.simple_resource_generators.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.Instance
    public static SimpleResourceGenerators instance;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
        // !TODO finish gui of generators
        //NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

}
