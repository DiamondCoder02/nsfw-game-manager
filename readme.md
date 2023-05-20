# F95zone game manager

Maybe good idea

Now that this is independent from uni work... Time to make it better
and to torture myself

Because I'm lazy everything is handled with ids, mostly.

---
---

## TODO

---

### All the things that has button but no fucntionality

Games tab:

- [x] ~~add game~~
- [x] ~~remove game~~
- [x] ~~update game~~
- [ ] save file to different location
- [ ] api and file refresh??? Is this needed?

Other tab:

- [ ] Show game info needs more options, but the first five is good
- [ ] FAQ
- [ ] Credits

Settings tab:

- [ ] darkmode
- [ ] autofetchnewgames
- [ ] autoUpdateGames

---

### Ideas

- [ ] F95zone.to support
- [ ] dlsite.com support

---

### Limitations and bugs

- [ ] You have to reastart the app to see edited columns

---
---

Notes from and to myself

``` xml
If there is id but something goes wrong and one or more info is not available 
Actually, while there is no api/rss, this is not needed
<game id="1">
		idea: some stuff can be demonstrated with styles 
		or put a star or symbol
	<name>testname</name>
	<developer>testdeveloper</developer>

		idea: if games is completed or onhold or abondoned, 
		have small thing at the online last version
	<onlineVersion>v0.1</onlineVersion>
	<played_version>v0.2</played_version>
	<dateof_lastupate>0000-00-00</dateof_lastupate>
	<onlineLastUpdate>0009-09-09</onlineLastUpdate>
	<websiteRate>10/10</websiteRate>
	<userRate>Shitgame/something OR 5/5</userRate>
	<howFarUserPlayed>finished/tobecompleted/neverplayed</howFarUserPlayed>
	<deletedFromPc>true/false</deletedFromPc>
	<engine>Unreal/Renpy</engine>

		Choose one of these maybe?
	<OS>Win./Linux/Mac/Android</OS>
	<OS win="y" lin="n" mac="n" and="y" other="y">randomOS</OS>

	<selfNote>This is shit games</selfNote>
</game>
```
