package net.algorithmlx.tenytech.mixin;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = CropTier.class, remap = false)
public class CropTierMixin {
    @Shadow private Supplier<? extends FarmlandBlock> farmland;

    @Inject(method = "isEffectiveFarmland", at = @At("HEAD"), cancellable = true)
    public void inject0(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (farmland == null) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}