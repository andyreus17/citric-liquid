package cl.uchile.dcc.citric
package model.panel

import model.panels.{HomePanel, Panel}
import model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends munit.FunSuite {
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
  private var panelToAdd: HomePanel = _

  private var player: PlayerCharacter = _
  private var homePanel: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    initialNextPanels = ArrayBuffer.empty[Panel]
    initialCharacters = ArrayBuffer.empty[PlayerCharacter]
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    nextPanels = ArrayBuffer.empty[Panel]
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    homePanel = new HomePanel(characters, nextPanels)
    panelToAdd = new HomePanel(initialCharacters, initialNextPanels)
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

  test("A home panel should be able to add a next panel to it") {
    assertEquals(homePanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    homePanel.addPanel(panelToAdd)
    assertEquals(homePanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(homePanel: Panel))
  }

  test("A home panel should be able to remove a next panel to it") {
    assertEquals(homePanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    homePanel.addPanel(panelToAdd)
    assertEquals(homePanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(homePanel: Panel))
    homePanel.removePanel(panelToAdd)
    assertEquals(homePanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("A home panel should be able to get his attributes") {
    assertEquals(homePanel.getCharacters, homePanel.characters)
    assertEquals(homePanel.getNextPanels, homePanel.nextPanels)
  }

}
