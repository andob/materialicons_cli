package ro.dob.materialicons.api.request

import ro.dob.materialicons.api.ApiClient
import ro.dob.materialicons.api.response.InitializeApiResponse

class InitializeApiRequest : BaseRequest<InitializeApiResponse>()
{
    override fun api() =
        ApiClient.Instance.initialize()
}
