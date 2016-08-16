This repo is used in monmonja apps
> clone the repo then add library with new module then import gradle project then add library to dependencies

on the apps gradle add
>    debugCompile project(path: ':kotlin-monmonja-android-library', configuration: "libraryDebug")
>    releaseCompile project(path: ':kotlin-monmonja-android-library', configuration: "libraryRelease")

If android studio cannot compile it try
> ./gradlew build --debug

