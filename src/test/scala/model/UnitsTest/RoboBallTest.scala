package cl.uchile.dcc.citric
package model.UnitsTest

import model.Units.RoboBall

class RoboBallTest extends munit.FunSuite {
  private var roboBall: RoboBall = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboBall = new RoboBall()
  }

  test("A roboBall should have correctly set their attributes") {
    assertEquals(roboBall.maxHp, 3)
    assertEquals(roboBall.hp, roboBall.maxHp)
    assertEquals(roboBall.attack, -1)
    assertEquals(roboBall.defense, 1)
    assertEquals(roboBall.evasion, -1)
  }

  test("A roboBall should be able to update his stars") {
    assertEquals(roboBall.getStars, 0)
    roboBall.updateStars(10)
    assertEquals(roboBall.getStars, 10)
  }

  test("A roboBall should be able to update his hp") {
    assertEquals(roboBall.getHp, roboBall.maxHp)
    roboBall.updateHp(-1)
    assertEquals(roboBall.getHp, roboBall.maxHp - 1)
  }

}
