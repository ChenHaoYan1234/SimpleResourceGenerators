package bootmgr.simple_resource_generators.gui;

import bootmgr.simple_resource_generators.Tags;
import bootmgr.simple_resource_generators.utils.GeneratorType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGenerators extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tags.MOD_ID, "textures/gui/generators.png");
    private static final int xSize = 176;
    private static final int ySize = 166;
    private final GeneratorType type;

    public GuiGenerators(Container inventorySlotsIn, GeneratorType type) {
        super(inventorySlotsIn);
        this.type = type;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = type.getBlock().getLocalizedName();
        mc.fontRenderer.drawString(name, 10, 10, 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        mc.getTextureManager().bindTexture(TEXTURE);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
