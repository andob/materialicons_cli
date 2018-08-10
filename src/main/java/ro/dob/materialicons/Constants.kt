package ro.dob.materialicons

import ro.dob.materialicons.model.Color
import ro.dob.materialicons.model.Size

const val DEBUG = true

const val UNZIP_BUFFER_SIZE = 256*1024*1024

val SYNTAX_DESCRIPTION = "Syntax:\n" +
            "java -jar path_to_material_icons.jar install path_to_zip color size\n" +
            "java -jar path_to_material_icons.jar uninstall file_name.png\n" +
            "colors: ${Color.Black}, ${Color.White}, ${Color.Grey}\n" +
            "sizes: ${Size._18}, ${Size._24}, ${Size._36}, ${Size._48}"