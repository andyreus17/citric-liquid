package cl.uchile.dcc.citric
package controller

class Wait(controller: GameController) extends GameState {
  override def evade(controller: GameController): Unit = {
    controller.state = new Combat(controller)
  }

  override def defend(controller: GameController): Unit = {
    controller.state = new Combat(controller)
  }
}
