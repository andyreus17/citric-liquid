package cl.uchile.dcc.citric
package model.PanelTest

import model.Panels.{HomePanel, Panel}
import model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends munit.FunSuite {
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1

  private var randomNumberGenerator: Random = _
  private val playerToAdd = new PlayerCharacter("player2", maxHp, attack, defense, evasion, randomNumberGenerator)
  private val nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]

  private var characters: ArrayBuffer[PlayerCharacter] = _
  private var player: PlayerCharacter = _
  private var homePanel: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    homePanel = new HomePanel(characters, nextPanels)
  }

  test("A home panel should have correctly set their attributes") {
    assertEquals(homePanel.characters, characters)
    assertEquals(homePanel.nextPanels, nextPanels)
  }

  test("A home panel should be able to add a character") {
    assertEquals(homePanel.characters, ArrayBuffer(player))
    homePanel.addCharacter(playerToAdd)
    assertEquals(homePanel.characters, ArrayBuffer(player, playerToAdd))
  }

  test("A home panel should be able to remove a character") {
    assertEquals(homePanel.characters, ArrayBuffer(player))
    homePanel.removeCharacter(player)
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A home panel should be able to get his attributes") {
    assertEquals(homePanel.getCharacters, homePanel.characters)
    assertEquals(homePanel.getNextPanels, homePanel.nextPanels)
  }

}
