package pt.joelcosta.stwarps.objects;


import org.bukkit.Location;

public class Warp {
    private String name;
    private Location location;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public org.bukkit.Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
