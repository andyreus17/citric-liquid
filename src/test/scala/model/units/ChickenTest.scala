package cl.uchile.dcc.citric
package model.units

import model.units.Chicken

import scala.util.Random

class ChickenTest extends munit.FunSuite {
  private val name2 = "testPlayer2"
  private val maxHp = 10
  private val defense = 1
  private var randomNumberGenerator: Random = _

  private var chicken: Chicken = _
  private var character2: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken()

    randomNumberGenerator = new Random(11)

    character2 = new PlayerCharacter(
      name2,
      maxHp,
      100, //attack
      defense,
      100, //evasion
      randomNumberGenerator
    )
  }

  test("A chicken should have correctly set their attributes") {
    assertEquals(chicken.getMaxHp, 3)
    assertEquals(chicken.getHp, chicken.getMaxHp)
    assertEquals(chicken.getAttack, -1)
    assertEquals(chicken.getDefense, -1)
    assertEquals(chicken.getEvasion, 1)
  }

  test("A chicken should be able to update his stars") {
    assertEquals(chicken.getStars, 0)
    chicken.updateStars(10)
    assertEquals(chicken.getStars, 10)
  }

  test("A chicken should be able to update his hp") {
    assertEquals(chicken.getHp, chicken.getMaxHp)
    chicken.updateHp(-1)
    assertEquals(chicken.getHp, chicken.getMaxHp - 1)
  }

  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(chicken.rollDice >= 1 && chicken.rollDice <= 6)
    }
  }

  test("A Chicken should be able to attack") {
    assert(!chicken.getKO)
    assert(chicken.attacking() > chicken.getAttack)
  }

  test("A player should be able to attack") {
    assert(!chicken.getKO)
    assert(chicken.attacking() > chicken.getAttack)
  }

  test("A player should be able to defend an attack") {
    val oldHp = chicken.getHp
    assert(!chicken.getKO)
    assert(!character2.getKO)
    chicken.defend(character2)
    assert(chicken.getHp < oldHp)
  }

  test("A player should be able to evade an attack") {
    val oldHp2 = character2.getHp
    assert(!chicken.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getEvasion, 100)
    character2.evade(chicken)
    assertEquals(oldHp2, character2.getHp)

    val oldHp = chicken.getHp
    assert(!chicken.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getAttack, 100)
    chicken.evade(character2)
    assert(oldHp > chicken.getHp)
  }

}
