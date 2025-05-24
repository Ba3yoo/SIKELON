package com.rati.sikelon.view.payment

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.navigate.DetailType

// Composable for the EditDetails screen, dynamically showing content based on DetailType
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDetailsScreen(navController: NavController, detailType: DetailType) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit ${detailType.name.lowercase().capitalize()}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Using a when statement to display the correct content based on DetailType
            when (detailType) {
                DetailType.ADDRESS -> AddressDetailsScreen()
                DetailType.SHIPPING -> ShippingDetailsScreen()
                DetailType.PAYMENT -> PaymentDetailsScreen()
            }
        }
    }
}

// --- Address Details Screen Composable ---
@Composable
fun AddressDetailsScreen() {
    val context = LocalContext.current
    var selectedAddress by remember { mutableStateOf("Rumah") } // Default selected

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Addresses",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(Modifier.selectableGroup()) {
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Rumah",
                description = "Jl. Kaliurang RT 1/RW 4, Kecamatan Lowokwaru",
                selected = selectedAddress == "Rumah",
                onClick = { selectedAddress = "Rumah" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Rumah 2",
                description = "Jl. Kaliurang RT 2/RW 4, Kecamatan Lowokwaru",
                selected = selectedAddress == "Rumah 2",
                onClick = { selectedAddress = "Rumah 2" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Kantor",
                description = "Jl. Kaliurang RT 3/RW 4, Kecamatan Lowokwaru",
                selected = selectedAddress == "Kantor",
                onClick = { selectedAddress = "Kantor" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Address changed to: $selectedAddress", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Ubah", style = MaterialTheme.typography.titleMedium)
        }
    }
}

// --- Shipping Details Screen Composable ---
@Composable
fun ShippingDetailsScreen() {
    val context = LocalContext.current
    var selectedShipping by remember { mutableStateOf("Ojek Online") } // Default selected

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shipping Services",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(Modifier.selectableGroup()) {
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Ojek Online",
                description = "Estimasi datang pukul 20.00",
                selected = selectedShipping == "Ojek Online",
                onClick = { selectedShipping = "Ojek Online" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Ambil Sendiri",
                description = "Estimasi datang pukul 17.05",
                selected = selectedShipping == "Ambil Sendiri",
                onClick = { selectedShipping = "Ambil Sendiri" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.LocationOn,
                label = "Pengiriman Toko",
                description = "Estimasi datang pukul 21.00",
                selected = selectedShipping == "Pengiriman Toko",
                onClick = { selectedShipping = "Pengiriman Toko" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Shipping changed to: $selectedShipping", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Ubah", style = MaterialTheme.typography.titleMedium)
        }
    }
}

// --- Payment Details Screen Composable ---
@Composable
fun PaymentDetailsScreen() {
    val context = LocalContext.current
    var selectedPayment by remember { mutableStateOf("Cash on Delivery") } // Default selected

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Payment Methods",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(Modifier.selectableGroup()) {
            DetailRadioButton(
                icon = Icons.Default.AccountBox,
                label = "Cash on Delivery",
                description = null, // No specific description for payment method
                selected = selectedPayment == "Cash on Delivery",
                onClick = { selectedPayment = "Cash on Delivery" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.AccountBox,
                label = "GoPay",
                description = null,
                selected = selectedPayment == "GoPay",
                onClick = { selectedPayment = "GoPay" }
            )
            Spacer(Modifier.height(8.dp))
            DetailRadioButton(
                icon = Icons.Default.AccountBox,
                label = "BCA",
                description = null,
                selected = selectedPayment == "BCA",
                onClick = { selectedPayment = "BCA" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Payment method changed to: $selectedPayment", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Ubah", style = MaterialTheme.typography.titleMedium)
        }
    }
}

// Reusable Radio Button Composable for Address, Shipping, and Payment
@Composable
fun DetailRadioButton(
    icon: ImageVector,
    label: String,
    description: String?,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = label, style = MaterialTheme.typography.titleMedium)
                description?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            RadioButton(
                selected = selected,
                onClick = null // null because the whole row is clickable
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditDetailsScreenPreviewAddress() {
    SimpleAppTheme {
        EditDetailsScreen(
            navController = rememberNavController(),
            detailType = DetailType.ADDRESS
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditDetailsScreenPreviewShipping() {
    SimpleAppTheme {
        EditDetailsScreen(
            navController = rememberNavController(),
            detailType = DetailType.SHIPPING
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditDetailsScreenPreviewPayment() {
    SimpleAppTheme {
        EditDetailsScreen(
            navController = rememberNavController(),
            detailType = DetailType.PAYMENT
        )
    }
}


@Composable
fun SimpleAppTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}