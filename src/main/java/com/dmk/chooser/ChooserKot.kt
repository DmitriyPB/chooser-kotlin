package com.dmk.chooser

import java.io.FileInputStream
import java.nio.file.Paths
import java.util.*


const val PLAYER_PROPERTY_NAME = "chooser.playerPath"
const val LIBRARY_PROPERTY_NAME = "chooser.musicLibraryPath"

private fun fetchJarLocation(clazz: Class<*>): String {
    return Paths.get(clazz.protectionDomain.codeSource.location.toURI()).toFile().parentFile.path
}

fun main(args: Array<String>) {
    val jarPath = fetchJarLocation(Chooser::class.java)
    val logFileLocation = "$jarPath/settings.properties"
    val properties = Properties()
    properties.load(FileInputStream(logFileLocation))
    println("properties : $properties")

    Runtime.getRuntime().exec(arrayOf(properties.getProperty(PLAYER_PROPERTY_NAME), Chooser(properties.getProperty(LIBRARY_PROPERTY_NAME)).getRandomAlbumPath()))
}
