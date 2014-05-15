/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.util.ArrayList;
import java.util.List;
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
    
    private List<String> Keys = new ArrayList<String>();
    private List<Integer> requiredQuest = new ArrayList<Integer>();
    
    private String npc;
    private String ai;
    
    public Quest(int id, List<String> Keys, String npc, int Boundsx[], int Boundsz[], String ai, List<Integer> opened, int curr){
        this.Boundsx = Boundsx;
        this.Boundsz = Boundsz;
        this.Keys = Keys;
        this.npc = npc;
        this.ai = ai;
        this.requiredQuest = opened;
        this.needCurr = curr;
    }
    public String getAI(String input, boolean isFirst){
        String Returner = "";
        input = input.toLowerCase();
        switch(ai){
            case "TB1":
                if(input.contains("star") && input.contains("dunedain")){
                    return "I think there is a star in Bag End...Somewhere";
                }else{
                    return "I don't understand...";
                }
            case "TB2":
                
        }
        return Returner;
    }
    public boolean MatchKeys(List<String> keys){
        boolean returner = true;
        for(String s : this.Keys){
            if(!keys.contains(s)){
                returner = false;
            }
        }
        return returner;
    }
    public boolean isUnlocked(Player player){
        if(DBmanager.currQuests.get(player).getcompleted().containsAll(requiredQuest) || (DBmanager.currQuests.get(player).getcompleted().contains("-1") && DBmanager.currQuests.get(player).getcompleted().contains("-2"))){
            return true;
        }else{
            return false;
        }
    }
    public boolean hasCurr(Player player){
        if(DBmanager.currQuests.get(player).getCurrent() == this.needCurr || this.needCurr == -1){
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
