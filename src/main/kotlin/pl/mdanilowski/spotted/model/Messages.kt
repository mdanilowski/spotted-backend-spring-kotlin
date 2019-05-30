package pl.mdanilowski.spotted.model

data class Messages(var _id: String,
                    var message: String,
                    var city: String,
                    var tags: List<String>?,
                    var comments: List<Comment?>?)