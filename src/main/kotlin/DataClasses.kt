package net.opens3

import org.jetbrains.exposed.sql.Date
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

data class ChannelSummary(
    val channel: Channel,
    val feeds: List<Feed>
)

data class Channel(
    val created_at: String,
    val description: String?,
    val field1: String?,
    val field2: String?,
    val field3: String?,
    val field4: String?,
    val field5: String?,
    val field6: String?,
    val field7: String?,
    val field8: String?,
    val id: Int,
    val last_entry_id: Int?,
    val latitude: Float?,
    val longitude: Float?,
    val name: String?,
    val updated_at: String?
)

data class ActiveChannel(
    val channel_id: Int,
    val last_entry_date: DateTime,
    val weatherStation: Boolean
    )
object ActiveChannelTable : Table() {
    val id_self = integer("id_self").autoIncrement().primaryKey()
    val channel_id = integer("channel_id")
    val name = varchar("name", 100).nullable()
    val description = text("description").nullable()
    val latitude = float("latitude").nullable()
    val longitude = float("longitude").nullable()
    val last_entry_date = datetime("last_entry_date")
    val weather_station = bool("weather_station")
}
object ChannelTable : Table() {
    val id_self = integer("id_self").autoIncrement().primaryKey()
    val created_at = datetime("created_at")
    val description = text("description").nullable()
    val field1 = varchar("field1", 256).nullable()
    val field2 = varchar("field2", 256).nullable()
    val field3 = varchar("field3", 256).nullable()
    val field4 = varchar("field4", 256).nullable()
    val field5 = varchar("field5", 256).nullable()
    val field6 = varchar("field6", 256).nullable()
    val field7 = varchar("field7", 256).nullable()
    val field8 = varchar("field8", 256).nullable()
    val channel_id = integer("channel_id")
    val last_entry_id = integer("last_entry_id").nullable()
    val latitude = float("latitude").nullable()
    val longitude = float("longitude").nullable()
    val name = varchar("name", 100).nullable()
    val updated_at = datetime("updated_at").nullable()
}

data class Feed(
    val channel_id: String,
    val created_at: String,
    val entry_id: Int,
    val field1: String?,
    val field2: String?,
    val field3: String?,
    val field4: String?,
    val field5: String?,
    val field6: String?,
    val field7: String?,
    val field8: String?
)

object FeedTable : Table() {
    val channel_id = integer("channel_id")
    val id_self = integer("id_self").autoIncrement().primaryKey()
    val created_at = datetime("created_at")
    val entry_id = integer("entry_id").nullable()
    val field1 = varchar("field1", 256).nullable()
    val field2 = varchar("field2", 256).nullable()
    val field3 = varchar("field3", 256).nullable()
    val field4 = varchar("field4", 256).nullable()
    val field5 = varchar("field5", 256).nullable()
    val field6 = varchar("field6", 256).nullable()
    val field7 = varchar("field7", 256).nullable()
    val field8 = varchar("field8", 256).nullable()

}
