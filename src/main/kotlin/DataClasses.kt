package net.opens3
import org.jetbrains.exposed.sql.Table

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
    val latitude: String?,
    val longitude: String?,
    val name: String?,
    val updated_at: String?
)

object ChannelTable: Table() {
    val id_self = integer("id_self").autoIncrement().primaryKey()
    val created_at= varchar("created_at", 100)
    val description= text("description").nullable()
    val field1= varchar("field1", 100).nullable()
    val field2= varchar("field2", 100).nullable()
    val field3= varchar("field3", 100).nullable()
    val field4= varchar("field4", 100).nullable()
    val field5= varchar("field5", 100).nullable()
    val field6= varchar("field6", 100).nullable()
    val field7= varchar("field7", 100).nullable()
    val field8= varchar("field8", 100).nullable()
    val id = integer("id")
    val last_entry_id = integer("last_entry_id").nullable()
    val latitude = varchar("latitude", 50).nullable()
    val longitude = varchar("longitude", 50).nullable()
    val name = varchar("name", 100).nullable()
    val updated_at = varchar("updated_at", 100).nullable()
}
data class Feed(
    val created_at: String,
    val entry_id: Int,
    val field1: String,
    val field2: String
)
