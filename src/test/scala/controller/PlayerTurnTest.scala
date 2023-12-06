package cl.uchile.dcc.citric
package controller

import org.junit.Assert

class PlayerTurnTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.state = new PlayerTurn()
  }

  test("A PlayerTurn state can't move to all states except Moving state") {
    Assert.assertThrows(classOf[AssertionError], () => controller.outOfMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.stopMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.choosePath())
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.endCombat())
    Assert.assertThrows(classOf[AssertionError], () => controller.doEffect())
    Assert.assertThrows(classOf[AssertionError], () => controller.playTurn())
    Assert.assertThrows(classOf[AssertionError], () => controller.sufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.insufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.isKo())
    Assert.assertThrows(classOf[AssertionError], () => controller.evade())
    Assert.assertThrows(classOf[AssertionError], () => controller.defend())
    Assert.assertThrows(classOf[AssertionError], () => controller.newChapter())
    Assert.assertThrows(classOf[AssertionError], () => controller.attack())
    Assert.assertThrows(classOf[AssertionError], () => controller.normaSixReached())
    Assert.assertThrows(classOf[AssertionError], () => controller.reboot())
  }

  test("A playerTurn state should be able to make roll dice of a player and move to Moving state") {
    controller.state = new PreGame // nos vamos al estado PreGame para que se creen los jugadores
    controller.startGame() // ejecutamos el metodo que los crea
    controller.state = new PlayerTurn // volvemos a este estado PlayerTurn que es el que queremos probar
    controller.selected = Some(controller.getPlayers(0))
    controller.rollDice() // hacemos el rolldice
    assert(controller.state.isInstanceOf[Moving])
  }

}
