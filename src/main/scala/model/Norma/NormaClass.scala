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
class NormaClass extends Norma {

  /** The norma level
   *
   * This variable specifies the norma level that the player has. It will be raise when de player fulfill a condition
   */
  var normaLevel: Int = 1

  /** The type of norma condition
   *
   * This variable specifies the type of condition that the player will be reach to raise his norma level
   */
  var normaType: String = "stars"

  /** Sets the new type of the norma
   *
   * Sets the type of the norma (stars or wins) to make norma clear after
   *
   * @param normaType The new norma type
   */
  override def setNormaType(normaType: String): Unit = {
    if (normaType == "stars" || normaType == "wins") {
      this.normaType = normaType
    }
    else {
      // tira error por parametro inadecuado, lo implementaré cuando sepa como hacer eso
    }
  }

  /** Returns the norma type of the norma
   *
   * @return The type of the norma (stars or wins)
   */
  override def getNormaType: String = {
    normaType
  }

  /** The condition to norma level up due to the stars obtained by the player
   *
   * This map has the level as the key and the condition to go up to that level due to the player stars as a value.
   */
  val starsCondition: Map[Int, Int] = Map(2 -> 10, 3 -> 30, 4 -> 70, 5 -> 120, 6 -> 200)

  /** The condition to norma level up due to the wins obtained by the player
   *
   * This map has the level as the key and the condition to go up to that level due to the player wins as a value
   */
  val winsCondition: Map[Int, Int] = Map(2 -> 1, 3 -> 3, 4 -> 6, 5 -> 10, 6 -> 14)

  /** Return a boolean, that represents if the player fulfill the condition to norma level up
   *
   * This method checks if the player met the condition to norma level up, according to the norma type
   * that he has previously chosen
   *
   * @param player The player to check if he fulfill the condition to norma level up
   * @return true if the player fulfill the condition, otherwise false
   */
  def normaCheck(player: PlayerCharacter): Boolean = {
    // The map conditions to make norma check according to his norma type
    val condition = if (normaType == "stars") starsCondition else winsCondition
    // The value that represents the stars or the wins of the player according to his norma type
    val playerValue = if (normaType == "stars") player.getStars else player.getWins
    // If the player value is >= than the condition to norma level up, it will return true, false otherwise
    playerValue >= condition(normaLevel + 1)
  }

  /** Norma level up the player if he meets the norma check
   *
   * This method makes norma level up to the player if he fulfill the condition to raise his norma level
   * on the norma check. If the player makes norma level up, must also choose his new norma type
   *
   * @param player The player to norma level up if he fulfills his norma check
   */
  def normaClear(player: PlayerCharacter): Unit = {
    // if the player fulfill the condition in norma check, raise norma level
    if (normaCheck(player: PlayerCharacter)) {
      normaLevel += 1
    }
  }
}