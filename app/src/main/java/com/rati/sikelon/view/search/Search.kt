package com.rati.sikelon.view.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavController
import com.rati.sikelon.navigate.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    navController: NavController,
    initialQuery: String? = null
) {
    var searchText by rememberSaveable(initialQuery) { mutableStateOf(initialQuery ?: "") }

    val recentSearchesState = remember {
        mutableStateListOf(
            "Prenagen Mommy", "Kopi Kapal Api", "Le Minerale", "Beng-beng",
            "Sabun Mandi", "Bear Brand", "Shampoo"
        )
    }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cari") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Cari produk atau toko") },
                leadingIcon = {
                    Icon(Icons.Filled.Search, "Ikon Cari")
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(Icons.Filled.Close, "Hapus Teks")
                        }
                    }
                },
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (searchText.isNotBlank()) {
                            if (recentSearchesState.none { it.equals(searchText, ignoreCase = true) }) {
                                recentSearchesState.add(0, searchText)
                            }
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            navController.navigate("${NavItem.SearchResult.route}/$searchText")
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (recentSearchesState.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Riwayat pencarian", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    Text(
                        "Hapus semua",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        modifier = Modifier.clickable { recentSearchesState.clear() }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(recentSearchesState.toList(), key = { it }) { term ->
                        RecentSearchItem(
                            searchTerm = term,
                            onItemClick = {
                                searchText = term
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                // navController.navigate("hasil_pencarian/$term")
                            },
                            onRemoveClick = { recentSearchesState.remove(term) }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tidak ada riwayat pencarian.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun RecentSearchItem(
    searchTerm: String,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = searchTerm,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Hapus '$searchTerm' dari riwayat",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true, name = "Search Screen With Initial Query")
@Composable
fun SearchScreenWithInitialQueryPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        SearchPage(navController = rememberNavController(), initialQuery = "Kopi Susu")
    }
}

@Preview(showBackground = true, name = "Search Screen No Initial Query")
@Composable
fun SearchScreenNoInitialQueryPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        SearchPage(navController = rememberNavController())
    }
}
