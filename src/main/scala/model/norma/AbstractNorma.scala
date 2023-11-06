package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

abstract class AbstractNorma extends Norma {

  override def getNormaType: String = normaType

  override def setNormaType(normaType: String): Unit = {
    if (normaType == "wins" || normaType == "stars") {
      this.normaType = normaType
    }
  }

  override def normaCheck(player: PlayerCharacter): Boolean = {
    val condition = if (this.getNormaType == "stars") this.starsCondition else this.winsCondition
    // The value that represents the stars or the wins of the player according to his norma type
    val playerValue = if (this.getNormaType == "stars") player.getStars else player.getWins
    // If the player value is >= than the condition to norma level up, it will return true, false otherwise
    playerValue >= condition
  }

}
