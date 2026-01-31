package com.rawen.e_commerce.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rawen.e_commerce.data.model.Product
import com.rawen.e_commerce.data.repository.ProductRepository
import com.rawen.e_commerce.ui.viewmodel.CartViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    onBackClick: () -> Unit,
    cartViewModel: CartViewModel = viewModel()
) {
    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var showAddedMessage by remember { mutableStateOf(false) }

    val repository = remember { ProductRepository() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(productId) {
        scope.launch {
            isLoading = true
            val result = repository.getProductById(productId)
            result.fold(
                onSuccess = {
                    product = it
                    isLoading = false
                },
                onFailure = {
                    error = it.message
                    isLoading = false
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Détails",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        letterSpacing = (-0.5).sp
                    )
                },
                navigationIcon = {
                    Surface(
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.size(48.dp)
                    ) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0066FF),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                modifier = Modifier
                    .height(88.dp)
                    .shadow(8.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFF0066FF),
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Chargement...",
                            color = Color(0xFF64748B),
                            fontSize = 14.sp
                        )
                    }
                }
                error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "😕",
                            fontSize = 48.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Oups! Une erreur est survenue",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = error ?: "Erreur inconnue",
                            color = Color(0xFF64748B),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = onBackClick,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Text("Retour", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
                product != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Product Image with modern gradient background
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color.White,
                                            Color(0xFFF8FAFC)
                                        )
                                    )
                                )
                        ) {
                            AsyncImage(
                                model = product!!.image,
                                contentDescription = product!!.title,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(40.dp),
                                contentScale = ContentScale.Fit
                            )

                            // Rating badge overlay - Modern design
                            Surface(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .align(Alignment.TopEnd),
                                shape = RoundedCornerShape(16.dp),
                                color = Color(0xFFFFF7ED),
                                shadowElevation = 6.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFFB923C),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "${product!!.rating.rate}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF9A3412),
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }

                        // Product Information with modern card design
                        Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                                    shadowElevation = 8.dp
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(24.dp)
                                    ) {
                                        // Category Badge
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = Color(0xFF0066FF),
                                            modifier = Modifier.padding(bottom = 16.dp)
                                        ) {
                                            Text(
                                                text = getCategoryInFrench(product!!.category),
                                                style = MaterialTheme.typography.labelLarge,
                                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                                color = Color.White,
                                                fontWeight = FontWeight.ExtraBold,
                                                letterSpacing = 0.5.sp
                                            )
                                        }

                                        // Title
                                        Text(
                                            text = product!!.title,
                                            style = MaterialTheme.typography.headlineSmall,
                                            fontWeight = FontWeight.ExtraBold,
                                            modifier = Modifier.padding(bottom = 16.dp),
                                            color = Color(0xFF1E293B),
                                            fontSize = 26.sp,
                                            lineHeight = 34.sp
                                        )

                                        // Rating with stars - Modern design
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = Color(0xFFFFF7ED),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 20.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(16.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    repeat(5) { index ->
                                                        Text(
                                                            text = if (index < product!!.rating.rate.toInt()) "★" else "☆",
                                                            style = MaterialTheme.typography.titleLarge,
                                                            color = if (index < product!!.rating.rate.toInt()) Color(0xFFFB923C) else Color(0xFFE2E8F0),
                                                            fontSize = 24.sp
                                                        )
                                                    }
                                                }
                                                Column(
                                                    horizontalAlignment = Alignment.End
                                                ) {
                                                    Text(
                                                        text = "${product!!.rating.rate}/5",
                                                        style = MaterialTheme.typography.titleMedium,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        color = Color(0xFF9A3412),
                                                        fontSize = 18.sp
                                                    )
                                                    Text(
                                                        text = "${product!!.rating.count} avis",
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = Color(0xFF9A3412).copy(alpha = 0.7f),
                                                        fontSize = 12.sp
                                                    )
                                                }
                                            }
                                        }

                                        // Price with modern styling
                                        Surface(
                                            shape = RoundedCornerShape(16.dp),
                                            color = Color(0xFFEFF6FF),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 24.dp),
                                            shadowElevation = 2.dp
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(20.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column {
                                                    Text(
                                                        text = "Prix",
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        color = Color(0xFF64748B),
                                                        fontSize = 14.sp
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = "$${String.format("%.2f", product!!.price)}",
                                                        style = MaterialTheme.typography.headlineLarge,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        color = Color(0xFF0066FF),
                                                        fontSize = 36.sp
                                                    )
                                                }
                                                Surface(
                                                    shape = RoundedCornerShape(12.dp),
                                                    color = Color(0xFF10B981)
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = "✓",
                                                            color = Color.White,
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text(
                                                            text = "En stock",
                                                            style = MaterialTheme.typography.labelLarge,
                                                            color = Color.White,
                                                            fontWeight = FontWeight.ExtraBold
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        // Livraison gratuite badge
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = Color(0xFF10B981).copy(alpha = 0.1f),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 24.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(16.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "🚚",
                                                    fontSize = 24.sp
                                                )
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Column {
                                                    Text(
                                                        text = "Livraison gratuite",
                                                        style = MaterialTheme.typography.bodyLarge,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFF10B981)
                                                    )
                                                    Text(
                                                        text = "Recevez-le sous 3-5 jours",
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = Color(0xFF059669),
                                                        fontSize = 12.sp
                                                    )
                                                }
                                            }
                                        }

                                        Divider(
                                            modifier = Modifier.padding(vertical = 20.dp),
                                            color = Color(0xFFE2E8F0),
                                            thickness = 1.dp
                                        )

                                        // Description
                                        Text(
                                            text = "Description",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.ExtraBold,
                                            modifier = Modifier.padding(bottom = 12.dp),
                                            color = Color(0xFF1E293B),
                                            fontSize = 22.sp
                                        )

                                        Text(
                                            text = product!!.description,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color(0xFF475569),
                                            lineHeight = 28.sp,
                                            fontSize = 16.sp
                                        )

                                        Spacer(modifier = Modifier.height(140.dp))
                                    }
                                }
                            }

                            // Add to Cart Button (Fixed at bottom) - Modern Design
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter),
                                shadowElevation = 16.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                ) {
                                    if (showAddedMessage) {
                                        Button(
                                            onClick = { },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF10B981)
                                            ),
                                            shape = RoundedCornerShape(16.dp),
                                            elevation = ButtonDefaults.buttonElevation(
                                                defaultElevation = 4.dp
                                            )
                                        ) {
                                            Surface(
                                                shape = CircleShape,
                                                color = Color.White.copy(alpha = 0.3f),
                                                modifier = Modifier.size(28.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(
                                                        text = "✓",
                                                        style = MaterialTheme.typography.titleLarge,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                "Ajouté au panier !",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 17.sp
                                            )
                                        }
                                        LaunchedEffect(Unit) {
                                            kotlinx.coroutines.delay(2000)
                                            showAddedMessage = false
                                        }
                                    } else {
                                        Button(
                                            onClick = {
                                                product?.let {
                                                    cartViewModel.addToCart(it)
                                                    showAddedMessage = true
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF0066FF)
                                            ),
                                            shape = RoundedCornerShape(16.dp),
                                            elevation = ButtonDefaults.buttonElevation(
                                                defaultElevation = 4.dp,
                                                pressedElevation = 8.dp
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = null,
                                                modifier = Modifier.size(24.dp)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                "Ajouter au panier",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 17.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }