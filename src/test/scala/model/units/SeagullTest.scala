package cl.uchile.dcc.citric
package model.units

import model.units.Seagull

import scala.util.Random

class SeagullTest extends munit.FunSuite {
  private val name2 = "testPlayer2"
  private val maxHp = 10
  private val defense = 1
  private var randomNumberGenerator: Random = _
  
  private var seagull: Seagull = _
  private var character2: PlayerCharacter = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull()

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

  test("A seagull should have correctly set their attributes") {
    assertEquals(seagull.getMaxHp, 3)
    assertEquals(seagull.getHp, seagull.getMaxHp)
    assertEquals(seagull.getAttack, 1)
    assertEquals(seagull.getDefense, -1)
    assertEquals(seagull.getEvasion, -1)
  }

  test("A seagull should be able to update his stars") {
    assertEquals(seagull.getStars, 0)
    seagull.updateStars(10)
    assertEquals(seagull.getStars, 10)
  }

  test("A seagull should be able to update his hp") {
    assertEquals(seagull.getHp, seagull.getMaxHp)
    seagull.updateHp(-1)
    assertEquals(seagull.getHp, seagull.getMaxHp - 1)
  }

  test("A seagull should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(seagull.rollDice >= 1 && seagull.rollDice <= 6)
    }
  }

  test("A seagull should be able to attack") {
    assert(!seagull.getKO)
    assert(seagull.attacking() > seagull.getAttack)
  }

  test("A seagull should be able to defend an attack") {
    val oldHp = seagull.getHp
    assert(!seagull.getKO)
    assert(!character2.getKO)
    seagull.defend(character2)
    assert(seagull.getHp < oldHp)
  }

  test("A seagull should be able to evade an attack") {
    val oldHp2 = character2.getHp
    assert(!seagull.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getEvasion, 100)
    character2.evade(seagull)
    assertEquals(oldHp2, character2.getHp)

    val oldHp = seagull.getHp
    assert(!seagull.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getAttack, 100)
    seagull.evade(character2)
    assert(oldHp > seagull.getHp)
  }

  test("A Seagull should be able to update his stars when beats a player character") {
    seagull.setStars(5) // establecemos sus stars en 5
    character2.setStars(11) // establecemos las stars del player en 11
    seagull.beatEnemy(character2)
    assertEquals(seagull.getStars, 10) // ahora las stars del seagull deberian ser 10 (5 que ya tenia + 5 que son la mitad hacia abajo del player al que derrotó)
    assertEquals(character2.getStars, 6) // ahora las stars del player deberian ser 6 (pierde la mitad hacia abajo de las que ya tenía)
  }
}
