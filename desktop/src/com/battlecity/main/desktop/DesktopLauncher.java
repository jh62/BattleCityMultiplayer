package com.battlecity.main.desktop;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.battlecity.main.CoreBC;
import com.battlecity.main.global.Presets;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config
                = new LwjglApplicationConfiguration();
        config.addIcon("data/ui/icon128x.png", Files.FileType.Internal);
        config.addIcon("data/ui/icon32x.png", Files.FileType.Internal);
        config.addIcon("data/ui/icon16x.png", Files.FileType.Internal);
        config.resizable = true;
        config.forceExit = true;
        config.width = Presets.SCREEN_WIDTH;
        config.height = Presets.SCREEN_HEIGHT;  
        config.allowSoftwareMode = true;
        new LwjglApplication(CoreBC.INSTANCE, config);
    }
}
