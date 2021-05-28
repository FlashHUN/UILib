package flash.uilib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy implements IProxy {

  Minecraft minecraft = Minecraft.getInstance();

  public static Map<KeyBinds, KeyBinding> keyBindingMap = new HashMap<>();
  private static final String KEYCATEGORY = "key.uilib.category";

  public void keyBinds() {

    KeyBinding[] keyBindings;

    keyBindingMap.put(KeyBinds.OPENSCREEN, new KeyBinding("key.uilib.openscreen", GLFW.GLFW_KEY_G, KEYCATEGORY));
    keyBindingMap.put(KeyBinds.ADDBARPROGRES, new KeyBinding("key.uilib.addbarprogress", GLFW.GLFW_KEY_RIGHT, KEYCATEGORY));
    keyBindingMap.put(KeyBinds.SUBBARPROGRESS, new KeyBinding("key.uilib.subbarprogress", GLFW.GLFW_KEY_LEFT, KEYCATEGORY));

    keyBindingMap.put(KeyBinds.PLAYANIM, new KeyBinding("key.uilib.playanim", GLFW.GLFW_KEY_C, KEYCATEGORY));

    keyBindings = keyBindingMap.values().toArray(new KeyBinding[0]);

    for (KeyBinding keyBinding : keyBindings) {
      ClientRegistry.registerKeyBinding(keyBinding);
    }
  }

  public enum KeyBinds {
    OPENSCREEN,
    ADDBARPROGRES,
    SUBBARPROGRESS,
    PLAYANIM
  }

}
