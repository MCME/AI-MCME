/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;


/**
 *
 * @author Donovan
 */
public class Quest{
    private int Boundsx[] = new int[2];
    private int Boundsz[] = new int[2];
    private int needCurr;
    private int id;
    
    private List<String> Keys = new ArrayList<String>();
    private List<Integer> requiredQuest = new ArrayList<Integer>();
    
    private String npc;
    private String aiid;
    
    private boolean walking;
    private boolean canTwice;
    
    private HashMap<List<String>, List<String>> ai = new HashMap<List<String>, List<String>>();
    
    public Quest(int id, List<String> Keys, String npc, int Boundsx[], int Boundsz[], String ai, List<Integer> opened, int curr, boolean canTwice){
        this.id = id;
        this.Boundsx = Boundsx;
        this.Boundsz = Boundsz;
        this.Keys = Keys;
        this.npc = npc;
        this.aiid = ai;
        this.requiredQuest = opened;
        this.needCurr = curr;
        this.walking = false;
        this.canTwice = canTwice;
    }
    private void setAI(){
        if(this.ai.isEmpty()){
            this.ai.putAll(DBmanager.AIs.get(aiid));
        }
    }
    public String getAI(String input, boolean isFirst, Player player) throws InterruptedException{
        if(!isFirst){
            player.sendMessage(String.valueOf(ChatColor.YELLOW + player.getName() + ": " + ChatColor.GRAY + input));
        }
        setAI();
//        AIMCME.getPlugin().getLogger().info(ai.toString());
        String prefix = ChatColor.AQUA + "";
        prefix = prefix + npc + ": " + ChatColor.GRAY;
        List<String> rtn = compute(input, isFirst);
        for(String s : rtn){
            if(s.equalsIgnoreCase("#done#")){
                DBmanager.currQuests.get(player.getName()).getcompleted().add(id);
            }else if(s.equalsIgnoreCase("#curr#")){
                DBmanager.currQuests.get(player.getName()).setCurrent(id);
            }else if(rtn.get(rtn.size()-1).equalsIgnoreCase(s)){
               return prefix + s;
            }else{
                player.sendMessage(String.valueOf(prefix + s));
                Thread.sleep(1000);
            }
        }
        return "";
    }
    public void getOps(Player player){
        player.sendMessage(String.valueOf(ai.keySet()));
    }
    private String getBaseAI(String input, String baseAI, String prefix){
        switch(baseAI){
            case "TB":
                
            case "Bb":
                if(input.contains("love")){
                    return prefix + "shreck";
                }else if(input.contains("farewell")){
                    return prefix + "Farewell!";
                }else{
                    return prefix + "idk";
                }
            case "E":
                
            case "G":
                
        }
        return "";
    }
    private List<String> compute(String input, boolean isFirst){
        input = input.toLowerCase();
        List<String> rtn = new ArrayList();
        rtn.clear();
        List<String> hold3 = new ArrayList();
        List<List<String>> hold2 = new ArrayList();
        List<String> hold4 = new ArrayList();
        hold4.add("#last#");
//        AIMCME.getPlugin().getLogger().info(name + " : " + this.AIkeys.toString());
//        AIMCME.getPlugin().getLogger().info(ai.toString());
        if(isFirst){
            hold3.add("#first#");
//            AIMCME.getPlugin().getLogger().info(ai.get(hold3).toString());
            rtn.addAll(ai.get(hold3));
            return rtn;
        }else if(ai.keySet().contains(hold4)){
            rtn.addAll(ai.get(hold4));
            return rtn;
        }
        for(List<String> hold : ai.keySet()){
            boolean works = true;
            for(String s : hold){
               if(!input.contains(s)){
                   works = false;
               }
            }
            if(works){
                hold2.add(hold);
            }
        }
        if(hold2.isEmpty()){
            hold4.clear();
            hold4.add("#idk#");
            if(ai.containsKey(hold4)){
                rtn.addAll(ai.get(hold4));
            }else{
                hold4.clear();
                hold4.add("I don't understand");
                rtn.addAll(hold4);
            }
        }else if(hold2.size() == 1){
            rtn.addAll(ai.get(hold2.get(0)));
        }else{
            rtn.add("dallen messed up big");
            rtn.add("there are dupes of the ais :c");
        }
        return rtn;
    }
    public boolean isWalking(Player player){
        return this.walking;
    }
    public Integer getId(){
        return this.id;
    }
    public boolean MatchKeys(List<String> input){
        return input.containsAll(Keys);
    }
    public boolean isUnlocked(Player player){
        Questdat q = DBmanager.currQuests.get(player.getName());
        return q.getcompleted().containsAll(requiredQuest);
    }
    public boolean canTwice(Player player){
        Questdat qd = DBmanager.currQuests.get(player.getName());
        if(qd.getcompleted().contains(id) && !canTwice){
            return false;
        }
        return true;
    }
    public boolean hasCurr(Player player){
        if(DBmanager.currQuests.get(player.getName()).getCurrent() == this.needCurr || this.needCurr == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean hasDone(Player player){
        if(DBmanager.currQuests.get(player.getName()).getcompleted().contains(id)){
            return true;
        }else{
            return false;
        }
    }
    public String getNPC(){
        return npc;
    }
    public boolean inBounds(Player player){
        Location ploc = player.getLocation();
        boolean inX = ((ploc.getX() > Boundsx[0]) && (ploc.getX() < Boundsx[1]));
        boolean inZ = ((ploc.getZ() > Boundsz[0]) && (ploc.getZ() < Boundsz[1]));
        if(inX && inZ){
            return true;
        }
        return false;
    }
}
