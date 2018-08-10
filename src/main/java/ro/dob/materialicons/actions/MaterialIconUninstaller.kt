package ro.dob.materialicons.actions

import ro.dob.materialicons.model.DrawableDir
import java.io.File

class MaterialIconUninstaller : MaterialIconAction
{
    var fileName : String = ""
    val filePaths : MutableList<String> = mutableListOf()

    constructor(fileName : String) : super()
    {
        this.fileName=
            if (fileName.endsWith(".png"))
                fileName
            else "${fileName}.png"
    }

    override fun isValid(): Boolean
    {
        var valid = true

        DrawableDir.forEach { drawableDir ->
            if (valid)
            {
                val file=findFileInDir(drawableDir)
                if (file!=null)
                {
                    filePaths.add(file.absolutePath)
                }
                else
                {
                    valid=false
                }
            }
        }

        return valid
    }

    private fun findFileInDir(drawableDir: DrawableDir) : File?
    {
        val dirPath=outputPath+drawableDir
        val dir=File(dirPath)
        if (dir.exists())
        {
            return dir.listFiles()
                    .asList()
                    .filter { file -> file.name.equals(fileName) }
                    .firstOrNull()
        }

        return null
    }

    override fun execute()
    {
        for (filePath in filePaths)
        {
            val file=File(filePath)
            if (file.exists())
                file.delete()
        }
    }
}