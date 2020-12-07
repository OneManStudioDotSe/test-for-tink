# Description
The goal of the project is to build a simple app that shows photos of dogs and also allows you to open each photo in a detail view.
Nothing tricky - and should not take too much time.

The basic feature set of the app is:
- Get list of dog photos from the JSON API
- Show a gallery view of all dog photos
- Show a detail view for a single dog photo
- Make the app as user-friendly as you can.

The photo API is available at: https://pugme.herokuapp.com/bomb?count=50

Feel free to write down any suggestions or improvements that you would make in the app given more time to work on it.

# Features of my solution
- Single Activity structure with multiple fragments
- Integration of Navigation Components via a navigation graph
- Integration of a fully-fledged Material theme with the ability to easily change fonts, styling and palette
- Integration of splash screen to handle cold starts
- Views to indicated loading of data and errors
- Use of MVVM as architecture
- Use of Coroutines and LiveData as data flow model
- Separation of concerns (UI, Repository, Data) with specific models between each layer
- Use of multiple repositories
- Extra features for playing around with the list of dogs (dynamic span count, dynamic source selection)
- Centralised handling of dependencies
- Handy scripts for maintenance tasks

# Known issues
- The transition animations are not the best due to the use of the navigation components which recreate the previous fragment at the backstack
- There is a bit of duplicated code for the setup of the repos which could be skipped with a use of DI
- Some strings are hardcoded and not localised to strings.xml

# Contact info for submission
Engineering Manager -Mobile Apps
Jonatan Klintberg
+46 76 307 84 60
jonatan.klintberg@tink.se