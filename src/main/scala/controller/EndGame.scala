package cl.uchile.dcc.citric
package controller

class EndGame extends GameState {
  override def reboot(controller: GameController): Unit = {
    controller.state = new PreGame()
  }
}
