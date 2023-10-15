package cl.uchile.dcc.citric
package model.panel

import model.panels.{NeutralPanel, Panel}
import model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeutralPanelTest extends munit.FunSuite {
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1

  private var randomNumberGenerator: Random = _

  private var initialNextPanels: ArrayBuffer[Panel] = _
  private var initialCharacters: ArrayBuffer[PlayerCharacter] = _
  private var nextPanels: ArrayBuffer[Panel] = _
  private var characters: ArrayBuffer[PlayerCharacter] = _

  private val playerToAdd = new PlayerCharacter("player2", maxHp, attack, defense, evasion, randomNumberGenerator)
  private var panelToAdd: NeutralPanel = _

  private var player: PlayerCharacter = _
  private var neutralPanel: NeutralPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    initialNextPanels = ArrayBuffer.empty[Panel]
    initialCharacters = ArrayBuffer.empty[PlayerCharacter]
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    nextPanels = ArrayBuffer.empty[Panel]
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    neutralPanel = new NeutralPanel(characters, nextPanels)
    panelToAdd = new NeutralPanel(initialCharacters, initialNextPanels)
  }

  test("A neutral panel should have correctly set their attributes") {
    assertEquals(neutralPanel.characters, characters)
    assertEquals(neutralPanel.nextPanels, nextPanels)
  }

  test("A neutral panel should do nothing when activates his effect") {
    neutralPanel.apply(player)
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

  test("A neutral panel should be able to add a next panel to it") {
    assertEquals(neutralPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    neutralPanel.addPanel(panelToAdd)
    assertEquals(neutralPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(neutralPanel: Panel))
  }

  test("A neutral panel should be able to remove a next panel to it") {
    assertEquals(neutralPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    neutralPanel.addPanel(panelToAdd)
    assertEquals(neutralPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(neutralPanel: Panel))
    neutralPanel.removePanel(panelToAdd)
    assertEquals(neutralPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("A neutral panel should be able to get his attributes") {
    assertEquals(neutralPanel.getCharacters, neutralPanel.characters)
    assertEquals(neutralPanel.getNextPanels, neutralPanel.nextPanels)
  }

}
