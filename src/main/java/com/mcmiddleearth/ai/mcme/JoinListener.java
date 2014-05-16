/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import static com.mcmiddleearth.ai.mcme.DBmanager.loadclass;
import static com.mcmiddleearth.ai.mcme.DBmanager.saveclass;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Donovan
 */
public class JoinListener implements Listener{

    @EventHandler
    public void onLogin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        getLogger().info(player.getName());
        if(!event.getPlayer().hasPlayedBefore()) {
            Questdat hold = new Questdat(player);
            DBmanager.currQuests.put(player.getName(), hold);
            saveclass(player);
        }
        DBmanager.loadclass(player);
    }
}
