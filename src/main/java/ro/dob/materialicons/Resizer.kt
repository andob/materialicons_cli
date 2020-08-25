package ro.dob.materialicons

import ro.dob.materialicons.model.VectorialIcon
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun resizePNGIcon(file : File, widthInDp : Int, heightInDp : Int)
{
    val image=ImageIO.read(file)
    val widthInPx=(image.width*widthInDp)/48
    val heightInPx=(image.height*heightInDp)/48
    val tmpImage=image.getScaledInstance(widthInPx, widthInPx, Image.SCALE_SMOOTH)
    val resizedImage=BufferedImage(widthInPx, heightInPx, BufferedImage.TYPE_INT_ARGB)
    val graphics=resizedImage.createGraphics()
    graphics.drawImage(tmpImage, 0, 0, null)
    graphics.dispose()
    ImageIO.write(resizedImage, "png", file)
}

fun resizeVectorialIcon(icon : VectorialIcon, widthInDp : Int, heightInDp : Int)
{
    icon.xmlCode=icon.xmlCode.replaceFirst(
        "    android:height=\"24dp\"",
        "    android:height=\"${heightInDp}dp\"")

    icon.xmlCode=icon.xmlCode.replaceFirst(
        "    android:width=\"24dp\"",
        "    android:width=\"${widthInDp}dp\"")

    icon.xmlCode=icon.xmlCode.replaceFirst(
        "    android:viewportHeight=\"24\"",
        "    android:viewportHeight=\"$heightInDp\"")

    icon.xmlCode=icon.xmlCode.replaceFirst(
        "    android:viewportWidth=\"24\"",
        "    android:viewportWidth=\"$widthInDp\"")
}
