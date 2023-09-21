package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** A class that represent a neutral panel
 *
 * A neutral panel is a type of panel that doesnt have an effect on characters when
 * they land on it
 *
 * @param characters The array with the characters on the panel
 * @param nextPanels The array with the next panels to the panel
 *
 * @constructor Creates a new neutral panel with the specified characters on it
 * and the specified next panels to it
 *
 * @author [[https://github.com/andyreus17 Andrés Salazar]]
 */
class NeutralPanel(characters: ArrayBuffer[PlayerCharacter],
                   nextPanels: ArrayBuffer[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** It does nothing since it's a neutral panel */
  override def activeEffect(player: PlayerCharacter): Unit = {
  }

}
