package com.piriurna.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.piriurna.data.local.models.ProfileSettingsStore
import com.piriurna.data.mappers.toProfileSettings
import com.piriurna.data.mappers.toProfileSettingsStore
import com.piriurna.domain.models.ProfileSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ProfileSettingsSerializer : Serializer<ProfileSettings> {
    override val defaultValue: ProfileSettings
        get() = ProfileSettings()

    override suspend fun readFrom(input: InputStream): ProfileSettings {
        return try {
            Json.decodeFromString(
                deserializer = ProfileSettingsStore.serializer(),
                string = input.readBytes().decodeToString()
            ).toProfileSettings()
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProfileSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ProfileSettingsStore.serializer(),
                value = t.toProfileSettingsStore()
            ).encodeToByteArray()
        )
    }
}