package github.showang.kat

import android.util.Log

interface Debuggable {

    companion object {
        val defaultLogger: Logger = AndroidLogger()
        private var fileLogger: Logger? = null
        private var currentLogger: Logger? = null

        fun updateLogger(logger: Logger) {
            currentLogger = logger
        }

        fun initFileLogger(logger: Logger) {
            fileLogger = logger
        }
    }

    val debug: Boolean get() = true //NOTE: override this if you want to close logging.
    private val mFileLogger get() = fileLogger

    fun log(isDebug: Boolean, logger: Logger, messageDelegate: () -> String) {
        if (isDebug) logger.e(javaClass.simpleName, messageDelegate())
    }

    fun log(messageDelegate: () -> String) = log(
        currentLogger ?: defaultLogger, messageDelegate
    )

    fun logd(messageDelegate: () -> String) = (currentLogger
        ?: defaultLogger).d(javaClass.simpleName, messageDelegate())

    fun log(logger: Logger, messageDelegate: () -> String) = log(debug, logger, messageDelegate)
    fun flog(messageDelegate: () -> String) = mFileLogger?.run {
        log(true, this, messageDelegate)
    } ?: log(messageDelegate)

    fun log(
        e: Throwable, logger: Logger = currentLogger
            ?: defaultLogger, messageDelegate: () -> String
    ) {
        logger.e(javaClass.simpleName, messageDelegate(), e)
    }
}

interface Logger {
    fun e(tag: String, message: String, e: Throwable? = null)
    fun d(tag: String, message: String)
}

class AndroidLogger : Logger {
    override fun e(tag: String, message: String, e: Throwable?) {
        Log.e(tag, message, e)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}