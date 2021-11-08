# WhereDidMyScooterKeysWent2
A bigger, better robust headless game that needs a configurable controller and optionally a view.

Run Main.main()!

I figured that initially what I am creating is, is a game that is half missing the controller and completely missing a view. I made the GameController abstract, that has an implemented execute method, but for that to work, we need to define where to get the I/O, how to handle errors and how to close the environment.
When a class extends the GameController class, it will have access to all the game data through the GameDataAccess class which provide methods for the controller to be displayed.
The GameController subclasses can only call the engine (GameLogic) when a newGame is started (for now... saving to be introduced later), otherwise it will only call it through the parser; player commands.
The GameDataModel essentially stores all the relevant data to the game, and can be only modifyed by the engine and accessed via the GameDataAccess class.

[Version: 2.0]
Player |
Areas |
A few commands |
Two rooms |
Some items placed |
Locked doors and keys (keys are not locked, duh, tho u can leave it in a room, leave and never be able to get back again. I have left this in, this happened to me too many times here in Denmark) |
GUI / CLI view, run Main to choose! |
Right on GUI items for faster commands (e.g. go to room, take/drop items)

![arch](https://user-images.githubusercontent.com/90683098/140827720-78169582-2fee-4a8b-8fd6-87ebd11b3fa2.PNG)
