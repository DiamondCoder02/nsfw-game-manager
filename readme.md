# Hentai game manager ( 0.1.1.4 )

Welcome to another game manager. The idea was simple, an easy to use manager that shows useful information and the newest infos about the stored games.

This was mostly designed for f95zone, but since I started this project, it's expanding quite a bit.

---_**Warning: I have only tested the program on windows, I have no idea if it works on other OS.**_---

## Features

- Easy to read table, excel like design
- Small and lightweight program
- I am a Discord nerd, so it has activity feature for it (Default off)
- Multi language support( more on that later)
- A backup system for the games every time it starts(, only saves the last 10 version of the database)

### Language support

Currently supported languages: English( +EngwishUwU ), Hungarian

Languages work like an excel. Top row is the language in English(, in lowercase). The first column is for the program, **second** column is to be translated from. I hope the small example shows it in the [language.csv](https://github.com/DiamondCoder02/nsfw-game-manager/blob/master/doNotTouch/language.csv) file.

## website fetures

Supported websites: [f95zone](https://f95zone.to/), ~~[steam](https://store.steampowered.com/)~~(soon), ~~[dlsite](https://www.dlsite.com/pro/?locale=en_US)~~(later)

### Default features for every added game

- Shows the name, developer, latest version, date and engine the game is available on
- Shows what platform the game you can play (Windows, Linux, Android, IOS...)
- And many other information if manually added
- You can even write small notes for yourself as a reminder for each game

Manually added games are games that are not on the supported websites, but you want to keep track of them.

### Features if added from f95zone

- For version column, "✖" if it's abandoned or a "✔" if the game is completed
- For name column, a "[VN]" tag will be if it's a Visual Novels
- For engine column, a "[VR]" tag will be if a game has VR support

### Folder features

You can add a folder where you store your games, and it will automatically add them to the database

**Sadly it only supports a specific format. for now...**

1. You set a folder where you store your games
2. You save your games in a specific format inside that folder:
  It must look like this:

    - ```fileName: {type}-{gameId}_{gameName}_{gameVersion} {anythingElseYouWant}```
    - ```Example: f95-696969_Good Game, try me_v12.31 DeveloperAndStuffMaybeNotes```
  | fileName      | explanation |
  | ----          | ----        |
  | type          | The website the game is from. If it's from f95zone, you write "f95". |
  |               | "man" - manually / "f95" - f95zone / "steam" - steam / ~"dls" - dlsite~ |
  | gameId        | The ID from the websites. If the game is manually added, you must give an ID, but must be unique |
  | gameName      | The name of the game, only here where spaces are allowed |
  | gameVersion   | The version of the game you have donwloaded. |
  |               | This is the one that get's compared when checking for online version. |
  | anythingElse  | If you have any note of the game, you can write anything after all of this |

3. If everything is correctly, the program will automatically add the game to the database
  It will also check for updates if the game is from online site

## TODO / Future ideas

### Priority ( less the better )

This list will probably change often as this is where I keep track of what I'm working on or what's temporarily not working.

- [x] ~Rewrite ReadMe~ (Should be fine?)
- [ ] Test writing a wiki on github?
- [ ] Rewrite language.csv
- [ ] move database from xml ( MUST NOT BREAK - 0.1.2.1 ? )
- [ ] fix bugs / do something with all the ~6 TODOs that is in the source code
- [X] ~Fix weird long pause after you change some setting?~ (I haven't changed anything and it's fixed? )
- [ ] optimize search ( searchByName: dungeon )
- [X] ~Edit the FAQ part about support, those don't work?~
- [ ] Write comments
- [ ] Optimizations ( Low priority )

### Later planned

- [ ] Memory & CPU usage higher? ( Need more test )
- [ ] improve dark mode
- [ ] FAQ rewrite
- [ ] dlsite.com support
- [ ] Steam support ( 0.1.2.1 ? )
- [ ] Make it LINUX compatible
- [ ] Rewrite the whole program
- [ ] ~~If you change the language, you have to restart the program.~~ Fixed by asking user to restart the program...

### Stupid ideas from friends

- [ ] Cum counter

## Additional information

### Debuging

- There is a debug console that you can open, but you have to edit the config.json to enable it.
- Every settings and language is saved:
  - (Windows) C:\Users\\{youUser}\AppData\Roaming\DiamondCoder\nsfwGameManager
- I have tested the program with my ~500 games, it works fine...

### Used Libraries

- Modified discord game sdk. [Original here](https://github.com/JnCrMx/discord-game-sdk4j)
