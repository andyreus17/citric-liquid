package cl.uchile.dcc.citric
package model.panels

import model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** An abstract class that has the common methods that panels have
 *
 * This abstract class implements the common methods that all panel types have.
 *
 * @param characters An array with the characters on the panel
 * @param nextPanels An array with the next panels to this panel
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
abstract class AbstractPanel(val characters: ArrayBuffer[PlayerCharacter],
                             val nextPanels: ArrayBuffer[Panel]) extends Panel {

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   */
  override def addCharacter(player: PlayerCharacter): Unit = {
    characters.append(player)
  }

  /** Removes the character from the list of characters currently on this panel.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param player The player character to remove from this panel.
   */
  override def removeCharacter(player: PlayerCharacter): Unit = {
    characters.remove(characters.indexOf(player))
  }

  override def addPanel(panel: Panel): Unit = {
    nextPanels.append(panel)
    panel.nextPanels.append(this)
  }

  override def removePanel(panel: Panel): Unit = {
    nextPanels.remove(nextPanels.indexOf(panel))
    panel.nextPanels.remove(panel.nextPanels.indexOf(this))
  }

  /** Returns an array with the player characters on the panel
   *
   * @return the player characters on the panel
   */
  override def getCharacters: ArrayBuffer[PlayerCharacter] = {
    characters
  }

  /** Return an array with the next panels that are connected with this one
   *
   * @return the panels directly connected with this panel
   */
  override def getNextPanels: ArrayBuffer[Panel] = {
    nextPanels
  }
}
