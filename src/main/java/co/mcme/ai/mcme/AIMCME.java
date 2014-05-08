/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.mcme.ai.mcme;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan
 */
public class AIMCME extends JavaPlugin {
    @Override
    public void onEnable(){
        getCommand("say").setExecutor(new Commands());
    }
}
