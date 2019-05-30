package pl.mdanilowski.spotted.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.mdanilowski.spotted.model.Messages

interface MessagesRepository : MongoRepository<Messages, String> {

    fun findAllByCity(city: String) : List<Messages>
}