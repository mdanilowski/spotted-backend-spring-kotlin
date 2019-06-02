package pl.mdanilowski.spotted.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.mdanilowski.spotted.model.Messages
import pl.mdanilowski.spotted.rest.model.Tags

interface MessagesRepository : MongoRepository<Messages, String> {

    fun findAllByCity(city: String) : List<Messages>

    fun findAllByCityAndMessageContains(city: String, message: String): List<Messages>

    fun findAllByCityAndTagsContaining(city: String, tags: Tags): List<Messages>
}