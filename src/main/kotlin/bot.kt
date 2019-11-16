package net.opens3

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.*


fun main(args: Array<String>): Unit {
    println("Running...")
    // find channels
    // findChannels()
    getLastEntryForChannels()

}

fun getLastEntryForChannels(): Unit {
    getAllChannels()
}

fun findChannels(): Unit {
    val intStep = 100
    for (i in 703800..(704000) step intStep) {
        val urls = mutableListOf<String>()
        for (j in i..(i + (intStep))) {
            urls.add("https://api.thingspeak.com/channels/${j}/feeds.json")
        }
        runBlocking {
            parallelRequests(urls)
        }

    }
}


suspend fun parallelRequests(requests: List<String>) = supervisorScope<Unit> {
    // Create our HTTP client
    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer {
                // .GsonBuilder
                serializeNulls()
                disableHtmlEscaping()
            }
        }
    }
    val jobs = mutableListOf<Deferred<Boolean>>()
    for (req in requests) {
        val job = async() {
            val results = client.get<ChannelSummary> {
                url(req)
                contentType(ContentType.Application.Json)
            }
            insertChannel(results.channel)
        }
        jobs.add(job)
    }
    for (job in jobs) {
        try {
            job.await()
        } catch (e: Throwable) {
            println(e)
        }
    }
    client.close()
}

