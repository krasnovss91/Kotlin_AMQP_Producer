import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets

fun main(args: Array<String>) {
    val factory = ConnectionFactory()
    factory.newConnection("amqp://guest:guest@localhost:15672/").use { connection ->
        connection.createChannel().use { channel ->
            channel.queueDeclare("test_queue", false, false, false, null)
            val message = "Hello World from AMQP!"
            channel.basicPublish(
                "",
                "DEMO",
                null,
                message.toByteArray(StandardCharsets.UTF_8)
            )
            println(" [x] Sent '$message'")
        }
    }
}