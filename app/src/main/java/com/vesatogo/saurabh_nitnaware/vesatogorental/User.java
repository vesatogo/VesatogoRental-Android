package com.vesatogo.saurabh_nitnaware.vesatogorental;

public class User {

    private String name;
    private String village_name;
    private String mob_number;
    private String land_size;

    public User(String name, String village_name, String mob_number, String land_size) {
        this.name = name;
        this.village_name = village_name;
        this.mob_number = mob_number;
        this.land_size = land_size;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public String getMob_number() {
        return mob_number;
    }

    public String getLand_size() {
        return land_size;
    }
}
