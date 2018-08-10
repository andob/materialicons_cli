package ro.dob.materialicons.model

enum class Color
{
    White,
    Black,
    Grey,
    Nil;

    override fun toString() = when (this)
    {
        White -> "white"
        Black -> "black"
        Grey -> "grey"
        Nil -> ""
    }

    companion object
    {
        fun fromString(value : String) : Color = when (value)
        {
            "white" -> White
            "black" -> Black
            "grey" -> Grey
            else -> Nil
        }
    }
}