package ro.dob.materialicons

import ro.dob.materialicons.model.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

fun unpackIconPackPNGFiles(iconPackFile : File,
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
        var zipEntry=zipInput.nextEntry

        while(zipEntry.isDirectory)
            zipEntry=zipInput.nextEntry

        val outputFileNameTransformers=mutableListOf<(String) -> (String)>()
        var zipIconColorName : String = color.name
        var zipIconSize : Int = size

        if (color!=Colors.White&&color!=Colors.Grey&&color!=Colors.Black)
        {
            zipIconColorName=Colors.White.name
            outputFileNameTransformers.add { fileName ->
                fileName.replace("_${zipIconColorName}_", "_${color.name}_")
            }
        }

        if (size!=18&&size!=24&&size!=36&&size!=48)
        {
            zipIconSize=48
            outputFileNameTransformers.add { fileName ->
                fileName.replace("_${zipIconSize}dp", "_${size}dp")
            }
        }

        while(zipEntry!=null)
        {
            if (zipEntry.name.contains(zipIconColorName)&&
                zipEntry.name.contains(zipIconSize.toString()))
            {
                var outputFileName=zipEntry.name
                for (outputFileNameTransformer in outputFileNameTransformers)
                    outputFileName=outputFileNameTransformer(outputFileName)
                val outputFile=File(outputPath+outputFileName)
                var outputFileStream : FileOutputStream? = null

                try
                {
                    outputFileStream=FileOutputStream(outputFile)
                    val buffer=ByteArray(UNZIP_BUFFER_SIZE)
                    var numberOfReadBytes : Int

                    do
                    {
                        numberOfReadBytes=zipInput.read(buffer, 0, UNZIP_BUFFER_SIZE)
                        if (numberOfReadBytes>0)
                        {
                            outputFileStream.write(buffer, 0, numberOfReadBytes)
                        }
                    }
                    while(numberOfReadBytes>0)
                }
                finally
                {
                    outputFileStream?.close()
                }

                if (color!=Colors.White&&color!=Colors.Grey&&color!=Colors.Black)
                    tintPNGIcon(file = outputFile, color = color)

                if (size!=18&&size!=24&&size!=36&&size!=48)
                    resizePNGIcon(file = outputFile, widthInDp = size, heightInDp = size)
            }

            zipEntry=zipInput.nextEntry
        }
    }
    finally
    {
        zipInput?.closeEntry()
        zipInput?.close()
        fileInput?.close()
    }
}

fun unpackIconPackVectorialFile(iconPackFile : File,
                                iconName : String,
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
        var zipEntry=zipInput.nextEntry

        while(zipEntry.isDirectory)
            zipEntry=zipInput.nextEntry

        while(zipEntry!=null)
        {
            if (zipEntry.name=="drawable/${iconName.replace("-", "_")}.xml")
            {
                val iconOutputStream=ByteArrayOutputStream()
                val buffer=ByteArray(UNZIP_BUFFER_SIZE)
                var numberOfReadBytes : Int

                do
                {
                    numberOfReadBytes=zipInput.read(buffer, 0, UNZIP_BUFFER_SIZE)
                    if (numberOfReadBytes>0)
                    {
                        iconOutputStream.write(buffer, 0, numberOfReadBytes)
                    }
                }
                while(numberOfReadBytes>0)

                val vectorialIcon=VectorialIcon(xmlCode = String(iconOutputStream.toByteArray()))

                tintVectorialIcon(icon = vectorialIcon, color = color)

                resizeVectorialIcon(icon = vectorialIcon, widthInDp = size, heightInDp = size)

                val outputFile=File(outputPath, "drawable/ic_${iconName.replace("-", "_")}_${color.name}_${size}dp.xml")

                FileOutputStream(outputFile).use { fileOutputStream ->
                    fileOutputStream.write(vectorialIcon.toByteArray())
                }
            }

            zipEntry=zipInput.nextEntry
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
        val dir=File(dirPath)
        if (!dir.exists())
            dir.mkdir()
    }
}
