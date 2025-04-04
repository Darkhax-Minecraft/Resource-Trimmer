package net.darkhax.resourcetrimmer.common.mixin.patch;

import io.netty.buffer.ByteBuf;
import net.darkhax.bookshelf.common.impl.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourceLocation.class)
public class MixinResourceLocation {

    @Shadow
    @Mutable
    @Final
    public static StreamCodec<ByteBuf, ResourceLocation> STREAM_CODEC;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void inject(CallbackInfo ci) {
        STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(ResourceLocation::parse, rl -> (rl.getNamespace().equalsIgnoreCase(ResourceLocation.DEFAULT_NAMESPACE)) ? rl.getPath() : rl.toString());
    }
}