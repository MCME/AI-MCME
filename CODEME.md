This is the documentation outlining the classes and major functions used in MCME-quests. The main classes  used are `Quest` and `Questdat`:
- `Quest`
	- this is the class used to define the parameters of a quest, this class is saved in `.quest` files in the QuestDB folder.
	- `getAI( String, boolean, player )` | used to get the AI for quests, operates in a massive switch loop. 
	- `isWalking` | returns a boolean for if the quest isa walking quest
	- `getId` | returns int id
	- `MatchKeys(String list)` | returns true if input has all the required strings 
	- `isUnlocked(player)` | returns if the player has unlocked the quest
	- `hasCurr(player)` | returns if the player is on the right quest
	- `hasDone(player)` | checks if the player has already done the quest
	- `getNPC` | returns npc name
	- `inBounds(player)` | checks if the player is within the quest bound

- `Questdat`
  - `getcompleted` | returns list of completed ids
  - `getCurrent` | returns current quest id
  - `stopQuest` | stops the current quest and saves the class
  
DBmanager keeps track of classes and save files as well as several hashmaps of Quests
  - `saveclass(player)` | saves the players questdat
  - `loadquest(player)` | loads the players questdat
  - `loadQuests` | loads all the quests in QuestDB and returns the number of quests loaded
  - `firstLoad` | makes sure the DB files are setup and ready to be loaded

There are also four filetypes that this plugin uses. These are used in the databases and stored in the `Quests.zip` and `AIs.zip`
- `playerdat` and `playerdat.new` the name of the file is the players UUID
    -Line 1: quests completed by this player
    -Line 2: the current quest the player is on
- `quest` this is named the quest id, starts at 0
    -Line 1: Z bound 1
    -Line 2: Z bound 2
    -Line 3: X bound 1
    -Line 4: X bound 2
    -Line 5: NPC name
    -Line 6: ai-id
    -Line 7: required quests 0=none (csv form)
    -Line 8: required current quest -1=none
    -the rest of the lines are required key words, one line per word
- `ai` the name of these files are the ai-id defined in the quest file
    - keys : response
