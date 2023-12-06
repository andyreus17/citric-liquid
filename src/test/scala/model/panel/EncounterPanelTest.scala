package cl.uchile.dcc.citric
package model.panel

import model.panels.{EncounterPanel, Panel}
import model.units.{Chicken, PlayerCharacter, RoboBall, Seagull}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class EncounterPanelTest extends munit.FunSuite {
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
  private var panelToAdd: EncounterPanel = _

  private var player: PlayerCharacter = _
  private var encounterPanel: EncounterPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    initialNextPanels = ArrayBuffer.empty[Panel]
    initialCharacters = ArrayBuffer.empty[PlayerCharacter]
    player = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator)
    nextPanels = ArrayBuffer.empty[Panel]
    characters = ArrayBuffer.empty[PlayerCharacter]
    characters += player
    encounterPanel = new EncounterPanel(characters, nextPanels)
    panelToAdd = new EncounterPanel(initialCharacters, initialNextPanels)
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

  test("A encounter panel should be able to add a next panel to it") {
    assertEquals(encounterPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    encounterPanel.addPanel(panelToAdd)
    assertEquals(encounterPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(encounterPanel: Panel))
  }

  test("A encounter panel should be able to remove a next panel to it") {
    assertEquals(encounterPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
    encounterPanel.addPanel(panelToAdd)
    assertEquals(encounterPanel.nextPanels, ArrayBuffer(panelToAdd: Panel))
    assertEquals(panelToAdd.nextPanels, ArrayBuffer(encounterPanel: Panel))
    encounterPanel.removePanel(panelToAdd)
    assertEquals(encounterPanel.nextPanels, ArrayBuffer.empty[Panel])
    assertEquals(panelToAdd.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("An encounter panel should be able to get his attributes") {
    assertEquals(encounterPanel.getCharacters, encounterPanel.characters)
    assertEquals(encounterPanel.getNextPanels, encounterPanel.nextPanels)
  }

  test("An encounter panel should can create a random wild unit") {
    val randomUnit1 = encounterPanel.createRandomUnit()
    val randomUnit2 = encounterPanel.createRandomUnit()
    val randomUnit3 = encounterPanel.createRandomUnit()
    val randomUnit4 = encounterPanel.createRandomUnit()
    val randomUnit5 = encounterPanel.createRandomUnit()
    val randomUnit6 = encounterPanel.createRandomUnit()
    assert(randomUnit1.isInstanceOf[Chicken] || randomUnit1.isInstanceOf[Seagull] || randomUnit1.isInstanceOf[RoboBall])
    assert(randomUnit2.isInstanceOf[Chicken] || randomUnit2.isInstanceOf[Seagull] || randomUnit2.isInstanceOf[RoboBall])
    assert(randomUnit3.isInstanceOf[Chicken] || randomUnit3.isInstanceOf[Seagull] || randomUnit3.isInstanceOf[RoboBall])
    assert(randomUnit4.isInstanceOf[Chicken] || randomUnit4.isInstanceOf[Seagull] || randomUnit4.isInstanceOf[RoboBall])
    assert(randomUnit5.isInstanceOf[Chicken] || randomUnit5.isInstanceOf[Seagull] || randomUnit5.isInstanceOf[RoboBall])
    assert(randomUnit6.isInstanceOf[Chicken] || randomUnit6.isInstanceOf[Seagull] || randomUnit6.isInstanceOf[RoboBall])
  }

  test("An encounter panel should be able to do his effect") {
    encounterPanel.apply(encounterPanel.getCharacters(0))
    assert(encounterPanel.randomUnit.getHp < encounterPanel.randomUnit.getMaxHp)
  }

}
