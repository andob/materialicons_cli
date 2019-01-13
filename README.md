# materialicons_cli

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-materialicons__cli-green.svg?style=flat )]( https://android-arsenal.com/details/1/7199 )

A command line tool to install and uninstall icons from https://materialdesignicons.com/ into Android Studio projects. Written in Kotlin

## Setup

[Download the jar package](https://github.com/andob/materialicons_cli/blob/master/material_icons.jar?raw=true)

## Installing an icon

1. Go to [MaterialDesignIcons.com](https://materialdesignicons.com/). Find an icon. Choose (download) Icon package -> Android 5.x. You'll get a zip file with the icons

1. Open a terminal. Go to the root of your Android Studio project

1. Run

```
java -jar /path_to/material_icons.jar install path_to_zip color size
```

Where color = white, grey or black, size = 18,24,36,48. Example usage:

```
cd Desktop/my-awesome-app/
java -jar ~/Downloads/material_icons.jar install ~/Downloads/access-point.zip black 24
```

Copies the following files:

```
drawable-mdpi/ic_access_point_black_24dp.png
drawable-hdpi/ic_access_point_black_24dp.png
drawable-xhdpi/ic_access_point_black_24dp.png
drawable-xxhdpi/ic_access_point_black_24dp.png
drawable-xxxhdpi/ic_access_point_black_24dp.png
```

## Uninstalling an icon

1. Open a terminal. Go to the root of your Android Studio project

1. Run

```
java -jar /path_to/material_icons.jar uninstall file_name.png
```

Example usage:

```
cd Desktop/my-awesome-app/
java -jar ~/Downloads/material_icons.jar uninstall ic_access_point_black_24dp
```

Removes the following files:

```
drawable-mdpi/ic_access_point_black_24dp.png
drawable-hdpi/ic_access_point_black_24dp.png
drawable-xhdpi/ic_access_point_black_24dp.png
drawable-xxhdpi/ic_access_point_black_24dp.png
drawable-xxxhdpi/ic_access_point_black_24dp.png
```

## Custom module name

If your module name is other than ``app``, you can specify it as the last argument of a command. Example:

``
java -jar ~/Downloads/material_icons.jar install ~/Downloads/access-point.zip black 24 library
``

``
java -jar ~/Downloads/material_icons.jar uninstall ic_access_point_black_24dp library
``

## License

```
Copyright 2018 Dobrescu Andrei  

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
