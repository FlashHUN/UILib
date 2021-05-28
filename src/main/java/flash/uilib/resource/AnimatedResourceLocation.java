package flash.uilib.resource;

import com.mojang.blaze3d.matrix.MatrixStack;
import flash.uilib.util.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AnimatedResourceLocation {

  private final int width, height, numOfFrames;
  private final boolean loops;
  private final ResourceLocation texture;
  private int currentTick;
  private boolean isForward, finished;

  public AnimatedResourceLocation(boolean autoPlay, boolean loops, ResourceLocation texture, int width, int height, int numOfFrames) {
    this.currentTick = 0;
    this.loops = loops;
    this.texture = texture;
    this.width = width;
    this.height = height;
    this.numOfFrames = numOfFrames;
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
      currentTick = numOfFrames - 1;

    finished = false;
  }

  public void tick() {
    if (!finished) {
      if (isForward) {
        if (currentTick < numOfFrames - 1) {
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
          currentTick = numOfFrames - 1;
        } else {
          finished = true;
        }
      }
    }
  }

  public boolean isFinished() {
    return finished;
  }

  public void draw(Minecraft minecraft, MatrixStack matrixStack, int x, int y, int width, int height) {
    UIHelper.drawTexture(minecraft, matrixStack, x, y, width, height, texture, this.width, this.height*numOfFrames, 0, this.height*currentTick, this.width, this.height);
  }
}
