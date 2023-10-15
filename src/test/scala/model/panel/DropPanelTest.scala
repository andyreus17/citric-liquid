package cl.uchile.dcc.citric
package model.panel

import model.units.PlayerCharacter
import model.panels.{DropPanel, Panel}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class DropPanelTest extends munit.FunSuite {
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
  private var panelToAdd: DropPanel = _

  private var player: PlayerCharacter = _
  private var dropPanel: DropPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    initialNextPanels = ArrayBuffer.empty[Panel]
    initialCharacters = ArrayBuffer.empty[PlayerCharacter]
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    nextPanels = ArrayBuffer.empty[Panel]
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    dropPanel = new DropPanel(characters, nextPanels)
    panelToAdd = new DropPanel(initialCharacters, initialNextPanels)
  }

  test("A drop panel should have correctly set their attributes") {
    assertEquals(dropPanel.characters, characters)
    assertEquals(dropPanel.nextPanels, nextPanels)
  }

  test("A drop panel should subtracts stars to a player"){
    player.setStars(15) // supongamos que tiene 15 estrellas
    assertEquals(player.getStars, 15)
    assertEquals(player.getNormaLevel, 1) // player tiene nivel de norma 1 (arbitrario)
    dropPanel.apply(player)
    assert(9 <= player.getStars && player.getStars <= 15)
  }

  test("A drop panel should be able to add a character") {
    assertEquals(dropPanel.characters, ArrayBuffer(player))
    dropPanel.addCharacter(playerToAdd)
    assertEquals(dropPanel.characters, ArrayBuffer(player, playerToAdd))
  }

  test("A drop panel should be able to remove a character") {
    assertEquals(dropPanel.characters, ArrayBuffer(player))
    dropPanel.removeCharacter(player)
    assertEquals(dropPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A drop panel should be able to add a next panel to it") {
    assertEquals(dropPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    dropPanel.addPanel(panelToAdd)
    assertEquals(dropPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(dropPanel: Panel))
  }

  test("A drop panel should be able to remove a next panel to it") {
    assertEquals(dropPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    dropPanel.addPanel(panelToAdd)
    assertEquals(dropPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(dropPanel: Panel))
    dropPanel.removePanel(panelToAdd)
    assertEquals(dropPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("A drop panel should be able to get his attributes") {
    assertEquals(dropPanel.getCharacters, dropPanel.characters)
    assertEquals(dropPanel.getNextPanels, dropPanel.nextPanels)
  }
}
