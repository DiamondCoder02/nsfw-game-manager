# Shut up error handler

SQL types
0   Unsigned_INT		Key / internal ID
1   Unsigned_SMALLINT	SiteNum
2   TEXT			SiteID
3   MEDIUMTEXT		Online_Name
4   TEXT			Online_Developer
5   TEXT			Online_Publisher
6   TEXT			Online_Version
7   DATE			Online_Last_Version_Date
8   Unsigned_TINYINT	User_Played_Progress
9   Unsigned_TINYINT	User_rating
10  LONGTEXT		User_Notes
11  Unsigned_SMALLINT	User_Cum_Counter
12  TEXT			Local_path_to_exe
13  TEXT			Local_Version
14  DATE			Local_Last_Play_Date
15  Unsigned_TINYINT	Online_Rating
16  Unsigned_TINYINT	Local_Engine
17  Unsigned_SMALLINT	Local_os
18  MEDIUMTEXT		Local_Language
19  Unsigned_INT		Local_playTime
20  MEDIUMTEXT		Online_links

Database IDs and accepteable variables
0   Int   Key / internal ID
      Because there is mostly and possible that from 2 sites you get the same id,
      just use a random incrementing internal id. You will always need to search through the database,
      bet less conflicts.
1   Int   SiteNum
      Just use numbers and assign each to a string, less data to store
    Possible values:
      1 - Manual ( Not on any supported site)
      2 - F95
      4 - Steam
      8 - Dlsite
2   Int   SiteID
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
      0 - 100% Done (User got everything that is possible in game)
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
      0 - unknown
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
