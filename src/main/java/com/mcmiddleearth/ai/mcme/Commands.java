/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

/**
 *
 * @author Donovan
 */
public class Commands implements CommandExecutor, ConversationAbandonedListener {
    
    private final ConversationFactory conversationFactory;
    
    private Player player;
    
    public Commands() {
        conversationFactory = new ConversationFactory(AIMCME.getPlugin())
                .withModality(true)
                .withEscapeSequence("goodbye")
                .withPrefix(new prefixer())
                .withFirstPrompt(new speaking())
                .withTimeout(60)
                .thatExcludesNonPlayersWithMessage("You must be a player to send this command");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player) {
            player = (Player) sender;
            if(args.length == 0){
               return false;
            }
            Polygon area;
            int zbounds[] = {-217, -167}; //2047 -197
            int xbounds[] = {2047, 2080};
            area = new Polygon(xbounds, zbounds, xbounds.length);
            if(area.getBounds2D().contains(player.getLocation().getX(), player.getLocation().getZ()) && Arrays.toString(args).contains("Teebeard")){
                if(sender instanceof Conversable){
                    conversationFactory.buildConversation((Conversable) sender).begin();
                }
            }
           player.sendMessage(ChatColor.GRAY + "There is no reply...");
        } else {
           sender.sendMessage("You must be a player!");
           return false;
        }
        return true;
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent cae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class prefixer implements ConversationPrefix {

        @Override
        public String getPrefix(ConversationContext context) {
            String prefix = ChatColor.AQUA + "";
            prefix += "npc:";
            return prefix;
        }

    }
    private class speaking extends StringPrompt {

        @Override
        public String getPromptText(ConversationContext context) {
            if (context.getSessionData("PlayerTalk") == null) {
                return "Hello " + player.getName();
            }else{
                return context.getSessionData("NpcTalk").toString();
            }
        }


        @Override
        public Prompt acceptInput(ConversationContext context, String input) {
            context.setSessionData("PlayerTalk", input);
            if(input.contains("star")){
                context.setSessionData("NpcTalk", "I think Bilbo has some stars somewhere...");
                return new speaking();
            }
            context.setSessionData("NpcTalk", "I didn't catch that.");
            return new speaking();
        }
    }
}
