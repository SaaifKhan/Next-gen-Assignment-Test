package com.saif.domain.exception

import java.io.IOException

class ConnectivityException(override var message: String = "No internet connection") :
    IOException(message)