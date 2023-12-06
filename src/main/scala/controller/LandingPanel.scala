package cl.uchile.dcc.citric
package controller

/** This class represents the landing panel state of the game */
class LandingPanel extends GameState {

  /** This method should ve invoked when a player finish his movements and activate the effect of the panel where he is.
   * Also, the state change to Chapter state
   *
   * @param controller The game controller
   * */
  override def doEffect(controller: GameController): Unit = {
    for(panel <- controller.panels) {
      if (panel.getCharacters.contains(controller.getPlayers((controller.turnPlayer-1)%4))) {
        panel(controller.getPlayers((controller.turnPlayer-1)%4))
      }
    }
    controller.state = new Chapter()
  }
}
