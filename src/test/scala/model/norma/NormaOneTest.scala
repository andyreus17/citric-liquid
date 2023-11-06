package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import scala.util.Random

class NormaOneTest extends munit.FunSuite {

  val winsCondition = 1
  val starsCondition = 10

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val randomNumberGenerator = new Random(11)

  private var norma: NormaOne = _
  private var character: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    norma = new NormaOne()

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

  test("A NormaOne should have correctly set their attributes") {
    //assertEquals(norma.normaLevel, 1)
    assertEquals(norma.getNormaType, "wins")
    assertEquals(norma.winsCondition, winsCondition)
    assertEquals(norma.starsCondition, starsCondition)
  }

  test("A NormaOne should get his norma type") {
    assertEquals(norma.getNormaType, "wins")
  }

  test("A NormaOne should set his norma type") {
    assertEquals(norma.getNormaType, "wins")
    norma.setNormaType("stars")
    assertEquals(norma.getNormaType, "stars")
  }

  test("A NormaOne should can do norma check to a player character") {
    character.setWins(1)
    assertEquals(norma.normaCheck(character), true)
  }

  test("A NormaOne should can do norma clear to a player character") {
    character.setWins(1)
    assertEquals(norma.normaCheck(character), true)
    norma.normaClear(character)
    assert(character.norma.isInstanceOf[NormaTwo])
  }
}