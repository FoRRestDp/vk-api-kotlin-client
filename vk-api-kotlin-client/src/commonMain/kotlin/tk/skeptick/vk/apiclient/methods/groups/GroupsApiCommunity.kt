package tk.skeptick.vk.apiclient.methods.groups

import kotlinx.serialization.ExperimentalSerializationApi
import tk.skeptick.vk.apiclient.VkApiRequest

interface GroupsApiCommunity : GroupsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/groups.getTokenPermissions">VK API</a>
     */
    @ExperimentalSerializationApi
    fun getTokenPermissions(): VkApiRequest<CommunityTokenPermissions>

}