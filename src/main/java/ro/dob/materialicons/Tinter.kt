package ro.dob.materialicons

import ro.dob.materialicons.model.Color
import ro.dob.materialicons.model.VectorialIcon
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

private fun BufferedImage.createCompatibleImage() : BufferedImage
{
    val graphicsConfiguration=GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
    val newImage=graphicsConfiguration.createCompatibleImage(width, height, Transparency.TRANSLUCENT)
    newImage.coerceData(true)
    return newImage
}

private fun Graphics2D.applyQualityRenderingHints()
{
    setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
    setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY)
    setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE)
    setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
    setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE)
}

fun tintPNGIcon(file : File, color : Color)
{
    val image=ImageIO.read(file)
    val tintedImage=image.createCompatibleImage()
    val maskImage=image.createCompatibleImage()

    val maskImageGraphics=maskImage.createGraphics()
    maskImageGraphics.applyQualityRenderingHints()
    maskImageGraphics.drawImage(image, 0, 0, null)
    maskImageGraphics.composite=AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1f)
    maskImageGraphics.color=java.awt.Color(color.red.toInt(), color.green.toInt(), color.blue.toInt())
    maskImageGraphics.fillRect(0, 0, image.width, image.height)
    maskImageGraphics.dispose()

    val tintedImageGraphics=tintedImage.createGraphics()
    tintedImageGraphics.applyQualityRenderingHints()
    tintedImageGraphics.drawImage(image, 0, 0, null)
    tintedImageGraphics.drawImage(maskImage, 0, 0, null)
    tintedImageGraphics.dispose()

    ImageIO.write(tintedImage, "png", file)
}

fun tintVectorialIcon(icon : VectorialIcon, color : Color)
{
    icon.xmlCode=icon.xmlCode.replaceFirst(
        oldValue = "    <path android:fillColor=\"#000\" ",
        newValue = "    <path android:fillColor=\"${color.toHexString()}\" ")
}
