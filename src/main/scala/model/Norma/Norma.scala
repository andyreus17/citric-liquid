package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

/** Represents the player's level in the game
 *
 * Each player has a norma, and must meet a certain condition in order to raise their norma, till
 * reach norma 6 to win the game.
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
trait Norma {

  /** This variable represents the level of the norma
   */
  var normaLevel: Int

  /** This variable represents the type of the norma to fulfill the condition to raise the norma level
   */
  var normaType: String

  /** The map that contains the norma level as key and the condition to norma level up due to
   * the player stars as value
   */
  val starsCondition: Map[Int, Int]

  /** The map that contains the norma level as key and the condition to norma level up due to
   * the player wins as value
   */
  val winsCondition: Map[Int, Int]

  /** Returns true if the player fulfill the condition to norma level up according to the his norma type
   *
   * @param player The player to check if he fulfill the condition to norma level up
   * @return true if the player fulfill the condition, otherwise false
   */
  def normaCheck(player: PlayerCharacter): Boolean

  /** Makes the norma level up to the player if he has fulfilled the condition in norma check
   *
   * @param player The player to norma level up if he fulfill the condition in norma check
   */
  def normaClear(player: PlayerCharacter): Unit

  /** Returns the norma type of the norma
   *
   * @return The type of the norma (stars or wins)
   */
  def getNormaType: String

  /** Sets the new type of the norma
   *
   * Sets the type of the norma (stars or wins) to make norma clear after
   *
   * @param normaType The new norma type
   */
  def setNormaType(normaType: String): Unit

}
