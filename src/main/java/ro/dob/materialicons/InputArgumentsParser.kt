package ro.dob.materialicons

import ro.dob.materialicons.model.Color
import java.io.File

object InputArgumentsParser
{
    const val KEY_COLOR = "-color"
    const val KEY_SIZE = "-size"
    const val KEY_MODULE = "-moduleName"

    fun parse(args : Array<String>)
    {
        if (!args.contains(KEY_COLOR))
            throw RuntimeException("Please specify color")
        if (!args.contains(KEY_SIZE))
            throw RuntimeException("Please specify size")

        val iconName=args[0].replace("~", System.getProperty("user.home"))
        var iconPackFile=File(iconName)
        if (!iconPackFile.exists())
        {
            iconPackFile=File("temp.zip")
            downloadIcon(iconName = iconName, outputFile = iconPackFile)
            iconPackFile.deleteOnExit()
        }

        val color=Color.parse(args[args.indexOfFirst { it==KEY_COLOR }+1])
        val size=args[args.indexOfFirst { it==KEY_SIZE }+1].toInt()
        val moduleName=if (args.contains(KEY_MODULE))
            args[args.indexOfFirst { it==KEY_MODULE }+1]
        else "app"

        unpackIconPack(
                iconPackFile = iconPackFile,
                color = color,
                size = size,
                moduleName = moduleName)
    }
}
