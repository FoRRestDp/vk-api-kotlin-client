package tk.skeptick.vk.apiclient.methods

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.Conversation
import tk.skeptick.vk.apiclient.domain.models.User

interface ListResponse<out T> {
    val count: Int
    val items: List<T>
}

@Serializable
data class DefaultListResponse<out T>(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<T>
) : ListResponse<T>

@Serializable
data class ExtendedListResponse<out T>(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<T>,
    @SerialName("profiles") val profiles: List<User>? = null,
    @SerialName("groups") val groups: List<Community>? = null,
    @SerialName("conversations") val conversations: List<Conversation>? = null
) : ListResponse<T>