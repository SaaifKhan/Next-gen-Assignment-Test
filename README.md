# Next-gen-Assignment-Test

## Features

- **Popular Movies**: Explore a curated list of popular movies.
- **Pagination Support**: Browse through an extensive collection of movies with ease.
- **Light and Dark Mode**: Choose between light and dark themes to suit your preferences.
## Architecture

Popular Movies App is built using modern Android development practices, ensuring a clean and maintainable codebase. Here's an overview of its architecture:
- **Clean Architecture**: The app follows a clean architecture pattern, separating concerns into layers using Multi Module: Presentation, Domain,Data,and Base.
- **MVVM (Model-View-ViewModel)**: Utilizes the MVVM architectural pattern to keep the UI logic separate from the business logic.
- **Usecase and Repository Pattern**: Employs use cases to handle business logic and repositories to manage data access.
## Built With 🛠
- **Kotlin**: The official and first-class programming language for Android development.
- **Coroutines**: Used for handling asynchronous operations efficiently.
- **Flow**: A cold asynchronous data stream for sequential value emission.
- **Android Architecture Components**: A collection of libraries for creating robust and maintainable apps.
    - **LiveData**: Data objects that notify views of database changes.
    - **ViewModel**: Stores UI-related data that survives configuration changes.
    - **ViewBinding**: Generates binding classes for XML layout files, simplifying view interaction.
- **Dependency Injection**:
    - **Hilt**: Simplifies Dagger DI integration for Android apps (main branch).
- **Retrofit**: A type-safe HTTP client for Android and Java.
- **Material Components for Android**: Modular and customizable Material Design UI components.
- **Coil**: An image loading library for efficiently displaying images in the app.

To get started with Popular Movies App, follow these steps:

1. Clone this repository: `git clone https://github.com/SaaifKhan/Next-gen-Assignment-Test`
2. Open the project in Android Studio.
3. Build and run the app on your Android device or emulator.