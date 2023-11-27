package cl.uchile.dcc.citric
package controller

class Moving(controller: GameController) extends GameState {
  override def choosePath(controller: GameController): Unit = {
    controller.state = new Moving(controller)
  }

  override def outOfMovement(controller: GameController): Unit = {
    controller.state = new Combat(controller)
  }

  override def stopMovement(controller: GameController): Unit = {
    controller.state = new Combat(controller)
  }
}
