package ro.dob.materialicons.model

data class Color
(
    val red : Short,
    val green : Short,
    val blue : Short,
    val name : String
)
{
    private fun Short.toHexString() = toString(radix = 16)
    fun toHexString() = "#${red.toHexString()}${green.toHexString()}${blue.toHexString()}"

    companion object
    {
        fun parse(colorName : String) : Color = when(colorName.toLowerCase())
        {
            "white", "ffffff" -> Colors.White
            "black", "000000" -> Colors.Black
            "grey",  "7f7f7f" -> Colors.Grey
            else ->
            {
                val matchResult="[A-Za-z]*=(#?)[0-9a-fA-F]*".toRegex().find(colorName)
                if (matchResult!=null&&matchResult.groups.firstOrNull()?.value==colorName)
                {
                    val tokens=colorName.split("=")
                    val hexCode=tokens[1]
                    val intColor=Integer.parseInt(hexCode.replaceFirst("#", ""), 16)
                    Color(red   = ((intColor and 0xff0000) shr 16).toShort(),
                        green = ((intColor and 0xff00) shr 8).toShort(),
                        blue  =  (intColor and 0xff).toShort(),
                        name  = tokens[0])
                }
                else throw RuntimeException("invalid color: $colorName")
            }
        }
    }
}

object Colors
{
    val White = Color(red = 255, green = 255, blue = 255, name = "white")
    val Grey  = Color(red = 127, green = 127, blue = 127, name = "grey")
    val Black = Color(red =   0, green =   0, blue =   0, name = "black")
}
