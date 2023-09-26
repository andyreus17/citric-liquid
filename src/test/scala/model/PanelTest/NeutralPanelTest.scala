package cl.uchile.dcc.citric
package model.PanelTest

import model.Panels.{NeutralPanel, Panel}
import model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeutralPanelTest extends munit.FunSuite {
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
  private var neutralPanel: NeutralPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    neutralPanel = new NeutralPanel(characters, nextPanels)
  }

  test("A neutral panel should have correctly set their attributes") {
    assertEquals(neutralPanel.characters, characters)
    assertEquals(neutralPanel.nextPanels, nextPanels)
  }

  test("A neutral panel should do nothing when activates his effect") {
    neutralPanel.activeEffect(player)
    // hacemos player eq
    assertEquals(player.getStars, 0)
  }

  test("A neutral panel should be able to add a character") {
    assertEquals(neutralPanel.characters, ArrayBuffer(player))
    neutralPanel.addCharacter(playerToAdd)
    assertEquals(neutralPanel.characters, ArrayBuffer(player, playerToAdd))
  }

  test("A neutral panel should be able to remove a character") {
    assertEquals(neutralPanel.characters, ArrayBuffer(player))
    neutralPanel.removeCharacter(player)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A neutral panel should be able to get his attributes") {
    assertEquals(neutralPanel.getCharacters, neutralPanel.characters)
    assertEquals(neutralPanel.getNextPanels, neutralPanel.nextPanels)
  }

}
