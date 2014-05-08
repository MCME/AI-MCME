/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.mcme.ai.mcme;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
class Commands implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player) {
           Player player = (Player) sender;
           if(args.length == 0){
               return false;
           }
        } else {
           sender.sendMessage("You must be a player!");
           return false;
        }
        return true;
    }
    
}
