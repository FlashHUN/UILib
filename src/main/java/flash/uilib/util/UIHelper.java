package flash.uilib.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import flash.uilib.main.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UIHelper {

  public static ResourceLocation getResource(String path) {
    return new ResourceLocation(Main.MODID, "textures/"+path);
  }

  public static void drawTexture(Minecraft minecraft, MatrixStack matrixStack,
                                 int x, int y, int width, int height,
                                 ResourceLocation texture, int tWidth, int tHeight,
                                 int u, int v, int uWidth, int uHeight) {
    drawTexture(minecraft, matrixStack, x, y, width, height, texture, tWidth, tHeight, u, v, uWidth, uHeight, 1f);
  }

  public static void drawTexture(Minecraft minecraft, MatrixStack matrixStack,
                                 int x, int y, int width, int height,
                                 ResourceLocation texture, int tWidth, int tHeight,
                                 int u, int v, int uWidth, int uHeight,
                                 float alpha) {
    drawTexture(minecraft, matrixStack, x, y, width, height, texture, tWidth, tHeight, u, v, uWidth, uHeight, 1f, 1f, 1f, alpha);
  }

  public static void drawTexture(Minecraft minecraft, MatrixStack matrixStack,
                                 int x, int y, int width, int height,
                                 ResourceLocation texture, int tWidth, int tHeight,
                                 int u, int v, int uWidth, int uHeight,
                                 float r, float g, float b) {
    drawTexture(minecraft, matrixStack, x, y, width, height, texture, tWidth, tHeight, u, v, uWidth, uHeight, r, g, b, 1f);
  }

  @SuppressWarnings("deprecation")
  public static void drawTexture(Minecraft minecraft, MatrixStack matrixStack,
                                 int x, int y, int width, int height,
                                 ResourceLocation texture, int tWidth, int tHeight,
                                 int u, int v, int uWidth, int uHeight,
                                 float r, float g, float b, float alpha) {
    RenderSystem.color4f(r, g, b, alpha);
    GlStateManager.enableBlend();
    RenderHelper.disableStandardItemLighting();
    minecraft.getTextureManager().bindTexture(texture);
    AbstractGui.blit(matrixStack, x, y, width, height, u, v, uWidth, uHeight, tWidth, tHeight);
    GlStateManager.disableBlend();
    RenderSystem.color4f(1f, 1f, 1f, 1f);
  }

  public static void drawStringWithBorder(MatrixStack matrixStack, FontRenderer fontRenderer, String text, int x, int y, int color) {
    drawStringWithBorder(matrixStack, fontRenderer, text, x, y, color, 0x000000);
  }

  public static void drawStringWithBorder(MatrixStack matrixStack, FontRenderer fontRenderer, String text, int x, int y, int color, int borderColor) {
    fontRenderer.drawString(matrixStack, text, x-1, y, borderColor);
    fontRenderer.drawString(matrixStack, text, x+1, y, borderColor);
    fontRenderer.drawString(matrixStack, text, x, y+1, borderColor);
    fontRenderer.drawString(matrixStack, text, x, y-1, borderColor);
    fontRenderer.drawString(matrixStack, text, x, y, color);
  }

  public static void drawCenteredStringWithBorder(MatrixStack matrixStack, FontRenderer fontRenderer, String text, int x, int y, int color) {
    drawCenteredStringWithBorder(matrixStack, fontRenderer, text, x, y, color, 0x000000);
  }

  public static void drawCenteredStringWithBorder(MatrixStack matrixStack, FontRenderer fontRenderer, String text, int x, int y, int color, int borderColor) {
    drawStringWithBorder(matrixStack, fontRenderer, text, x - fontRenderer.getStringWidth(text) / 2, y, color, borderColor);
  }

  public enum TextType {
    NORMAL,
    SHADOW,
    BORDER
  }
}
