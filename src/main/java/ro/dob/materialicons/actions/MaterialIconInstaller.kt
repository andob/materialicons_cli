package ro.dob.materialicons.actions

import ro.dob.materialicons.DEBUG
import ro.dob.materialicons.UNZIP_BUFFER_SIZE
import ro.dob.materialicons.model.Color
import ro.dob.materialicons.model.DrawableDir
import ro.dob.materialicons.model.Size
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

class MaterialIconInstaller
(
        val color : Color,
        val size : Size,
        moduleName : String
) : MaterialIconAction(moduleName)
{
    var zipPath : String = ""

    constructor(zipPath : String, color : Color, size : Size, moduleName : String) : this(color, size, moduleName)
    {
        this.zipPath=zipPath.replace("~", System.getProperty("user.home"))
    }

    override fun isValid(): Boolean =
            File(zipPath).exists()&&
            color!=Color.Nil&&
            size!=Size.Nil

    override fun execute()
    {
        createDrawableDirsIfNotExists()

        var fileInput : FileInputStream? = null
        var zipInput : ZipInputStream? = null

        try
        {
            fileInput=FileInputStream(zipPath)
            zipInput=ZipInputStream(fileInput)
            var entry=zipInput.nextEntry

            while(entry.isDirectory)
                entry=zipInput.nextEntry

            while(entry!=null)
            {
                if (entry.name.contains(color.toString())&&
                    entry.name.contains(size.toString()))
                {
                    var out : FileOutputStream? = null

                    try
                    {
                        out=FileOutputStream(outputPath+entry.name)
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
                    catch (ex : Exception)
                    {
                        if (DEBUG)
                            ex.printStackTrace()
                    }
                    finally
                    {
                        out?.close()
                    }
                }

                entry=zipInput.nextEntry
            }
        }
        catch (ex : Exception)
        {
            if (DEBUG)
                ex.printStackTrace()
        }
        finally
        {
            zipInput?.closeEntry()
            zipInput?.close()
            fileInput?.close()
        }
    }

    private fun createDrawableDirsIfNotExists()
    {
        DrawableDir.forEach { drawableDir ->
            val dirPath=outputPath+drawableDir
            val dir=File(dirPath)
            if (!dir.exists())
                dir.mkdir()
        }
    }
}