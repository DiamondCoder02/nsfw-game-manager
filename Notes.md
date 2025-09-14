# HGM Notes

## Old Notes

- [NK] NyanekoNNK -> Design, testing and emotional support
- [BF] Blackfox -> Frontend, backend and emotional support
- [DC] DiamondCoder (me) -> yes

---

- Remake UI with QT or anything that is less ass and not from 2007
  - [NK] Will do the testing and design, blame her if something doesn't look right unless I [DC] hate the design

- Wikipedia / guide / documentation about the whole project was an idea, just the problem that it's shit.
  - [NK] PDF version, smart as both online and offline can work.
    - [DC] It can be shown on Github and be opened with any browser.
  - [BF] Have a text / terminal version. (From Linux) have help command in terminal or something
    - ( Two version, one with PDF and one with text )

- Separeta the program into GUI and Server with GET and POST
- Remake the storage and config. XML wasn't bad, but for multiple database and for the future remake it
  - [BF] JSON is not the best, XML might be good, but if I open manually, my VSCode shits itself
    - < Send data in JSON? >

- Auto updater full rewrite / seperate module from backend
  - In settings 3 mode:
    - Auto update
    - No update
    - Ask me to decide( 2 option: update once, or not today or something)
  - Big question: where/how hosted. Or keep it with Github releases?

- Cum Counter... I will be choked if I don't add it.
  - TODO
  - [BF] Have a "Are you masturbating?" button, then a cum button so it adds +1 to that game you were playing
  - [NK] Most people update their games before or after they play so add a popup when updating the database.
    - A button with "Did you cum?" button
  - [BF] If we know the executeable of game, we could check if a game is running and then we ask the user after that process is over if they cummed.
    - If we have the exe, we can also track playtime and how many times the game opened.

### To sort

This is just a summary of our conversations and thoughts.
The initial queation were asked by DiamondCoder [DC].

- Local game folder search? (Steam will stay)

  Do we even need this? If the program itself could download and handle the games, than we could say what file is what.
  Or the version of writing one extra file into the game folder itself which stores information.

  It has a very much fucked up version to check:
  
  `folderName: {type}-{gameId}_{gameName}_{gameVersion} {anythingElseYouWant}`

  - [NK] I don't see why, it's useless trying for something not many people will use.
  - [DC] Steam has an amazing system for this and even I can use some of that information, but DLsite and F95 doesn't have this.
You okay
    I just want user friendly and convinient folder handling, but this will be on hold then temporary.
  - [BF] I don't see any negativity in putting one additional file in game folders. The most problem would be auto detect probably.

    I can also imagine that you store the starting executeable for the game and next to it you put the local infos.
    ( ! Check Lutris ! ) ( Maybe even start the apps from the mannager like Steam? )
  - [DC] I have been thinking of moving the database away from XML then I would feel comfortable adding more and game paths into the DB too.

    My biggest problem will remain as most people like to just delete previous game then all of inside content is lost.
    Additionally all references to itt will either throw error or just shit itself as there is nothing and don't know where the game went.
    ( Lutris? )

- [BF] Lutrsis does something similar on what you are working on. Store games and list them. As they are open source, you could check how they done the storing system.
  For update you can just ask the games exe path or if you are feeling brave you can try Regex so it's version compatible
- [DC] While I like the idea and I'm a masochist for Regex we will see how I can handle it.
  Also if we work on a server and different storage, we could put a password on the database.
- [BF] Use Symetric algorithm for password, easy to add it and that should be enough.
    <https://stackoverflow.com/questions/10303767/encrypt-and-decrypt-in-java>

## Project: Lightweight "installer" ( This is stupid )

User gets an installer. -> Installer ask what the user likes and wants?
User selects the prefered look and sites -> Installer downloads "packages".

Example:
  user ask for a cmd style and only uses f95 and dlsite, but not Steam
  installer gets the cmd style module and also gets the f95 and dlsite API/webscrapper

Each module can have version and updates

---

Java       JRE and bundle, meh
Python     same as java problem
Rust       Fucker can't make their shit dynamic
C#
C++        "C++? I know you are a masochist, but if you use C++, I'll give u a pointer to the closest corner to think about your choices." - Blackfox
JS/HTML    I would rather jump of a bridge

UI Framworks:
  .NET MAUI
  Avalonia
  QT

dotnet run
dotnet new console -n <name> -lang "C#" -f net8.0 --use-program-main

---

## Project HGM, the unlogical logic

backend with Get/Post (Possible: Java, c#, Rust, Python)
  Maven/gradle/spring

Any frontend possible (Java, C#, plain JS/TS/HTML/Electron)
  Terminal              ascii
  minimalist UI         Excel table similar to current/old
  Webpage               Probably with most custimization
  Fancy UI              Yeah, no idea for style

  Just for Blackfox and Linux community. Special terminal only version and full version compiled will be available.
  No I don't care I have to compile twice. (Also technically it would cut down quite a bit in size)
    <https://stackoverflow.com/questions/9725675/is-there-a-standard-format-for-command-line-shell-help-text>

Mini sql,mongodb database
  Smallest space
  Doesn't have to serve, only slave to the backend

"Half modular"
  User can choose UI
  Package everything and fuck modular download
  3 modules: backend, frontend, updater
    updater send message to backend to refresh updater
    updater kills itself
    backend checks, renames old updater to updater_old
    backend renames updater_new to updater
    backend starts it
      Backend and installer works together and dependent on other for health checks and error handling
  In backend, don't limit anything in size, don't create 50 size array(, especially for Steam)

Game data / API / Scrapping
  While I don't want scraping, some bitches don't or can't provide API.
  Inconsistency is a bitch
    Steam for some reason I need 2 API? Why...

Database IDs and accepteable variables
0   Int   Key / internal ID
      Because there is mostly and possible that from 2 sites you get the same id,
      just use a random incrementing internal id. You will always need to search through the database,
      bet less conflicts.
1   Int   SiteNum
      Just use numbers and assign each to a string, less data to store
    Possible values:
      0 - Manual ( Not on any supported site)
      1 - F95
      2 - Steam
      3 - Dlsite
2   Int   SiteIDNew project:
      Simply the ID on the site so easier to ask info from site
    Set it to either 0 or -1 if unknown
      If manual game and user wants to give ID 0 or negative, just no.
3   Str   Online_Name
      Largest problem is the coding, so make sure to support at least UTF-8
      Probably test with UTF-16 or more
4   Str   Online_Developer
5   Str   Online_Publisher
      Meh, seperate them so at least you can search for it.
        (Steam has array for devs and publishers... Can you store array in DB? )
6   Str   Online_Version
      Publicly available version to play/buy
7   Date  Online_Last_Version_Date
      Not sure if DateTime or Unix timestamp or maybe just fuck it and simple string would be best.
      (To be tested)
8   Int   User_Played_Progress
      How far the user have played said game.
    Possible valuse:
      0 - 100% Done
      1 - Finished  ( Reason these two handled seperatly as not every game can be finished and not everyone has time to grind)
      2 - Dropped   ( To never touch the game as it's bad or not for their taste )
      3 - In progress ( Currently playing, duh )
      4 - Planned   ( To play )
9   Int   User_rating
      Should be just a general rating between 1 and 10?
        (Maybe be it string? but this should be just a number)
10  Str   User_Notes
      Basicly notes on game Limit needed?
      Support .md format?
      A length of 1024 char limit?
11  Float User_Cum_Counter
      Thank you Balazs
      Why float? Ruined counts half, frick you

12  Bool  Local_available
      True if downloaded, false if deleted
      As to how local game detection, that's TODO to figure out
        (Blackfox recommended: Create a single file in each game folder and store the executeable, version and other small info, to check)
        (Maybe implement a download feature? )
12  Str   Local_path_to_exe
      If I wanna check if game is available, why not just check the exe is still there
      If path is null or not found then it's not there.
        (And because I specially want the exe I can do what Blackfox suggested)
      Special path cases will be a bitch.
      I can use this to start the games too, maybe hour/time counting like Steam

13  Str   Local_Version
      Local version
14  Date  Local_Last_Play_Date
      Not sure if DateTime or Unix timestamp or maybe just fuck it and simple string would be best.
15  Int   Online_Rating
      Public online ratings, hopefully I can convert or somehow display.
16  Int   Local_Engine
      Publicly online see what the engine is, but from folder structure, you can usually figure it out.
    Possible Values:
      0 - Other ( or unknown )
      1 - WolfRPG
      2 - WinGit
      3 - Unreal Engine
      4 - Unity
      5 - RPGmaker
      6 - Ren'Py
      7 - QSP
      8 - Java
      9 - HTML
      10 - Godot
      11 - Flash
17  Int   Local_os
      Operation System
    Possible Values:
      1 - Html ( So basically any hardware )
      2 - Windows
      4 - Linux
      8 - Mac
      16 - Android
      32 - IOS
      64 - Online only
18  Str   Local_Language
      Array of languages, maybe unicode flags?
19  Float Local_playTime
20  Str   Online_links
      Additional website link of array on where to find the dev, or game

Outside folder structure:
HGM
  Assets
    defult              (Default pictures, needed for general app use) [ If it get's deleted, the program must be able to work without them ]
    gameBanners         (If I plan to have webpage and fancy pictures, I should store somewhere locally so faster to load)
      <gamename>.png    [ If picture larger than 1 MB or 4k*4k cut that shit down smaller]
  Games                 (Why store games? Why not? easier to manage them.) [IMPORTANT: MUST BE ABLE TO BE MOVED / CONFIGURED]
    <gamenames>
      hgm_data.txt      (Blackfox idea to keep track for games, opens a few ideas)
  Storage               (Todo, figure out database and backup of it)
    _config.json
    _db1.db
    english.json        (No idea, just store languages too)

  Todo, figure out UI and backend
    Should it be possible to just go to a UI folder, press delete on 3 of them.
    Also backend should be a seperate file/folder?
    OR... Abuse Discord Rich Presence and used that as additional error handling, fixer and updater
      It will need a seperate thread anyway
      Also don't forget buttons
        <https://discord.com/developers/docs/rich-presence/overview>
        <https://github.com/JnCrMx/discord-game-sdk4j>

Discord integration
  new possibility, "Compiting in gooning" status
    <https://discord.com/developers/docs/developer-tools/game-sdk#activitytype-enum>
  two buttons: "Join the fun" and "Goonecting"?

Future huge idea.
  If backend detect that you open a page about a game you played in the past, show a warning or a small transparent window about stats

Nyaneko:

  1. Able to export/import database.
    Only have a single file when export/import
    (Idea, zip file)
  
  2. Make the application portable
    Ask user on first startup for portable version ?

  3. When asking user use *YOUR_NAME* or *your shit*

Idea: Brother idea (Swoi)
 Gooner counter gets plates depending count
  5 - bronze trophy or something
  10 - silver
  25 - gold
  69 - platinum
