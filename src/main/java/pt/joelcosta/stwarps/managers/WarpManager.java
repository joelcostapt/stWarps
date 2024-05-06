package pt.joelcosta.stwarps.managers;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pt.joelcosta.stwarps.Main;
import pt.joelcosta.stwarps.objects.Warp;
import pt.joelcosta.stwarps.utils.LocationSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarpManager {

    private ArrayList<Warp> warpList;
    private static final Main instance = Main.getInstance();

    public WarpManager() {
        warpList = new ArrayList<>();
        load();
    }

    public Warp get(String warpName){
        return warpList.stream().filter(w -> w.getName().equalsIgnoreCase(warpName)).findFirst().orElse(null);
    }

    public void insert(Warp warp){
        warpList.add(warp);
        save(warp);
    }

    public void go(Player player, Warp warp){
        player.teleport(warp.getLocation());
    }

    public String list(){
        List<String> warpNameList = warpList.stream().map(Warp::getName).collect(Collectors.toList());
        String list = String.join(", ", warpNameList);
        return "§eLista de warps: §f" + list;
    }

    public boolean delete(String warpName){
        Warp warp = get(warpName);
        if (warp != null){
            warpList.remove(warp);
            ConfigurationSection config = instance.getConfig().getConfigurationSection("Warps");
            config.set(warpName, null);
            instance.saveConfig();
            return true;
        }
        return false;
    }

    private void save(Warp warp){
        ConfigurationSection config = instance.getConfig().getConfigurationSection("Warps");
        config.set(warp.getName(), LocationSerializer.serialize(warp.getLocation()));
        instance.saveConfig();
    }

    private void load(){
        ConfigurationSection config = instance.getConfig().getConfigurationSection("Warps");
        for (String key : config.getKeys(false)) {
            Location location = LocationSerializer.unserialize(config.getString(key));
            Warp warp = new Warp(key, location);
            warpList.add(warp);
        }
        System.out.println("[stWarps] " + warpList.size() + " warps foram carregadas.");
    }
}
