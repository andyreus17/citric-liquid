package cl.uchile.dcc.citric
package controller

class GameController {
  var state: GameState = new PreGame(this)

  def startGame(): Unit = {
    state.startGame(this)
  }

  def newChapter(): Unit = {
    state.newChapter(this)
  }

  def normaSixReached(): Unit = {
    state.normaSixReached(this)
  }

  def isKo(): Unit = {
    state.isKo(this)
  }

  def sufficientRoll(): Unit = {
    state.sufficientRoll(this)
  }

  def insufficientRoll(): Unit = {
    state.insufficientRoll(this)
  }

  def playTurn(): Unit = {
    state.playTurn(this)
  }

  def rollDice(): Unit = {
    state.rollDice(this)
  }

  def choosePath(): Unit = {
    state.choosePath(this)
  }

  def stopMovement(): Unit = {
    state.stopMovement(this)
  }

  def outOfMovement(): Unit = {
    state.outOfMovement(this)
  }

  def attack(): Unit = {
    state.attack(this)
  }

  def evade(): Unit = {
    state.evade(this)
  }

  def defend(): Unit = {
    state.defend(this)
  }

  def endCombat(): Unit = {
    state.endCombat(this)
  }

  def doEffect(): Unit = {
    state.doEffect(this)
  }
}
