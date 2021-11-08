# WhereDidMyScooterKeysWent2
A bigger, better robust headless game that needs a configurable controller and optionally a view.

I figured that initially what I am creating is, is a game that is half missing the controller and completely missing a view. I made the GameController abstract, that has an implemented execute method, but for that to work, we need to define where to get the I/o, how to handle errors and how to close the environment.
When a class extends the GameController class, it will have access to all the game data through the GameDataAccess class which provide methods for the controller to be displayed.
The GameController subclasses can only call the engine when a newGame is started (for now... saving to be introduced later), otherwise it will only call it through the parser; player commands.

Version: 2.0
