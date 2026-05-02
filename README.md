# GSB Visites - Application Mobile Android

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-28%2B-brightgreen.svg)](https://android-arsenal.com/api?level=28)
[![Language](https://img.shields.io/badge/Language-Java-ED8936.svg)](https://www.java.com)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen.svg)](#)

**GSB Visites** est une application mobile Android permet de centraliser et visualiser les comptes-rendus de visite du laboratoire GSB.

Architecture MVVM avec Hilt, Retrofit et LiveData.

---

## À propos

**GSB Visites** permet aux visiteurs commerciaux du Groupe GSB de :

- Se connecter de manière sécurisée via email/password
- Consulter leurs informations personnelles
- Afficher la liste de leurs portefeuilles actifs

---

## Fonctionnalités

### Authentification
- Login avec validation des identifiants
- Génération JWT côté serveur
- Gestion d'erreurs robuste

### Gestion des données
- Affichage profil visiteur (nom, prénom, email)
- Liste des portefeuilles actifs

### Architecture
- MVVM Pattern
- Injection dépendances Hilt
- Data binding ReactiveX (LiveData)
- Navigation Component (Safe Args)
- Network calls asynchrones (Retrofit)

---

## Architecture

```
┌─────────────────────────────────────────┐
│        UI Layer (Fragments)             │
│  LoginFragment | HomeVisiteurFragment   │
└─────────────────────┬───────────────────┘
                      │
┌─────────────────────────────────────────┐
│   ViewModel Layer (@HiltViewModel)      │
│      VisiteurViewModel (holder état)    │
└─────────────────────┬───────────────────┘
                      │
┌─────────────────────────────────────────┐
│  Repository Pattern (@Singleton)        │
│  VisiteurRepository (logique métier)    │
└─────────────────────┬───────────────────┘
                      │
┌─────────────────────────────────────────┐
│     Network Layer (Retrofit)            │
│  GsbApi | NetworkModule | Models        │
└─────────────────────┬───────────────────┘
                      │
                   HTTP/REST
                      │
            🖥️ Serveur (API)

```

---

## Structure du projet

```
GSB_visite/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/gsb_visites/
│   │   │   ├── App.java ........................ Application class Hilt
│   │   │   ├── ui/
│   │   │   │   ├── main/
│   │   │   │   │   └── MainActivity.java ..... Entry point application
│   │   │   │   └── visiteur/
│   │   │   │       ├── LoginFragment.java .... Authentification
│   │   │   │       └── HomeVisiteurFragment.java .... Affichage données
│   │   │   ├── viewmodel/
│   │   │   │   └── VisiteurViewModel.java ... Gestion état
│   │   │   ├── data/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Visiteur.java
│   │   │   │   │   ├── Portefeuille.java
│   │   │   │   │   └── ApiResponse.java
│   │   │   │   ├── network/
│   │   │   │   │   ├── GsbApi.java ......... API Retrofit
│   │   │   │   │   ├── NetworkModule.java .. Hilt injection
│   │   │   │   │   └── RetrofitClientInstance.java
│   │   │   │   └── repository/
│   │   │   │       └── VisiteurRepository.java
│   │   │   └── adapter/
│   │   │       └── PortefeuilleAdapter.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── fragment_login.xml
│   │   │   │   ├── fragment_home_visiteur.xml
│   │   │   │   └── item_portefeuille.xml
│   │   │   ├── navigation/
│   │   │   │   └── nav_graph.xml
│   │   │   └── values/
│   │   │       ├── strings.xml
│   │   │       ├── colors.xml
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## Technologies

### Framework & Libraries

| Domaine | Library | Version |
|---------|---------|---------|
| **UI** | AppCompat | 1.7.1 |
| **Design** | Material Design | 1.13.0 |
| **Navigation** | Navigation Component | 2.9.4 |
| **Layout** | ConstraintLayout | 2.2.1 |
| **State** | LiveData + ViewModel | 2.8.6+ |
| **Injection** | Hilt | 2.44 |
| **Network** | Retrofit | 2.11.0 |
| **JSON** | Gson | (built-in) |
| **HTTP** | OkHttp | 4.12.0 |

### Platform

```
Compile SDK  : 36
Min SDK      : 28 (Android 9.0 Pie)
Target SDK   : 36
Java         : JDK 11
Build Tool   : Gradle 8.13.0
```

---

## Sécurité

| Aspect | Statut | Notes |
|--------|--------|-------|
| HTTPS | ✅ | API en HTTPS |
| Auth | ✅ | JWT token |
| Input Validation | ✅ | Email + password |
| Token Storage | 🟡 | RAM (améliorer) |
| Code Obfuscation | ✅ | ProGuard release |

---

## Licence

Ce projet est sous licence propriétaire GSB.

---

## Apprentissages clés

Ce projet démontre :

**Architecture mobile robuste** avec MVVM  
**Patterns design** : Repository, ViewModel, Observer  
**Framework modernes** : Hilt, Retrofit, LiveData  
**Réactivité** avec programmation asynchrone  
**Best practices** Android 2024  

