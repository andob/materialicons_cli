package ro.dob.materialicons.model

enum class DrawableDir
{
    Mdpi,
    Hdpi,
    XHdpi,
    XXHdpi,
    XXXHdpi;

    override fun toString(): String = when (this)
    {
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
            consumer(Mdpi)
            consumer(Hdpi)
            consumer(XHdpi)
            consumer(XXHdpi)
            consumer(XXXHdpi)
        }
    }
}
