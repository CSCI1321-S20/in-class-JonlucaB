package drlewis

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Grid {
    private var _currentPill = new Pill(Seq(new PillHalf(3, 0, ColorOption.randomColor),
        new PillHalf(4, 0, ColorOption.randomColor)))

    def currentPill = _currentPill
    def elements: Seq[Element] = for (i <- 0 until 8; j <- 6 until 16; if math.random < odds) yield {
        new Virus(i, j, ColorOption.randomColor)
    }
    def update(delay: Double): Unit = ???
}