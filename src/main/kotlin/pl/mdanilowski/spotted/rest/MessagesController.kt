package pl.mdanilowski.spotted.rest

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.mdanilowski.spotted.model.Comment
import pl.mdanilowski.spotted.model.Messages
import pl.mdanilowski.spotted.repository.MessagesRepository
import pl.mdanilowski.spotted.rest.exception.DocumentNotFoundException
import pl.mdanilowski.spotted.rest.model.CommentDao
import pl.mdanilowski.spotted.rest.model.Tags
import java.util.*
import kotlin.collections.ArrayList
import kotlin.streams.toList

@RestController
@RequestMapping("/messages")
class MessagesController {

    @Autowired
    private lateinit var messagesRepository: MessagesRepository

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun getAllPets(): List<Messages> {
        return messagesRepository.findAll()
    }

    @RequestMapping(value = ["/{city}"], method = [RequestMethod.GET])
    fun getMessagesByCity(@PathVariable city: String): List<Messages> {
        return messagesRepository.findAllByCity(city)
    }

    @RequestMapping(value = ["/{city}/{text}"], method = [RequestMethod.GET])
    fun getMessagesByCityAndMessage(@PathVariable city: String, @PathVariable text: String): List<Messages> {
        return messagesRepository.findAllByCityAndMessageContains(city, text)
    }

    @RequestMapping(value = ["/{city}"], method = [RequestMethod.POST])
    fun getMessagesByCityAndTags(@PathVariable city: String, @RequestBody tags: Tags): List<Messages> {
        return messagesRepository.findAllByCity(city)
                .stream()
                .filter { it.tags?.containsAll(tags.tags)!! }
                .toList()
    }

    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun addMessage(@RequestBody message: Messages) {
        initializeMessage(message)
        messagesRepository.insert(message)
    }

    private fun initializeMessage(message: Messages) {
        message._id = ObjectId()
        message.date = Date()
        message.comments = ArrayList()
        message.tags = ArrayList()
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteMessage(@PathVariable id: String) {
        messagesRepository.deleteById(id)
    }

    @RequestMapping(value = ["/{city}/count"], method = [RequestMethod.GET])
    fun countMessagesInCity(@PathVariable city: String): Int {
        return messagesRepository.findAllByCity(city).count()
    }

    @RequestMapping(value = ["/{messageId}/comment"], method = [RequestMethod.POST])
    fun addComment(@PathVariable messageId: String, @RequestBody comment: CommentDao) {
        val messageForComment = messagesRepository.findById(messageId).orElseThrow { DocumentNotFoundException("This message does not exist") }
        messageForComment.comments?.add(Comment(comment.comment, Date()))
        messagesRepository.save(messageForComment)
    }
}