package cl.uchile.dcc.citric
package model.Units

/** Represents any unit of the game
 *
 * A unit can be of type wild unit or of type character
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
trait Units {

  /** The maximum health points a unit can have. It represents the unit's endurance. */
  protected val maxHp: Int

  /** This variable represents the current health points of the unit */
  protected var hp: Int

  /** The unit's capability to deal damage to opponents. */
  val attack: Int

  /** The unit's capability to resist or mitigate damage from opponents. */
  val defense: Int

  /** The unit's skill to completely avoid certain attacks. */
  val evasion: Int

  /** This variable represents the stars that the unit has */
  protected var stars: Int

  /** Returns the stars that the unit has */
  def getStars: Int

  /** Returns the health points that the unit has */
  def getHp: Int

  /** This method updates the stars that the unit has
   *
   * This might be invoked when the stars of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit stars
   */
  def updateStars(amount: Int): Unit

  /** This method updates the health points that the unit has
   *
   * This might be invoked when the health points of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit health points
   */
  def updateHp(amount:Int): Unit

}
