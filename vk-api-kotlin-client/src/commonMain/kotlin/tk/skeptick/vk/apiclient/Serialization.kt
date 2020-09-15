@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import tk.skeptick.vk.apiclient.domain.Keyboard
import tk.skeptick.vk.apiclient.domain.models.Address
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import kotlin.reflect.KClass

internal val json = Json {
    encodeDefaults = false
    ignoreUnknownKeys = true
    isLenient = false
    allowSpecialFloatingPointValues = false
    allowStructuredMapKeys = true
    prettyPrint = false
    useArrayPolymorphism = false

}

@Serializable
data class BooleanInt(val value: Boolean) {
    @ExperimentalSerializationApi
    @Serializer(forClass = BooleanInt::class)
    companion object : KSerializer<BooleanInt> {
        override fun serialize(encoder: Encoder, value: BooleanInt) = encoder.encodeInt(if (value.value) 1 else 0)
        override fun deserialize(decoder: Decoder): BooleanInt = BooleanInt(decoder.decodeNullableSerializableValue(Int.serializer().nullable) == 1)
    }
}

interface IntEnum {
    val value: Int
}

abstract class EnumIntSerializer<E>(clazz: KClass<E>, cases: Array<E>) : KSerializer<E> where E : Enum<E>, E : IntEnum {
    private val caseByInt: Map<Int, E> = cases.associateBy(IntEnum::value)
    @ExperimentalSerializationApi
    override val descriptor: SerialDescriptor = EnumDescriptor(clazz, cases)
    override fun serialize(encoder: Encoder, value: E) = encoder.encodeInt(value.value)
    override fun deserialize(decoder: Decoder): E = caseByInt.getValue(decoder.decodeInt())
}

@ExperimentalSerializationApi
@Suppress("FunctionName")
private fun <E : Enum<E>> EnumDescriptor(clazz: KClass<E>, cases: Array<E>): SerialDescriptor {
    return buildClassSerialDescriptor(clazz.simpleName ?: "") {
        for (case in cases) element(case.name, descriptor(case))
    }
}

@ExperimentalSerializationApi
private inline fun <E : Enum<E>> ClassSerialDescriptorBuilder.descriptor(case: E): SerialDescriptor {
    return buildClassSerialDescriptor("$serialName.${case.name}")
}

internal fun Keyboard.serialize(): String = json.encodeToString(Keyboard.serializer(), this)

internal fun Address.Timetable.serialize(): String = json.encodeToString(Address.Timetable.serializer(), this)

internal inline fun <T : Any> list(nestedSerializer: KSerializer<T>) = DefaultListResponse.serializer(nestedSerializer)

internal inline fun <T : Any> extendedList(nestedSerializer: KSerializer<T>) = ExtendedListResponse.serializer(nestedSerializer)