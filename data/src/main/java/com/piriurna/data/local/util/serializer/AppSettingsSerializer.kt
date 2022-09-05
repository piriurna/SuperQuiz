package com.piriurna.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.piriurna.data.local.models.AppSettingsStore
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object AppSettingsSerializer : Serializer<AppSettingsStore> {

    override val defaultValue: AppSettingsStore
        get() = AppSettingsStore()

    override suspend fun readFrom(input: InputStream): AppSettingsStore {
        return try {
            Json.decodeFromString(
                deserializer = AppSettingsStore.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettingsStore, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppSettingsStore.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }


}