MCME-Quests
=======

<i>Allows aquest-line and some AI to work in MCME</i><br/>
<br/>
Author(s): DonoA
<br/>

Implements a short and linear quest line as well as some convertible npcs. The quest line is static and can only be used on the MCME build terra nova server. Npc conversations can be activated if you are near their home and use a `/say` command with their name in it. AI also evolve based on your progression in the quest line, after players have completed a task for an npc that npc might share more valuable information with them (old stories, person events, etc.) The AI are however ambient and are accessible at any point in the quest line and will not loose conversation topics, only gain them. The current AI are:

- Bilbo Baggins, located in Bagend (95%)
- Treebeard, just out side his house (100%)
- Elrond, in the meeting area of Rivendell (75%)
- Galadriel, in the Palace of Lothlórien (60%)

There following AI may also be implemented at a later date:

- Gandalf the Grey, location unset (--%)
- Cirdan the Shipwright, in the Grey Havens after they are built that is (95%)
- Saruman, in his throne room (--%)
- Theoden, in his palace (80%)
- Samwise, unset location (95%)
- Thranduil, in Mirkwood somewhere (80%)
- Denethor, Gondor after finished (90%)
- Gothmog, in Mordor after finished (75%)

The percentages are how likely you are to find them there. If they are not there at the time, the server will reply, `They are not home`. If you do not catch them at home you must wait one minute before the chance will be recalculated. 

This plugin has only one command:

- `/say 'sentence'` | this will start a conversation with a given NPC if the following are met:
    - You are in the right location.
    - Your sentence has all the required words to activate the npc, for ambient AI this is just their name.
    - You have completed any prerequisite quest, if any are required.
    - The npc is at home.

To leave a conversation with an npc say `farewell` or `goodbye`. Npcs may also kick you out of conversations after they give you a quest.

A planned feature is the walking quest, in this quest the use of warps and calls will be disabled. If this is added there will aslo be a quest exit command that will allow you to warp and call again but you will need to start the quest again from the beginning.
