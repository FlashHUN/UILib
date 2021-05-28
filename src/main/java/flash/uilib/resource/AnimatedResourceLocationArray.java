package flash.uilib.resource;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedResourceLocationArray {

  private final boolean loops;
  private final ResourceLocation[] textures;
  private int currentTick;
  private boolean isForward, finished;

  public AnimatedResourceLocationArray(boolean autoPlay, boolean loops, ResourceLocation... textures) {
    this.loops = loops;
    this.textures = textures;
    this.finished = !autoPlay;
  }

  public void reverse() {
    isForward = !isForward;

    if (finished)
      finished = false;
  }

  public void reset() {
    if (isForward)
      currentTick = 0;
    else
      currentTick = textures.length - 1;

    finished = false;
  }

  public void tick() {
    if (!finished) {
      if (isForward) {
        if (currentTick < textures.length - 1) {
          currentTick++;
        } else if (loops) {
          currentTick = 0;
        } else {
          finished = true;
        }
      } else {
        if (currentTick > 0) {
          currentTick--;
        } else if (loops) {
          currentTick = textures.length - 1;
        } else {
          finished = true;
        }
      }
    }
  }

  public boolean isFinished() {
    return finished;
  }

  public ResourceLocation getTexture() {
    return textures[currentTick];
  }

}
