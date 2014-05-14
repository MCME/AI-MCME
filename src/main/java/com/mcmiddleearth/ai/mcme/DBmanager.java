/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class DBmanager {
    
    public static final TreeMap<Integer, Quest> Quests = new TreeMap();
    
    public static final TreeMap<Player, Questdat> currQuests = new TreeMap();
    
    private File questDB = new File(AIMCME.getPlugin().getDataFolder() + System.getProperty("file.separator") + "Quests");
    
    public static final TreeMap<String, Integer> QuestKeys = new TreeMap();
    
    public void saveclass(Player player){
        if (!questDB.exists()) {
            questDB.mkdirs();
        }
        boolean successful = true;
        File start = new File(questDB + System.getProperty("file.separator") + "PlayerDat", player.getUniqueId().toString() +  ".questdat.new");
        File finished = new File(questDB + System.getProperty("file.separator") + "PlayerDat", player.getUniqueId().toString() + ".questdat");
        try {
            PrintWriter writer = new PrintWriter(start, "UTF-8");
            String completed = "";
            for(Integer id : currQuests.get(player).getcompleted()){
                completed += id.toString() + " , ";
            }
            completed = completed.substring(0, completed.length()-3);
            writer.println(completed);
            writer.println(currQuests.get(player).getCurrent());
        } catch (IOException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            successful = false;
        } finally {
            if (successful) {
                if (finished.exists()) {
                    finished.delete();
                }
                start.renameTo(finished);
            }
        }
    }
    public void loadclass(Player player){
        if (!questDB.exists()) {
            boolean successful = true;
            File save = new File(questDB + System.getProperty("file.separator") + "PlayerDat", player.getUniqueId().toString() +  ".questdat.new");
            try {
                Scanner s;
                s = new Scanner(save);
                String line = s.nextLine();
                List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
                List<Integer> ids = new ArrayList<Integer>();
                for(String value : items){
                    ids.add(Integer.valueOf(value));
                }
                String l = s.nextLine();
                int currid = Integer.valueOf(l);
                Questdat currquest = new Questdat(player, ids, currid);
                currQuests.put(player, currquest);
            } catch (IOException ex) {
                Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
                successful = false;
            }
        }
    }
    public int loadQuests(){
        int loaded = 0;
        File QuestDB = new File(questDB + System.getProperty("file.separator") + "QuestDB");
        File questSave = new File(QuestDB, + loaded +  ".quest");
        while(questSave.exists()){
            try {
                Scanner s;
                s = new Scanner(questSave);
                int Bounds[][] = new int[2][2]; // (x,z)
                Bounds[0][0] = Integer.parseInt(s.nextLine());
                Bounds[1][0] = Integer.parseInt(s.nextLine());
                Bounds[0][1] = Integer.parseInt(s.nextLine());
                Bounds[1][1] = Integer.parseInt(s.nextLine());
                String npc = s.nextLine();
                List<String> Keys = new ArrayList<String>();
                String line;
                while (s.hasNext()){
                    line = s.nextLine();
                    Keys.add(line);
                    QuestKeys.put(line, loaded);
                }
                Quest q = new Quest(loaded,Keys,npc,Bounds);
                Quests.put(loaded, q);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            loaded++;
            questSave = new File(QuestDB, + loaded +  ".quest");
        }
        return loaded;
    }
}
