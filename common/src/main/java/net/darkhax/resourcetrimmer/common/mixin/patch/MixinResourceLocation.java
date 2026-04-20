package net.darkhax.resourcetrimmer.common.mixin.patch;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Identifier.class)
public class MixinResourceLocation {

    @Shadow
    @Mutable
    @Final
    public static StreamCodec<ByteBuf, Identifier> STREAM_CODEC;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void inject(CallbackInfo ci) {
        STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(Identifier::parse, rl -> (rl.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) ? rl.getPath() : rl.toString());
    }
}