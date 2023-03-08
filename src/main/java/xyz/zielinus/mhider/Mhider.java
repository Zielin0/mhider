package xyz.zielinus.mhider;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.zielinus.mhider.commands.MorphCommand;
import xyz.zielinus.mhider.utils.Colorize;

import java.util.logging.Logger;

import static xyz.zielinus.mhider.utils.Colorize.Color.RED;
import static xyz.zielinus.mhider.utils.Colorize.Color.GREEN;

public final class Mhider extends JavaPlugin {

    public Logger logger = this.getLogger();
    public final String PLUGIN_NAME = this.getDescription().getName();

    @Override
    public void onEnable() {
        logger.info(Colorize.colorConsole(PLUGIN_NAME + " enabled!", GREEN));

        commandRegister();
    }

    @Override
    public void onDisable() {
        logger.info(Colorize.colorConsole(PLUGIN_NAME + " disabled!", RED));
    }

    private void commandRegister() {
        getCommand("morph").setExecutor(new MorphCommand(this));
    }
}
