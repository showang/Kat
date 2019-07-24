package github.showang.kat

import kotlin.reflect.KMutableProperty0

fun <T> T.assignN(ref: KMutableProperty0<T?>): T {
    ref.set(this)
    return this
}

fun <T> T.assign(ref: KMutableProperty0<T>): T {
    ref.set(this)
    return this
}

fun <T, R> T.forceAssign(ref: KMutableProperty0<R>): T {
    @Suppress("UNCHECKED_CAST")
    ref.set(this as R)
    return this
}

fun measure(block: () -> Unit) =
    System.currentTimeMillis().let {
        block()
        System.currentTimeMillis() - it
    }