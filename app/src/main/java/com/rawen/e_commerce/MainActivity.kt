package com.rawen.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.rawen.e_commerce.ui.navigation.NavGraph
import com.rawen.e_commerce.ui.theme.EcommerceTheme
import com.rawen.e_commerce.ui.viewmodel.CartViewModel
import com.rawen.e_commerce.ui.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                EcommerceApp()
            }
        }
    }
}

@Composable
fun EcommerceApp() {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()

    NavGraph(
        navController = navController,
        productViewModel = productViewModel,
        cartViewModel = cartViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun EcommerceAppPreview() {
    EcommerceTheme {
        EcommerceApp()
    }
}