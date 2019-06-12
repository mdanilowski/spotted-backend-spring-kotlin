package pl.mdanilowski.spotted.rest.exception

import org.springframework.http.HttpStatus
import javax.xml.ws.http.HTTPException

class DocumentNotFoundException(override val message: String) : HTTPException(HttpStatus.BAD_REQUEST.value())