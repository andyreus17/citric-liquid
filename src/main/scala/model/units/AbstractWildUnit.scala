package cl.uchile.dcc.citric
package model.units

abstract class AbstractWildUnit extends AbstractUnits with WildUnit {
  /** The maximum health points a unit can have. It represents the unit's endurance. */
  protected val maxHp: Int = 3

  /** This variable represents the current health points of the unit */
  var hp: Int = maxHp

  override def getOwnStars: Int = ownStars

  override def beatEnemy(enemy: Units): Unit = {
    enemy.beatenByWildUnit(this)
  }

  override def beatenByPlayer(player: PlayerCharacter): Unit = {
    player.updateStars(this.getOwnStars + this.getStars)
    this.setStars(0)
    player.updateWins(1)
  }

  override def beatenByWildUnit(wild: WildUnit): Unit = {
    // hasta el momento no estoy seguro si dos wild unit pueden pelear y que pasaría
  }

}
