package tk.skeptick.vk.apiclient.methods.utils

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class CheckLinkResponse(
    @SerialName("status") val status: Status,
    @SerialName("link") val link: String) {

    @Serializable(with = Status.Companion::class)
    enum class Status(override val value: String) : SerializableEnum<String> {
        NOT_BANNED("not_banned"),
        BANNED("banned"),
        PROCESSING("processing");

        companion object : EnumStringSerializer<Status>(
            clazz = Status::class,
            values = enumValues()
        )
    }

}

@Serializable
data class ShortLink(
    @SerialName("short_url") val shortUrl: String,
    @SerialName("url") val url: String,
    @SerialName("key") val key: String,
    @Optional @SerialName("access_key") val accessKey: String? = null,
    @Optional @SerialName("timestamp") val timestamp: Int? = null,
    @Optional @SerialName("views") val views: Int? = null)

@Serializable
data class ShortLinkStats(
    @SerialName("key") val key: String,
    @SerialName("stats") val stats: List<Stats>) {

    @Serializable
    data class Stats(
        @SerialName("timestamp") val timestamp: Int,
        @SerialName("views") val views: Int,
        @Optional @SerialName("sex_age") val sexAge: List<SexAge>? = null,
        @Optional @SerialName("countries") val countries: List<Country>? = null,
        @Optional @SerialName("cities") val cities: List<City>? = null) {

        @Serializable
        data class SexAge(
            @SerialName("age_range") val ageRange: String,
            @SerialName("female") val female: Int,
            @SerialName("male") val male: Int)

        @Serializable
        data class Country(
            @SerialName("country_id") val countryId: Int,
            @SerialName("views") val views: Int)

        @Serializable
        data class City(
            @SerialName("city_id") val cityId: Int,
            @SerialName("views") val views: Int)

    }

}

@Serializable
data class ResolveScreenNameResponse(
    @SerialName("type") val type: Type,
    @SerialName("object_id") val objectId: Int) {

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        USER("user"),
        GROUP("group"),
        APPLICATION("application");

        companion object : EnumStringSerializer<Type>(
            clazz = Type::class,
            values = enumValues()
        )
    }

}