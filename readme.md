# Hentai game manager

This is a *hentai* game manager. The idea was simple, an easy to use manager that shows useful information and the newest infos about the stored games. Although it only shows what you have added already yourself. If you have a game folder it can read that too, but later on that.

Mostly designed for f95zone ~~and dlsite (soon?) and Steam(if I can)~~. You can also add your own manually if the program don't support the site although of course it won't get updated if a new version is on the web.

**Warning: I have only tested the program on windows, I have no idea if it works on other OS.**

## Some nice features

- Easy to read table, excel like design
- Small and lightweight program
- I am a Discord nerd, so it has activity feature for it (Default off)
- Multi language support( more on that later)
- The program creates a backup of the games database every time it starts(, only saves the last 10)
- JDK 17, nut sure if it's nice, but it's something

## Features if you add game from a site

If it's limited to some site, it will be in brackets ()

- If the game is the final version, it gets a "✔" at the version column
- If game is abandoned, it gets a "✖" at the version column
- For VisualNovels there will be a "[VN]" at the name column
- If a game has VR tag, it will have "[VR]" at the engine column

### Language support

Currently supported languages: English( +EngwishUwU ), Hungarian

Languages work like an excel. Top row is the language in English(, in lowercase). The first column is for the program, **second** column is to be translated from. I hope the small example shows it.

### How the program handles games folder

- Every settings and language is saved:
  - (Windows) C:\Users\\{youUser}\AppData\Roaming\DiamondCoder\nsfwGameManager
- I have tested the program with my ~500 games, it works fine...

- If you add the game folder, you have to save your games in a specific format. It is unfortunate, but it works. Because I'm stupid and a programmer, currently the program only supports this format:

``` text
man-000000_{gameName}_{gameVersion} {anythingElseYouWant}

f95-696969_Good Game, try me_v12.31 DeveloperAndStuffMaybeNotes
```

If it doesn't begin with man, f95 ~~or dls(soon?)~~ it will be ignored. (man means manually added game, so it's not connected to any site, you can give any ID you want it to have, but make it unique)

## TODO / Ideas

- [ ] FAQ rewrite
- [ ] Optimizations
- [ ] Text size small on large display
- [ ] dlsite.com support
- [ ] Steam support
- [ ] Auto updater
- [ ] Discord is buggy, when you turn it **on or off**, it get's stuck and you have to restart the program. (Kill it with task manager)
