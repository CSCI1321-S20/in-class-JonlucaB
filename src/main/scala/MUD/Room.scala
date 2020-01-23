package MUD

 /*  
 
   * def description(): String - Build a String with the description of the room for printing. Note this is the full printed description with the name, items, 
   * and exits. 
   * def getExit(dir: Int): Option[Room] - Return the room that is reached by going in a particular direction if there is an exit in that direction.
   * def getItem(itemName: String): Option[Item] - Pull an item from the room if it is there and return it. Note that if an item is returned, it should no 
   * longer be in the room.
   *
   *  def dropItem(item: Item): Unit - Add an item to this room. */

class Room( //arguments for instantiating a new room, because they are going to be different everytime a room is created
    val name: String, 
    val dsc: String,
    private val exits: Array[Int],
    private var items: List[Item]
    ) {

  def description(): String = ???
  def getExit(dir: Int): Option[Room] = ???
  def getItem(itemName: String): Option[Item] = {
      items.find(_.name.toUpperCase == itemName.toUpperCase) match {
        case Some(item) =>
          items = items.filter(_ != item) //switch to patch, looks more fancy ;) also can just get index where and drop that one instead of all the matching items
          Some(item) 
        case None => None
      }
  }
  def dropItem(item: Item): Unit = {
      item ::= items
      println("You have dropped "+item.desc+" in the room. It feels abandoned.")
  }
}

object Room {
    //parse data from XML file and create all the rooms

  def readRooms(): Array[Room] = { //put this in XML terms, will be much easier...
    val source = scala.io.Source.fromFile("world.xml")
    val genesis = source.getLines() //in the beginning... there were a bunch of rooms...
    
    source.close
  }
    val world = readRooms()
}