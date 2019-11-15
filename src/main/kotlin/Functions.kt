package net.opens3
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import net.opens3.DB_ADDRESS
import net.opens3.DB_NAME
import net.opens3.DB_PASSWORD
import net.opens3.DB_USERNAME

fun connectToDB(): Unit {
    Database.connect(
        "jdbc:mysql://${DB_ADDRESS}/${DB_NAME}",
        "com.mysql.jdbc.Driver",
        user = DB_USERNAME,
        password = DB_PASSWORD
    )
}

fun insertChannel(channel: Channel): Boolean {
    connectToDB()

    transaction {
        SchemaUtils.create(ChannelTable)
        ChannelTable.deleteWhere{ChannelTable.id eq channel.id}
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
            it[ last_entry_id] = channel.last_entry_id
            it[latitude] = channel.latitude
            it[longitude] = channel.longitude
            it[name] = channel.name
            it[updated_at] = channel.updated_at
        }
    }
    return true
}