package cl.uchile.dcc.citric
package controller

/** This class represents the Wait state of the game. It is, when a player is attacked and needs to evade or defend the attack */
class Wait extends GameState {

  /** This method should ve invoked when the attacked player wants to evade the attack.
   * then the roles of attacker and attacked are exchanged and the state of the game change to Combat state
   *
   * @param controller The game controller
   * */
  override def evade(controller: GameController): Unit = {
    controller.target.get.evade(controller._selected.get) // hacemos que evada el ataque del jugador que ataca
    val temp = controller._selected
    controller._selected = controller.target
    controller.target = temp
    controller.state = new Combat()
  }

  /** This method should ve invoked when the attacked player wants to defend the attack.
   * then the roles of attacker and attacked are exchanged and the state of the game change to Combat state
   *
   * @param controller The game controller
   * */
  override def defend(controller: GameController): Unit = {
    controller.target.get.defend(controller._selected.get) // hacemos que defienda el ataque del jugador que ataca
    val temp = controller._selected
    controller._selected = controller.target
    controller.target = temp
    controller.state = new Combat()
  }
}
