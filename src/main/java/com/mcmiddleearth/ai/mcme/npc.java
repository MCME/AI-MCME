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
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class npc {
    public HashMap<List<String>, List<String>> ai = new HashMap<List<String>, List<String>>();
    public String tname;
    public npc(String name, HashMap<List<String>, List<String>> ais){
        tname=name;
        ai=ais;
    }
    public String getOps(){
        List<String> hold = new ArrayList();
        String rtn = ChatColor.GREEN + "";
        hold.add("#ops#"); 
        if(ai.containsKey(hold)){
            rtn += ai.get(hold);
            rtn = rtn.substring(1, rtn.length()-1);
            return rtn;
        }
        return "";
    }
    public List<String> compute(String input, boolean isFirst){
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
    @Override
    public String toString(){
        return tname;
    }
}
