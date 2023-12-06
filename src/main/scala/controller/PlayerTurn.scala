package cl.uchile.dcc.citric
package controller

/** This class represents the Player turn state of the game */
class PlayerTurn extends GameState {
  /** This method should ve invoked when the player who is taking his turn must roll the dice to then be able to move.
   * Also, the state of the game change to moving state.
   *
   * @param controller The game controller
   */
  override def rollDice(controller: GameController): Unit = {
    controller.selected.get.rollDice()
    controller.state = new Moving()
  }
}
