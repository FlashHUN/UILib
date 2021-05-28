package flash.uilib.client;

import flash.uilib.client.gui.overlay.HudOverlay;
import flash.uilib.client.gui.screen.SomeRandomScreen;
import flash.uilib.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class KeyHandler {

  private final Minecraft minecraft;

  public static int PROGRESS;
  public static final int MAXPROGRESS = 25;

  public KeyHandler() {
    this.minecraft = Minecraft.getInstance();
    PROGRESS = 0;
  }

  @SubscribeEvent
  public void onKeyInput(InputEvent.KeyInputEvent event) {
    PlayerEntity player = minecraft.player;
    Map<ClientProxy.KeyBinds, KeyBinding> keyBindings = ClientProxy.keyBindingMap;
    if (minecraft.isGameFocused() && player != null && player.isAlive()) {
      // Key Press
      if (event.getAction() == 1) {
        if (keyBindings.get(ClientProxy.KeyBinds.OPENSCREEN).isPressed()) {
          minecraft.displayGuiScreen(new SomeRandomScreen());
        }

        if (keyBindings.get(ClientProxy.KeyBinds.ADDBARPROGRES).isPressed()) {
          if (PROGRESS < MAXPROGRESS) {
            PROGRESS++;
            HudOverlay.barWidget.setProgress(PROGRESS);
          }
        }

        if (keyBindings.get(ClientProxy.KeyBinds.SUBBARPROGRESS).isPressed()) {
          if (PROGRESS > 0) {
            PROGRESS--;
            HudOverlay.barWidget.setProgress(PROGRESS);
          }
        }

        if (keyBindings.get(ClientProxy.KeyBinds.PLAYANIM).isPressed()) {
          HudOverlay.useAbility();
        }
      }

    }
  }

}
