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
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        DBmanager.currQuests.remove(player.getName());
    }
    @EventHandler
    public void PlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
        if(event.getMessage().equalsIgnoreCase("warp")){
            Player player = event.getPlayer();
            Questdat pq = DBmanager.currQuests.get(player.getName());
            Quest q = DBmanager.Quests.get(pq.getCurrent());
            if(q.isWalking(player)){
                event.setCancelled(true);
                player.sendMessage("You are on a walking quest!");
                player.sendMessage("/endquest to leave current quest");
            }
        }
    }
}
