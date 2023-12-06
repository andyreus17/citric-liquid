package cl.uchile.dcc.citric
package model.observer

import model.units.PlayerCharacter

/** Represents a observer that observes something. It is part of the observer design pattern
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 * */
trait Observer {
  /** This method updates the level of the player. If the player reaches norma six, the observer will be informed */
  def updateObserver(player: PlayerCharacter): Unit
}
