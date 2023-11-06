package cl.uchile.dcc.citric
package model.units

/** A class that represents a Chicken wild unit
 *
 * By default, this type of wild unit has (maxHp, ATK, DEF, EVA) = (3, -1, -1, 1)
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class Chicken extends AbstractWildUnit {
  /** The unit's capability to deal damage to opponents. */
  protected val attack: Int = -1

  /** The unit's capability to resist or mitigate damage from opponents. */
  protected val defense: Int = -1

  /** The unit's skill to completely avoid certain attacks. */
  protected val evasion: Int = 1

  /** The stars that this wild unit will give to the player that beat him */
  protected val ownStars = 3

}
