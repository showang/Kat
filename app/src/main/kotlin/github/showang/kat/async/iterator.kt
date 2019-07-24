package github.showang.kat.async

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun <A, B> Iterable<A>.pMap(f: (A) -> B): List<B> = runBlocking {
    map {
        async {
            f(it)
        }
    }.map {
        it.await()
    }
}