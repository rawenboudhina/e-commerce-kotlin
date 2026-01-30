package com.rawen.e_commerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rawen.e_commerce.ui.screens.CartScreen
import com.rawen.e_commerce.ui.screens.ProductDetailScreen
import com.rawen.e_commerce.ui.screens.ProductListScreen
import com.rawen.e_commerce.ui.viewmodel.CartViewModel
import com.rawen.e_commerce.ui.viewmodel.ProductViewModel

sealed class Screen(val route: String) {
    object ProductList : Screen("product_list")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    object Cart : Screen("cart")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    productViewModel: ProductViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        composable(Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                },
                productViewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(
                productId = productId,
                onBackClick = {
                    navController.popBackStack()
                },
                cartViewModel = cartViewModel
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onOrderSuccess = {
                    navController.popBackStack(Screen.ProductList.route, inclusive = false)
                },
                cartViewModel = cartViewModel
            )
        }
    }
}
