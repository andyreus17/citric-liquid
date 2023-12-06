package cl.uchile.dcc.citric
package controller

class Moving extends GameState {
  override def choosePath(controller: GameController): Unit = {
    controller.state = new Moving()
  }

  override def outOfMovement(controller: GameController): Unit = {
    controller.state = new Combat()
  }

  override def stopMovement(controller: GameController): Unit = {
    controller.state = new Combat()
  }
}
