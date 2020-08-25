package ro.dob.materialicons.model

class VectorialIcon
(
    var xmlCode : String
)
{
    override fun toString() = xmlCode
    fun toByteArray() = xmlCode.toByteArray()
}
