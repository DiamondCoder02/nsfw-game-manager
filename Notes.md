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
- [x] full URL support. You don't need just the ID, you can paste the full URL

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

---

---
{num} number

## Wiki pages

Note: make everything dynamic

IF {} it's a picture
{name, width, height}
  so this: string.replace( "" , <img src= name width= width height= height ></img> )

if [] it's a link
{link, text}
  so this: string.replace( "" , <font color = 64AFFF><a href= link > text </a></font>")

Note this might note of I use different to display the text
if text has \n, it's a new line
  so this: string.replace( "\n" , <br>)

### 0 - welcome

Welcome to the hentai Game Manager wiki!
This little window should tell every possibility that you can do with the program.
On the left side, you can see the pages that you can visit.
If you have any questions, encounter bugs or anything, check the {num} - Credits page.

Version: 0.1.2.0
Last update date: 2024.06.03

---

### {num} - FAQ

"Q: What is this?"+br+"A: A simple excel like hentai game manager."+br+br+
"Q: Why this exist?"+br+"A: Because I had enough managing my games in an excel table and wanted something better."+br+br+
"Q: What can this program do?"+br+" Check out the wiki for all the possibilities: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki\">Hentai game manager wiki</a></font>"+br+br+
"Q: Support the project?"+br+"A: Currently not available"+br+br+
"Q: Can I help coding? / The program not working correctly? / Want a new feature?"+br+"A: All isssue, requests and help is accepted on GitHub:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose\">HGM Github</a></font>"+br+br+
"Q: placeholder?"+br+"A: placeholder!"+

---

### {num} - Credits

"<img src='file:"+logos[1][0]+"' width="+logos[1][1]+" height="+logos[1][2]+"></img>"+br+

"Originally: This was a small project to learn Java for university."+br+
"Main focus is something dynamic and easy to use for hentai games."+br+
"This whole program is just to convert my old excel file into a smart storage"+br+
"Full open source:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br+
"----------------------------------------------------------------"+br+br+ // 64

"Hi, I'm DiamondCoder or Diamond."+br+
"University student, programer, stupid gamer"+br+
"I started the project in 2023, handling Steam, check issues, still coding and arguing with Nyaneko why the UI looks from 2008"+br+
"Thank you for using this program, it gives me smile I can make something for others :3"+br+
"Huge thanks to Nyaneko who made graphic elements and helped me mentally and pushed me to work more on this program."+br+

"<img src='file:"+logos[0][0]+"' width="+logos[0][1]+" height="+logos[0][2]+"></img>"+br+ // Picture as break

"Hi, I'm NyanekoNNK or Nyaneko."+br+
"Graphic designer and I'm here to help Diamond with this project."+br+
"I'm responsible some graphic elements, the Steam store, legal matters and quality control."+br+
"I can and will break everything so I will make sure there are close to no bugs in the application"+br+br+

"----------------------------------------------------------------"+br+ // 64
"Contact us:"+br+
"Support Email: <KnockyNekos@gmail.com>"+br+
"Github Issues: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose\">Github Feedback</a></font>"+br+
"Github Wiki: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki/\">Github Wiki</a></font>"+br
