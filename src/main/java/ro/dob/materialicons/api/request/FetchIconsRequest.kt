package ro.dob.materialicons.api.request

import ro.dob.materialicons.api.ApiClient
import ro.dob.materialicons.api.response.FetchIconsResponse
import ro.dob.materialicons.model.IconsPackage

class FetchIconsRequest
(
    val iconsPackage : IconsPackage,
    val iconName : String
) : BaseRequest<FetchIconsResponse>()
{
    override fun api() =
        ApiClient.Instance.fetchIcons(
            packageId = iconsPackage.id,
            iconName = iconName)
}
