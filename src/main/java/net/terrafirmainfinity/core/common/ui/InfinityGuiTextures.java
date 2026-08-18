package net.terrafirmainfinity.core.common.ui;

import brachy.modularui.drawable.ColorType;
import brachy.modularui.drawable.UITexture;
import net.terrafirmainfinity.core.InfinityCore;
import org.jetbrains.annotations.Nullable;

public interface InfinityGuiTextures {
    UITexture ELECTRODE_OVERLAY_1 = fullImage("textures/gui/overlay/electrode_overlay_1.png",
            ColorType.DEFAULT);
    UITexture ELECTRODE_OVERLAY_2 = fullImage("textures/gui/overlay/electrode_overlay_2.png",
            ColorType.DEFAULT);

    private static UITexture fullImage(String path) {
        return fullImage(path, null);
    }

    private static UITexture fullImage(String path, @Nullable ColorType colorType) {
        return UITexture.fullImage(InfinityCore.id(path), colorType);
    }

    public static void init() {}
}
