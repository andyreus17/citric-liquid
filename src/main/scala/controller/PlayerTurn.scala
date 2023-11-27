package cl.uchile.dcc.citric
package controller

class PlayerTurn(controller: GameController) extends GameState {
  override def rollDice(controller: GameController): Unit = {
    controller.state = new Moving(controller)
  }
}
