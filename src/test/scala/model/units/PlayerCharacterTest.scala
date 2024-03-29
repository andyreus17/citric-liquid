package cl.uchile.dcc.citric
package model.units

import model.norma.{NormaOne, NormaTwo}

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name = "testPlayer"
  private val name2 = "testPlayer2"
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
  private var character2: PlayerCharacter = _
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  private var wildUnit: WildUnit = _
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)

    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )

    character2 = new PlayerCharacter(
      name2,
      maxHp,
      100, //attack
      defense,
      100, //evasion
      randomNumberGenerator
    )

    wildUnit = new Seagull()
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.getName, name)
    assertEquals(character.getMaxHp, maxHp)
    assertEquals(character.getAttack, attack)
    assertEquals(character.getDefense, defense)
    assertEquals(character.getEvasion, evasion)
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

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A character should be able to roll a dice with a fixed seed") {
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(character.rollDice(), other.rollDice())
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
    assertEquals(character.getNormaType, "wins")
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
    assert(character.norma.isInstanceOf[NormaOne]) // parte con norma de tipo NormaOne
    character.setNormaType("stars") // establecemos que el norma type de la norma sea 'stars'
    assertEquals(character.getNormaType, "stars") // vemos que cambió el norma type
    character.updateStars(10) // le damos suficientes estrellas para subir al nivel norma 2
    character.normaClear() // hacemos norma clear
    assert(character.norma.isInstanceOf[NormaTwo]) // cumple norma check y entonces se hizo efectivamente norma clear
  }

  test("A player should be able to attack") {
    assert(!character.getKO)
    assert(character.attacking() > character.getAttack)
  }

  test("A player should be able to defend an attack") {
    val oldHp = character.getHp
    assert(!character.getKO)
    assert(!character2.getKO)
    character.defend(character2)
    assert(character.getHp < oldHp)
  }

  test("A player should be able to evade an attack") {
    val oldHp2 = character2.getHp
    assert(!character.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getEvasion, 100)
    character2.evade(character)
    assertEquals(oldHp2, character2.getHp)

    val oldHp = character.getHp
    assert(!character.getKO)
    assert(!character2.getKO)
    assertEquals(character2.getAttack, 100)
    character.evade(character2)
    assert(oldHp > character.getHp)
  }

  test("A player should be able to enter in KO state") {
    assert(!character.getKO) //no esta KO
    character.setHp(0)//ponemos vida en 0
    character.isKO()
    assert(character.getKO) //esta KO
  }

  test("A player should be able to update stars and wins when beats a wild unit") {
    character.setWins(0) // establecemos victorias del player en 0
    character.setStars(10) // establecemos sus stars en 10
    wildUnit.setStars(11) // establecemos las stars del wild unit en 11
    assertEquals(wildUnit.getOwnStars, 2) // revisamos que las ownStars de esta wild unit son 2 (es un Seagull)
    character.beatEnemy(wildUnit)
    assertEquals(character.getStars, 23) // ahora el player deberia tener 23 stars (10 que ya tenia + 11 que tenia el wild unit + 2 que son las ownStars de este wild unit)
    assertEquals(wildUnit.getStars, 0) // las estrellas del wild unit deberian ser 0, pues fueron todas transferidas al player que lo derrotó
    assertEquals(character.getWins, 1) // ahora el player deberia tener 1 victoria
  }

  test("A player should be able to update stars and wins when beats another player character") {
    character.setWins(0) // establecemos victorias del player en 0
    character.setStars(10) // establecemos sus stars en 10
    character2.setStars(11) // establecemos las stars del otro juagador en 11
    character.beatEnemy(character2)
    assertEquals(character.getStars, 15) // ahora el player deberia tener 15 stars (10 que ya tenia + 5 que son la mitad hacia abajo del player2)
    assertEquals(character2.getStars, 6) // el player derrotado pierde la mitad hacia arriba de stars (11-5=6 en este caso)
    assertEquals(character.getWins, 2) // ahora el player deberia tener 2 victorias
  }

}
