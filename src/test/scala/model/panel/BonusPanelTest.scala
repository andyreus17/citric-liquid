package cl.uchile.dcc.citric
package model.panel

import model.units.PlayerCharacter
import model.panels.{BonusPanel, Panel}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class BonusPanelTest extends munit.FunSuite {
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
  private var panelToAdd: BonusPanel = _

  private var player: PlayerCharacter = _
  private var bonusPanel: BonusPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    initialNextPanels = ArrayBuffer.empty[Panel]
    initialCharacters = ArrayBuffer.empty[PlayerCharacter]
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    nextPanels = ArrayBuffer.empty[Panel]
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    bonusPanel = new BonusPanel(characters, nextPanels)
    panelToAdd = new BonusPanel(initialCharacters, initialNextPanels)
  }

  test("A bonus panel should have correctly set their attributes") {
    assertEquals(bonusPanel.characters, characters)
    assertEquals(bonusPanel.nextPanels, nextPanels)
  }

  test("A bonus panel should gives stars to a player"){
    assertEquals(player.getStars, 0)
    assertEquals(player.getNormaLevel, 1)
    bonusPanel.apply(player)
    assert(1 <= player.getStars && player.getStars <= 6)
  }

  test("A bonus panel should be able to add a character") {
    assertEquals(bonusPanel.characters, ArrayBuffer(player))
    bonusPanel.addCharacter(playerToAdd)
    assertEquals(bonusPanel.characters, ArrayBuffer(player, playerToAdd))
  }

  test("A bonus panel should be able to remove a character") {
    assertEquals(bonusPanel.characters, ArrayBuffer(player))
    bonusPanel.removeCharacter(player)
    assertEquals(bonusPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A bonus panel should be able to add a next panel to it") {
    assertEquals(bonusPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    bonusPanel.addPanel(panelToAdd)
    assertEquals(bonusPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(bonusPanel: Panel))
  }

  test("A bonus panel should be able to remove a next panel to it") {
    assertEquals(bonusPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    bonusPanel.addPanel(panelToAdd)
    assertEquals(bonusPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(bonusPanel: Panel))
    bonusPanel.removePanel(panelToAdd)
    assertEquals(bonusPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("A bonus panel should be able to get his attributes") {
    assertEquals(bonusPanel.getCharacters, bonusPanel.characters)
    assertEquals(bonusPanel.getNextPanels, bonusPanel.nextPanels)
  }
}
