package ro.dob.materialicons.model

enum class DrawableDir
{
    Default,
    Mdpi,
    Hdpi,
    XHdpi,
    XXHdpi,
    XXXHdpi;

    override fun toString(): String = when (this)
    {
        Default -> "drawable/"
        Mdpi -> "drawable-mdpi/"
        Hdpi -> "drawable-hdpi/"
        XHdpi -> "drawable-xhdpi/"
        XXHdpi -> "drawable-xxhdpi/"
        XXXHdpi -> "drawable-xxxhdpi/"
    }

    companion object
    {
        inline fun forEach(consumer : (DrawableDir) -> (Unit))
        {
            consumer(Default)
            consumer(Mdpi)
            consumer(Hdpi)
            consumer(XHdpi)
            consumer(XXHdpi)
            consumer(XXXHdpi)
        }
    }
}
