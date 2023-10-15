package cl.uchile.dcc.citric
package model.panels

import model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** A class that represent a bonus panel
 *
 * A bonus panel is a type of panel that gives stars to the characters when
 * they land on it
 *
 * @param characters The array with the characters on the panel
 * @param nextPanels The array with the next panels to the panel
 *
 * @constructor Creates a new bonus panel with the specified characters on it
 * and the specified next panels to it
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class BonusPanel(characters: ArrayBuffer[PlayerCharacter],
                 nextPanels: ArrayBuffer[Panel]) extends AbstractPanel(characters, nextPanels) {


  /** Actives the panel effect, so gives an amount of (min(roll ⋅ Norma, roll ⋅ 3)) stars to the player,
   * with 'roll' the amount obtained by the player when rolling the dice
   *
   * This might be invoked when a player lands on this panel
   *
   * @param player The player to give the stars
   */
  def apply(player: PlayerCharacter): Unit = {
    // The number obtained by the player when rolls the dice of 6 faces.
    val roll = player.rollDice()
    // The minimum integer between roll ⋅ Norma and roll ⋅ 3
    val minInt = math.min(roll * player.getNormaLevel, roll * 3)
    player.updateStars(minInt)
  }
}