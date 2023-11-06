package cl.uchile.dcc.citric
package model.units

/** interface from which all wild units will extend
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
trait WildUnit extends Units {
  /** The own stars that each wild unit will give to a player when he beat him  */
  protected val ownStars: Int

  /** This method performs the effects that must happen when a wild unit beats an enemy player
   *
   * This might be invoked everytime a wild unit beats an enemy (PlayerCharacter type), that is, when that enemy
   * reaches 0 hp
   *
   * @param enemy The enemy PlayerCharacter that is beaten by the wild unit
   */
  def beatEnemy(enemy: PlayerCharacter): Unit

  /** Returns the amount of the ownStars that each wild unit has */
  def getOwnStars: Int
}
