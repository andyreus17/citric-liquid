package cl.uchile.dcc.citric
package model.panels

import model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures.
  *
  * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
  * @author [[https://github.com/andyreus17 Andrés Salazar]]
  */
trait Panel {

  /** Array of the characters currently positioned on this panel.
    *
    * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
    * land on the same space.
    */
  val characters: ArrayBuffer[PlayerCharacter]

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  val nextPanels: ArrayBuffer[Panel]

  /** Adds a character to the list of characters currently on this panel.
    *
    * This might be invoked when a player moves to this panel or starts their turn on it.
    *
    * @param player The player character to add to this panel.
    */
  def addCharacter(player: PlayerCharacter): Unit

  /** Removes the character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit

  /** Adds a panel to the list of next panels of this panel.
   *
   * This might be invoked when a panel will be added to the next panels to this panel
   *
   * @param panel The panel to add to next panels of this panel.
   */
  def addPanel(panel: Panel): Unit

  /** Removes a panel to the list of next panels of this panel.
   *
   * This might be invoked when a panel will be removed of the next panels to this panel
   *
   * @param panel The panel to remove of next panels to this panel.
   */
  def removePanel(panel:Panel): Unit

  /** Returns an array with the player characters on the panel
   *
   * @return the player characters on the panel
   */
  def getCharacters: ArrayBuffer[PlayerCharacter]

  /** Return an array with the next panels that are connected with this one
   *
   * @return the panels directly connected with this panel
   */
  def getNextPanels: ArrayBuffer[Panel]

  /** Activate the panel effect
   *
   * @param player The character to apply the panel effect
   */
  def apply(player: PlayerCharacter): Unit
}
