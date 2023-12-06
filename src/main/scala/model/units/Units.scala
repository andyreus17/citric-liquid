package cl.uchile.dcc.citric
package model.units

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
  protected val attack: Int

  /** The unit's capability to resist or mitigate damage from opponents. */
  protected val defense: Int

  /** The unit's skill to completely avoid certain attacks. */
  protected val evasion: Int

  /** This variable represents the stars that the unit has */
  protected var stars: Int

  /** Returns the stars that the unit has */
  def getStars: Int

  /** Sets de stars of the unit */
  def setStars(amount: Int): Unit

  /** This method updates the stars that the unit has
   *
   * This might be invoked when the stars of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit stars
   */
  def updateStars(amount: Int): Unit

  /** Returns the health points that the unit has */
  def getHp: Int

  /** This method updates the health points that the unit has
   *
   * This might be invoked when the health points of the unit are added or subtracted
   *
   * @param amount The amount to add or subtract to the unit health points
   */
  def updateHp(amount:Int): Unit

  /** Sets the hp of an unit */
  def setHp(amount: Int): Unit

  /** Returns the Max Hp of the unit */
  def getMaxHp: Int

  /** Returns the Attack of the unit */
  def getAttack: Int

  /** Returns the Defense of the unit */
  def getDefense: Int

  /** Returns the Evasion of the unit */
  def getEvasion: Int

  /** Rolls a dice of 6 faces */
  def rollDice(): Int

  /** Gives the attack to other unit
   *
   * This method returns the amount of attack that the unit will do to an enemy unit
   *
   * @return The attack to the enemy unit
   */
  def attacking(): Int

  /** Defend the enemy attack
   *
   * This method will be activate when de unit attacked choose to defend the enemy unit attack
   *
   * @param enemy The enemy unit that is attacking
   */
  def defend(enemy: Units): Unit

  /** Evade the enemy attack
   *
   * This method will be activate when de unit attacked choose to evade the enemy unit attack
   *
   * @param enemy The enemy unit that is attacking
   */
  def evade(enemy: Units): Unit

  /** This method performs the effects that must happen when a unit beats an enemy unit
   *
   * This might be invoked everytime a unit beats an enemy (Units type), that is, when that enemy
   * reaches 0 hp
   *
   * @param enemy The enemy unit that is beaten by the unit
   */
  def beatEnemy(enemy: Units): Unit

  /** This method updates the stars and wins of a player when he beats a unit
   *
   * This method might be invoked when a player character beats a unit, and his effect is that gives
   * the actual amount of stars and the own stars that each wild unit have (if it is a wild unit) to the winner player.
   * Also, the  winner player's number of wins increases by one in case of beating a wild unit or two in case of beating
   * a player character.
   *
   * @param player The player who has beaten the unit
   */
  def beatenByPlayer(player: PlayerCharacter): Unit

  /** This method updates the stars of the PlayerCharacter and the wild unit when the wild unit beats the PlayerCharacter
   *
   * This method might be invoked when a wild unit beats a PlayerCharacter, and his effect is to transfer
   * the half actual amount of stars from the PlayerCharacter to the winner wild unit.
   *
   * @param wild The wild unit who has beaten the player character
   */
  def beatenByWildUnit(wild: WildUnit): Unit
}
