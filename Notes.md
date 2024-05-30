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

Or if I'm retarded just -1 to everything and site is handled seperatly and not in Array

---
---

IMPORTANT:
This is just a rough draft on future plans.
These are not final and can be changed at any time.

[TODO/Release] [version number] (tasks)

## Release => [0.1.2.0] (tasks: 18)

- [x] Added a counter for the sites of game and all games counter
- [x] Change pictures in Credit and FAQ
- [x] Able to click and copy values from table, but no longer edit them as originally planned.
- [x] Experimenting with colors, will probably change next update.
- [x] Updated images and huge thanks to @NyanekoNNK for the new logo!
  - Yes, the image is a pun
- [x] Written comments in the program so future development will be easier hopefully.
- [x] Fix Discord custom button
- [x] optimize search ( searchByName: dungeon )
  - Now it won't overflow out of your screen
- [x] Written wiki on Github
- [x] Update FAQ in program
- [x] settings moved to json
  - You will have to redo it, sorry for that >.<
- [x] added steam
- [x] Load steam when start loop thing api
- [x] remake files
  - Asset folder LOL
- [x] App unable to start if there is space anywhere in the path of the program
  - Only if developer console is enabled. Somehow the console commits unalive
  - Fixed by changing to other console method
- [x] Info when starting
- [x] Save slots
  - <https://github.com/DiamondCoder02/nsfw-game-manager/issues/7>
- [x] Fixed exe saying "This application requires a Java Runtime Environment."
  - <https://stackoverflow.com/questions/7071133/how-to-bundle-a-jre-with-launch4j?rq=3>
- If steam is detected, the update application is not shown as Steam will update and the buttons is useless
- [x] dlsite.com support **TESTING NEEDED**

## TODO => [0.1.2.0] (tasks: 1)

- [ ] Write detailed wiki both in app and on Github

## TODO [0.1.3.0] (tasks: 6)

- [ ] Rewrite language.csv
- [ ] Cum counter ( ͡° ͜ʖ ͡°)
- [ ] improve dark mode?
- [ ] In app wiki
- [ ] Make it LINUX compatible

## TODO random: [???]

- [ ] move database from xml ( MUST NOT BREAK )
- [ ] Memory & CPU usage higher? ( Need more test )
  - This happened when I run a full database update with API refreshes?
