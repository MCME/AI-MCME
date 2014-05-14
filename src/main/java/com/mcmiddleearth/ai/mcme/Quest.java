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
    
    private List<String> Keys = new ArrayList<String>();
    
    private String npc;
    
    public Quest(int id, List<String> Keys, String npc, int Boundsx[], int Boundsz[]){
        this.Boundsx = Boundsx;
        this.Boundsz = Boundsz;
        this.Keys = Keys;
        this.npc = npc;
    }
    public String getAI(String input, boolean isFirst){
        String Returner = "";
        input = input.toLowerCase();
        switch(npc){
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
