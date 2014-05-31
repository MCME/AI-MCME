/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mcmiddleearth.ai.mcme;

import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    private Quest currQuest;
    
    private Player player;
    
    public Commands() {
        conversationFactory = new ConversationFactory(AIMCME.getPlugin())
                .withLocalEcho(false)
                .withModality(true)
                .withFirstPrompt(new speaking())
                .withTimeout(60)
                .thatExcludesNonPlayersWithMessage("You must be a player to send this command");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("say")){
            if (sender instanceof Player) {
                player = (Player) sender;
                if(args.length == 0){
                   return false;
                }
                ArrayList<String> argz = new ArrayList(Arrays.asList(args));
                ArrayList<Integer> ids2 = new ArrayList<Integer>();
                for(Integer i : DBmanager.Quests.keySet()){
                    if (DBmanager.Quests.get(i).inBounds(player) && DBmanager.Quests.get(i).MatchKeys(argz) && DBmanager.Quests.get(i).isUnlocked(player) && DBmanager.Quests.get(i).canTwice(player)){//&& DBmanager.Quests.get(i).MatchKeys(argz) && DBmanager.Quests.get(i).isUnlocked(player)
                        ids2.add(i);
                        player.sendMessage(String.valueOf(i));
                    }
                }
                if(ids2.size()==1){
                    currQuest = DBmanager.Quests.get(ids2.get(0));
                    conversationFactory.buildConversation((Conversable) sender).begin();
                    return true;
                }else if(ids2.isEmpty()){
                    player.sendMessage(ChatColor.GRAY + "There is no reply...");
                    return true;
                }else{
                    player.sendMessage("Thats and error, please report it =)");
                    player.sendMessage("http://www.mcmiddleearth.com/conversations/add?to=dallen1393");
                    return false;
                }
            } else {
               sender.sendMessage("You must be a player!");
               return false;
            }
        } else if(cmd.getName().equalsIgnoreCase("endquest")){
            Questdat hold = DBmanager.currQuests.get(player.getName());
            hold.stopQuest();
        }
        return false;
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent cae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class prefixer implements ConversationPrefix {

        @Override
        public String getPrefix(ConversationContext context) {
            String prefix = ChatColor.AQUA + "";
            prefix = prefix + currQuest.getNPC() + ": ";
            return prefix;
        }

    }
    private class speaking extends StringPrompt {

        @Override
        public String getPromptText(ConversationContext context) {
            if (context.getSessionData("PlayerTalk") == null) {
                context.setSessionData("NpcTalk", currQuest.getAI("", true, player));
            }else{
                return context.getSessionData("NpcTalk").toString();
            }
            return context.getSessionData("NpcTalk").toString();
        }
        @Override
        public Prompt acceptInput(ConversationContext context, String input) {
            context.setSessionData("PlayerTalk", input);
            context.setSessionData("NpcTalk", currQuest.getAI(input, false, player));
            if(context.getSessionData("NpcTalk").equals("Farewell!")){
                return Prompt.END_OF_CONVERSATION;
            }
            return new speaking();
        }
    }
}
