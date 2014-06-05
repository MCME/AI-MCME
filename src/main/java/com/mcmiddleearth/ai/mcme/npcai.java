/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.bukkit.ChatColor;

/**
 *
 * @author Donovan
 */
public class npcai {
    
    public static final TreeMap<List<String>, List<String>> AIkeys = new TreeMap();

    
    private String name;
    public npcai(String name){
        this.name = name;
    }
    public List<String> compute(String input, boolean isFirst){
        input = input.toLowerCase();
        //find all the posible resonses
        //then test each possible responce
        //return resonce
        List<String> rtn = new ArrayList();
        rtn.clear();
        List<List<String>> hold2 = new ArrayList();
        for(List<String> hold : AIkeys.keySet()){
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
            rtn.add("idk");
        }else if(hold2.size() == 1){
            rtn.addAll(AIkeys.get(hold2.get(1)));
        }else{
            rtn.add("dallen messed up big");
            rtn.add("there are dupes of the ais =(");
        }
        return rtn;
    }
}


//        switch(name){
//            case "TB1":
//                if(input.contains("star") && input.contains("dunedain")){
//                    return "I think there is a star in Bag End...Somewhere";
//                }else if(isFirst){
//                    return prefix + "Goodmorning";
//                }else{
//                    return getBaseAI(input, "TB", prefix);
//                }
//            case "BB1":
//                if(isFirst){
//                    DBmanager.currQuests.get(player.getName()).getcompleted().add(id);
//                    return prefix + "Thank goodness your here. I need you to go order a cake for me from Bagshot Row";                   
//                }else{
//                    return prefix + "Farewell!";
//                }
//            case "BB2":
//                if(isFirst){
//                    DBmanager.currQuests.get(player.getName()).getcompleted().add(id);
//                    return prefix + "Now that we have a cake the party can go on";
//                }else if(input.contains("adventure")&&input.contains("my")){
//                    DBmanager.currQuests.get(player.getName()).setCurrent(id);
//                    return prefix + "Go to Dallen's Tavern in Bree and try to find some gossip. The tavern is near the town square";
//                }else{
//                    return getBaseAI(input, "Bb", prefix);
//                }
//            case "baker1":
//                if(isFirst){
//                    DBmanager.currQuests.get(player.getName()).getcompleted().add(id);
//                    return prefix + "Oh, I'll send it over right away";
//                }else{
//                    return prefix + "Can't talk now, Farewell!";
//                }
//            case "IK1":
//                if(isFirst){
//                    return prefix + "Welcome! Is there anything I can get for you?";
//                }else if(input.contains("adventure")){
//                    return prefix + "If your looking for a quest you should talk to Ragnar.";
//                }else if(input.contains("farewell")){
//                    return prefix + "Farewell!";
//                }else if(input.contains("food")){
//                    return prefix + "We are fresh out of food at the moment";
//                }else if(input.contains("room")){
//                    return prefix + "We are totaly booked at the moment";
//                }else{
//                    return prefix + "I'm afarid I don't understand";
//                }
//            case "R1":
//                if(isFirst){
//                    return "So your looking for a quest?";
//                }else if(input.contains("no")){
//                    return "Oh I must have miss-understood, Farewell";
//                }else{
//                    player.sendMessage("I just so happen to have a job");
//                    player.sendMessage("I need you to search through an old tomb to find a Jurnal");
//                    player.sendMessage("I know the tomb is in the burrow downs. (/warp BorrowDowns)");
//                    return "Goodluck, Farewell!";
//                }
//            case "R2":
//                return "R2";
//            case "N1":
//                return "N1";
//            case " ":
//            case "E1":
//                return "E1";
//            case "E2":
//                return "E2";
//            case "E3":
//                return "E3";
//        }