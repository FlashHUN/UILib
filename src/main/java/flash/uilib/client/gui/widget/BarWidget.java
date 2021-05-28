package flash.uilib.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.util.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BarWidget extends Widget {

  private ResourceLocation texture;
  private int u, v, tWidth, tHeight, uWidth, uHeight, maxValue, progress, textColor,
      backgroundX, backgroundY, backgroundWidth, backgroundHeight, backgroundU, backgroundV, backgroundUWidth, backgroundUHeight;
  private String formatString;
  private boolean hasBackground;
  private UIHelper.TextType textType;

  public BarWidget(int x, int y, int width, int height,
                   ResourceLocation texture, int tWidth, int tHeight,
                   int u, int v, int uWidth, int uHeight,
                   int maxValue, int progress) {
    this(x, y, width, height, texture, tWidth, tHeight, u, v, uWidth, uHeight, maxValue);
    this.progress = progress;
  }

  public BarWidget(int x, int y, int width, int height,
                   ResourceLocation texture, int tWidth, int tHeight,
                   int u, int v, int uWidth, int uHeight,
                   int maxValue) {
    super(x, y, width, height, StringTextComponent.EMPTY);
    this.texture = texture;
    this.tWidth = tWidth;
    this.tHeight = tHeight;
    this.u = u;
    this.v = v;
    this.uWidth = uWidth;
    this.uHeight = uHeight;
    this.maxValue = maxValue;
    this.textColor = 0xFFFFFF;
    this.formatString = "/";
    this.textType = UIHelper.TextType.SHADOW;
  }

  protected int getProgress() {
    return progress;
  }

  public void setProgress(int progress) {
    this.progress = progress;
  }

  public void setTextColor(int colorHEX) {
    this.textColor = colorHEX;
  }

  public void setFormatString(String formatString) {
    this.formatString = formatString;
  }

  public void setTextType(UIHelper.TextType textType) {
    this.textType = textType;
  }

  public void setBackground(int x, int y, int width, int height, int u, int v, int uWidth, int uHeight) {
    this.hasBackground = true;
    this.backgroundX = x;
    this.backgroundY = y;
    this.backgroundWidth = width;
    this.backgroundHeight = height;
    this.backgroundU = u;
    this.backgroundV = v;
    this.backgroundUWidth = uWidth;
    this.backgroundUHeight = uHeight;
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    Minecraft minecraft = Minecraft.getInstance();
    FontRenderer fontrenderer = minecraft.fontRenderer;

    if (hasBackground) {
      UIHelper.drawTexture(minecraft, matrixStack,
          backgroundX, backgroundY, backgroundWidth, backgroundHeight,
          texture, tWidth, tHeight,
          backgroundU, backgroundV, backgroundUWidth, backgroundUHeight);
    }

    int barWidth = (int)(width * (progress / (float)maxValue));
    int barUWidth = (int)(uWidth * (progress / (float)maxValue));
    UIHelper.drawTexture(minecraft, matrixStack,
        x, y, barWidth, height,
        texture, tWidth, tHeight,
        u, v, barUWidth, uHeight);

    String text = progress + formatString + maxValue;
    switch (textType) {
      case SHADOW:
        drawCenteredString(matrixStack, fontrenderer,
            text, this.x + this.width / 2, this.y + (this.height - (fontrenderer.FONT_HEIGHT - 1)) / 2, textColor);
        break;
      case NORMAL:
        fontrenderer.drawString(matrixStack, text,
            x + width/2 - fontrenderer.getStringWidth(text)/2, y + (height - (fontrenderer.FONT_HEIGHT-1))/2, textColor);
        break;
      case BORDER:
        UIHelper.drawCenteredStringWithBorder(matrixStack, fontrenderer,
            text, this.x + this.width / 2, this.y + (this.height - (fontrenderer.FONT_HEIGHT - 1)) / 2, textColor);
        break;
    }
  }
}
