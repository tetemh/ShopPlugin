package fr.tetemh.shop.commands;

import fr.tetemh.shop.Main;
import fr.tetemh.shop.tools.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandShop implements CommandExecutor, Listener {

    Inventory shop = Bukkit.createInventory(null, 3*9, "§eShop");
    Inventory shopDeco = Bukkit.createInventory(null, 3*9, "§eShop §lDecoration");
    Inventory shopSpawners = Bukkit.createInventory(null, 3*9, "§eShop §lSpawners");
    Inventory shopMinerals = Bukkit.createInventory(null, 3*9, "§eShop §lMinerals");
    Inventory shopFoods = Bukkit.createInventory(null, 3*9, "§eShop §lFoods");

    //Shop Menu
    ItemBuilder decoration = new ItemBuilder(Material.GRASS, 1).setName("§e§lDecoration");
    ItemBuilder spawner = new ItemBuilder(Material.MOB_SPAWNER, 1).setName("§e§lSpawner");
    ItemBuilder minerals = new ItemBuilder(Material.DIAMOND, 1).setName("§e§lMinerals");
    ItemBuilder foods = new ItemBuilder(Material.COOKED_BEEF, 1).setName("§e§lFoods");
    ItemBuilder glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setName(" ");

    private Main main;

    public CommandShop(Main main) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            for (int i = 0; i < shop.getSize(); i++) shop.setItem(i, glass.build());
            shop.setItem(10, decoration.build());
            shop.setItem(12, minerals.build());
            shop.setItem(14, foods.build());
            shop.setItem(16, spawner.build());

            player.openInventory(shop);

        }

        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();


        if (current == null) return;

        if (inv.getName().equalsIgnoreCase("§eMenu")) event.setCancelled(true);
        if (event.getView().getBottomInventory().equals(player.getInventory())) event.setCancelled(true);

        if(inv.getName().equals("§eShop") && current.getType() == Material.GRASS && current.getItemMeta().getDisplayName().equals("§e§lDecoration")) {
            player.closeInventory();
            player.openInventory(shopDeco);
        }

        if(inv.getName().equals("§eShop") && current.getType() == Material.DIAMOND && current.getItemMeta().getDisplayName().equals("§e§lMinerals")) {
            player.closeInventory();
            player.openInventory(shopMinerals);
        }

        if(inv.getName().equals("§eShop") && current.getType() == Material.COOKED_BEEF && current.getItemMeta().getDisplayName().equals("§e§lFoods")) {
            player.closeInventory();
            player.openInventory(shopFoods);
        }

        if(inv.getName().equals("§eShop") && current.getType() == Material.MOB_SPAWNER && current.getItemMeta().getDisplayName().equals("§e§lSpawner")) {
            player.closeInventory();
            player.openInventory(shopSpawners);
        }
    }

    public void onQuitInv(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        player.openInventory(shop);
    }

}
