package cl.uchile.dcc.citric
package model.units

import model.units.RoboBall

import scala.util.Random

class RoboBallTest extends munit.FunSuite {
  private val name2 = "testPlayer2"
  private val maxHp = 10
  private val defense = 1
  private var randomNumberGenerator: Random = _
  
  private var roboBall: RoboBall = _
  private var character2: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboBall = new RoboBall()

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

  test("A roboBall should have correctly set their attributes") {
    assertEquals(roboBall.getMaxHp, 3)
    assertEquals(roboBall.getHp, roboBall.getMaxHp)
    assertEquals(roboBall.getAttack, -1)
    assertEquals(roboBall.getDefense, 1)
    assertEquals(roboBall.getEvasion, -1)
  }

  test("A roboBall should be able to update his stars") {
    assertEquals(roboBall.getStars, 0)
    roboBall.updateStars(10)
    assertEquals(roboBall.getStars, 10)
  }

  test("A roboBall should be able to update his hp") {
    assertEquals(roboBall.getHp, roboBall.getMaxHp)
    roboBall.updateHp(-1)
    assertEquals(roboBall.getHp, roboBall.getMaxHp - 1)
  }

  test("A roboBall should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(roboBall.rollDice >= 1 && roboBall.rollDice <= 6)
    }
  }

  test("A roboBall should be able to attack") {
    assert(!roboBall.getKO)
    assert(roboBall.attacking() > roboBall.getAttack)
  }

  test("A roboBall should be able to defend an attack") {
    val oldHp = roboBall.getHp
    assert(!roboBall.getKO)
    assert(!character2.getKO)
    roboBall.defend(character2)
    assert(roboBall.getHp < oldHp)
  }

  test("A roboBall should be able to evade an attack") {
    val oldHp2 = character2.getHp
    assert(!roboBall.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getEvasion, 100)
    character2.evade(roboBall)
    assertEquals(oldHp2, character2.getHp)

    val oldHp = roboBall.getHp
    assert(!roboBall.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getAttack, 100)
    roboBall.evade(character2)
    assert(oldHp > roboBall.getHp)
  }

}
