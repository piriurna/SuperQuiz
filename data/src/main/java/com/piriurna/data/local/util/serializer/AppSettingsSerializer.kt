package com.piriurna.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.piriurna.data.local.models.AppSettingsStore
import com.piriurna.data.mappers.toAppSettings
import com.piriurna.data.mappers.toAppSettingsStore
import com.piriurna.domain.models.AppSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object AppSettingsSerializer : Serializer<AppSettings> {

    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            Json.decodeFromString(
                deserializer = AppSettingsStore.serializer(),
                string = input.readBytes().decodeToString()
            ).toAppSettings()
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppSettingsStore.serializer(),
                value = t.toAppSettingsStore()
            ).encodeToByteArray()
        )
    }


}