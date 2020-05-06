# Kotlin multiplatform library

This project is an evaluation of the possibilites using [kotlins multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) feature. It aims to provide a `server` which provides a RESTish-Api. To make the interaction with this server easier, the API-Objects are defined within an `api`-project. This api-project is compiled for multiple platforms: ios, android, js and java itself. 

The shared-code gives the opportunity to implement validation, caching, serialization only once for all targeted platforms, no need to reimplement these details.

## Building 

### ios

* Use an mac
* call `./gradlew clean releaseFatFramework`
* whatfor the fat-Framework task is, is explained [here](https://proandroiddev.com/our-kotlin-multiplatform-implementation-c99ae369a0f3)
    * call `./gradlew clean debugFatFramework` ... smth debug? don't know
* `Employee/api/build/fat-framework` should contain the build-result

### android

* download android-sdk by:
    * scrolling down on this [page](https://developer.android.com/studio) to Command line tools only
    * download the package for your platform
    * unzip it to `ANDROID-SDK` (define the folder by yourself) 
    * edit `local.properties` within the project and set `sdk.dir=ANDROID-SDK`
    * accept licenses by calling: `yes | sudo ANDROID-SDK/bin/sdkmanager --licenses`
* start the build by: `./gradlew clean assemble`
* `Employee/api/build/output` should contain the resulting `aar`-Package
    