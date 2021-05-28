package flash.uilib.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.util.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScrollBarWidget extends Widget {

  private ResourceLocation texture;
  private int scrollerHeight, maxScroll, tWidth, tHeight, u, v, uWidth, uHeight,
      backgroundWidth, backgroundHeight, backgroundU, backgroundV, backgroundUWidth, backgroundUHeight, backgroundOffsetX, backgroundOffsetY;
  private float currentScroll;
  private boolean isScrolling, hasBackground;

  public ScrollBarWidget(int x, int y, int width, int height, int scrollerHeight, int maxScroll, ResourceLocation texture, int tWidth, int tHeight,
                         int u, int v, int uWidth, int uHeight) {
    super(x, y, width, height, StringTextComponent.EMPTY);
    this.scrollerHeight = scrollerHeight;
    this.maxScroll = maxScroll;
    this.texture = texture;
    this.tWidth = tWidth;
    this.tHeight = tHeight;
    this.u = u;
    this.v = v;
    this.uWidth = uWidth;
    this.uHeight = uHeight;
  }

  public void setBackground(int offsetX, int offsetY, int width, int height, int u, int v, int uWidth, int uHeight) {
    this.hasBackground = true;
    this.backgroundOffsetX = offsetX;
    this.backgroundOffsetY = offsetY;
    this.backgroundWidth = width;
    this.backgroundHeight = height;
    this.backgroundU = u;
    this.backgroundV = v;
    this.backgroundUWidth = uWidth;
    this.backgroundUHeight = uHeight;
  }

  public float getCurrentScroll() {
    return currentScroll;
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    Minecraft minecraft = Minecraft.getInstance();

    if (hasBackground) {
      UIHelper.drawTexture(minecraft, matrixStack, x+backgroundOffsetX, y+backgroundOffsetY, backgroundWidth, backgroundHeight, texture, tWidth, tHeight, backgroundU, backgroundV, backgroundUWidth, backgroundUHeight);
    }

    int i = this.getYImage(isHovered());
    int yOffset = (int)((height - scrollerHeight) * currentScroll);
    UIHelper.drawTexture(minecraft, matrixStack, x, y + yOffset, width, scrollerHeight, texture, tWidth, tHeight, u, v + i*uHeight, uWidth, uHeight);
  }

  @Override
  public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
    if (!active) {
      return false;
    } else {
      this.currentScroll = (float)(currentScroll - delta / maxScroll);
      this.currentScroll = MathHelper.clamp(this.currentScroll, 0f, 1f);
      return true;
    }
  }

  @Override
  public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
    if (isScrolling) {
      int j = y + height;
      this.currentScroll = ((float)mouseY - (float)y - 7.5f) / ((float)(j - y) - 15f);
      this.currentScroll = MathHelper.clamp(this.currentScroll, 0f, 1f);
      return true;
    } else {
      return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    if (isHovered()) {
      this.isScrolling = this.active;
      mouseDragged(mouseX, mouseY, button, 0, 0);
    }

    return super.mouseClicked(mouseX, mouseY, button);
  }

  @Override
  public boolean mouseReleased(double mouseX, double mouseY, int button) {
    if (button == 0) {
      this.isScrolling = false;
      return true;
    }
    return super.mouseReleased(mouseX, mouseY, button);
  }
}
