package flash.uilib.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.client.gui.widget.ScrollBarWidget;
import flash.uilib.client.gui.widget.TexturedButton;
import flash.uilib.resource.AnimatedResourceLocation;
import flash.uilib.util.UIHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SomeRandomScreen extends Screen {

  private static final ResourceLocation TEXTURE = UIHelper.getResource("gui/example.png");

  private static final AnimatedResourceLocation SHARINGAN = new AnimatedResourceLocation(false, false,
      UIHelper.getResource("gui/animated/sharingan.png"), 276, 276, 14);

  private TexturedButton buttonPlaySharinganAnim;
  private ScrollBarWidget scrollBarWidget;

  public SomeRandomScreen() {
    super(new StringTextComponent("screen.uilib.somerandomscreen.title"));
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    super.render(matrixStack, mouseX, mouseY, partialTicks);
    SHARINGAN.draw(minecraft, matrixStack, width/2-100/2, height/2-100/2, 100, 100);
  }

  @Override
  protected void init() {
    super.init();
    buttonPlaySharinganAnim = this.addButton(new TexturedButton(width/2-100/2, height/2+100/2+10, 100, 20, TEXTURE, 256, 256, 0, 10, 100, 20, new TranslationTextComponent("screen.uilib.somerandomscreen.playanim"), pressed -> SHARINGAN.reverse()));
    buttonPlaySharinganAnim.setTextOffset(0, -3);
    buttonPlaySharinganAnim.setTextHoveredOffset(1, 3);

    scrollBarWidget = this.addButton(new ScrollBarWidget(width/2+100/2+10, height/2-100/2, 8, 100, 16, 10, TEXTURE, 256, 256, 100, 10, 8, 16));
    scrollBarWidget.setBackground(-1, -1, 10, 102, 108, 10, 10, 102);
  }

  @Override
  public void tick() {
    super.tick();

    SHARINGAN.tick();
  }
}