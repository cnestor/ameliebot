package ameliebot

import com.typesafe.config.ConfigFactory
import sx.blah.discord.api.events.IListener
import sx.blah.discord.api.{ClientBuilder, IDiscordClient}
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.handle.obj.IChannel
import sx.blah.discord.util.MessageBuilder

object AmelieBot extends App {
  val config = ConfigFactory.load()
  val token = config.getString("discord-app.token")
  val client = (new ClientBuilder).withToken(token).login()

  val dispatcher = client.getDispatcher
  dispatcher.registerListener(new Listener(client))
}

class Listener(client: IDiscordClient) extends IListener[MessageReceivedEvent] {

  def sendText(text: String)(implicit channel: IChannel): Unit =  {
    new MessageBuilder(this.client).withChannel(channel).withContent(text).build()
  }

  override def handle(event: MessageReceivedEvent): Unit = {
    implicit val channel = event.getChannel

    def processMessage(): Unit = {
      event.getMessage.getContent match {
        case "!countdown" => sendText(Amelies.getCounter().toString)
        case _ => Amelies.getAmelie().foreach(sendText)
      }
    }

    if (!event.getAuthor.isBot) processMessage() //avoid a feedback loop with other bots
  }
}