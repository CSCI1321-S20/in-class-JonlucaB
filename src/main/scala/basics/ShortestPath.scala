package basics

import adt.ArrayQueue

object ShortestPath {
  val maze = Array(
    Array(0,1,0,0,0,0,0,0,0,0),
    Array(0,1,0,1,1,1,1,1,1,0),
    Array(0,1,0,0,0,1,0,0,0,0),
    Array(0,1,1,1,0,1,0,1,1,1),
    Array(0,1,0,0,0,1,0,0,0,0),
    Array(0,0,0,0,0,1,0,1,1,0),
    Array(0,1,1,1,1,1,0,0,0,0),
    Array(0,1,0,0,0,1,1,0,1,0),
    Array(0,1,0,1,0,1,0,0,1,0),
    Array(0,0,0,1,0,0,0,0,1,0)
  )

  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1))
  def breadthFirstShortestPath(sx: Int, sy: Int, ex: Int, ey: Int): Int = { //ex and ey are the end goals. Turn into player x and y
      val queue = new ArrayQueue
      val visitd = mutable.Set[(Int, Int)]((sx, sy))
      queue.enqueue((sx, sy, 0))
      while (!queue.isEmpty) {
          val (x, y, steps) = queue.dequeue()
          for ((offsetx, offsety) <- offsets) {
              val nx = x + offsetx
              val ny = y + offsety
              if (nx == ex && ny == ey) return steps + 1 //do not leave these checks of equality, make sure the minus is < 1
              if (nx >= 0 &&  nx < maze.length && ny >= 0 && ny < maze(nx).length //don't need out of bounds for maze 
                && maze(nx)(ny) == 0) { //this is gonna need to be Maze.isClear()
                    queue.enqueue((nx, ny, steps + 1))
                    visited += nx -> ny
              }
          }
        }
      Int.MaxValue
  }
  def main(args: Array[String]): Unit = {
    println(breadthFirstShortestPath(0, 0, 9, 9))
  }
} 

/*make work for graphic game**********
graphic game has enities as double, all ints in here are gonna need to be doubles
notice that you need to check if something is inside a wall
wrap the whole thing to make sure it doesn't spawn insie of a wall
use this instead of the random thing to check where to move. You will use this search in 4 different directions for each enemy, and whichever search has-
returned the smallest value is the direction it should go. The target x and y are the player's x and y at the last update
*/