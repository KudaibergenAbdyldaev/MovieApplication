package com.pacckage.data.network.check_internet_connection

import java.io.IOException

class NoConnectivityException : IOException() {
    override fun getLocalizedMessage(): String? {
        return "No connectivity exception"
    }
}