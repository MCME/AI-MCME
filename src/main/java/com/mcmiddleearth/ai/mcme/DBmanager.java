/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class DBmanager {
    
    public static final TreeMap<Integer, Quest> Quests = new TreeMap();
    
    public static final TreeMap<String, Questdat> currQuests = new TreeMap();
    
    private static File questDB = new File(AIMCME.getPlugin().getDataFolder() + System.getProperty("file.separator") + "Quests");
    
    public static final TreeMap<String, Integer> QuestKeys = new TreeMap();
    
    public static final TreeMap<String, HashMap<List<String>, List<String>>> AIs = new TreeMap();
    
    public static void saveclass(Player player){
        firstLoad();
        boolean successful = true;
        File start = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + player.getUniqueId().toString() +  ".questdat.new");
        File finished = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + player.getUniqueId().toString() + ".questdat");
        if(finished.exists()&&start.exists()){
            finished.delete();
            start.renameTo(finished);
        }
        try {
            FileWriter fr = new FileWriter(start.toString());
            try (PrintWriter writer = new PrintWriter(fr)) {
                String completed = "";
                for(Integer id : currQuests.get(player.getName()).getcompleted()){
                    completed += id.toString() + " , ";
                }
                completed = completed.substring(0, completed.length()-3);
                writer.println(completed);
                writer.println(currQuests.get(player.getName()).getCurrent());
            }
            System.gc();
        } catch (IOException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            successful = false;
        }
        if (successful) {
            if(finished.exists()){
                finished.delete();
            }
            start.renameTo(finished);
        }
    }
    public static void loadclass(Player player){
        firstLoad();
        File start = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + player.getUniqueId().toString() +  ".questdat.new");
        File finished = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + player.getUniqueId().toString() + ".questdat");
        if(finished.exists()&&start.exists()){
            finished.delete();
            start.renameTo(finished);
        }
        File save = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + player.getUniqueId().toString() +  ".questdat");
        if (save.exists()) {
            try {
                Scanner s;
                s = new Scanner(save);
                String line = s.nextLine();
                List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
                List<Integer> ids = new ArrayList<Integer>();
                for(String value : items){
                    ids.add(Integer.parseInt(value));
                }
                String l = s.nextLine();
                int currid = Integer.parseInt(l);
                Questdat currquest = new Questdat(player, ids, currid);
                currQuests.put(player.getName(), currquest);
            } catch (IOException ex) {
                Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static int loadQuests(){
        firstLoad();
        int loaded = 0;
        File QuestDB = new File(questDB + System.getProperty("file.separator") + "QuestDB");
        File questSave = new File(QuestDB, + loaded +  ".quest");
        while(questSave.exists()){
            try {
                Scanner s;
                s = new Scanner(questSave);
                int Boundsx[] = new int[2]; //
                int Boundsz[] = new int[2]; //
                Boundsx[0] = Integer.parseInt(s.nextLine());
                Boundsx[1] = Integer.parseInt(s.nextLine());
                Boundsz[0] = Integer.parseInt(s.nextLine());
                Boundsz[1] = Integer.parseInt(s.nextLine());
                sort(Boundsx);
                sort(Boundsz);
                String npc = s.nextLine();
                String ai = s.nextLine();
                //AIMCME.getPlugin().getLogger().info(String.valueOf(loaded));
                String SCanTwice = s.nextLine();
                boolean canTwice = Boolean.valueOf(SCanTwice);
                String opened = s.nextLine();
                List<String> values2 = Arrays.asList(opened.split(","));
                List<Integer> values = new ArrayList<Integer>();
                for(String v : values2){
                    values.add(Integer.valueOf(v));
                }
                String hold = s.nextLine();
                int curr = Integer.valueOf(hold);
                List<String> Keys = new ArrayList<String>();
                String line;
                while (s.hasNext()){
                    line = s.nextLine();
                    Keys.add(line.toLowerCase());
                    QuestKeys.put(line, loaded);
                }
                Quest q = new Quest(loaded,Keys,npc,Boundsx,Boundsz,ai,values,curr,canTwice);
                Quests.put(loaded, q);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            loaded++;
            questSave = new File(QuestDB, + loaded +  ".quest");
        }
        return loaded;
    }
    public static void firstLoad(){
        if(!questDB.exists()) {
            questDB.mkdirs();
        }
        File QuestDB = new File(questDB + System.getProperty("file.separator") + "QuestDB");
        if(!QuestDB.exists()){
            QuestDB.mkdir();
        }
        File save = new File(questDB + System.getProperty("file.separator") + "PlayerDat");
        if(!save.exists()){
            save.mkdir();
        }
        File AIdb = new File(questDB + System.getProperty("file.separator") + "AIs");
        if(!AIdb.exists()){
            AIdb.mkdir();
        }
    }
    public static boolean hasSave(Player player){
        String uuid = player.getUniqueId().toString();
        File loc = new File(questDB + System.getProperty("file.separator") + "PlayerDat" + System.getProperty("file.separator") + uuid + ".questdat");
        return loc.exists();
    }
    public static int Load_AI() throws FileNotFoundException{
        int Loaded=0;
        File aiDB = new File(questDB + System.getProperty("file.separator") + "AIs");
        String hold;
        String speach;
        String key;
        for(File target : aiDB.listFiles()){
            HashMap<List<String>, List<String>> AIkeyhold = new HashMap<List<String>, List<String>>();
            Scanner s;
            s = new Scanner(target);
            String tname = target.getName().replace(".ai", "");
            while(s.hasNext()){
                hold = s.nextLine();
                List<String> values = Arrays.asList(hold.split("\\s*:\\s*"));
                key = values.get(0);
                speach = values.get(1);
                List<String> keys = Arrays.asList(key.split(",\\s*"));
                List<String> rtns = Arrays.asList(speach.split(",\\s*"));
                List<String> Lkeys = new ArrayList();
                for(String h : keys){
                    Lkeys.add(h.toLowerCase());
                }
                AIkeyhold.put(Lkeys, rtns);
            }
            AIs.put(tname, AIkeyhold);
            Loaded++;
        }
        return Loaded;
    }
}
