package com.rati.sikelon.view.reusable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SectionRow(
    icon: @Composable () -> Unit,
    title: String,
    actionText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            icon()

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    TextButton(
                        onClick = onActionClick,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.height(24.dp)
                    ) {
                        Text(
                            text = actionText,
                            fontSize = 14.sp,
                            color = Color(0xFF9F2BFF)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                content()
            }
        }
    }
}
