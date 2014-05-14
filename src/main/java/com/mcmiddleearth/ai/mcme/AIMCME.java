/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan
 */
public class AIMCME extends JavaPlugin {
    
    static AIMCME pluginInstance;
    
    @Override    
    public void onEnable(){
        pluginInstance = this;
        getCommand("say").setExecutor(new Commands());
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        int Loaded = DBmanager.loadQuests();
        getLogger().info(String.valueOf("Loaded " + Loaded + " Quests"));
    }
    @Override
    public void onDisable(){
        
    }
    public static AIMCME getPlugin(){
        return pluginInstance;
    }
}
