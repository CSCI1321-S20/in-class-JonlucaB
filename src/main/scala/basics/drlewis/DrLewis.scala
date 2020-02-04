package drlewis

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

object DrLewis extends JFXApp {
  val grid = new Grid
  val renderer = new Renderer(gc)
  val canvas = new Canvas(800, 800)
  val gc = canvas.graphicsContext2D 

  stage = new JFXApp.PrimaryStage {
    title = "Dr. Lewis"
    scene = new Scene(800, 800) {
      content = canvas
      renderer.render(grid)

      val timer = AnimationTimer(time => {
          println(time)
      })
      timer.start
    }
  }
}
