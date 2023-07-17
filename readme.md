# Hentai game manager

Maybe good idea

Now that this is independent from uni work... Time to make it better
and to torture myself

I have tested this with my 4-500 games. Still small bugs, but it handles it without *much* problem.

## Important

- Currently java 8
- Files: AppData\Roaming\DiamondCoder\nsfwGameManager\
- (For now )2+1 languages: English(, Engwish UwU), Hungarian
- The app creates a backup of the database every time it starts(, only saves the last 10)

### If you put games from F95zone website

- If the game is the final version, it gets a "✔" at the version column
- If game is abandoned, it gets a "✖" at the version column
- For VisualNovels there will be a "[VN]" at the name column
- If a game has VR tag, it will have "[VR]" at the engine column

---
---

## TODO

- [ ] FAQ
- [ ] Optimizations
- [ ] Text size small on large display - [Bug here](<https://bugs.openjdk.org/browse/JDK-8202973>)

---

### Ideas

- [x] ~~F95zone.to support~~
- [ ] dlsite.com support

### Notes

Idea: User can choose a folder and the programm will search through it. But due to lazyness the format should be like this:

``` text
man-000000_{gameName}_{gameVersion} {anythingElseYouWant}
f95-696969_Good Game, try me_v12.31 DeveloperAndStuffMaybeNotes
```

If it doesn't begin with f95 or man ~~or dls(soon?)~~ it will be ignored.

This I can use to check last downloaded version, and last time the folder has changed. Also auto update if game is still on pc.
