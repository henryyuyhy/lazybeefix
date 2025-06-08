package com.yu.lazybeefix.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow public abstract ResourceKey<Level> dimension();

    @Inject(method = "isRaining", at = @At("HEAD"), cancellable = true)
    public void isRaining(CallbackInfoReturnable<Boolean> cir) {
        if (this.dimension() == Level.NETHER || this.dimension() == Level.END) {
            cir.setReturnValue(false);
            cir.cancel();
            return;
        }
        //return (double)this.getRainLevel(1.0F) > 0.2;
    }
}
