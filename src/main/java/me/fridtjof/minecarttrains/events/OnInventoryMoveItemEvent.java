package me.fridtjof.minecarttrains.events;

import me.fridtjof.minecarttrains.MinecartTrains;
import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class OnInventoryMoveItemEvent implements Listener
{

    static MinecartTrains plugin = MinecartTrains.getInstance();

    private final String FUEL_CART_NAME = plugin.configManager.mainConfig.getConfig().getString("trains.fuel_cart_name");

    //stop hoppers from unloading fuel carts
    @EventHandler
    public void onMoveItemOutOffFuelCartEvent(InventoryMoveItemEvent event)
    {
        if(!(event.getSource().getHolder() instanceof StorageMinecart))
        {
            return;
        }

        if(!(event.getDestination().getHolder() instanceof Hopper))
        {
            return;
        }

        if(isFuelCart((StorageMinecart) event.getSource().getHolder()))
        {
            event.setCancelled(true);
        }
    }

    //prevent hoppers from filling non coal items in carts
    //cut from rebuild due to HopperUtils error

    public boolean isFuelCart(StorageMinecart cart)
    {
        if(cart.getCustomName() == null)
        {
            return false;
        }

        if(cart.getCustomName().equalsIgnoreCase(FUEL_CART_NAME))
        {
            return true;
        }

        return false;
    }
}
