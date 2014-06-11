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
    
    private String npcname;
    private String aiid;
    
    private boolean walking;
    private boolean canTwice;
    
    private npc npc;
    
    public Quest(int id, List<String> Keys, String npc, int Boundsx[], int Boundsz[], String ai, List<Integer> opened, int curr, boolean canTwice){
        this.id = id;
        this.Boundsx = Boundsx;
        this.Boundsz = Boundsz;
        this.Keys = Keys;
        this.npcname = npc;
        this.aiid = ai;
        this.requiredQuest = opened;
        this.needCurr = curr;
        this.walking = false;
        this.canTwice = canTwice;
    }
    private void setAI(){
        if(npc == null){
            npc = DBmanager.AIs.get(aiid);
        }
    }
    public String getAI(String input, boolean isFirst, Player player) throws InterruptedException{
        if(isCooling()){
            if(!isFirst){
                player.sendMessage(String.valueOf(ChatColor.YELLOW + player.getName() + ": " + ChatColor.GRAY + input));
            }
            setAI();
    //        AIMCME.getPlugin().getLogger().info(ai.toString());
            String prefix = ChatColor.AQUA + "";
            prefix = prefix + npcname + ": " + ChatColor.GRAY;
            List<String> rtn = npc.compute(input, isFirst);
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
        }else{
            return ChatColor.AQUA + "Doorman: I'm afraid " + npcname + " isn't here";
        }
    }
    public String getOps(){
        return npc.getOps();
    }
    public boolean isCooling(){
        return npc.isCooldown();
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
        return npcname;
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
