package ro.dob.materialicons.api.request

import retrofit2.Call

abstract class BaseRequest<RESPONSE>
{
    protected abstract fun api() : Call<RESPONSE>

    open fun execute() = api().execute()
}
