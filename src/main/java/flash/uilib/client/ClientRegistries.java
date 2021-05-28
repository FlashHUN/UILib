package flash.uilib.client;

import flash.uilib.client.gui.overlay.HudOverlay;
import flash.uilib.main.Main;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistries {

  @SubscribeEvent
  public static void clientSetup(final FMLClientSetupEvent event) {
    IEventBus forge = MinecraftForge.EVENT_BUS;
    Main.PROXY.keyBinds();
    forge.register(new KeyHandler());

    forge.register(new HudOverlay());
  }

}
