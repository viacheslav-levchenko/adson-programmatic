package com.adson.programmatic

import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Config : AppConfig() {

    object Format {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val DATE_TIME_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN).withZone(DateTimeZone.UTC)
    }

    const val HTTP_CONNECT_TIMEOUT_MILLIS = 10 * 1000 //20s
    const val HTTP_READ_TIMEOUT_MILLIS = 20 * 1000 //25s
    const val HTTP_WRITE_TIMEOUT_MILLIS = 20 * 1000 //25s
    const val HTTP_DISK_CACHE_SIZE = 20 * 1024 * 1024 //20 MB
}