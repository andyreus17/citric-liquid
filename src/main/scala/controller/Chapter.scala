package cl.uchile.dcc.citric
package controller

class Chapter(controller: GameController) extends GameState {
  override def newChapter(controller: GameController): Unit = {
    controller.state = new Chapter(controller)
  }
}
