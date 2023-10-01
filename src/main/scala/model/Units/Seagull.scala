package cl.uchile.dcc.citric
package model.Units

/** A class that represents a Seagull wild unit
 *
 * By default, this type of wild unit has (maxHp, ATK, DEF, EVA) = (3, 1, -1, -1)
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class Seagull extends AbstractUnits {
  /** The maximum health points a unit can have. It represents the unit's endurance. */
  protected val maxHp: Int = 3

  /** This variable represents the current health points of the unit */
  protected var hp: Int = maxHp

  /** The unit's capability to deal damage to opponents. */
  protected val attack: Int = 1

  /** The unit's capability to resist or mitigate damage from opponents. */
  protected val defense: Int = -1

  /** The unit's skill to completely avoid certain attacks. */
  protected val evasion: Int = -1

}
