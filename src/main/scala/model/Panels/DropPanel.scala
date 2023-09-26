package cl.uchile.dcc.citric
package model.Panels

import model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** A class that represent a drop panel
 *
 * A drop panel is a type of panel that subtracts stars from the characters when
 * they land on it
 *
 * @param characters The array with the characters on the panel
 * @param nextPanels The array with the next panels to the panel
 *
 * @constructor Creates a new drop panel with the specified characters on it
 * and the specified next panels to it
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class DropPanel(characters: ArrayBuffer[PlayerCharacter],
                nextPanels: ArrayBuffer[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Actives the panel effect, so subtracts an amount of (roll ⋅ Norma) stars from the player,
   * with 'roll' the amount obtained by the player when rolling the dice
   *
   * This might be invoked when a player lands on this panel
   *
   * @param player The player to subtract the stars
   */
  def activeEffect(player: PlayerCharacter): Unit = {

    // The number obtained by the player when rolls the dice of 6 faces
    val roll = player.rollDice()
    player.updateStars(-roll * player.getNormaLevel)
  }
}
