package cl.uchile.dcc.citric
package model.UnitsTest

import model.Units.Seagull

class SeagullTest extends munit.FunSuite {
  private var seagull: Seagull = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull()
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

}
