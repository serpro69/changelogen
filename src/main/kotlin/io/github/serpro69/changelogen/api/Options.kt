package io.github.serpro69.changelogen.api

import java.util.*

object Options {
    fun getOpts(fileName: String): Properties {
        return Properties().apply {
            val inStream = this@Options.javaClass.classLoader.getResourceAsStream(fileName)

            load(inStream)
        }
    }
}