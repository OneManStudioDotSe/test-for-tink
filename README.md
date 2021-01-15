# Tink Test

The test I did when I applied at Tink.
The app was supposed to be an implementation of their designs and requested functionality, along with some innovation from my side. Their API was broken so I took things one step further by adding a new API for the main content and another one for showing a bit of random and dynamic information.
I got an offer for a job but declined it since I had a better one.

This was the official description of the test assignment:
The goal of the project is to build a simple app that shows photos of dogs and also allows you to open each photo in a detail view.
Nothing tricky - and should not take too much time.

The basic feature set of the app is:
- Get list of dog photos from the JSON API
- Show a gallery view of all dog photos
- Show a detail view for a single dog photo
- Make the app as user-friendly as you can.

The photo API is available at: https://pugme.herokuapp.com/bomb?count=50

Feel free to write down any suggestions or improvements that you would make in the app given more time to work on it.

<br>

## Android development

Rugby Ranker attempts to make use of the latest Android libraries and best practices:
* Entirely written in [Kotlin](https://kotlinlang.org/) (including [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* Centralized way of handling versioning of dependencies
* Use of handy scripts for overall project maintenance
* MVVM as architecture
* Integration of splash screen to handle cold starts
* Separation of concerns (UI, Repository, Data) with specific models between each layer
* Control of URLs of repositories endpoints at the gradle build scripts' level for easier use of flavors and variants
* Easy state handling with Views to indicate data loading and error
* Designed and built using Material Design [components](https://material.io/components/) and a fully-fledged [Material theme](https://material.io/design/material-theming/overview.html#material-theming) with the ability to easily change fonts, styling and palette
* Combo of [Retrofit](https://square.github.io/retrofit/) and [OkHttp](https://square.github.io/okhttp/) for networking
* [Coil](https://coil-kt.github.io/coil/) for image loading
* Makes use of [Android Jetpack](https://developer.android.com/jetpack/):
  * Single Activity structure with multiple [fragments](https://developer.android.com/jetpack/androidx/releases/fragment)
  * [Architecture Components](https://developer.android.com/topic/libraries/architecture) including **ViewModel**, **LiveData**, **Navigation**
  * [Navigation Components](https://developer.android.com/jetpack/androidx/releases/navigation) for the navigation around the app
  * [Safe Args](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) for passing arguments across views
  * [Android KTX](https://developer.android.com/kotlin/ktx) for more fluent use of Android APIs
  * [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout), [View Binding](https://developer.android.com/topic/libraries/view-binding) and more for layouts and UI


## Inspiration

The idea for this test was given to me from the Product Manager at Tink.

# Known issues
- The transition animations are not the best due to the use of the navigation components which recreate the previous fragment at the back stack
- There is a bit of duplicated code for the setup of the repos which could be skipped with a use of DI
- Some strings are hardcoded and not localized to strings.xml

## License

```
Copyright 2020 One Man Studio AB

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
