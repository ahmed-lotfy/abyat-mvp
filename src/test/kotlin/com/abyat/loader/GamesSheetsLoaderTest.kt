package com.abyat.loader

import org.junit.Test

class GamesSheetsLoaderTest {

    @Test
    fun testLoadingFiles() {
        val result = GamesSheetsLoader.loadGamesResult("files/")
        print(result)
    }

}
