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
            if (args.size==4)
            {
                if (args[0]=="install")
                {
                    action=MaterialIconInstaller(
                            zipPath = args[1],
                            color = Color.fromString(args[2]),
                            size = Size.fromInt(args[3].toInt()))
                }
            }
            else if (args.size==2)
            {
                if (args[0]=="uninstall")
                {
                    action=MaterialIconUninstaller(
                            fileName = args[1])
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
}