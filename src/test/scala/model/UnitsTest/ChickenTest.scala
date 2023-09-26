package cl.uchile.dcc.citric
package model.UnitsTest

import model.Units.Chicken

class ChickenTest extends munit.FunSuite {

  private var chicken: Chicken = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken()
  }

  test("A chicken should have correctly set their attributes") {
    assertEquals(chicken.getMaxHp, 3)
    assertEquals(chicken.getHp, chicken.getMaxHp)
    assertEquals(chicken.attack, -1)
    assertEquals(chicken.defense, -1)
    assertEquals(chicken.evasion, 1)
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

}
