package MUD

/*   * def processCommand(command: String): Unit - Parse and act on a command
   * def getFromInventory(itemName: String): Option[Item] - Pull an item out of the inventory (if it exists) and return it.
   * def addToInventory(item: Item): Unit - Add the given item to inventory.
   * def inventoryListing(): String - Build a String with the contents of the inventory for printing.
   * def move(dir: String): Unit - Move the player in a particular direction if possible. */

class Player(
    private val _name: String,
    private var inventory: List[Item],
    private[this] var position: Room,
    private[this] val _health: Int){

  def health() = _health

  def name() = _name

  def processCommand(command: String): Unit = {
    val input = command.split(" ").toList.map(_.trim.toUpperCase)

    input match {
      case List("HELP") => ??? /* print out a text file that explains how all player commands operate*/
      case List("LOOK") => println(position.description) /*tells the player a description of the room by calling [Room].description*/
      case List("INV") => println(this.inventoryListing) /*shows the player their inventory*/
      case "GET"::itemName => { /*adds the specified item into the player's inventory*/
        val item = position.getItem(itemName(0))
        item match {
          case Some(item) => addToInventory(item)
          case None => println(itemName+" IS NOT IN THIS ROOM-")
        }
      }
      case "DROP"::itemName => { /*drops the specified item in the current room*/
        val item = inventory.find(_.name == (itemName(0).toUpperCase)) 
        item match {
        case Some(item) => {
          inventory = inventory.patch(inventory.indexOf(_.name == itemName), Seq(), 1) //patch looks pretty. This line of code takes out the first item named 'itemName'
          position.dropItem(item)
        }
        case None => println("-YOU DO NOT HAVE THAT ITEM IN YOUR INVENTORY-")
        }
      }
      case _ => move(_(0)) /*moves the player in the specified direction*/
    }
  }

  def getFromInventory(itemName: String): Option[Item] = {
    inventory.find(_.name == itemName.toUpperCase) match {
      case Some(item) => Some(item)
      case None => None
    }
  }

  def addToInventory(item: Item): Unit = {
    item ::= inventory
  }

  def inventoryListing(): String = "-CURRENT INVENTORY-\n"+inventory.foreach(_.toString)

  def move(dir: String): Unit = {
    position.getExit(Room.directions.indexOf(dir)) match {
      case Some(room) => {
        position = room
        println("-YOU HAVE MOVED TO THE "+room.name+"-")
      }
      case None => println("-YOU CANNOT GO THAT WAY-")
    }
  }
  /*need to match dir with index of directions in Room class. This method 
  changes the player's position by calling a method from the Room class and getting a room back from there*/
}