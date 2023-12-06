package cl.uchile.dcc.citric
package controller

import org.junit.Assert

class EndGameTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.state = new EndGame()
  }

  test("A EndGame state can only change to PreGame state") {
    Assert.assertThrows(classOf[AssertionError], () => controller.outOfMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.stopMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.choosePath())
    Assert.assertThrows(classOf[AssertionError], () => controller.rollDice())
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
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    controller.reboot()
    assert(controller.state.isInstanceOf[PreGame])
  }

}
