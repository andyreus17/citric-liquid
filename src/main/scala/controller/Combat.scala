package cl.uchile.dcc.citric
package controller

class Combat(controller: GameController) extends GameState {
  override def attack(controller: GameController): Unit = {
    controller.state = new Wait(controller)
  }

  override def endCombat(controller: GameController): Unit = {
    controller.state = new LandingPanel(controller)
  }
}
