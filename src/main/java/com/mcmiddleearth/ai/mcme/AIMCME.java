/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
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
        getLogger().info(String.valueOf("Player saves: " + getpSaves()));
        
    }
    @Override
    public void onDisable(){
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            DBmanager.saveclass(p);
        }
    }
    public static AIMCME getPlugin(){
        return pluginInstance;
    }
    private String getpSaves(){
        File saveloc = new File(AIMCME.getPlugin().getDataFolder() + System.getProperty("file.separator") + "Quests" + System.getProperty("file.separator") + "PlayerDat");
        int good=0;
        int bad=0;
        int rand=0;
        for(File f : saveloc.listFiles()){
            if(!f.getName().contains(".questdat.new")&&f.getName().contains(".questdat")){
                good++;
            }else if(f.getName().contains(".questdat.new")){
                bad++;
            }else{
                rand++;
            }
        }
        TreeMap<String, Integer> rtn = new TreeMap();
        rtn.put("Player saves", good);
        rtn.put("Undeleted new saves", bad);
        rtn.put("random files", rand);
        String rtn2 = rtn.toString();
        rtn2=rtn2.replace("{", "");
        rtn2=rtn2.replace("}", "");
        rtn2=rtn2.replace("=", ": ");
        return rtn2;
    }
}
