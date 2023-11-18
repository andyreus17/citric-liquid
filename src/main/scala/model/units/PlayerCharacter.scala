package cl.uchile.dcc.citric
package model.units

import model.norma.{Norma, NormaOne}

import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
 * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
 * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
 * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
 * @author [[https://github.com/Seivier/ Vicente González B.]]
 * @author [[https://github.com/andyreus17/ Andrés Salazar C.]]
 */
class PlayerCharacter(private val name: String,
                      protected val maxHp: Int,
                      protected val attack: Int,
                      protected val defense: Int,
                      protected val evasion: Int,
                      randomNumberGenerator: Random) extends AbstractUnits(randomNumberGenerator) {

  /** This variable represents the current health points of the player */
  protected var hp: Int = maxHp

  /** This variable represents the wins that the player has */
  private var wins: Int = 0

  /** The number that the player must at least equal when rolling a dice to exit of the ko state */
  private var recoveryCounter: Int = 6

  /** The norma that the player will have */
  var norma: Norma = new NormaOne()

  /** Returns the name of the player */
  def getName: String = {
    name
  }

  def getRecoveryCounter: Int = {
    recoveryCounter
  }

  /** Puts the player in KO status and resets the recovery counter
   *
   * When a player has 0 health points, they will enter in a KO state and have their recovery counter at 6.
   */
  override def isKO(): Unit = {
    if (getHp == 0) {
      KO = true
      recoveryCounter = 6
    }
  }

  /** Removes the player from the KO state or otherwise reduces the recovery counter
   *
   * When a player is in KO state, he enters on recovery phase. This method represents that phase and might be invoked
   * every time is the player turn and he is on recovery phase, till exit of the KO state
   *
   * */
  def recovery(): Unit = {
    val roll = rollDice()
    if(roll >= recoveryCounter) KO = false else recoveryCounter -= 1
  }

  /** Returns the wins that the player has */
  def getWins: Int = {
    wins
  }

  /** Sets the wins amount of the player */
  def setWins(amount: Int): Unit = {
    wins = amount
  }

  /** Update the wins that the player has
   *
   * This might be invoked when the wins of the player are added or subtracted
   *
   * @param amount The amount to be added or subtracted to the wins stars
   * @return The number of wins updated
   */
  def updateWins(amount: Int): Unit = {
    setWins(getWins + amount)
  }

  /** Returns the norma level that the player has on his norma*/
  def getNormaLevel: Int = {
    norma.normaLevel
  }

  /** returns the norma type of the norma player */
  def getNormaType: String = {
    norma.getNormaType
  }

  /** Sets the norma's norma type of the player */
  def setNormaType(newNormaType: String): Unit = {
    if (newNormaType == "wins" || newNormaType == "stars") {
      norma.setNormaType(newNormaType)
    }
  }

  /** Makes the norma clear of this player */
  def normaClear(): Unit = {
    norma.normaClear(this)
  }

  /** This method performs the effects that must happen when a Player Character beats an enemy unit
   *
   * This might be invoked everytime a Player Character beats an enemy (Units type), that is, when that enemy
   * reaches 0 hp
   *
   * @param enemy The enemy unit that is beaten by the PlayerCharacter
   */
  def beatEnemy(enemy: Units): Unit = {
    enemy.beatenByPlayer(this)
  }

  override def beatenByPlayer(player: PlayerCharacter): Unit = {
    player.updateWins(2)
    player.updateStars(this.getStars/2)
    this.updateStars(-this.getStars/2)
  }

  def beatenByWildUnit(wild: WildUnit): Unit = {
    wild.updateStars(this.getStars/2)
    this.updateStars(-this.getStars/2)
  }

}