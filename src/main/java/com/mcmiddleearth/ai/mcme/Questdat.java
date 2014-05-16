/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class Questdat {
    private Player player;
    private List<Integer> completed;
    private int current;
    public Questdat(Player player, List<Integer> completed, int current){
        this.player = player;
        this.completed = completed;
        this.current = current;
    }
    public Questdat(Player player){
        this.player = player;
        List<Integer> comp = new ArrayList<Integer>();
        comp.add(-1);
        this.completed = comp;
        this.current = -1;
    }
    public List<Integer> getcompleted(){
        return completed;
    }
    public int getCurrent(){
        return current;
    }
    public void stopQuest(){
        this.current = -1;
        DBmanager.saveclass(player);
    }
}
