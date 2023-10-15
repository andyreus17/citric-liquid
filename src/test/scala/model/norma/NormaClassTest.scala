package cl.uchile.dcc.citric
package model.norma

import model.norma.NormaClass

import model.units.PlayerCharacter

import scala.util.Random

class NormaClassTest extends munit.FunSuite {

  val starsCondition: Map[Int, Int] = Map(2 -> 10, 3 -> 30, 4 -> 70, 5 -> 120, 6 -> 200)
  val winsCondition: Map[Int, Int] = Map(2 -> 1, 3 -> 3, 4 -> 6, 5 -> 10, 6 -> 14)

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val randomNumberGenerator = new Random(11)

  private var norma: NormaClass = _
  private var character: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    norma = new NormaClass()

    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )
  }

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.normaLevel, 1)
    assertEquals(norma.normaType, "stars")
    assertEquals(norma.starsCondition, starsCondition)
    assertEquals(norma.winsCondition, winsCondition)
  }

  test("A norma should be able to change his norma type") {
    norma.setNormaType("stars")
    assertEquals(norma.getNormaType, "stars")
    norma.setNormaType("wins")
    assertEquals(norma.getNormaType, "wins")
  }

  test("A norma should get his norma type") {
    norma.setNormaType("wins")
    assertEquals(norma.getNormaType, "wins")
  }

  test("Should can do norma check to a player character") {
    assertEquals(character.getNormaType, "stars")
    character.updateStars(10)
    assertEquals(norma.normaCheck(character), true)
  }

  test("Should can do norma clear to a player character") {
    assertEquals(character.getNormaType, "stars")
    character.updateStars(10)
    assertEquals(norma.normaCheck(character), true)
    norma.normaClear(character)
    assertEquals(norma.normaLevel, 2)
  }

}
