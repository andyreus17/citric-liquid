package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

/** Represents the player's level in the game
 *
 * Each player has a norma, and must meet a certain condition in order to raise their norma, till
 * reach norma 6 to win the game.
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
trait Norma {
  /** The condition to norma level up due to the wins obtained by the player
   *
   * This integer represents the amount of wins that the player needs to reach to be able to level up
   */
  val winsCondition: Int

  /** The condition to norma level up due to the stars obtained by the player
   *
   * This integer represents the amount of stars that the player needs to reach to be able to level up
   */
  val starsCondition: Int

  /** This variable represents the type of the norma to fulfill the condition to raise the norma level */
  protected var normaType: String

  /** Represents the level of the norma (for example, a class NormaTwo() will have normaLevel=2 */
  val normaLevel: Int

  /** Return a boolean, that represents if the player fulfill the condition to norma level up
   *
   * This method checks if the player met the condition to norma level up, according to the norma type
   * that he has previously chosen
   *
   * @param player The player to check if he fulfill the condition to norma level up
   * @return true if the player fulfill the condition, otherwise false
   */
  def normaCheck(player: PlayerCharacter): Boolean

  /** Norma level up the player if he meets the norma check
   *
   * This method makes norma level up to the player if he fulfill the condition to raise his norma level
   * on the norma check. If the player makes norma level up, must also choose his new norma type
   *
   * @param player The player to norma level up if he fulfills his norma check
   */
  def normaClear(player: PlayerCharacter): Unit

  /** Returns the norma type of the norma
   *
   * @return The type of the norma (stars or wins)
   */
  def getNormaType: String

  /** Sets the type of the norma (type stars or wins) */
  def setNormaType(normaType: String): Unit

}
