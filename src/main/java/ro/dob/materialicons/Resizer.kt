package ro.dob.materialicons

import ro.dob.materialicons.model.MAX_ICON_SIZE_IN_DP
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun resizeIcon(file : File, widthInDp : Int, heightInDp : Int)
{
    val image=ImageIO.read(file)
    val widthInPx=(image.width*widthInDp)/MAX_ICON_SIZE_IN_DP
    val heightInPx=(image.height*heightInDp)/MAX_ICON_SIZE_IN_DP
    val tmpImage=image.getScaledInstance(widthInPx, widthInPx, Image.SCALE_SMOOTH)
    val resizedImage=BufferedImage(widthInPx, heightInPx, BufferedImage.TYPE_INT_ARGB)
    val graphics=resizedImage.createGraphics()
    graphics.drawImage(tmpImage, 0, 0, null)
    graphics.dispose()
    ImageIO.write(resizedImage, "png", file)
}
