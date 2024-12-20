# HGM Changelog and Notes

## To compile the app

<https://stackoverflow.com/a/49553161>

HGM.rar must contain:

```text
Folder/rar/zip structure:
+ Assets ( default files and needed pictures, these found in the root next to src folder )
  + Pics ( pictures for the program )
    ...
  - default_hentai.xml
  - default_language.csv
  - default_settings.json
+ jre_17_win64 ( or any JRE for that OS )
- HentaiGameManager.exe ( or any application )
- HentaiGameManager.jar 
- THIS IS NOT STORAGE.txt
```

When compiling with Launch4j, the JRE must have "jre_17" as value or anything that has the JRE with the bin/conf/lib folders.

Note: don't include the JDK, it' 3X bigger than the JRE, use JRE

---
---

order of table:
0 - Site
1 - ID
2 - Name
3 - Developer
4 - Played version
5 - Last time play
6 - Rated
7 - Newest version
8 - Last update
9 - People rating
10 - Player progress
11 - Still on pc?
12 - Engine
13 - OS
14 - Language
15 - Personal notes

Or if I'm retarded just -1 to everything and site is handled seperatly and not in Array ( What? )

---
---
---
---

## For the future, notes

- [NK] NyanekoNNK -> Design, testing and emotional support
- [BF] Blackfox -> Frontend, backend and emotional support
- [DC] DiamondCoder (me) -> yes

[1-9] higher, the better

1 - Low priority / Not important idea / Not fully fledged idea, feature

9 - Important / must be feature / perfect idea

### Frontend / UI

- [9] Remake UI with QT or anything that is less ass and not from 2007 ( This can stay Java )
  - [2] If bored we could make a website if some people prefer it and search in it with pictures (HTML, JS, CSS)
  - [6] Have a terminal version with a bit limited functionality ( What is universal? )
    - Keep search, random, add, update, remove function, minimal ASCII art as UI
  - [NK] Will do the testing and design, blame her if something doesn't look right unless I [DC] hate the design
  - Main idea was to have multiple option, depending user need

- [7] Wikipedia / guide / documentation about the whole project was an idea, just the problem that it's shit.
  - [NK] PDF version, smart as both online and offline can work.
    - [DC] It can be shown on Github and be opened with any browser.
    - Or the program itself can open it too?
  - [BF] Have a text / terminal version. (From Linux) have help command in terminal or something
  - ( Two version, one with PDF and one with text )

### Backend / server

- [9] Separeta the program into GUI and Server with GET and POST ( Java? Python? )
  - Only reason I'm [DC] focused on Java or Python to make it compatible with most if not every platform easily
  - [DC] is stupid and wants to do it from scratch, don't let me...

- [9] Remake the storage and config. XML wasn't bad, but for multiple database and for the future remake it ( SQlite? )
  - [BF] is right that JSON is not the best, but better in my opinion than other config, and XML might be good, but if I open manually, my VSCode shits itself
  - < Send data in JSON? >

### Features add / remove / rework

- [5] Auto Update was good while Itch.io and Steam wasn't in plan, now it's half broken?
  - Best idea to just let the user get a notification that there is a new version and they can update manually if they wish.
  - Also maybe ask user where they planning to update from so they can the appropiate notification.
  - There is already a toggle !!! ( [BF] "DON'T REMIND ME TILL THE NEXT VERSION" button )

- [8] Cum Counter... I will be choked if I don't add it so if the new UI and server is up I will add the button
  - [BF] Have a "Are you masturbating?" button, then a cum button so it adds +1 to that game you were playing
  - [NK] Most people update their games before or after they play so add a popup when updating the database.
    - A button with "Did you cum?" button
  - [BF] If we also know what the executeable is for a game, we could check if a game is running and then we ask the user after that process is over if they cummed.
    - If we have the exe, we can also track playtime and how many times the game opened.

---
---
---

## To sort

This is just a summary of our conversations and thoughts.
The initial queation were asked by DiamondCoder [DC].

- Local game folder search? (Steam will stay)

  Do we even need this? If the program itself could download and handle the games, than we could say what file is what.
  Or the version of writing one extra file into the game folder itself which stores information.

  It has a very much fucked up version to check:
  
  `folderName: {type}-{gameId}_{gameName}_{gameVersion} {anythingElseYouWant}`

  - [NK] I don't see why, it's useless trying for something not many people will use.
  - [DC] Steam has an amazing system for this and even I can use some of that information, but DLsite and F95 doesn't have this.

    I just want user friendly and convinient folder handling, but this will be on hold then temporary.
  - [BF] I don't see any negativity in putting one additional file in game folders. The most problem would be auto detect probably.

    I can also imagine that you store the starting executeable for the game and next to it you put the local infos.
    ( ! Check Lutris ! ) ( Maybe even start the apps from the mannager like Steam? )
  - [DC] I have been thinking of moving the database away from XML then I would feel comfortable adding more and game paths into the DB too.

    My biggest problem will remain as most people like to just delete previous game then all of inside content is lost.
    Additionally all references to itt will either throw error or just shit itself as there is nothing and don't know where the game went.
    ( Lutris? )

(Continue from 7.)

- [BF] Lutrsis does something similar on what you are working on. Store games and list them. As they are open source, you could check how they done the storing system.
  For update you can just ask the games exe path or if you are feeling brave you can try Regex so it's version compatible
- [DC] While I like the idea and I'm a masochist for Regex we will see how I can handle it.
  Also if we work on a server and different storage, we could put a password on the database.
- [BF] Use Symetric algorithm for password, easy to add it and that should be enough. <https://stackoverflow.com/questions/10303767/encrypt-and-decrypt-in-java>

---

What we haven't talked about:

- Language.csv rewrite
- Start game from app
- RAM and CPU management/useage high
  - While testing I saw some weirdness, but that might have been already fixed.
- How to better scrap/use API to get informations
  - One main problem is the inconsistency of scraping

---

From my Discord pins:

- <https://github.com/JnCrMx/discord-game-sdk4j>
- Problem: Might have to rewrite how folders and files are checked...
  - Idea: Check if there is setting file, if not use written in version. Stupid as I might forget to update it, but it solves the problem.
  - Logic: If setting file:
    - no: Check inside the class what version the program is and check online. If different:
      - Different: update
      - Not: Continue
    - yes: check version
      - old config, no version: Fuck it and copy what's inside and continue.
      - newest version: Good, continue.
      - old version: update.
- Discord Rich Presence buttons seems to be broken. Fix probably needed
