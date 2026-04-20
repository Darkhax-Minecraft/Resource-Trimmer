package net.darkhax.resourcetrimmer.common.mixin.patch;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FriendlyByteBuf.class)
public class MixinFriendlyByteBuf {

    @Inject(method = "writeIdentifier", at = @At("HEAD"), cancellable = true)
    public void writeResourceLocation(Identifier identifier, CallbackInfoReturnable<FriendlyByteBuf> cbr) {
        if (identifier.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            this.writeUtf(identifier.getPath());
            cbr.setReturnValue((FriendlyByteBuf) (Object) this);
        }
    }

    @Shadow
    public FriendlyByteBuf writeUtf(String value) {
        return null;
    }
}