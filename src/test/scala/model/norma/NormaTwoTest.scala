package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import scala.util.Random

class NormaTwoTest extends munit.FunSuite {
  val winsCondition = 3
  val starsCondition = 30

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val randomNumberGenerator = new Random(11)

  private var norma: NormaTwo = _
  private var character: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    norma = new NormaTwo()

    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )

    character.norma = norma
  }

  test("A NormaTwo should have correctly set their attributes") {
    //assertEquals(norma.normaLevel, 1)
    assertEquals(norma.getNormaType, "wins")
    assertEquals(norma.winsCondition, winsCondition)
    assertEquals(norma.starsCondition, starsCondition)
  }

  test("A NormaTwo should get his norma type") {
    assertEquals(norma.getNormaType, "wins")
  }

  test("A NormaTwo should set his norma type") {
    assertEquals(norma.getNormaType, "wins")
    norma.setNormaType("stars")
    assertEquals(norma.getNormaType, "stars")
  }

  test("A NormaTwo should can do norma check to a player character") {
    character.setWins(winsCondition)
    assertEquals(norma.normaCheck(character), true)
  }

  test("A NormaTwo should can do norma clear to a player character") {
    character.setWins(winsCondition)
    assertEquals(norma.normaCheck(character), true)
    norma.normaClear(character)
    assert(character.norma.isInstanceOf[NormaThree])
  }
}

