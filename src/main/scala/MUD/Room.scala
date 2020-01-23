package MUD

import scala.xml._
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
    val desc: String,
    private val exits: Array[Int],
    private var items: List[Item]
    ) {
  
  //val directions() = List[String](n, s, e, w, u, d) //used EXCLUSIVELY for exitDir().
  private val possibleDirections = exitDir()

  def exitDir(): Array[String] = { //translates the possible directions to n, s, e, w, u, d. The player will see directions, but code will keep INTs so it can
    //know just where it is actually going
    val exitDirs = Array.fill(6)("@")
    for( i <- 0 until 6) if(exits(i) > -1) exitDirs(i) = Room.directions(i)
    exitDirs
  }
  
  def description(): String = {
    val itemsDesc = items.foreach(_.toString)
    this.name+"-Descritption-\n"+this.desc+"-Items-\n"+itemsDesc+"-You may exit by going "+possibleDirections.filter(_ != "@").mkString(", ")
  }

  def getExit(dir: Int): Option[Room] = { /*when player wishes to go to a different room, they will type a direction. That direction will then need to be
    translated from n, s, e, w, u d, into the specific index of the exits Array[Int] in this class so this call can be made*/
    dir match {
      case -1 => None
      case _ => Some(Room.world(exits(dir))) 
    }
  }

  def getItem(itemName: String): Option[Item] = {
      items.find(_.name == itemName.toUpperCase) match {
        case Some(item) =>
          items = items.patch(items.indexOf(item), Seq(), 1) //patch looks pretty. This line of code takes out the first item named 'itemName'
          Some(item) 
        case None => None
      }
  }
  def dropItem(item: Item): Unit = {
      item ::= items
      println("-You have dropped "+item.desc+" in "+name+".\n-It feels abandoned.")
  }
}

object Room {
  val directions = List[String]("n", "s", "e", "w", "u", "d") //used EXCLUSIVELY for direcion parsing
    //parse data from XML file and create all the rooms

  def readRooms(): Array[Room] = { //put this in XML terms, will be much easier...
    val source = XML.loadFile("world.xml")
    val genesis = source \ "world" //in the beginning... there were a bunch of rooms...
    source.close

    Array.tabulate(genesis.length)({
      i => {
        val rName = i \ "@name"
        val rDesc = i \ "@desc"
        val rItems = (i \ "items").map(new Item(_ \ "@name", _ \ "@desc"))
        val rExits = (i \ "exits").split(", ").map(_.toInt)

        new Room(rName, rDesc, rExits, rItems)
      }
    })
  }
  val world = readRooms() //Array[Room] - just a big array of rooms
}