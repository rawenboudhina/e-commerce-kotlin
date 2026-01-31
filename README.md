# 🛍️ E-Commerce Mobile Application

Une application mobile de commerce électronique moderne développée en **Kotlin** avec **Jetpack Compose**, utilisant l'API FakeStore pour la gestion des produits.

---

## 📋 Table des matières

1. [Présentation du projet](#-présentation-du-projet)
2. [Fonctionnalités](#-fonctionnalités)
3. [Architecture du projet](#️-architecture-du-projet)
4. [Technologies utilisées](#️-technologies-utilisées)
5. [Structure détaillée](#-structure-détaillée-du-code)
6. [Installation et lancement](#-installation-et-lancement)
7. [Captures d'écran](#-captures-décran)
8. [Explications techniques](#-explications-techniques-pour-votre-professeur)

---

## 🎯 Présentation du projet

Cette application e-commerce est un projet académique qui démontre la maîtrise des concepts modernes du développement Android :

- **Architecture MVVM** (Model-View-ViewModel) pour une séparation claire des responsabilités
- **Jetpack Compose** pour une UI déclarative et moderne
- **Programmation réactive** avec StateFlow et Coroutines
- **Appels API REST** avec Retrofit
- **Navigation** entre plusieurs écrans
- **Gestion d'état** complexe (panier, produits, UI)

### Objectifs pédagogiques atteints

✅ Comprendre et implémenter l'architecture MVVM
✅ Maîtriser Jetpack Compose pour créer des interfaces modernes
✅ Gérer les appels réseau asynchrones avec Retrofit et Coroutines
✅ Implémenter un système de navigation multi-écrans
✅ Gérer l'état de l'application de manière réactive
✅ Appliquer les principes de Material Design 3

---

## 📱 Fonctionnalités

### 1. Liste de produits
- ✨ Affichage en grille (2 colonnes) de tous les produits
- 🏷️ Filtrage par catégorie (Électronique, Bijoux, Homme, Femme)
- ⭐ Affichage des notes et évaluations
- 🛒 Ajout rapide au panier depuis la liste
- 🔔 Badge du panier avec compteur d'articles

### 2. Détails du produit
- 🖼️ Image du produit en haute qualité
- 📝 Description complète et détaillée
- 💰 Prix et disponibilité
- ⭐ Système d'évaluation avec étoiles
- 🚚 Information de livraison gratuite
- ➕ Bouton d'ajout au panier avec feedback visuel

### 3. Panier d'achat
- 📦 Liste complète des articles ajoutés
- ➕➖ Contrôles de quantité pour chaque article
- 🗑️ Suppression d'articles
- 💵 Calcul automatique du total
- ✅ Validation de commande
- 🎉 Confirmation de commande avec dialog

### 4. Design et UX
- 🎨 Interface Material Design 3 moderne
- 🌈 Palette de couleurs cohérente (bleu tech)
- ✨ Animations et transitions fluides
- 📱 Design responsive et adaptatif
- 🔄 États de chargement et d'erreur élégants

---

## 🏗️ Architecture du projet

L'application suit une architecture **MVVM (Model-View-ViewModel)** propre et organisée, qui est le standard recommandé par Google pour les applications Android modernes.

```
app/src/main/java/com/rawen/e_commerce/
├── data/
│   ├── api/
│   │   ├── FakeStoreApi.kt          # Interface Retrofit pour l'API
│   │   └── RetrofitInstance.kt      # Configuration Retrofit
│   ├── model/
│   │   ├── Product.kt               # Modèle de données Product
│   │   └── CartItem.kt              # Modèle de données CartItem
│   └── repository/
│       ├── ProductRepository.kt     # Repository pour les produits
│       └── CartRepository.kt        # Repository pour le panier
├── ui/
│   ├── screens/
│   │   ├── ProductListScreen.kt     # Écran liste des produits
│   │   ├── ProductDetailScreen.kt   # Écran détails du produit
│   │   └── CartScreen.kt            # Écran panier
│   ├── viewmodel/
│   │   ├── ProductViewModel.kt      # ViewModel pour les produits
│   │   └── CartViewModel.kt         # ViewModel pour le panier
│   ├── navigation/
│   │   └── NavGraph.kt              # Configuration de la navigation
│   └── theme/
│       ├── Color.kt                 # Palette de couleurs
│       ├── Theme.kt                 # Thème de l'application
│       └── Type.kt                  # Typographie
└── MainActivity.kt                  # Point d'entrée de l'application
```

## 🛠️ Technologies utilisées

- **Kotlin** : Langage de programmation principal
- **Jetpack Compose** : Framework UI moderne et déclaratif
- **Material Design 3** : Design system pour une interface élégante
- **Retrofit** : Client HTTP pour les appels API
- **Coil** : Chargement d'images asynchrone
- **Coroutines** : Gestion asynchrone des tâches
- **StateFlow** : Gestion réactive de l'état
- **Navigation Compose** : Navigation entre les écrans
- **ViewModel** : Gestion du cycle de vie et de l'état



## 📦 Dépendances principales

```kotlin
// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

// Retrofit pour les appels API
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

// Coil pour le chargement d'images
implementation("io.coil-kt:coil-compose:2.5.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 🎨 Fonctionnalités détaillées

### Écran Liste des Produits
- Grille de produits avec 2 colonnes
- Affichage de l'image, titre, prix et note
- Badge du panier avec le nombre d'articles
- Bouton "Add" pour ajouter rapidement au panier
- Feedback visuel lors de l'ajout

### Écran Détails du Produit
- Image du produit en grand format
- Informations complètes : titre, catégorie, prix, description
- Évaluations avec nombre d'avis
- Bouton "Add to Cart" fixe en bas
- Confirmation visuelle de l'ajout au panier

### Écran Panier
- Liste des articles avec images et détails
- Contrôles de quantité (+/-)
- Bouton de suppression pour chaque article
- Calcul automatique du total
- Livraison gratuite
- Bouton "Place Order" pour valider
- Dialog de confirmation de commande
- Écran vide élégant si le panier est vide

## 🌐 API utilisée

L'application utilise l'API **FakeStore** : https://fakestoreapi.com/

Endpoints utilisés :
- `GET /products` - Liste de tous les produits
- `GET /products/{id}` - Détails d'un produit
- `GET /products/categories` - Liste des catégories
- `GET /products/category/{category}` - Produits par catégorie

---

## 📚 Structure détaillée du code

### 📂 Couche Data (Données)

#### `data/api/`
- **`FakeStoreApi.kt`** : Interface Retrofit définissant tous les endpoints de l'API
  - `getProducts()` : Récupère tous les produits
  - `getProductById(id)` : Récupère un produit spécifique
  - `getCategories()` : Récupère les catégories
  - `getProductsByCategory(category)` : Filtre par catégorie

- **`RetrofitInstance.kt`** : Configuration singleton de Retrofit
  - URL de base : `https://fakestoreapi.com`
  - Convertisseur JSON (Gson)
  - Intercepteur de logs pour le débogage

#### `data/model/`
- **`Product.kt`** : Modèle de données pour un produit
  ```kotlin
  data class Product(
      val id: Int,
      val title: String,
      val price: Double,
      val description: String,
      val category: String,
      val image: String,
      val rating: Rating
  )
  ```

- **`CartItem.kt`** : Modèle pour un article dans le panier
  ```kotlin
  data class CartItem(
      val product: Product,
      val quantity: Int
  )
  ```

#### `data/repository/`
- **`ProductRepository.kt`** : Gère la logique métier des produits
  - Appels API avec gestion d'erreurs
  - Transformation des données
  - Pattern Repository pour abstraire la source de données

- **`CartRepository.kt`** : Gère la logique du panier
  - Ajout/suppression d'articles
  - Mise à jour des quantités
  - Calcul du total

### 🎨 Couche UI (Interface utilisateur)

#### `ui/screens/`
- **`ProductListScreen.kt`** : Écran principal avec la liste des produits
  - Grille de produits (LazyVerticalGrid)
  - Filtres de catégories (LazyRow)
  - Badge du panier
  - Gestion des états (Loading, Success, Error)

- **`ProductDetailScreen.kt`** : Écran de détails d'un produit
  - Affichage complet des informations
  - Système d'évaluation visuel
  - Bouton d'ajout au panier avec animation
  - Scroll vertical pour le contenu

- **`CartScreen.kt`** : Écran du panier d'achat
  - Liste des articles (LazyColumn)
  - Contrôles de quantité
  - Calcul du total en temps réel
  - Dialog de confirmation de commande
  - État vide élégant

#### `ui/viewmodel/`
- **`ProductViewModel.kt`** : Gère l'état et la logique des produits
  - StateFlow pour l'état UI réactif
  - Chargement des produits
  - Filtrage par catégorie
  - Gestion des erreurs

- **`CartViewModel.kt`** : Gère l'état et la logique du panier
  - StateFlow pour les articles du panier
  - Ajout/suppression d'articles
  - Mise à jour des quantités
  - Calcul du total
  - Compteur d'articles

#### `ui/navigation/`
- **`NavGraph.kt`** : Configuration de la navigation
  - Définition des routes (ProductList, ProductDetail, Cart)
  - Navigation avec arguments (ID du produit)
  - Gestion du back stack

#### `ui/theme/`
- **`Color.kt`** : Palette de couleurs de l'application
  - Couleurs primaires (TechBlue: #0066FF)
  - Couleurs secondaires et variantes
  - Support du mode clair

- **`Theme.kt`** : Configuration du thème Material Design 3
  - ColorScheme personnalisé
  - Configuration des formes et typographies

- **`Type.kt`** : Définition de la typographie
  - Styles de texte cohérents
  - Tailles et poids de police

### 🚀 Point d'entrée

- **`MainActivity.kt`** : Activité principale
  - Configuration de Compose
  - Initialisation du NavHost
  - Thème de l'application

---

## 🔧 Explications techniques pour votre professeur

### 1. Architecture MVVM

**Pourquoi MVVM ?**
- ✅ **Séparation des responsabilités** : Le code est organisé en couches distinctes
- ✅ **Testabilité** : Chaque couche peut être testée indépendamment
- ✅ **Maintenabilité** : Facile à modifier et à étendre
- ✅ **Réutilisabilité** : Les ViewModels peuvent être partagés entre plusieurs vues

**Les 3 couches :**

1. **Model (Modèle)** : `data/model/`, `data/repository/`
   - Représente les données et la logique métier
   - Indépendant de l'interface utilisateur
   - Gère la communication avec l'API

2. **View (Vue)** : `ui/screens/`
   - Composables Jetpack Compose
   - Affiche les données et capture les interactions utilisateur
   - Ne contient PAS de logique métier

3. **ViewModel** : `ui/viewmodel/`
   - Pont entre Model et View
   - Gère l'état de l'UI avec StateFlow
   - Survit aux changements de configuration (rotation d'écran)

### 2. Jetpack Compose

**Avantages par rapport aux XML traditionnels :**
- 🚀 **Déclaratif** : On décrit ce qu'on veut, pas comment le faire
- 🔄 **Réactif** : L'UI se met à jour automatiquement quand l'état change
- 📝 **Moins de code** : Plus concis et lisible
- 🎨 **Prévisualisation** : Voir l'UI sans lancer l'app
- 🧩 **Composabilité** : Réutilisation facile des composants

**Exemple de composant réutilisable :**
```kotlin
@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    // Composant personnalisé pour les filtres de catégorie
}
```

### 3. Programmation asynchrone

**Coroutines Kotlin :**
- Permettent d'exécuter du code asynchrone de manière séquentielle
- Évitent le "callback hell"
- Gèrent automatiquement les threads

**StateFlow :**
- Observable réactif pour l'état de l'UI
- Émet des valeurs que Compose observe automatiquement
- Thread-safe et optimisé pour Android

**Exemple :**
```kotlin
private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

fun loadProducts() {
    viewModelScope.launch {
        val result = repository.getProducts()
        _uiState.value = ProductUiState.Success(result)
    }
}
```

### 4. Appels réseau avec Retrofit

**Retrofit** simplifie les appels API REST :
- Conversion automatique JSON ↔ Objets Kotlin (avec Gson)
- Gestion des erreurs HTTP
- Support des Coroutines

**Exemple d'interface API :**
```kotlin
interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}
```

### 5. Navigation

**Navigation Compose** gère les transitions entre écrans :
- Type-safe avec des routes
- Passage d'arguments (ex: ID du produit)
- Gestion du back stack automatique

**Exemple de navigation :**
```kotlin
NavHost(navController, startDestination = "productList") {
    composable("productList") { ProductListScreen() }
    composable("productDetail/{productId}") { backStackEntry ->
        val productId = backStackEntry.arguments?.getString("productId")?.toInt()
        ProductDetailScreen(productId = productId)
    }
}
```

### 6. Gestion d'état

**Pattern UiState :**
```kotlin
sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}
```

Avantages :
- ✅ États mutuellement exclusifs (impossible d'être Loading ET Success)
- ✅ Type-safe avec sealed class
- ✅ Facile à gérer dans l'UI avec `when`

### 7. Bonnes pratiques appliquées

✅ **Single Responsibility Principle** : Chaque classe a une seule responsabilité
✅ **Dependency Injection** : Les dépendances sont passées en paramètres
✅ **Immutabilité** : Utilisation de `data class` et `val`
✅ **Gestion d'erreurs** : Try-catch et états d'erreur
✅ **Code lisible** : Nommage clair et commentaires en français
✅ **Material Design 3** : Respect des guidelines de Google

---

## 🎯 Points clés à mentionner à votre professeur

### Compétences techniques démontrées :

1. **Kotlin moderne**
   - Coroutines pour l'asynchrone
   - Extension functions
   - Data classes
   - Sealed classes
   - Lambda expressions

2. **Architecture Android**
   - MVVM pattern
   - Repository pattern
   - Separation of concerns
   - Lifecycle awareness

3. **Jetpack Compose**
   - Composables
   - State management
   - Recomposition
   - Material Design 3

4. **Réseau et données**
   - Retrofit pour les API REST
   - Gson pour la sérialisation JSON
   - Gestion des erreurs réseau
   - Chargement d'images avec Coil

5. **UX/UI**
   - Design moderne et cohérent
   - Feedback utilisateur (loading, erreurs, succès)
   - Animations et transitions
   - Responsive design

### Défis techniques relevés :

✅ Gestion de l'état complexe du panier (ajout, suppression, quantités)
✅ Navigation avec passage de paramètres
✅ Appels API asynchrones avec gestion d'erreurs
✅ Interface réactive qui se met à jour automatiquement
✅ Filtrage dynamique par catégorie
✅ Calcul en temps réel du total du panier

---

## 🎯 Prochaines améliorations possibles

- [ ] ✅ **Filtrage par catégorie** (DÉJÀ IMPLÉMENTÉ)
- [ ] 🔍 Recherche de produits par nom
- [ ] 🔐 Authentification utilisateur
- [ ] 📜 Historique des commandes
- [ ] ❤️ Favoris/Wishlist
- [ ] 🌙 Mode sombre complet
- [ ] 💾 Cache local avec Room Database
- [ ] 🧪 Tests unitaires et d'intégration
- [ ] 🌍 Support multilingue (i18n)
- [ ] 💳 Intégration de paiement

---

## 📝 Notes importantes

- ⚠️ L'application nécessite une **connexion Internet** pour fonctionner
- 🌐 Les données sont récupérées depuis l'**API FakeStore** (https://fakestoreapi.com)
- 💾 Le panier est stocké **en mémoire** (réinitialisé au redémarrage de l'app)
- 📱 Compatible avec **Android API 24** (Android 7.0) et supérieur
- 🎨 Interface en **français** pour une meilleure expérience utilisateur

---

## 👨‍💻 Informations développeur

**Développé avec ❤️ en Kotlin et Jetpack Compose**

- **Langage** : Kotlin 100%
- **Framework UI** : Jetpack Compose
- **Architecture** : MVVM
- **API** : FakeStore API
- **Version** : 1.0
- **Dernière mise à jour** : 2025

---


