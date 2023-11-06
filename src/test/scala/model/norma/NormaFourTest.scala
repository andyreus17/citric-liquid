package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import scala.util.Random

class NormaFourTest extends munit.FunSuite {
  val winsCondition = 10
  val starsCondition = 120

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val randomNumberGenerator = new Random(11)

  private var norma: NormaFour = _
  private var character: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    norma = new NormaFour()

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

  test("A NormaFour should have correctly set their attributes") {
    //assertEquals(norma.normaLevel, 1)
    assertEquals(norma.getNormaType, "wins")
    assertEquals(norma.winsCondition, winsCondition)
    assertEquals(norma.starsCondition, starsCondition)
  }

  test("A NormaFour should get his norma type") {
    assertEquals(norma.getNormaType, "wins")
  }

  test("A NormaFour should set his norma type") {
    assertEquals(norma.getNormaType, "wins")
    norma.setNormaType("stars")
    assertEquals(norma.getNormaType, "stars")
  }

  test("A NormaFour should can do norma check to a player character") {
    character.setWins(winsCondition)
    assertEquals(norma.normaCheck(character), true)
  }

  test("A NormaFour should can do norma clear to a player character") {
    character.setWins(winsCondition)
    assertEquals(norma.normaCheck(character), true)
    norma.normaClear(character)
    assert(character.norma.isInstanceOf[NormaFive])
  }
}