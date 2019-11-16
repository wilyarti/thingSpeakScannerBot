package net.opens3

import net.opens3.ChannelTable.created_at
import net.opens3.ChannelTable.description
import net.opens3.ChannelTable.field1
import net.opens3.ChannelTable.field2
import net.opens3.ChannelTable.field3
import net.opens3.ChannelTable.field4
import net.opens3.ChannelTable.field5
import net.opens3.ChannelTable.field6
import net.opens3.ChannelTable.field7
import net.opens3.ChannelTable.field8
import net.opens3.ChannelTable.id
import net.opens3.ChannelTable.last_entry_id
import net.opens3.ChannelTable.latitude
import net.opens3.ChannelTable.longitude
import net.opens3.ChannelTable.name
import net.opens3.ChannelTable.updated_at
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun connectToDB(): Unit {
    Database.connect(
        "jdbc:mysql://${DB_ADDRESS}/${DB_NAME}",
        "com.mysql.jdbc.Driver",
        user = DB_USERNAME,
        password = DB_PASSWORD
    )
}

fun addLatestData(channel: ChannelSummary): Boolean {
    connectToDB()
    transaction {
        SchemaUtils.create(FeedTable)
        if (channel.feeds.getOrNull(0) !== null) {
            val thisEntry = channel.feeds[0]
            FeedTable.deleteWhere { FeedTable.entry_id eq thisEntry.entry_id }
            FeedTable.insert {
                it[field1] = thisEntry.field1
                it[field2] = thisEntry.field2
                it[field3] = thisEntry.field3
                it[field4] = thisEntry.field4
                it[field5] = thisEntry.field5
                it[field6] = thisEntry.field6
                it[field7] = thisEntry.field7
                it[field8] = thisEntry.field8
                it[entry_id] = thisEntry.entry_id
                it[created_at] = thisEntry.created_at
                it[channel_id] = channel.channel.id
            }
        }
    }
    return true
}

fun insertChannel(channel: Channel): Boolean {
    connectToDB()

    transaction {
        SchemaUtils.create(ChannelTable)
        ChannelTable.deleteWhere { ChannelTable.id eq channel.id }
        ChannelTable.insert {
            it[created_at] = channel.created_at
            it[description] = channel.description
            it[field1] = channel.field1
            it[field2] = channel.field2
            it[field3] = channel.field3
            it[field4] = channel.field4
            it[field5] = channel.field5
            it[field6] = channel.field6
            it[field7] = channel.field7
            it[field8] = channel.field8
            it[id] = channel.id
            it[last_entry_id] = channel.last_entry_id
            it[latitude] = channel.latitude
            it[longitude] = channel.longitude
            it[name] = channel.name
            it[updated_at] = channel.updated_at
        }
    }
    return true
}

fun getAllChannels(): List<Channel> {
    val allChannels = mutableListOf<Channel>()
    connectToDB()
    transaction {
        SchemaUtils.create(ChannelTable)
        for (thisChannel in ChannelTable.selectAll()) {
            val addedChannel = Channel(
                id = thisChannel[id],
                created_at = thisChannel[created_at],
                description = thisChannel[description],
                field1 = thisChannel[field1],
                field2 = thisChannel[field2],
                field3 = thisChannel[field3],
                field4 = thisChannel[field4],
                field5 = thisChannel[field5],
                field6 = thisChannel[field6],
                field7 = thisChannel[field7],
                field8 = thisChannel[field8],
                last_entry_id = thisChannel[last_entry_id],
                latitude = thisChannel[latitude],
                longitude = thisChannel[longitude],
                name = thisChannel[name],
                updated_at = thisChannel[updated_at]
            )
            allChannels.add(addedChannel)
        }

    }
    return allChannels
}