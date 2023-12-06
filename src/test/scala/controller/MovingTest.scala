package cl.uchile.dcc.citric
package controller

import org.junit.Assert

class MovingTest extends munit.FunSuite {
  private var controller: GameController = _

  private def rebootState(): Unit = {
    controller.state = new Moving()
  }
  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.state = new Moving()
  }

  test("A Moving state can change to Moving state again") {
    Assert.assertThrows(classOf[AssertionError], () => controller.rollDice())
    Assert.assertThrows(classOf[AssertionError], () => controller.endCombat())
    Assert.assertThrows(classOf[AssertionError], () => controller.doEffect())
    Assert.assertThrows(classOf[AssertionError], () => controller.playTurn())
    Assert.assertThrows(classOf[AssertionError], () => controller.sufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.insufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.isKo())
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.newChapter())
    Assert.assertThrows(classOf[AssertionError], () => controller.attack())
    Assert.assertThrows(classOf[AssertionError], () => controller.normaSixReached())
    Assert.assertThrows(classOf[AssertionError], () => controller.reboot())
    Assert.assertThrows(classOf[AssertionError], () => controller.evade())
    Assert.assertThrows(classOf[AssertionError], () => controller.defend())
    controller.choosePath()
    assert(controller.state.isInstanceOf[Moving])
  }

  test("A Moving state can change to Combat state") {
    controller.stopMovement()
    assert(controller.state.isInstanceOf[Combat])
    rebootState()
    controller.outOfMovement()
    assert(controller.state.isInstanceOf[Combat])
  }

}
