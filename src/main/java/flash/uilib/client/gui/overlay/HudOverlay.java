package flash.uilib.client.gui.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.client.KeyHandler;
import flash.uilib.client.gui.widget.AnimatedBarWidget;
import flash.uilib.resource.AnimatedResourceLocationArray;
import flash.uilib.util.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class HudOverlay extends AbstractGui {

  private static final ResourceLocation TEXTURE = UIHelper.getResource("gui/example.png");

  private static final AnimatedResourceLocationArray ABILITY_USE = new AnimatedResourceLocationArray(false, false,
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_0.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_1.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_2.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_3.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_4.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_5.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_6.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_7.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_8.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_9.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_10.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_11.png"),
      UIHelper.getResource("gui/animated/valorant_ability_use/frame_12.png"), UIHelper.getResource("gui/animated/valorant_ability_use/frame_13.png"));
  private static final int ABILITY_USE_WIDTH = 200;
  private static final int ABILITY_USE_UV = 0;

  private final Minecraft minecraft;

  public static AnimatedBarWidget barWidget;

  public HudOverlay() {
    minecraft = Minecraft.getInstance();
    barWidget = new AnimatedBarWidget(2, 2, 262, 8, TEXTURE, 256, 256, 0, 6, 131, 4, KeyHandler.MAXPROGRESS, KeyHandler.PROGRESS);
    barWidget.setBackground(0, 0, 266, 12, 0, 0, 133, 6);
    barWidget.setTextType(UIHelper.TextType.BORDER);
  }

  @SubscribeEvent
  public void renderOverlay(RenderGameOverlayEvent.Pre event) {
    if (minecraft.player != null && minecraft.player.isAlive()) {
      if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
        MatrixStack matrixStack = event.getMatrixStack();
        float partialTicks = event.getPartialTicks();

        barWidget.renderWidget(matrixStack, 0, 0, partialTicks);

        if (!ABILITY_USE.isFinished()) {
          int w = 100;
          UIHelper.drawTexture(minecraft, matrixStack,
              minecraft.getMainWindow().getScaledWidth()/2-w/2,
              minecraft.getMainWindow().getScaledHeight()-w/2-75,
              w, w, ABILITY_USE.getTexture(),
              ABILITY_USE_WIDTH, ABILITY_USE_WIDTH, ABILITY_USE_UV, ABILITY_USE_UV, ABILITY_USE_WIDTH, ABILITY_USE_WIDTH,
              0.5f);
        }
      }
    }
  }

  @SubscribeEvent
  public void clientTick(TickEvent.ClientTickEvent event) {
    if (event.phase == TickEvent.Phase.START) {
      if (minecraft.player != null && minecraft.player.isAlive()) {
        barWidget.tick();
        ABILITY_USE.tick();
      }
    }
  }

  public static void useAbility() {
    ABILITY_USE.reset();
  }
}
