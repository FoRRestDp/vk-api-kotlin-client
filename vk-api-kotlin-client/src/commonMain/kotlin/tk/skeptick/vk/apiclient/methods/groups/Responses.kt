package tk.skeptick.vk.apiclient.methods.groups

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum
import tk.skeptick.vk.apiclient.domain.AccessPermissionsCommunity
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.Geo
import tk.skeptick.vk.apiclient.domain.models.User

@Serializable(with = PublicUnitAccessType.Companion::class)
enum class PublicUnitAccessType(override val value: Int) : IntEnum {
    DISABLED(0),
    OPEN(1);

    companion object : EnumIntSerializer<PublicUnitAccessType>(PublicUnitAccessType::class, values())
}

@Serializable(with = GroupUnitAccessType.Companion::class)
enum class GroupUnitAccessType(override val value: Int) : IntEnum {
    DISABLED(0),
    OPEN(1),
    LIMITED(2);

    companion object : EnumIntSerializer<GroupUnitAccessType>(GroupUnitAccessType::class, values())
}

@Serializable(with = GroupUnitAccessTypeExtended.Companion::class)
enum class GroupUnitAccessTypeExtended(override val value: Int) : IntEnum {
    DISABLED(0),
    OPEN(1),
    LIMITED(2),
    CLOSED(3);

    companion object : EnumIntSerializer<GroupUnitAccessTypeExtended>(GroupUnitAccessTypeExtended::class, values())
}

@Serializable
data class AddLinkResponse( // TODO
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("name") val name: String,
    @SerialName("edit_title") val canEditTitle: BooleanInt,
    @SerialName("desc") val description: String,
    @SerialName("image_processing") val isImageInProcessing: BooleanInt)

@Serializable
data class EditPlaceResponse(
    @SerialName("success") val isSuccess: BooleanInt,
    @SerialName("address") val address: String)

@Serializable
data class CommunityBan(
    @SerialName("ban_info") val banInfo: Info,
    @SerialName("type") val type: Type,
    @SerialName("group") val group: Community? = null,
    @SerialName("profile") val profile: User? = null) {

    @Serializable
    enum class Type(val value: String) {
        @SerialName("group") GROUP("group"),
        @SerialName("profile") PROFILE("profile")
    }

    @Serializable
    data class Info(
        @SerialName("admin_id") val adminId: Int,
        @SerialName("date") val date: Int,
        @SerialName("comment") val comment: String,
        @SerialName("end_date") val endDate: Int,
        @SerialName("reason") val reason: Reason) {

        val isPermanently: Boolean get() = endDate == 0
        
    }

    @Serializable(with = Reason.Companion::class)
    enum class Reason(override val value: Int) : IntEnum {
        OTHER(0),
        SPAM(1),
        HARASSMENT(2),
        PROFANITY(3),
        IRRELEVANT_MESSAGES(4);

        companion object : EnumIntSerializer<Reason>(Reason::class, values())
    }

}

@Serializable
data class CommunityCallbackServer(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("creator_id") val creatorId: Int,
    @SerialName("url") val url: String,
    @SerialName("secret_key") val secretKey: String,
    @SerialName("status") val status: Status) {

    @Serializable
    enum class Status(val value: String) {
        @SerialName("unconfigured") UNCONFIGURED("unconfigured"),
        @SerialName("failed") FAILED("failed"),
        @SerialName("wait") WAIT("wait"),
        @SerialName("ok") OK("ok")
    }

}

@Serializable
data class ServerSettings(
    @SerialName("is_enabled") val isEnabled: Boolean? = null,
    @SerialName("api_version") val apiVersion: String,
    @SerialName("events") val events: Events) {

    @Serializable
    data class Events(
        @SerialName("message_reply") val messageReply: BooleanInt,
        @SerialName("messages_edit") val messagesEdit: BooleanInt,
        @SerialName("message_allow") val messageAllow: BooleanInt,
        @SerialName("message_deny") val messageDeny: BooleanInt,
        @SerialName("message_typing_state") val messageTypingState: BooleanInt,
        @SerialName("message_read") val messageRead: BooleanInt,
        @SerialName("photo_new") val photoNew: BooleanInt,
        @SerialName("photo_comment_new") val photoCommentNew: BooleanInt,
        @SerialName("photo_comment_edit") val photoCommentEdit: BooleanInt,
        @SerialName("photo_comment_restore") val photoCommentRestore: BooleanInt,
        @SerialName("photo_comment_delete") val photoCommentDelete: BooleanInt,
        @SerialName("audio_new") val audioNew: BooleanInt,
        @SerialName("video_new") val videoNew: BooleanInt,
        @SerialName("video_comment_new") val videoCommentNew: BooleanInt,
        @SerialName("video_comment_edit") val videoCommentEdit: BooleanInt,
        @SerialName("video_comment_restore") val videoCommentRestore: BooleanInt,
        @SerialName("video_comment_delete") val videoCommentDelete: BooleanInt,
        @SerialName("wall_post_new") val wallPostNew: BooleanInt,
        @SerialName("wall_repost") val wallRepost: BooleanInt,
        @SerialName("wall_reply_new") val wallReplyNew: BooleanInt,
        @SerialName("wall_reply_edit") val wallReplyEdit: BooleanInt,
        @SerialName("wall_reply_restore") val wallReplyRestore: BooleanInt,
        @SerialName("wall_reply_delete") val wallReplyDelete: BooleanInt,
        @SerialName("board_post_new") val boardPostNew: BooleanInt,
        @SerialName("board_post_edit") val boardPostEdit: BooleanInt,
        @SerialName("board_post_restore") val boardPostRestore: BooleanInt,
        @SerialName("board_post_delete") val boardPostDelete: BooleanInt,
        @SerialName("market_comment_new") val marketCommentNew: BooleanInt,
        @SerialName("market_comment_edit") val marketCommentEdit: BooleanInt,
        @SerialName("market_comment_restore") val marketCommentRestore: BooleanInt,
        @SerialName("market_comment_delete") val marketCommentDelete: BooleanInt,
        @SerialName("group_leave") val groupLeave: BooleanInt,
        @SerialName("group_join") val groupJoin: BooleanInt,
        @SerialName("user_block") val userBlock: BooleanInt,
        @SerialName("user_unblock") val userUnblock: BooleanInt,
        @SerialName("poll_vote_new") val pollVoteNew: BooleanInt,
        @SerialName("group_officers_edit") val groupOfficersEdit: BooleanInt,
        @SerialName("group_change_settings") val groupChangeSettings: BooleanInt,
        @SerialName("group_change_photo") val groupChangePhoto: BooleanInt,
        @SerialName("vkpay_transaction") val vkPayTransaction: BooleanInt,
        @SerialName("app_payload") val appPayload: BooleanInt)

}

@Serializable
data class CommunitiesCatalog(
    @SerialName("enabled") val isEnabled: BooleanInt,
    @SerialName("categories") val categories: List<Category>? = null) {

    @Serializable
    data class Category(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("page_count") val pageCount: Int? = null,
        @SerialName("page_previews") val pagePreviews: List<Community>? = null,
        @SerialName("subcategories") val subcategories: List<Category>? = null)

}

@Serializable
data class LongPollServerResponse(
    @SerialName("key") val key: String,
    @SerialName("server") val server: String,
    @SerialName("ts") val ts: Long)

@Serializable
data class CommunityManager(
    @SerialName("id") val id: Int,
    @SerialName("role") val role: Role) {

    @Serializable
    enum class Role(val value: String) {
        @SerialName("moderator") MODERATOR("moderator"),
        @SerialName("editor") EDITOR("editor"),
        @SerialName("administrator") ADMINISTRATOR("administrator"),
        @SerialName("creator") CREATOR("creator")
    }

}

@Serializable
data class CommunityOnlineStatus(
    @SerialName("status") val status: Status,
    @SerialName("minutes") val minutes: Int? = null) {

    @Serializable
    enum class Status(val value: String) {
        @SerialName("none") NONE("none"),
        @SerialName("online") ONLINE("online"),
        @SerialName("answer_mark") ANSWER_MARK("answer_mark")
    }

}

@Serializable
data class CommunitySettings(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("address") val address: String,
    @SerialName("age_limits") val ageLimits: Community.AgeLimits,
    @SerialName("country_id") val countryId: Int,
    @SerialName("city_id") val cityId: Int,
    @SerialName("wall") val wall: GroupUnitAccessTypeExtended,
    @SerialName("photos") val photos: GroupUnitAccessType,
    @SerialName("video") val video: GroupUnitAccessType,
    @SerialName("audio") val audio: GroupUnitAccessType,
    @SerialName("docs") val docs: GroupUnitAccessType,
    @SerialName("topics") val topics: GroupUnitAccessType,
    @SerialName("wiki") val wiki: GroupUnitAccessType,
    @SerialName("messages") val messages: BooleanInt,
    @SerialName("articles") val articles: BooleanInt,
    @SerialName("obscene_filter") val obsceneFilter: BooleanInt,
    @SerialName("obscene_stopwords") val obsceneStopwords: BooleanInt,
    @SerialName("obscene_words") val obsceneWords: List<String>,
    @SerialName("website") val website: String,
    @SerialName("market") val market: Community.Market,
    @SerialName("sections_list") val sectionList: List<Map<Int, String>>,
    @SerialName("main_section") val mainSection: Community.MainSectionType,
    @SerialName("secondary_section") val secondarySection: Community.MainSectionType,
    @SerialName("access") val access: Community.CloseType? = null,
    @SerialName("rss") val rss: String? = null,
    @SerialName("public_category") val publicCategory: Int? = null,
    @SerialName("public_subcategory") val publicSubcategory: Int? = null,
    @SerialName("public_category_list") val publicCategoryList: List<CommunitiesCatalog.Category>? = null,
    @SerialName("links") val links: BooleanInt? = null,
    @SerialName("events") val events: BooleanInt? = null,
    @SerialName("places") val places: BooleanInt? = null,
    @SerialName("contacts") val contacts: BooleanInt? = null,
    @SerialName("addresses") val addresses: BooleanInt? = null,
    @SerialName("place") val place: Geo.Place? = null,
    @SerialName("start_date") val startDate: Int? = null,
    @SerialName("finish_date") val finishDate: Int? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("public_date") val publicDate: String? = null,
    @SerialName("public_date_label") val publicDateLabel: String? = null,
    @SerialName("suggested_privacy") val suggestedPrivacy: CommunitySuggestedPrivacyType? = null,
    @SerialName("action_button") val actionButton: Community.ActionButton? = null,
    @SerialName("live_covers") val liveCovers: Community.LiveCovers? = null) {

    @Serializable(with = CommunitySuggestedPrivacyType.Companion::class)
    enum class CommunitySuggestedPrivacyType(override val value: Int) : IntEnum {
        DISABLED(0),
        ALL(1),
        SUBSCRIBERS(2);

        companion object : EnumIntSerializer<CommunitySuggestedPrivacyType>(CommunitySuggestedPrivacyType::class, values())
    }

}

@ExperimentalSerializationApi
@Serializable
data class CommunityTokenPermissions(
    @SerialName("mask") val mask: AccessPermissionsCommunity,
    @SerialName("settings") val settings: List<Setting>) {

    @Serializable
    data class Setting(
        @SerialName("setting") val setting: AccessPermissionsCommunity,
        @SerialName("name") val name: String)

}

@Serializable
data class CommunityMemberResponse(
    @SerialName("member") val isMember: BooleanInt,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("request") val hasRequest: BooleanInt? = null,
    @SerialName("invitation") val isInvitation: BooleanInt? = null)