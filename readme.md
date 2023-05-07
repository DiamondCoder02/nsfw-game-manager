# F95zone game manager

[IMPORTANT!!!](https://github.com/DiamondPRO02/Program-modszertan-Java/blob/master)

Maybe good idea

## 2023.05.06 02:43

- Had the stupid idea to make my hentai excel sheet into xml java.
- Also later mix it with the knowledge from Programming 2 with the windows.

First I have to figure out how the data is stored in xml.

## 2023.05.06 12:44

After waking up and finally stoped drinking...

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<nsfwgames>
 <settings>
  <autoupdate>true</autoupdate>
 </settings>
 <source from="f95zone">
  <game id="74965">
   <name>!Ω Factorial Omega: My Dystopian Robot Girlfriend</name>
   <developer>Incontinent Cell</developer>
   <old_version>v0.61 beta</old_version>
   <new_version>v0.85.69</new_version>
  </game>
  <game id="19095">
   <name>2037 - Almost ready Inc.</name>
   <developer>MadAlice</developer>
   <old_version>v0.9.6</old_version>
   <new_version>v0.9.6</new_version>
  </game>
  <!-- If there is id but something goes wrong and one or more info is not available -->
  <game id="011">
   <name>na</name>
   <developer>na</developer>
   <old_version>na</old_version>
   <new_version>na</new_version>
  </game>
 </source>
</nsfwgames>
```

What the hell is this?

Anyway. I did some changes and this should be better as a test:

``` xml
<game id="74965">
 <!-- all this is string -->
 <name>!Ω Factorial Omega: My Dystopian Robot Girlfriend</name>
 <developer>Incontinent Cell</developer>
 <played_version>v0.61 beta</played_version>
 <!-- handle date as integer - ddmmyyyy -->
 <dateof_lastupate>11052021</dateof_lastupate>
</game>
```

Time to push to github.

## 2023.05.06 16:40 Forgot to log but anyway

Added the whole add command for console. Although I need to format the save.

Also started the menu system.

## 2023.05.06 18:13

Aftor long painfull googling I found the problem for one thing. Noone told us the proper use of Scanner package.
Anyway, I added a simple menu and i fixed an anoying thing with the Scanner about exhausting the input.
[Solution was this.](https://stackoverflow.com/questions/13576734/java-while-loop-for-menu-selection-console-based-program)

- There is a bug for Date, need fix.
- I also hate xml style, need to fix that too.

## 2023.05.06 20:23

Back from shower and with dinner I search for solutions.

~~I'm stupid, date fixed~~ Fuck the xml time to make removal.

## 2023.05.06 22:06

There is a reason I love github copilot and I don't prefer ChatGPT

After an hour this should be nice, just need some tweaking:

``` txt
-----------------------------------------------------------------
|id    | developer                | played_version              |
| LastUpdate | name                                                                                    |
-----------------------------------------------------------------
|011| na | na |
| 0000-00-00 | na |
-----------------------------------------------------------------
```

To this later:

``` txt
-----------------------------------------------------------------
|id    | developer                | played_version              |
| LastUpdate | name                                            |
-----------------------------------------------------------------
|011   | na                       | na                          |
| 0000-00-00 | na                                               |
-----------------------------------------------------------------
```

*Note: changing taps into spaces at name fixed weird formating.*

## 2023.05.07 ~00:00

I'm done with listing, should be fine for now. Next is remove.
Luckily remove was easy to write. Way to easy. I'm scared...

## 2023.05.07 02:49

Now with Dinner, I have not much idea how to handle the update...
Probably with asking ID, then what to change, but I'm sure I can make it better.

## 2023.05.07 03:37

Most stuff is done, only remaining is the update. But that is after sleep.
I also need to test as I haven't bug tested yet.

## 2023.05.07 12:00

4-5 hours of sleep is enough, right?

At least in 3 hourse I'm done. Update system works and editing is saved temporary to another file.
Bughunting?

Honestly it's done, only question how will I submit this to teacher...
