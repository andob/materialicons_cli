package ro.dob.materialicons.model

enum class Size
{
    _18,
    _24,
    _36,
    _48,
    Nil;

    override fun toString(): String
    {
        return toInt().toString()
    }

    fun toInt() : Int = when (this)
    {
        _18 -> 18
        _24 -> 24
        _36 -> 36
        _48 -> 48
        Nil -> 0
    }

    companion object
    {
        fun fromInt(value : Int) : Size = when(value)
        {
            18 -> _18
            24 -> _24
            36 -> _36
            48 -> _48
            else -> Nil
        }
    }
}