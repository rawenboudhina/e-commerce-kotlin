# E-Commerce Mobile Application

Une application mobile de commerce électronique développée en Kotlin avec Jetpack Compose, utilisant l'API FakeStore.

## 📱 Fonctionnalités

- **Liste de produits** : Affichage de tous les produits disponibles dans une grille élégante
- **Détails du produit** : Page détaillée pour chaque produit avec image, description, prix et évaluations
- **Panier d'achat** : Système de panier complet avec gestion des quantités
- **Validation de commande** : Processus de commande simple et intuitif
- **Design moderne** : Interface utilisateur Material Design 3 avec animations fluides

## 🏗️ Architecture

L'application suit une architecture MVVM (Model-View-ViewModel) propre et organisée :

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

## 🚀 Installation et lancement

1. **Cloner le projet** (si applicable)
   ```bash
   git clone <repository-url>
   cd ecommerce
   ```

2. **Ouvrir dans Android Studio**
   - Ouvrir Android Studio
   - File → Open → Sélectionner le dossier du projet

3. **Synchroniser Gradle**
   - Android Studio devrait automatiquement synchroniser les dépendances
   - Si ce n'est pas le cas : File → Sync Project with Gradle Files

4. **Lancer l'application**
   - Connecter un appareil Android ou démarrer un émulateur
   - Cliquer sur le bouton Run (▶️) ou appuyer sur Shift+F10

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

## 🎯 Prochaines améliorations possibles

- [ ] Filtrage par catégorie
- [ ] Recherche de produits
- [ ] Authentification utilisateur
- [ ] Historique des commandes
- [ ] Favoris/Wishlist
- [ ] Mode sombre complet
- [ ] Animations de transition
- [ ] Cache local avec Room
- [ ] Tests unitaires et d'intégration

## 📝 Notes

- L'application nécessite une connexion Internet pour fonctionner
- Les données sont récupérées depuis l'API FakeStore
- Le panier est stocké en mémoire (réinitialisé au redémarrage)
- Compatible avec Android API 24 (Android 7.0) et supérieur

## 👨‍💻 Développeur

Développé avec ❤️ en Kotlin et Jetpack Compose

---

**Version** : 1.0
**Dernière mise à jour** : 2025
