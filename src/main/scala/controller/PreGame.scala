package cl.uchile.dcc.citric
package controller

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.model.panels.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel, Panel}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** This class represents the PreGame state, when the game is about to start */
class PreGame extends GameState {
  /** This method should ve invoked when the game starts.
   *
   * The players are created and saved on the game controller, also the panels are created, they are joined as
   * appropriate and the board is formed, then it is set on the game controller.
   * Also, the state of the game change to Chapter state.
   *
   * @param controller The game controller
   * */
  override def startGame(controller: GameController): Unit = {
    // agregamos los jugadores al game controller
    val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
    for (i <- 1 to 4) {
      val aPlayer: PlayerCharacter = new PlayerCharacter(s"Player$i", 10, 5, 5, 5, new Random())
      aPlayer.registerObserver(controller)
      players += aPlayer
    }
    controller.setPlayers(players)

    // y ahora creamos y agregamos los paneles al game controller (tablero)
    val h0: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](controller.getPlayers(0)), ArrayBuffer.empty[Panel])
    val h1: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](controller.getPlayers(1)), ArrayBuffer.empty[Panel])
    val h2: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](controller.getPlayers(2)), ArrayBuffer.empty[Panel])
    val h3: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](controller.getPlayers(3)), ArrayBuffer.empty[Panel])
    val n0: Panel = new NeutralPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val n1: Panel = new NeutralPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val n2: Panel = new NeutralPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val n3: Panel = new NeutralPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val d0: Panel = new DropPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val d1: Panel = new DropPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val b0: Panel = new BonusPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val b1: Panel = new BonusPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    val e0: Panel = new EncounterPanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel])
    // conectamos los paneles correspondientes
    h0.addPanel(n0)
    h0.addPanel(d0)
    h1.addPanel(d0)
    h1.addPanel(n1)
    b0.addPanel(n0)
    b0.addPanel(e0)
    b0.addPanel(n3)
    b1.addPanel(n1)
    b1.addPanel(e0)
    b1.addPanel(n2)
    h2.addPanel(n2)
    h2.addPanel(d1)
    h3.addPanel(n3)
    h3.addPanel(d1)
    // agregamos los paneles al arraybuffer
    val panel: ArrayBuffer[Panel] = ArrayBuffer[Panel](h0,h1,h2,h3,n0,n1,n2,n3,d0,d1,b0,b1,e0)
    // seteamos los paneles al game controller
    controller.setPanels(panel)
    // cambiamos de estado
    controller.state = new Chapter()
  }
}
