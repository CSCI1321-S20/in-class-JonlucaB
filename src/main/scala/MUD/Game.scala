package MUD

object Game {
    def main(args: Array[String]): Unit = {
        val player = new Player("Jim", List[Item](), Room.world(0), 100)
        var playerInput = ""
        while(playerInput != "EXIT") {
            player.processCommand(readLine())
        }
    }
}