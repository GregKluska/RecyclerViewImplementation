# RecyclerView Implementation

The project purpose is to practice the recyclerview implementation with AsyncListDiffer
Branches:
  - simple-implementation - single viewholder in one adapter
  - master - three viewholders with different models in one adapter

# About

The app implements MVVM architecture and uses ```https://jsonplaceholder.typicode.com/``` API to get the data.
The user is able to scroll through all the users, click on them to get a list of albums and then get a list of photos that belongs to these albums.
The adapter uses **AsyncListDiffer** for better performance.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.

# Package Structure
    
    com.gregkluska.recyclerviewimplementation    # Root Package
    .
    ├── api                 # retrofit API for remote end point.
    ├── di                  # Dependency Injection             
    ├── models              # Model classes
    ├── repository          # Repositories
    │   └── main            # Main Repository
    ├── ui                  # Activity/View layer
    │   └── main            # Main Screen Activity & ViewModel
    └── utils              # Utility Classes / Kotlin extensions


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
