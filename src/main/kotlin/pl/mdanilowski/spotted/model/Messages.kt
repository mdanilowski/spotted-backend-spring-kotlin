package pl.mdanilowski.spotted.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "messages")
data class Messages(var _id: ObjectId?,
                    var message: String,
                    var city: String,
                    var date: Date?,
                    var tags: ArrayList<String>?,
                    var comments: ArrayList<Comment?>?)