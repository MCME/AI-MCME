/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.util.ArrayList;
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
    private String ai;
    
    private boolean walking;
    
    public Quest(int id, List<String> Keys, String npc, int Boundsx[], int Boundsz[], String ai, List<Integer> opened, int curr){
        this.id = id;
        this.Boundsx = Boundsx;
        this.Boundsz = Boundsz;
        this.Keys = Keys;
        this.npc = npc;
        this.ai = ai;
        this.requiredQuest = opened;
        this.needCurr = curr;
        this.walking = false;
    }
    public String getAI(String input, boolean isFirst, Player player){
        String prefix = ChatColor.AQUA + "";
        prefix = prefix + npc + ": " + ChatColor.GRAY;
        String Returner = "";
        input = input.toLowerCase();
        switch(ai){
            case "TB1":
                if(input.contains("star") && input.contains("dunedain")){
                    return "I think there is a star in Bag End...Somewhere";
                }else if(isFirst){
                    return prefix + "Goodmorning";
                }else{
                    Returner = getBaseAI(input, "TB", player);
                    if(Returner.equals("-1")){
                        return prefix + "I don't understand...";
                    }
                    return prefix + Returner;
                }
            case "BB1":
                if(isFirst){
                    DBmanager.currQuests.get(player.getName()).getcompleted().add(id);
                    return "Thank goodness your here. I need you to go order a cake for me from Bagshot Row";                   
                }else{
                    return "Farewell!";
                }
            case "BB2":
                if(isFirst){
                    return "Now that we have a cake the party can go on";
                }
            case "E1":
                
            case "E2":
                
            case "E3":
                
        }
        return Returner;
    }
    private String getBaseAI(String input, String baseAI, Player player){
        switch(baseAI){
            case "TB":
                
            case "Bb":
            
            case "E":
                
            case "G":
                
        }
        return "";
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
