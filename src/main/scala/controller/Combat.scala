package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.model.panels.Panel
import cl.uchile.dcc.citric.model.units.{PlayerCharacter, Units}

import scala.collection.mutable.ArrayBuffer

/** A class that represents a state Combat of the game */
class Combat extends GameState {
  /** This method should ve invoked when Player attacks another player
   *
   * This makes that the attacker player selects an target player and the state change to wait state
   *
   * @param controller The game controller
   * */
  override def attack(controller: GameController): Unit = {
    if(controller.target.isEmpty) {
      for(i <- controller.panels) {
        if(i.getCharacters.contains(controller.selected.get)) {
          val enemies: ArrayBuffer[PlayerCharacter] = i.getCharacters
          enemies -= controller.selected.get // quitamos al jugador que va a atacar del array que contiene a los enemigos
          controller.target = Some(enemies(0)) // aquí deberia ir un input para escoger al enemigo a atacar
        }
      }
    }
    controller.state = new Wait
  }

  /** This method should ve invoked when a combat has finished or never started
   *
   * Leave the target variable free and change the state to landing panel state
   *
   * @param controller The game controller
   * */
  override def endCombat(controller: GameController): Unit = {
    if(controller.target.isEmpty || controller._selected.get.getHp == 0) {
        //controller.selected.get.beatEnemy(controller.target.get) // no actualizamos las estrellas y tal pues eso lo hacen los metodos evade y defend
        controller._selected.get.isKO()
        controller.target = None
    }
    controller.state = new LandingPanel()
  }
}
