package flash.uilib.main;

import flash.uilib.proxy.ClientProxy;
import flash.uilib.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main {

  // Modid
  public static final String MODID = "uilib";

  public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

  // Directly reference a log4j logger.
  public static final Logger LOGGER = LogManager.getLogger();

  public Main() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event) {}
}
