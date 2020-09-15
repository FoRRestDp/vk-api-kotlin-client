package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import tk.skeptick.vk.apiclient.json

@Serializable
data class MessagePayload(val value: String) {

    fun <T> to(loader: DeserializationStrategy<T>): T = json.decodeFromString(loader, value)

    inline fun <reified T : Any> to(): T = to(serializer<T>())

    @ExperimentalSerializationApi
    @Serializer(forClass = MessagePayload::class)
    companion object : KSerializer<MessagePayload> {

        override fun serialize(encoder: Encoder, value: MessagePayload) = encoder.encodeString(value.value)

        override fun deserialize(decoder: Decoder): MessagePayload = MessagePayload(decoder.decodeString())

        fun <T> from(strategy: SerializationStrategy<T>, value: T): MessagePayload = MessagePayload(json.encodeToString(strategy, value))

        inline fun <reified T : Any> from(value: T): MessagePayload = from(serializer<T>(), value)

    }

}