# Habits Tracker PRO App

Esta es una aplicación para el seguimiento de hábitos escrita en Kotlin para Smartphones Android. \
**HabitsPro** soporta múltiples usuarios, cada uno con sus registros independientes, siguiendo el enfoque **"Offline-First"** que permite realizar un seguimiento de los hábitos diarios y recibir recordatorios para mantenerse en el buen camino. \
La app está diseñada siguiendo los principios **SOLID** y de **Clean Architecture**, así como el popular patrón de presentación **MVVM**, utilizando las últimas tecnologías y bibliotecas como **Kotlin 2.0**.

---

## Screenshots

| ![Loading Screen](./githubimages/loading.png) | ![Settings Screen](./githubimages/settings.png) |
|:--------------------------------------------:|:---------------------------------------------:|
|                   **Loading**                |                  **Settings**                 |

| ![Login Screen](./githubimages/loginscreen.png) | ![SignUp Screen](./githubimages/signupscreen.png) |
|:-----------------------------------------------:|:------------------------------------------------:|
|                    **Login**                   |                    **SignUp**                    |

| ![Home Screen](./githubimages/home.png) | ![Detail Screen](./githubimages/settings.png) |
|:---------------------------------------:|:---------------------------------------------:|
|                    **Home**             |                    **Detail**                 |

---

## Características principales y tecnologías utilizadas

- **Kotlin 2.0**: Se utiliza para aprovechar sus características modernas y mejoras en el rendimiento, facilitando el desarrollo de una aplicación robusta y eficiente.
- **Modularización**: La aplicación está dividida en múltiples módulos, mejorando la organización del código, el mantenimiento y la escalabilidad.
- **Clean Architecture**: El proyecto sigue este patrón, facilitando la separación de responsabilidades y mejorando la mantenibilidad del código.
- **SOLID**: Los principios SOLID (Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) se aplican para promover un código limpio, modular y extensible.
- **MVVM**: Implementa el patrón Model-View-ViewModel para separar la lógica de presentación de la lógica de negocio y los datos, mejorando la testabilidad y la organización del código.
- **Inyección de Dependencias - Dagger-Hilt**: Se utiliza para simplificar la gestión de las dependencias y permitir una mejor escalabilidad y prueba unitaria del código.
- **Jetpack Compose**: La interfaz de usuario se desarrolla utilizando este moderno toolkit de UI de Android, facilitando la creación de interfaces flexibles y dinámicas.
- **Firebase Authentication**: Integrado para proporcionar un sistema de autenticación seguro y confiable.
- **Room**: Biblioteca de persistencia de Android utilizada para almacenar los datos de los hábitos en una base de datos local, permitiendo un acceso rápido y eficiente.
- **Retrofit**: Utilizado para realizar llamadas a una API remota y obtener datos relacionados con los hábitos, permitiendo una sincronización eficiente y actualizada.
- **Notificaciones**: Permite configurar recordatorios personalizados para cada hábito y muestra notificaciones en los días y horas especificados.
- **AlarmManager**: Utilizado para programar las notificaciones y garantizar que se muestren en los momentos adecuados.
- **WorkManager**: Gestiona las tareas en segundo plano, permitiendo que los hábitos creados en modo avión se guarden automáticamente en la nube una vez que el dispositivo tenga conexión a Internet.
- **Unit Test**: Se incluyen pruebas unitarias para verificar el correcto funcionamiento de los componentes clave de la aplicación y garantizar la calidad del código.
- **UI Test**: Pruebas de interfaz de usuario para verificar que la aplicación se comporte correctamente y proporcione una experiencia fluida.
- **Offline-First**: Diseñada siguiendo este enfoque, asegurando que la funcionalidad principal esté disponible sin conexión a Internet. Los datos se sincronizan automáticamente una vez que la conexión esté disponible.
- **Broadcast Receivers**: Gestiona el agregado de alarmas cuando el usuario reinicia el dispositivo, y cuando suena una alarma, para configurar la siguiente.

---

## Uso

1. Clona este repositorio o realiza un fork desde GitHub.
2. Importa el proyecto desde Android Studio.
3. Configura Firebase Authentication en la consola de Firebase y agrega las credenciales necesarias en el proyecto.
4. Configura tu API.
5. Ejecuta la aplicación.
6. Personalízala a tu gusto.

---
