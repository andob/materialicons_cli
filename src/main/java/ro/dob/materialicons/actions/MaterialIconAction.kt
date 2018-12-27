package ro.dob.materialicons.actions

abstract class MaterialIconAction
(
    val moduleName : String
)
{
    val outputPath : String by lazy { "${System.getProperty("user.dir")}/$moduleName/src/main/res/" }

    abstract fun isValid() : Boolean
    abstract fun execute()
}