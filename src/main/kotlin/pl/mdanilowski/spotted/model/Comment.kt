package pl.mdanilowski.spotted.model

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Comment(var comment: String, var date: Date)
