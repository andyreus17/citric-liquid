package cl.uchile.dcc.citric
package model.PanelTest

import model.Panels.{EncounterPanel, Panel}
import model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class EncounterPanelTest extends munit.FunSuite {
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
  private var encounterPanel: EncounterPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    encounterPanel = new EncounterPanel(characters, nextPanels)
  }

  test("An encounter panel should have correctly set their attributes") {
    assertEquals(encounterPanel.characters, characters)
    assertEquals(encounterPanel.nextPanels, nextPanels)
  }

  test("An encounter panel should be able to add a character") {
    assertEquals(encounterPanel.characters, ArrayBuffer(player))
    encounterPanel.addCharacter(playerToAdd)
    assertEquals(encounterPanel.characters, ArrayBuffer(player, playerToAdd))
  }

  test("An encounter panel should be able to remove a character") {
    assertEquals(encounterPanel.characters, ArrayBuffer(player))
    encounterPanel.removeCharacter(player)
    assertEquals(encounterPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("An encounter panel should be able to get his attributes") {
    assertEquals(encounterPanel.getCharacters, encounterPanel.characters)
    assertEquals(encounterPanel.getNextPanels, encounterPanel.nextPanels)
  }

}
