package ameliebot

import scala.util.Random

object Amelies {
  private val Amelies = List("amélie?", "amélie??", "hello i am amélie", "i am amélie!")

  private val RareAmelies = List("but maybe fall in love with me amélie",
    "hellooooooo", "i cook an egg with a spoon", "fall in love again with me amélie",
    "amélie: now on dvd")

  private def selectAmelie(): String = {
    val choices = if (Random.nextInt(4) == 0) RareAmelies else Amelies
    Random.shuffle(choices).head
  }

  private var amelieCounter = Random.nextInt(15)

  def getCounter(): Int = amelieCounter

  def getAmelie(): Option[String] = {
    if (amelieCounter == 0) {
      amelieCounter = Random.nextInt(15) + 15
      Some(selectAmelie())
    } else {
      amelieCounter = amelieCounter - 1
      None
    }
  }
}
