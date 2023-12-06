package cl.uchile.dcc.citric
package controller

/** A class that represents a state Chapter of the game */
class Chapter extends GameState {
  /** This method should ve invoked when a new chapter has begin
   *
   * Updates de stars to all the players and the state stay in Chapter state
   *
   * @param controller The game controller
   * */
  override def newChapter(controller: GameController): Unit = {
    val players = controller.getPlayers
    for (i <- players) {
      i.updateStars(controller.chapter / 5 + 1)
    }
    controller.chapter += 1
    controller.state = new Chapter()
  }

  /** This method should ve invoked when a player reaches a norma six
   *
   * When the game is finished, the game can change to EndGame state, since some player reaches rorma six
   *
   * @param controller The game controller
   * */
  override def normaSixReached(controller: GameController): Unit = {
    controller.state = new EndGame()
  }

  /** This method should ve invoked when a player is in ko state
   *
   * if the player is in KO, the game state changes to Recovery state
   *
   * @param controller The game controller
   * */
  override def isKo(controller: GameController): Unit = {
    controller.state = new Recovery()
  }

  /** This method should ve invoked when a player needs to have a new turn
   *
   * Selects the new player that is playing his turn and the state change to player turn state
   *
   * @param controller The game controller
   * */
  override def playTurn(controller: GameController): Unit = {
    controller.selected = Some(controller.getPlayers(controller.turnPlayer%4))
    controller._selected = controller.selected
    controller.turnPlayer += 1
    controller.state = new PlayerTurn()
  }
}
