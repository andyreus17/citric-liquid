package cl.uchile.dcc.citric
package observer

import controller.GameController

import cl.uchile.dcc.citric.model.norma.NormaFive

class GameControllerTest extends munit.FunSuite {

  private var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
    controller.startGame()
  }

  test("The controller as observer should be able to receive notification when a player reaches norma six") {
    val playerTest = controller.getPlayers(3)
    playerTest.norma = new NormaFive
    playerTest.setWins(14)
    playerTest.normaClear()
    assert(playerTest.getNormaLevel == 6)
    assert(controller.finished)
  }
}
