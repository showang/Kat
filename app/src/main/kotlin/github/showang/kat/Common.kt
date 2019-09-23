package github.showang.kat

import kotlin.reflect.KMutableProperty0

infix fun <T> T.assignN(ref: KMutableProperty0<T?>) = apply(ref::set)

infix fun <T> T.assign(ref: KMutableProperty0<T>) = apply(ref::set)

@Suppress("UNCHECKED_CAST")
fun <T, R> T.forceAssign(ref: KMutableProperty0<R>): T = apply { (this as R).let(ref::set) }

private val runStartTime = { block: () -> Unit -> System.currentTimeMillis().apply { block() } }

private val calcCost = { startTime: Long -> System.currentTimeMillis() - startTime }

val measure = runStartTime andThen calcCost

infix fun <P, R> Function0<P>.andThen(function: Function1<P, R>): Function0<R> =
    { function(invoke()) }

infix fun <P1, P2, R> Function1<P1, P2>.andThen(function: Function1<P2, R>): Function1<P1, R> =
    { function(invoke(it)) }

infix fun <R1, R2> Function0<R1>.with(function: Function0<R2>): Function0<Unit> = {
    invoke()
    function()
}

infix fun <P, R> P.bind(f: Function1<P, R>): Function0<R> = { f(this) }

infix fun <P1, P2, R> Pair<P1, P2>.bind(f: Function2<P1, P2, R>): Function0<R> =
    { f(first, second) }

fun <R> perform(what: () -> R) = what()

fun <P, R> perform(p: P, f: Function1<P, R>) = f(p)

fun <P1, P2, R> perform(p1: P1, p2: P2, f: Function2<P1, P2, R>) = f(p1, p2)