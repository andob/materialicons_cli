package ro.dob.materialicons.model

const val DEBUG = true

const val BASE_URL = "http://materialdesignicons.com/"

const val UNZIP_BUFFER_SIZE = 256*1024*1024

const val SYNTAX_DESCRIPTION =
"""
Syntax:

java -jar /path/to/material_icons.jar iconName -color <color> -size <size> [-vectorial] [-moduleName <moduleName>]

iconName                 Icon name (access-point) or icon pack path (~/Downloads/access-point.zip).
                         if you specify an icon name, the icon will be automatically downloaded
-color <color>           Required, icon color: white, black, grey
                         or custom color in HEX (ex: red=f10000)
-size <size>             Required, icon size in dp: 18, 24, 36, 48
                         or any other number (size in dp)
-vectorial               Optional, to import vectorial (XML) icons rather than raster (PNG) icons.
-moduleName <moduleName> Optional, module name (default value=app)

Examples:
java -jar ./material_icons.jar ~/Downloads/access-point.zip -color black -size 24
Copies black 24dp png icons from the zip to drawable dirs: drawable-.../ic_access_point_black_24dp.png

java -jar ./material_icons.jar access-point -color red=F21122 -size 15 -moduleName library
Downloads the icon pack, generates 15dp #F23122 red icons to drawable dirs from the library module: drawable-.../ic_access_point_red_15dp.png

java -jar ./material_icons.jar access-point -color white -size 20 -vectorial
Downloads the icon pack, generates 20dp white vector icon to drawable dir: drawable/ic_access_point_white_20dp.xml
"""
