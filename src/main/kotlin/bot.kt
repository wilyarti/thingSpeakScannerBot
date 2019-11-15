package net.opens3
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>): Unit {
    println("Running...")
    runBlocking {
        val client = HttpClient(Apache) {
            install(JsonFeature) {
                serializer = GsonSerializer {
                    // .GsonBuilder
                    serializeNulls()
                    disableHtmlEscaping()
                }
            }
        }




        for (i in 1..100) {
            println(i)
            try {
                val thisChannel = client.get<ChannelSummary> {
                    url("https://api.thingspeak.com/channels/${i}/feeds.json")
                    contentType(ContentType.Application.Json)
                }
                if ( thisChannel.channel.latitude  != null&& thisChannel.channel.longitude != null) {
                    println(thisChannel.channel.latitude)
                }
                insertChannel(thisChannel.channel)
            } catch (e: Throwable) {
                println(e)
            }
        }

        client.close()
    }
}