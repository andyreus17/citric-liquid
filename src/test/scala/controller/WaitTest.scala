package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.model.units.PlayerCharacter
import org.junit.Assert

class WaitTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.startGame()
    controller.state = new Wait()
  }

  test("A Wait state can't change to any of this states") {
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
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.newChapter())
    Assert.assertThrows(classOf[AssertionError], () => controller.attack())
    Assert.assertThrows(classOf[AssertionError], () => controller.normaSixReached())
    Assert.assertThrows(classOf[AssertionError], () => controller.reboot())
  }

  test("A Wait state can make a playerCharacter evade an attack and change to Combat state") {
    val playerTest: PlayerCharacter = controller.getPlayers(0)
    val playerTest2: PlayerCharacter = controller.getPlayers(1)
    controller._selected = Some(playerTest)
    controller.target = Some(playerTest2)
    controller.evade()
    assert(controller.state.isInstanceOf[Combat]) // cambia a estado Combat
  }

  test("A Wait state can make a playerCharacter defend an attack and change to Combat state") {
    val playerTest: PlayerCharacter = controller.getPlayers(0)
    val playerTest2: PlayerCharacter = controller.getPlayers(1)
    controller._selected = Some(playerTest)
    controller.target = Some(playerTest2)
    assert(playerTest.getHp == playerTest2.getHp)
    controller.defend()
    assert(playerTest.getHp > playerTest2.getHp) // ahora la vida del target es menor a la del atacante
    assert(controller.target.get == playerTest) // ahora los roles de atacante y receptor se intercambian
    assert(controller._selected.get == playerTest2)
    assert(controller.state.isInstanceOf[Combat]) // cambia a estado Combat
  }

}
