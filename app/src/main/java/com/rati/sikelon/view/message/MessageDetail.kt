package com.rati.sikelon.view.message

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.ui.theme.SIKELONTheme
import com.rati.sikelon.R
import kotlinx.coroutines.launch

// SECTION: Data Models
enum class SenderType {
    USER, OTHER
}

data class Message(
    val id: String,
    val text: String? = null,
    val senderType: SenderType,
    val timestamp: String, // Contoh: "5:10 PM", tidak terlihat tapi penting
    @DrawableRes val imageResId: Int? = null,
    val imageOverlayText: String? = null
)

data class StoreInfo(
    val name: String,
    @DrawableRes val avatarResId: Int,
    val isOnline: Boolean
)

// SECTION: Main Chat Detail Screen Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(
    navController: NavController,
    storeInfo: StoreInfo,
    initialMessages: List<Message>
) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>().also { it.addAll(initialMessages) } }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Scaffold(
        topBar = {
            ChatDetailTopAppBar(
                navController = navController,
                storeInfo = storeInfo
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(messages, key = { it.id }) { message ->
                        MessageBubble(message = message)
                    }
                }
            }

            MessageInputBar(
                inputText = inputText,
                onTextChange = { inputText = it },
                onSendClick = {
                    if (inputText.isNotBlank()) {
                        messages.add(
                            Message(
                                id = (messages.size + 1).toString(),
                                text = inputText,
                                senderType = SenderType.USER,
                                timestamp = "5:14 PM"
                            )
                        )
                        inputText = ""
                    }
                }
            )
        }
    }
}

// SECTION: Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailTopAppBar(
    navController: NavController,
    storeInfo: StoreInfo
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = storeInfo.avatarResId),
                    contentDescription = "${storeInfo.name} Avatar",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = storeInfo.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (storeInfo.isOnline) "Online" else "Offline",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (storeInfo.isOnline) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// SECTION: Message Bubble Composable
@Composable
fun MessageBubble(message: Message) {
    val isUserMessage = message.senderType == SenderType.USER
    val bubbleColor = if (isUserMessage) Color(0xFF7A5DE8) else Color(0xFFE0E0E0)
    val textColor = if (isUserMessage) Color.White else Color.Black
    val bubbleAlignment = if (isUserMessage) Alignment.CenterEnd else Alignment.CenterStart

    val bubbleShape = if (isUserMessage) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    } else {
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (isUserMessage) 48.dp else 0.dp,
                end = if (isUserMessage) 0.dp else 48.dp
            ),
        contentAlignment = bubbleAlignment
    ) {
        Surface(
            shape = bubbleShape,
            color = bubbleColor,
            modifier = Modifier.wrapContentSize()
        ) {
            if (message.imageResId != null) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 200.dp)
                        .clip(bubbleShape)
                ) {
                    Image(
                        painter = painterResource(id = message.imageResId),
                        contentDescription = "Gambar pesan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    if (message.imageOverlayText != null) {
                        Surface(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = message.imageOverlayText,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else if (message.text != null) {
                Text(
                    text = message.text,
                    color = textColor,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

// SECTION: Message Input Bar Composable
@Composable
fun MessageInputBar(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Aksi lampirkan file */ }) {
                Icon(
                    imageVector = Icons.Filled.AddCircleOutline,
                    contentDescription = "Lampirkan File",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .heightIn(min = 48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = inputText,
                    onValueChange = onTextChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (inputText.isEmpty()) {
                            Text(
                                "Ketik pesan...",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    },
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
                )
            }

            IconButton(onClick = onSendClick) {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "Kirim Pesan",
                    tint = if (inputText.isBlank()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

// SECTION: Preview (For quick preview and testing)
@Preview(showBackground = true)
@Composable
fun PreviewChatDetailScreen() {
    SIKELONTheme  {
        ChatDetailScreen(
            navController = rememberNavController(),
            storeInfo = StoreInfo(
                name = "Store K",
                avatarResId = R.drawable.ic_launcher_foreground,
                isOnline = true
            ),
            initialMessages = listOf(
                Message(
                    id = "1",
                    text = "Halo, ada yang bisa saya bantu?",
                    senderType = SenderType.OTHER,
                    timestamp = "5:10 PM"
                ),
                Message(
                    id = "2",
                    text = "Saya ingin tanya tentang produk.",
                    senderType = SenderType.USER,
                    timestamp = "5:11 PM"
                ),
                Message(
                    id = "3",
                    imageResId = R.drawable.stock_beng_beng,
                    imageOverlayText = "Produk terbaru",
                    senderType = SenderType.USER,
                    timestamp = "5:12 PM"
                )
            )
        )
    }
}