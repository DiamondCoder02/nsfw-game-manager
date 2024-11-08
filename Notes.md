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

## For the future, notes
