package ro.dob.materialicons

import ro.dob.materialicons.actions.MaterialIconAction
import ro.dob.materialicons.actions.MaterialIconInstaller
import ro.dob.materialicons.actions.MaterialIconUninstaller
import ro.dob.materialicons.model.Color
import ro.dob.materialicons.model.Size

object MaterialIcons
{
    fun main(args : Array<String>)
    {
        var action : MaterialIconAction? = null

        try
        {
            if (args.size==4||args.size==5)
            {
                if (args[0]=="install")
                {
                    action=MaterialIconInstaller(
                            zipPath = args[1],
                            color = Color.fromString(args[2]),
                            size = Size.fromInt(args[3].toInt()),
                            moduleName = args.getModuleName(atIndex = 4))
                }
            }
            else if (args.size==2||args.size==3)
            {
                if (args[0]=="uninstall")
                {
                    action=MaterialIconUninstaller(
                            fileName = args[1],
                            moduleName = args.getModuleName(atIndex = 2))
                }
            }
        }
        catch (ex : Exception)
        {
            if (DEBUG)
                ex.printStackTrace()
        }

        if (action?.isValid()==true)
        {
            action.execute()
        }
        else
        {
            println(SYNTAX_DESCRIPTION)
        }
    }

    private fun Array<String>.getModuleName(atIndex : Int) : String =
        if (size==atIndex+1)
            get(atIndex)
        else "app"
}