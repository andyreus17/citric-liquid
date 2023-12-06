package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

class NormaSix extends AbstractNorma {
  /** Represents the level of the norma (for example, a class NormaTwo() will have normaLevel=2 */
  val normaLevel: Int = 6

  /** The condition to norma level up due to the wins obtained by the player
   *
   * This integer represents the amount of wins that the player needs to reach to be able to level up
   */
  val winsCondition: Int = 0

  /** The condition to norma level up due to the stars obtained by the player
   *
   * This integer represents the amount of stars that the player needs to reach to be able to level up
   */
  val starsCondition: Int = 0

  /** This variable represents the type of the norma to fulfill the condition to raise the norma level */
  protected var normaType: String = "wins"

  override def normaClear(player: PlayerCharacter): Unit = {
    // if the player fulfill the condition in norma check, raise his norma class
    if (normaCheck(player)) {
      println("Juego terminado")
    }
  }

}
