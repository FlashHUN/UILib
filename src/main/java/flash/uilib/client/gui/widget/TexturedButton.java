package flash.uilib.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.util.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TexturedButton extends Button {

  private ResourceLocation texture;
  private int u, v, tWidth, tHeight, uWidth, uHeight, textOffsetX, textOffsetY, textHoveredOffsetX, textHoveredOffsetY;
  private UIHelper.TextType textType;

  public TexturedButton(int x, int y, int width, int height, ResourceLocation texture, int tWidth, int tHeight, int u, int v, int uWidth, int uHeight, IPressable pressedAction) {
    this(x, y, width, height, texture, tWidth, tHeight, u, v, uWidth, uHeight, StringTextComponent.EMPTY, pressedAction);
  }

  public TexturedButton(int x, int y, int width, int height, ResourceLocation texture, int tWidth, int tHeight, int u, int v, int uWidth, int uHeight, ITextComponent title, IPressable pressedAction) {
    super(x, y, width, height, title, pressedAction);
    this.texture = texture;
    this.tWidth = tWidth;
    this.tHeight = tHeight;
    this.u = u;
    this.v = v;
    this.uWidth = uWidth;
    this.uHeight = uHeight;
    this.textType = UIHelper.TextType.SHADOW;
  }

  public void setTextType(UIHelper.TextType textType) {
    this.textType = textType;
  }

  public void setTextOffset(int x, int y) {
    this.textOffsetX = x;
    this.textOffsetY = y;
  }

  public void setTextHoveredOffset(int x, int y) {
    this.textHoveredOffsetX = x;
    this.textHoveredOffsetY = y;
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    Minecraft minecraft = Minecraft.getInstance();
    FontRenderer fontrenderer = minecraft.fontRenderer;

    int i = this.getYImage(this.isHovered()) - 1;

    UIHelper.drawTexture(minecraft, matrixStack,
        x, y, width, height,
        texture, tWidth, tHeight,
        u, v + i * uHeight, uWidth, uHeight);
    this.renderBg(matrixStack, minecraft, mouseX, mouseY);
    int j = getFGColor();

    int textX = this.x + this.width / 2 + textOffsetX + (this.isHovered() ? textHoveredOffsetX : 0);
    int textY = this.y + (this.height - (fontrenderer.FONT_HEIGHT - 1)) / 2 + textOffsetY + (this.isHovered() ? textHoveredOffsetY : 0);
    int color = j | MathHelper.ceil(this.alpha * 255.0F) << 24;
    switch (textType) {
      case SHADOW:
        drawCenteredString(matrixStack, fontrenderer,
            this.getMessage(), textX, textY, color);
        break;
      case NORMAL:
        fontrenderer.drawString(matrixStack, this.getMessage().getString(),
            textX, textY, color);
        break;
      case BORDER:
        UIHelper.drawCenteredStringWithBorder(matrixStack, fontrenderer,
            this.getMessage().getString(), textX, textY, color);
        break;
    }
    if (this.isHovered()) {
      this.renderToolTip(matrixStack, mouseX, mouseY);
    }
  }
}
