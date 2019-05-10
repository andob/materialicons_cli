package ro.dob.materialicons

import ro.dob.materialicons.model.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

fun unpackIconPack(iconPackFile : File,
                   color : Color,
                   size : Int,
                   moduleName : String)
{
    val outputPath : String by lazy { "${System.getProperty("user.dir")}/$moduleName/src/main/res/" }

    createDrawableDirs(outputPath = outputPath)

    var fileInput : FileInputStream? = null
    var zipInput : ZipInputStream? = null

    try
    {
        fileInput=FileInputStream(iconPackFile)
        zipInput=ZipInputStream(fileInput)
        var entry=zipInput.nextEntry

        while(entry.isDirectory)
            entry=zipInput.nextEntry

        while(entry!=null)
        {
            val outputFileNameTransformers=mutableListOf<(String) -> (String)>()
            var zipFileColorName : String = color.name
            var zipFileSize : Int = size

            if (!color.isStandardColor())
            {
                zipFileColorName=Colors.White.name
                outputFileNameTransformers.add { fileName ->
                    fileName.replace("_${zipFileColorName}_", "_${color.name}_")
                }
            }

            if (!size.isStandardSize())
            {
                zipFileSize=48
                outputFileNameTransformers.add { fileName ->
                    fileName.replace("_${zipFileSize}dp", "_${size}dp")
                }
            }

            if (entry.name.contains(zipFileColorName)&&
                entry.name.contains(zipFileSize.toString()))
            {
                var outputFileName=entry.name
                for (outputFileNameTransformer in outputFileNameTransformers)
                    outputFileName=outputFileNameTransformer(outputFileName)
                val outputFile=File(outputPath+outputFileName)
                var out : FileOutputStream? = null

                try
                {
                    out=FileOutputStream(outputFile)
                    val buffer=ByteArray(UNZIP_BUFFER_SIZE)
                    var len : Int

                    do
                    {
                        len=zipInput.read(buffer, 0, UNZIP_BUFFER_SIZE)
                        if (len>0)
                        {
                            out.write(buffer, 0, len)
                        }
                    }
                    while(len>0)
                }
                finally
                {
                    out?.close()
                }

                if (!color.isStandardColor()) tintIcon(file = outputFile, color = color)
                if (!size.isStandardSize()) resizeIcon(file = outputFile, widthInDp = size, heightInDp = size)
            }

            entry=zipInput.nextEntry
        }
    }
    finally
    {
        zipInput?.closeEntry()
        zipInput?.close()
        fileInput?.close()
    }
}

fun createDrawableDirs(outputPath : String)
{
    DrawableDir.forEach { drawableDir ->
        val dirPath=outputPath+drawableDir
        val dir= File(dirPath)
        if (!dir.exists())
            dir.mkdir()
    }
}
