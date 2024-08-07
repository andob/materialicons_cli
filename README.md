# materialicons_cli

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-materialicons__cli-green.svg?style=flat )]( https://android-arsenal.com/details/1/7199 )

A command line tool to install icons from https://materialdesignicons.com/ into Android Studio projects. Written in Kotlin

## Setup

[Download the jar package](https://andob.io/repository/open_source/ro/andob/materialicons/materialicons/1.1.2/materialicons-1.1.2.jar)

## Installing an icon

1. Go to [MaterialDesignIcons.com](https://materialdesignicons.com/). Find an icon. Remember the icon the name (hover the icon to see its name).

2. Open a terminal. Go to the root of your Android Studio project

3. Run the icon installer. Syntax:

```
Syntax:

java -jar /path/to/material_icons.jar iconName -color <color> -size <size> [-vectorial] [-moduleName <moduleName>]

iconName                 Icon name (access-point) or icon pack path (~/Downloads/access-point.zip).
                         if you specify an icon name, the icon will be automatically downloaded
-color <color>           Required, icon color: white, black, grey
                         or custom color in HEX (ex: red=f10000)
-size <size>             Required only if you're not using the -vectorial flag
                         icon size in dp: 18, 24, 36, 48
                         or any other number (size in dp)
-vectorial               Optional, to import vectorial (XML) icons rather than raster (PNG) icons.
-moduleName <moduleName> Optional, module name (default value=app)

Examples:
java -jar ./material_icons.jar ~/Downloads/access-point.zip -color black -size 24
Copies black 24dp png icons from the zip to drawable dirs: drawable-.../ic_access_point_black_24dp.png

java -jar ./material_icons.jar access-point -color red=F21122 -size 15 -moduleName library
Downloads the icon pack, generates 15dp #F23122 red icons to drawable dirs from the library module: drawable-.../ic_access_point_red_15dp.png

java -jar ./material_icons.jar access-point -color white -vectorial
Downloads the icon pack, generates 24dp white vectorial icon to drawable dir: drawable/ic_access_point_white_24dp.xml
```

## License

```
Copyright 2018 - 2020 Dobrescu Andrei  

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at  

http://www.apache.org/licenses/LICENSE-2.0  

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License.
```
