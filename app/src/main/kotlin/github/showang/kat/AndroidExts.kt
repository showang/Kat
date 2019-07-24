package github.showang.kat

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> AppCompatActivity.extra(key: String, default: T) =
    object : ReadOnlyProperty<AppCompatActivity, T> {

        private var mData: T? = null

        override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T =
            mData ?: with(thisRef.intent) {
                when (default) {
                    is Serializable -> getSerializableExtra(key) ?: default
                    is String -> getStringExtra(key) ?: default
                    is Int -> getIntExtra(key, default)
                    is Boolean -> getBooleanExtra(key, default)
                    is Long -> getLongExtra(key, default)
                    else -> throw IllegalStateException("Unknown Type")
                }
            }.cast().assignN(::mData)


        @Suppress("UNCHECKED_CAST")
        private fun <R> R.cast() = this as T
    }

fun <T> AppCompatActivity.extraN(key: String) =
    object : ReadOnlyProperty<AppCompatActivity, T?> {

        private var mData: T? = null

        override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T? =
            mData ?: with(thisRef.intent) {
                when (mData) {
                    is Serializable? -> getSerializableExtra(key)
                    is String? -> getStringExtra(key)
                    is Int? -> getIntExtra(key, 0)
                    is Boolean? -> getBooleanExtra(key, false)
                    is Long? -> getLongExtra(key, 0L)
                    else -> throw IllegalStateException("Unknown Type")
                }
            }.cast().assignN(::mData)


        @Suppress("UNCHECKED_CAST")
        private fun <R> R.cast(): T? = this as T?
    }


fun <T> Fragment.extra(key: String, default: T) =
    object : ReadOnlyProperty<Fragment, T> {

        private var data: T? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
            data ?: arguments?.run {
                when (default) {
                    is Serializable -> getSerializable(key) ?: default
                    is String -> getString(key, default)
                    is Int -> getInt(key, default)
                    is Boolean -> getBoolean(key, default)
                    is Long -> getLong(key, default)
                    else -> throw IllegalStateException("Unknown Type")
                }.cast().assignN(::data)
            } ?: throw IllegalStateException("No argument found")


        @Suppress("UNCHECKED_CAST")
        private fun <R> R.cast(): T = this as T
    }

fun <T> Fragment.extraN(key: String) =
    object : ReadOnlyProperty<Fragment, T?> {

        private var data: T? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T? =
            data ?: arguments?.run {
                when (data) {
                    is Serializable? -> getSerializable(key)
                    is String? -> getString(key)
                    is Int? -> getInt(key)
                    is Boolean? -> getBoolean(key)
                    is Long? -> getLong(key)
                    else -> throw IllegalStateException("Unknown Type")
                }.cast().assign(::data)
            } ?: throw IllegalStateException("No argument found")


        @Suppress("UNCHECKED_CAST")
        private fun <R> R.cast(): T? = this as T?
    }