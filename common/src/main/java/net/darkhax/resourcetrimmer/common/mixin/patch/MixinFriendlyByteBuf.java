package net.darkhax.resourcetrimmer.common.mixin.patch;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FriendlyByteBuf.class)
public class MixinFriendlyByteBuf {

    @Inject(method = "writeResourceLocation", at = @At("HEAD"), cancellable = true)
    public void writeResourceLocation(ResourceLocation rl, CallbackInfoReturnable<FriendlyByteBuf> cbr) {
        if (rl.getNamespace().equalsIgnoreCase(ResourceLocation.DEFAULT_NAMESPACE)) {
            this.writeUtf(rl.getPath());
            cbr.setReturnValue((FriendlyByteBuf) (Object) this);
        }
    }

    @Shadow
    public FriendlyByteBuf writeUtf(String string) {
        return null;
    }
}