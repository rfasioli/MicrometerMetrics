package br.com.rfasioli.micrometermetrics.resource.controller

import io.micrometer.core.annotation.Timed
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import kotlin.random.Random

@RestController
@RequestMapping("/sample")
@Timed("sample")
class SampleController {
    private val log = LogManager.getLogger(SampleController::class.java)

    @GetMapping
    @Timed(value = "sample.all", longTask = true)
    fun listSample(): List<String> {
        val seconds2Sleep = Random.nextInt(500)
        log.debug("waiting for {}", seconds2Sleep)
        Thread.sleep(seconds2Sleep.toLong())
        return listOf("Jim", "Tom", "Tim")
    }

    @PutMapping
    @Timed(value = "sample.update", longTask = true)
    fun putSample(): List<String> {
        val seconds2Sleep = Random.nextInt(1000)
        log.debug("waiting for {}", seconds2Sleep)
        Thread.sleep(seconds2Sleep.toLong())
        return listOf("Jim", "Tom", "Tim")
    }

    @GetMapping("/asset")
    @Timed(value = "sample.asset", longTask = true)
    fun test() {
        throw Exception("error!")
    }

    @GetMapping("/property")
    @Timed(value = "sample.property", longTask = true)
    fun property(response: HttpServletResponse) {
        response.sendRedirect("/sample/asset")
    }

}
