package cl.uchile.dcc.citric
package controller

import org.junit.Assert

class ChapterTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.startGame()
    controller.state = new Chapter()
  }

  test("A Chapter state can't change to any of this states") {
    Assert.assertThrows(classOf[AssertionError], () => controller.outOfMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.stopMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.choosePath())
    Assert.assertThrows(classOf[AssertionError], () => controller.rollDice())
    Assert.assertThrows(classOf[AssertionError], () => controller.endCombat())
    Assert.assertThrows(classOf[AssertionError], () => controller.doEffect())
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.sufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.insufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.evade())
    Assert.assertThrows(classOf[AssertionError], () => controller.defend())
    Assert.assertThrows(classOf[AssertionError], () => controller.attack())
    Assert.assertThrows(classOf[AssertionError], () => controller.reboot())
  }

  test("A Chapter state can change to Chapter state again and the players can receive chapter stars") {
    controller.newChapter()
    for(i <- controller.getPlayers) {
      assert(i.getStars == 1)
    }
    assert(controller.state.isInstanceOf[Chapter])
  }

  test("A Chapter state can change to PlayerTurn state and define the player turn") {
    controller.playTurn()
    assert(controller.selected.isDefined)
    assert(controller.turnPlayer > 0)
  }

  test("A Chapter state can change to Recovery state") {
    controller.isKo()
    assert(controller.state.isInstanceOf[Recovery])
  }

  test("A Chapter state can change to EndGame state") {
    controller.normaSixReached()
    assert(controller.state.isInstanceOf[EndGame])
  }
}
