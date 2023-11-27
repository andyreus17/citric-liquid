package cl.uchile.dcc.citric
package controller

class Recovery(controller: GameController) extends GameState {
  override def insufficientRoll(controller: GameController): Unit = {
    controller.state = new Chapter(controller)
  }

  override def sufficientRoll(controller: GameController): Unit = {
    controller.state = new PlayerTurn(controller)
  }
}
