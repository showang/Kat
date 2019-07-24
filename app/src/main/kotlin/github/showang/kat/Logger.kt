package github.showang.kat

import android.util.Log

class Logger {

    companion object {

        private var loggerImpl: LoggerSpec = EmptyLogger()

        fun install(loggerImpl: LoggerSpec) {
            this.loggerImpl = loggerImpl
        }

        fun e(tag: String, message: String) {
            loggerImpl.e(tag, message)
        }

        fun w(tag: String, message: String) {
            loggerImpl.w(tag, message)
        }

        fun d(tag: String, message: String) {
            loggerImpl.d(tag, message)
        }

        fun i(tag: String, message: String) {
            loggerImpl.i(tag, message)
        }

    }

    interface LoggerSpec {
        fun e(tag: String, message: String, throwable: Throwable? = null)
        fun w(tag: String, message: String, throwable: Throwable? = null)
        fun d(tag: String, message: String, throwable: Throwable? = null)
        fun i(tag: String, message: String, throwable: Throwable? = null)
    }

    class EmptyLogger : LoggerSpec {
        override fun e(tag: String, message: String, throwable: Throwable?) {}

        override fun w(tag: String, message: String, throwable: Throwable?) {}

        override fun d(tag: String, message: String, throwable: Throwable?) {}

        override fun i(tag: String, message: String, throwable: Throwable?) {}
    }

    class AndroidLogger : LoggerSpec {
        override fun e(tag: String, message: String, throwable: Throwable?) {
            Log.e(tag, message, throwable)
        }

        override fun w(tag: String, message: String, throwable: Throwable?) {
            Log.w(tag, message, throwable)
        }

        override fun d(tag: String, message: String, throwable: Throwable?) {
            Log.d(tag, message, throwable)
        }

        override fun i(tag: String, message: String, throwable: Throwable?) {
            Log.i(tag, message, throwable)
        }

    }

    class UnitTestLogger : LoggerSpec {
        override fun e(tag: String, message: String, throwable: Throwable?) {
            println("[ERROR] $tag: $message")
        }

        override fun w(tag: String, message: String, throwable: Throwable?) {
            println("[WARNING] $tag: $message")
        }

        override fun d(tag: String, message: String, throwable: Throwable?) {
            println("[DEBUG] $tag: $message")
        }

        override fun i(tag: String, message: String, throwable: Throwable?) {
            println("[INFO] $tag: $message")
        }

    }

}