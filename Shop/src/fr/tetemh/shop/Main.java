package fr.tetemh.shop;

import fr.tetemh.shop.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        CommandShop commandShop = new CommandShop(this);

        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        getServer().getPluginManager().registerEvents(commandShop, this);
        getCommand("Shop").setExecutor(commandShop);
        getCommand("Rs").setExecutor(new CommandRs());
    }

}
