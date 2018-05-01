package hawaiianmoose.gamescout.data

class GameData {
    var id: Int = 0
    var name: String = ""
    lateinit var screenshots: Array<Screenshot>

}

class Screenshot {
    var url: String = ""
}
