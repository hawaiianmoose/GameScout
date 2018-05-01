package hawaiianmoose.gamescout.data

class TwitchResponse {
    lateinit var top: Array<TwitchGame>

    fun getTopGames() : Array<TwitchGame> {
        return top
    }
}

class TwitchGame {
    lateinit var game: Game
}

class Game {
    var name: String = ""
    var popularity: Int = 0
    lateinit var box: GameArt
}

class GameArt {
    var large: String = ""
    var medium: String = ""
    var small: String = ""
}