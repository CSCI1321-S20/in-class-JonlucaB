package drlewis

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
    val cellSize = 30
    def render(grid: Grid): Unit = {
        gc.fill = Color.DarkGrey
        gc.fillRect(40, 40, 8*cellSize, 16*cellSize)
        for (elem <- grid.elements; cell <- elem.cells) {
            val color = cell.color match {
                case ColorOption.Red => Color.Red
                case ColorOption.Blue => Color.Blue
                case ColorOption.Yellow => Color.Yellow
            }
        }
    }
}