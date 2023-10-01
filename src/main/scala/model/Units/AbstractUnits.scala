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

  /** Sets the stars of an unit */
  def setStars(amount: Int): Unit = {
    stars = math.max(amount, 0)
  }

  /** This method updates the stars that the unit has
   *
   * This might be invoked when the stars of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit stars
   */
  def updateStars(amount: Int): Unit = {
    setStars(getStars + amount)
  }

  /** Returns the max hp of an unit (the initial hp) */
  def getMaxHp: Int = {
    maxHp
  }

  /** Returns the health points that the unit has */
  def getHp: Int = {
    hp
  }

  /** Sets the hp of an unit */
  def setHp(amount: Int): Unit = {
    hp = math.max(amount, 0)
  }

  /** This method updates the health points that the unit has
   *
   * This might be invoked when the health points of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit health points
   */
  def updateHp(amount: Int): Unit = {
    setHp(getHp + amount)
  }

  /** Returns the attack of the unit */
  def getAttack: Int = {
    attack
  }

  /** Returns the defense of the unit */
  def getDefense: Int = {
    defense
  }

  /** Returns the evasion of the unit */
  def getEvasion:Int = {
    evasion
  }

}
