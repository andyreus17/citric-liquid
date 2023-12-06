package cl.uchile.dcc.citric
package model.units

import scala.util.Random

/** Represents the things in common between the two types of units
 *
 * This abstracts class has all the variables and methods that share the two types
 * of units (character and wild unit)
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
abstract class AbstractUnits(val randomNumberGenerator: Random = new Random()) extends Units {

  /** This variable represents the stars that the unit has */
  protected var stars: Int = 0

  /** This variable is a boolean than indicates if the unit is KO or not */
  var KO = false

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

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

  /** Puts a unit in KO status
   *
   * When a player has 0 health points, they will enter in a KO state.
   */
  def isKO(): Unit = {
    KO = getHp==0
  }

  /** Returns if the player is in KO state or not */
  def getKO: Boolean = {
    KO
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

  override def attacking(): Int = {
    if (!this.getKO) {
      val roll: Int = this.rollDice()
      val finalAttack: Int = this.getAttack + roll
      finalAttack
    }
    else 0
  }

  override def defend(enemy: Units): Unit = {
    val enemyATK: Int = enemy.attacking()
    val damage: Int = math.max(1, enemyATK - (this.rollDice() + this.getDefense))
    if (!this.getKO) {
      this.updateHp(-damage)
      if (this.getHp == 0) enemy.beatEnemy(this)
    }
  }

  override def evade(enemy: Units): Unit = {
    val enemyATK = enemy.attacking()
    if (!this.getKO) {
      if (this.rollDice() + this.getEvasion <= enemyATK) {
        this.updateHp(-enemyATK)
        if (this.getHp == 0) enemy.beatEnemy(this)
      }
    }
  }

}
