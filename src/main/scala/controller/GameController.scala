package cl.uchile.dcc.citric
package controller

import model.units.{PlayerCharacter, Units, WildUnit}

import cl.uchile.dcc.citric.model.observer.Observer
import cl.uchile.dcc.citric.model.panels.Panel

import scala.collection.mutable.ArrayBuffer

/** This class represents the game controller of the game */
class GameController extends Observer {
  /** This variable represents the state of the game */
  var state: GameState = new PreGame()
  /** This variable saves the players of the current game */
  private var players = ArrayBuffer.empty[PlayerCharacter]
  /** This variable saves the panels that the game has */
  var panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  /** This variable represents the target when a player attacks an enemy (the target) */
  var target: Option[PlayerCharacter] = None
  /** This variable represents the selected player that is playing the turn */
  var selected: Option[PlayerCharacter] = None
  /** This variable represents the player who has to attack in a combat */
  var _selected: Option[PlayerCharacter] = selected
  /** This variable represents the actual chapter of the game */
  var chapter = 0
  /** This variable saves the actual turn player. As each time it increases by one, in the methods it is occupied
   * with module 4 */
  var turnPlayer = 0 // variable que almacena el turno del proximo jugador
  /** This boolean represents if the game has finished. If a player reaches norma six, this variable becomes true */
  var finished: Boolean = false

  /** This method updates the level of the player. If the player reaches norma six, the game controller will be informed */
  override def updateObserver(player: PlayerCharacter): Unit = {
    if(player.getNormaLevel == 6) {
      println(s"¡Felicidades al jugador ${player.getName}! Ha ganado el juego")
      finished = true
    }
  }

  /** Returns the ArrayBuffer with the player of the game */
  def getPlayers: ArrayBuffer[PlayerCharacter] = players

  /** Returns the ArrayBuffer with the Panels of the game */
  def getPanels: ArrayBuffer[Panel] = panels

  /** Sets the player of the game if whe want to change that */
  def setPlayers(arrayPlayers: ArrayBuffer[PlayerCharacter]): Unit = {
    players = arrayPlayers
  }

  /** Sets the panels of the game if whe want to change that */
  def setPanels(arrayPanels: ArrayBuffer[Panel]): Unit = {
    panels = arrayPanels
  }

  /** This method calls the reboot method if the state allows it */
  def reboot(): Unit = {
    state.reboot(this)
  }

  /** This method calls the startGame method if the state allows it */
  def startGame(): Unit = {
    state.startGame(this)
  }

  /** This method calls the newChapter method if the state allows it */
  def newChapter(): Unit = {
    state.newChapter(this)
  }

  /** This method calls the normaSixReached method if the state allows it */
  def normaSixReached(): Unit = {

    state.normaSixReached(this)
  }

  /** This method calls the isKo method if the state allows it */
  def isKo(): Unit = {
    state.isKo(this)
  }

  /** This method calls the sufficientRoll method if the state allows it */
  def sufficientRoll(): Unit = {
    state.sufficientRoll(this)
  }

  /** This method calls the insufficientRoll method if the state allows it */
  def insufficientRoll(): Unit = {
    state.insufficientRoll(this)
  }

  /** This method calls the playTurn method if the state allows it */
  def playTurn(): Unit = {
    state.playTurn(this)
  }

  /** This method calls the rollDice method if the state allows it */
  def rollDice(): Unit = {
    state.rollDice(this)
  }

  /** This method calls the choosePath method if the state allows it */
  def choosePath(): Unit = {
    state.choosePath(this)
  }

  /** This method calls the stopMovement method if the state allows it */
  def stopMovement(): Unit = {
    state.stopMovement(this)
  }

  /** This method calls the outOfMovement method if the state allows it */
  def outOfMovement(): Unit = {
    state.outOfMovement(this)
  }

  /** This method calls the attack method if the state allows it */
  def attack(): Unit = {
    state.attack(this)
  }

  /** This method calls the evade method if the state allows it */
  def evade(): Unit = {
    state.evade(this)
  }

  /** This method calls the defend method if the state allows it */
  def defend(): Unit = {
    state.defend(this)
  }

  /** This method calls the endCombat method if the state allows it */
  def endCombat(): Unit = {
    state.endCombat(this)
  }

  /** This method calls the doEffect method if the state allows it */
  def doEffect(): Unit = {
    state.doEffect(this)
  }
}
