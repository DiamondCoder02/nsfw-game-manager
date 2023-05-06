# Maybe good idea

(I had a vsc project with git ready for long time, I just started now.)

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
