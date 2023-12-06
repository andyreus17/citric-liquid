package cl.uchile.dcc.citric
package model.panels

import model.units.{Chicken, PlayerCharacter, RoboBall, Seagull, Units, WildUnit}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** A class that represent an encounter panel
 *
 * An encounter panel is a type of panel where players enter combat against a random wild unit when they land
 * on this panel
 *
 * @param characters The array with the characters on the panel
 * @param nextPanels The array with the next panels to the panel
 *
 * @constructor Creates a new encounter panel with the specified characters on it
 * and the specified next panels to it
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class EncounterPanel(characters: ArrayBuffer[PlayerCharacter],
                       nextPanels: ArrayBuffer[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Variable where the random unit will be saved
   *
   * */
  var randomUnit: WildUnit = _

  /** Activates the encounter panel effect
   *
   * When a player lands on this type of panel, a new random wild unit is created and enter in combat with the player,
   * also, the stars corresponding to the winner unit must be transferred from the looser unit and the number of wins
   * to the player must be updated in case of the player wins.
   * This panel class will be improved in the future ;)
   *
   * @param player The player that will activate the panel effect since he lands on it
   */
  def apply(player: PlayerCharacter): Unit = {
    randomUnit = createRandomUnit()
    randomUnit.defend(player) // aquí se deberia recibir adicionalmente un input para saber si defenderse o evadir
  }

  /** Creates a new random wil unit
   *
   * @return A new random wild unit (Chicken, RoboBall or Seagull)
   */
  def createRandomUnit(): WildUnit = {
    /** This will be improved in the future, when i have a better vision of how to implement the panel effect */
    val randomNumber = Random.nextInt(3)
    val newUnit = randomNumber match {
      case 0 => new Chicken
      case 1 => new RoboBall
      case 2 => new Seagull
    }
    randomUnit = newUnit
    newUnit
  }

}
