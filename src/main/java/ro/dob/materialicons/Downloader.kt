package ro.dob.materialicons

import okio.Okio
import ro.dob.materialicons.api.request.DownloadIconRequest
import ro.dob.materialicons.api.request.FetchIconsRequest
import ro.dob.materialicons.api.request.InitializeApiRequest
import java.io.File

fun downloadIcon(iconName : String, outputFile : File)
{
    val iconsPackage=InitializeApiRequest().execute().body()!!.packages[0]
    val icon=FetchIconsRequest(iconsPackage, iconName).execute().body()!!.icons.find { it.name==iconName }!!
    val body=DownloadIconRequest(icon).execute().body()!!

    val sink=Okio.buffer(Okio.sink(outputFile))
    sink.writeAll(body.source())
    sink.close()
}
