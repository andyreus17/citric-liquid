package cl.uchile.dcc.citric
package model.units

abstract class AbstractWildUnit extends AbstractUnits with WildUnit {
  /** The maximum health points a unit can have. It represents the unit's endurance. */
  protected val maxHp: Int = 3

  /** This variable represents the current health points of the unit */
  protected var hp: Int = maxHp

  override def beatEnemy(enemy: PlayerCharacter): Unit = {
    enemy.beatenByWildUnit(this)
  }

  override def beatenByPlayer(player: PlayerCharacter): Unit = {
    player.updateStars(this.ownStars + this.stars)
    this.setStars(0)
    player.updateWins(1)
  }

  override def getOwnStars: Int = ownStars
}
