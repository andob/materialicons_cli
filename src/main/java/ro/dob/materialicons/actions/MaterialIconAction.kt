package ro.dob.materialicons.actions

abstract class MaterialIconAction
{
    val outputPath : String by lazy { "${System.getProperty("user.dir")}/app/src/main/res/" }

    abstract fun isValid() : Boolean
    abstract fun execute()
}