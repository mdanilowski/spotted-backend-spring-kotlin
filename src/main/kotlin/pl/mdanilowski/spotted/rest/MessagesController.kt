package pl.mdanilowski.spotted.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mdanilowski.spotted.repository.MessagesRepository
import org.springframework.web.bind.annotation.RequestMethod
import pl.mdanilowski.spotted.model.Messages


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
}