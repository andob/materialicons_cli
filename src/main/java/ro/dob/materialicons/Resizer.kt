package ro.dob.materialicons

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
