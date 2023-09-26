package cl.uchile.dcc.citric
package model.Units

/** Represents the things in common between the two types of units
 *
 * This abstracts class has all the variables and methods that share the two types
 * of units (character and wild unit)
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
abstract class AbstractUnits extends Units {

  /** This variable represents the stars that the unit has */
  protected var stars: Int = 0

  /** Returns the stars that the unit has */
  def getStars: Int = {
    stars
  }

  /** Returns the health points that the unit has */
  def getHp: Int = {
    hp
  }

  def getMaxHp: Int = {
    maxHp
  }

  /** This method updates the health points that the unit has
   *
   * This might be invoked when the health points of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit health points
   */
  def updateHp(amount: Int): Unit = {
    hp += amount
  }

  /** This method updates the stars that the unit has
   *
   * This might be invoked when the stars of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit stars
   */
  def updateStars(amount: Int): Unit = {
    stars += amount
  }

}
