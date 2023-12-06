package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.model.panels.{BonusPanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter
import org.junit.Assert

import scala.collection.mutable.ArrayBuffer

class CombatTest extends munit.FunSuite {
  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.startGame()
    controller.state = new Combat
  }

  test("A Combat state can't change to any of this states") {
    Assert.assertThrows(classOf[AssertionError], () => controller.outOfMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.stopMovement())
    Assert.assertThrows(classOf[AssertionError], () => controller.choosePath())
    Assert.assertThrows(classOf[AssertionError], () => controller.startGame())
    Assert.assertThrows(classOf[AssertionError], () => controller.doEffect())
    Assert.assertThrows(classOf[AssertionError], () => controller.rollDice())
    Assert.assertThrows(classOf[AssertionError], () => controller.playTurn())
    Assert.assertThrows(classOf[AssertionError], () => controller.sufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.insufficientRoll())
    Assert.assertThrows(classOf[AssertionError], () => controller.isKo())
    Assert.assertThrows(classOf[AssertionError], () => controller.evade())
    Assert.assertThrows(classOf[AssertionError], () => controller.defend())
    Assert.assertThrows(classOf[AssertionError], () => controller.newChapter())
    Assert.assertThrows(classOf[AssertionError], () => controller.normaSixReached())
    Assert.assertThrows(classOf[AssertionError], () => controller.reboot())
  }

  test("A Combat state can make a player attack and change to Wait state") {
    val playerTest: PlayerCharacter = controller.getPlayers(0) // escogemos un player para testear
    val playerTest2: PlayerCharacter = controller.getPlayers(1) // este será el otro jugador que estará en el mismo panel que playerTest
    controller.selected = Some(playerTest)
    val p: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](playerTest, playerTest2) // ahora hay dos jugadores en un mismo panel y los podemos hacer pelear
    controller.panels = ArrayBuffer[Panel](new BonusPanel(p, ArrayBuffer.empty[Panel]))
    controller.attack()
    assert(controller.target.isDefined) // el target está definido
    assert(controller.state.isInstanceOf[Wait]) // se cambio de estado a Wait
  }

}
