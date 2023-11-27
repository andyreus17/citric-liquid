package cl.uchile.dcc.citric
package controller

class LandingPanel(controller: GameController) extends GameState {
  override def doEffect(controller: GameController): Unit = {
    controller.state = new Chapter(controller)
  }
}
