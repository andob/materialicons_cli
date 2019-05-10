package ro.dob.materialicons.api.request

import okhttp3.ResponseBody
import ro.dob.materialicons.api.ApiClient
import ro.dob.materialicons.model.Icon

class DownloadIconRequest
(
    val icon : Icon
) : BaseRequest<ResponseBody>()
{
    override fun api() =
        ApiClient.Instance.downloadIcon(icon.id)
}
