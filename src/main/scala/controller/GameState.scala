package cl.uchile.dcc.citric
package controller

class GameState {

  private def error() = throw new AssertionError("wrong state")

  def startGame(controller: GameController): Unit = error()

  def newChapter(controller: GameController): Unit = error()

  def normaSixReached(controller: GameController): Unit = error()

  def isKo(controller: GameController): Unit = error()

  def sufficientRoll(controller: GameController): Unit = error()

  def insufficientRoll(controller: GameController): Unit = error()

  def playTurn(controller: GameController): Unit = error()

  def rollDice(controller: GameController): Unit = error()

  def choosePath(controller: GameController): Unit = error()

  def stopMovement(controller: GameController): Unit = error()

  def outOfMovement(controller: GameController): Unit = error()

  def attack(controller: GameController): Unit = error()

  def evade(controller: GameController): Unit = error()

  def defend(controller: GameController): Unit = error()

  def endCombat(controller: GameController): Unit = error()

  def doEffect(controller: GameController): Unit = error()
}
