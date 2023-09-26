package cl.uchile.dcc.citric
package model.UnitsTest

import model.Norma.NormaClass
import model.Units.PlayerCharacter

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private var randomNumberGenerator: Random = _
  /* Add any other constants you need here... */

  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  var norma: NormaClass = _
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
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

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    assertEquals(character.randomNumberGenerator, randomNumberGenerator)
    assertEquals(character.getHp, maxHp)
    assertEquals(character.getKO, false)
    assertEquals(character.getRecoveryCounter, 6)
    assertEquals(character.getWins, 0)
    assertEquals(character.getStars, 0)
  }

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  test("A character should be able to get his attributes") {
    character.updateWins(5)
    assertEquals(character.getWins, 5)
    character.updateStars(25)
    assertEquals(character.getStars, 25)
    character.updateHp(2)
    assertEquals(character.getHp, 12) // inicialmente tiene 10 de hp
    assertEquals(character.getNormaLevel, 1)
    assertEquals(character.getNormaType, "stars")
  }

  test("A character should be able to update his wins") {
    assertEquals(character.getWins, 0)
    character.updateWins(2)
    assertEquals(character.getWins, 2)
  }

  test("A player can enter in a KO status") {
    assertEquals(character.getHp, 10) //hp es 10 inicialmente
    character.updateHp(-10)
    assertEquals(character.getHp, 0)
    character.isKO()
    assert(character.getKO) // KO es true
    assertEquals(character.getRecoveryCounter, 6) // su contador de recovery se pone en 6
  }

  test("A player should be able to make a recovery phase") {
    assertEquals(character.getRecoveryCounter, 6)
    character.recovery()
    assert(character.getRecoveryCounter == 5 || !character.getKO)
    // el contador de recovery disminuyó 1 o salió del estado de KO (obtuvo 6)
  }

  test("A player should be able to update his stars") {
    assertEquals(character.getStars, 0)
    character.updateStars(10)
    assertEquals(character.getStars, 10)
  }

  test("A player should be able to update his hp") {
    assertEquals(character.getHp, maxHp)
    character.updateHp(1)
    assertEquals(character.getHp, 11)
  }

  test("A player should be able to make norma clear") {
    assertEquals(character.getStars, 0) //parte con stars=0
    assertEquals(character.getNormaLevel, 1) // parte con nivel norma 1
    assertEquals(character.getNormaType, "stars") // por defecto comienza con norma tipo stars para subir de nivel
    character.updateStars(10) // le damos suficientes estrellas para subir al nivel norma 2
    character.normaClear() // hacemos norma clear
    assertEquals(character.getNormaLevel, 2) // como cumple norma check y entonces se hizo efectivamente norma clear
  }
}
