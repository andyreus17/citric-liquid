package cl.uchile.dcc.citric
package model.units

/** interface from which all wild units will extend
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
trait WildUnit extends Units {
  /** The own stars that each wild unit will give to a player when he beat him  */
  protected val ownStars: Int

  /** Returns the amount of the ownStars that each wild unit has */
  def getOwnStars: Int
}
