package cl.uchile.dcc.citric
package controller

class PreGame(controller: GameController) extends GameState {
  override def startGame(controller: GameController): Unit = {
    controller.state = new Chapter(controller)
  }
}
