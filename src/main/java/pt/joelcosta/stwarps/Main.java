package pt.joelcosta.stwarps;

import org.bukkit.plugin.java.JavaPlugin;
import pt.joelcosta.stwarps.commands.WarpCommand;
import pt.joelcosta.stwarps.managers.WarpManager;

public final class Main extends JavaPlugin {

    private static Main instance;
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        warpManager = new WarpManager();
        getCommand("warp").setExecutor(new WarpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public static Main getInstance() {
        return instance;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }
}
