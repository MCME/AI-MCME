/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        getCommand("endquest").setExecutor(new Commands());
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        int Loaded = DBmanager.loadQuests();
        int AIload = 0;
        try {
            AIload = DBmanager.Load_AI();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AIMCME.class.getName()).log(Level.SEVERE, null, ex);
        }
        getLogger().info(String.valueOf("Loaded " + Loaded + " Quests"));
        getLogger().info(String.valueOf("Loaded " + AIload + " AI"));
    }
    @Override
    public void onDisable(){
        
    }
    public static AIMCME getPlugin(){
        return pluginInstance;
    }
}
