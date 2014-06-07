///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.mcmiddleearth.ai.mcme;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import org.bukkit.ChatColor;
//
///**
// *
// * @author Donovan
// */
//public class npcai {
//    
////    public final TreeMap<String[], List<String>> AIkeys = new TreeMap();
//    private HashMap<List<String>, List<String>> AIkeys = new HashMap<List<String>, List<String>>();
//    
//    public String name;
//    public npcai(String name){
//        this.name = name;
//    }
//    public void addPoint(List<String> keys, List<String> rtns){
//        AIkeys.put(keys, rtns);
//    }
//    public List<String> compute(String input, boolean isFirst){
//        input = input.toLowerCase();
//        List<String> rtn = new ArrayList();
//        rtn.clear();
//        List<String> hold3 = new ArrayList();
//        List<List<String>> hold2 = new ArrayList();
//        List<String> hold4 = new ArrayList();
//        hold4.add("#last#");
//        AIMCME.getPlugin().getLogger().info(name + " : " + this.AIkeys.toString());
//        if(isFirst){
//            hold3.add("#first#");
//            rtn.addAll((ArrayList<String>) AIkeys.get(hold3));
//            return rtn;
//        }else if(AIkeys.keySet().contains(hold4)){
//            rtn.addAll((ArrayList<String>) AIkeys.get(hold4));
//            return rtn;
//        }
//        for(List<String> hold : AIkeys.keySet()){
//            boolean works = true;
//            for(String s : hold){
//               if(!input.contains(s)){
//                   works = false;
//               }
//            }
//            if(works){
//                hold2.add(hold);
//            }
//        }
//        if(hold2.isEmpty()){
//            hold4.clear();
//            hold4.add("#idk#");
//            if(AIkeys.containsKey(hold4)){
//                rtn.addAll((ArrayList<String>) AIkeys.get(hold4));//add #idk#
//            }
//        }else if(hold2.size() == 1){
//            rtn.addAll((ArrayList<String>) AIkeys.get(hold2.get(1)));
//        }else{
//            rtn.add("dallen messed up big");
//            rtn.add("there are dupes of the ais :c");
//        }
//        return rtn;
//    }
//    public void Printmap(){
//        AIMCME.getPlugin().getLogger().info(AIkeys.toString());
//    }
//}