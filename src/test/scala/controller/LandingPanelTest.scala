package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.model.panels.{BonusPanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter
import org.junit.Assert

import scala.collection.mutable.ArrayBuffer

class LandingPanelTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.state = new LandingPanel()
  }

  test("A LandingPanel state can't move to any state except Chapter state") {
    Assert.assertThrows(classOf[AssertionError], () => controller.outOfMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.stopMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.choosePath())
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.endCombat())
    Assert.assertThrows(classOf[AssertionError], () => controller.rollDice())
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
    controller.doEffect()
    assert(controller.state.isInstanceOf[Chapter])
  }

  test("A LandingPanel state should activate the panel effect and change to Chapter state") {
    controller.state = new PreGame
    controller.startGame()
    val playerTest: PlayerCharacter = controller.getPlayers(0)
    assert(playerTest.getStars == 0)
    controller.state = new LandingPanel
    val p: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](playerTest)
    controller.panels = ArrayBuffer[Panel](new BonusPanel(p, ArrayBuffer.empty[Panel]))
    controller.turnPlayer += 1 // simulamos efecto que se hace al pasar por el estado PlayerTurn y asi poder saltar directamente al estado que nos interesa testear
    controller.doEffect()
    assert(playerTest.getStars > 0)
  }

}
