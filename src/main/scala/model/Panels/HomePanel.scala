package cl.uchile.dcc.citric
package model.Panels

import model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** A class that represent a home panel
 *
 * A home panel is a type of panel that houses a player when the game stars. It can activate his effect when the player
 * that houses on this panel passes by this panel and choose to stop here or when a player that doesn't houses on
 * this panel falls exactly on this panel.
 *
 * @param characters The array with the characters on the panel
 * @param nextPanels The array with the next panels to the panel
 *
 * @constructor Creates a new bonus panel with the specified characters on it
 * and the specified next panels to it
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class HomePanel(characters: ArrayBuffer[PlayerCharacter],
                nextPanels: ArrayBuffer[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Actives the panel effect, so gives one extra health point to the player and then makes a norma clear
   *
   * This panel will add one health point to the player every time he falls here. Also, it makes a norma clear
   * if the player fulfill the condition to approve the norma check
   *
   * @param player The player to apply the panel effect
   * */
  //override def activeEffect(player: PlayerCharacter): Unit = {
    // Add one health point to the player
    //player.updateHp(1)
    /**  The rest of the effect will be implemented later... */
  //}

}
