package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

@GTAddon(InfinityCore.MOD_ID)
public class InfinityCoreGTAddon implements IGTAddon
{
    @Override
    public GTRegistrate getRegistrate()
    {
        return InfinityCore.REGISTRATE;
    }

    @Override
    public void gtInitComplete()
    {
        InfinityCore.LOGGER.info("InfinityCoreGTAddon has loaded!");
    }
}
