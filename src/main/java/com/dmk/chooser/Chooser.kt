package com.dmk.chooser

import java.io.File
import java.util.*

class Chooser(libPath: String) {

    private val albumMap = LinkedHashMap<String, String>()

    init {
        map(File(libPath))
    }

    private fun map(inFile: File) {
        if (musicFile(inFile.name)) {
            albumMap[inFile.parentFile.name] = inFile.parentFile.absolutePath
        } else {
            val file = inFile.listFiles()
            if (file != null) {
                for (aFile in file) {
                    if (musicFile(aFile.name)) {
                        map(aFile)
                        break
                    } else {
                        map(aFile)
                    }
                }
            }
        }
    }

    private fun musicFile(fileName: String): Boolean {
        return fileName.endsWith("mp3") or
                fileName.endsWith("flac") or
                fileName.endsWith("mp4") or
                fileName.endsWith("m4p") or
                fileName.endsWith("wav") or
                fileName.endsWith("alac") or
                fileName.endsWith("wma") or
                fileName.endsWith("aac") or
                fileName.endsWith("m4a")
    }

    fun getRandomAlbumPath(): String? {
        val album = ArrayList(albumMap.keys)[Random().nextInt(albumMap.keys.size)]
        return albumMap[album]
    }
}
