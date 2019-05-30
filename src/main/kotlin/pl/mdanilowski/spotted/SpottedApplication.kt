package pl.mdanilowski.spotted

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpottedApplication

fun main(args: Array<String>) {
	runApplication<SpottedApplication>(*args)
}
