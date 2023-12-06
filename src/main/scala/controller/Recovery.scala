package cl.uchile.dcc.citric
package controller

/** This class represents the recovery state of the game. It is when a player in in ko state */
class Recovery extends GameState {

  /** This method should ve invoked when a player obtains a sufficient number in the roll dice, so the player
   * leaves the KO state and is able to play the turn. ALso, the sate change to PlayerTurn state
   *
   * @param controller The game controller
   * */
  override def insufficientRoll(controller: GameController): Unit = {
    controller.state = new Chapter()
  }

  /** This method should ve invoked when a player doesn't obtains a sufficient number in the roll dice, so the player
   * remains in KO state. ALso, the sate change to Chapter state
   *
   * @param controller The game controller
   * */
  override def sufficientRoll(controller: GameController): Unit = {
    controller.state = new PlayerTurn()
  }
}
