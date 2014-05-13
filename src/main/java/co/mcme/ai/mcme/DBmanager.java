/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.mcme.ai.mcme;

import java.io.File;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class DBmanager {
    public void saveclass(Player player){
        File questDB = new File(AIMCME.getPlugin().getDataFolder() + System.getProperty("file.separator") + "quests");
        if (!questDB.exists()) {
            questDB.mkdirs();
        }
    }
    public void loadclass(Player player){
        
    }
    public Quest loadQuest(String name){
        
    }
}
