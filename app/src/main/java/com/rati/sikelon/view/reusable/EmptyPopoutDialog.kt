package com.rati.sikelon.view.reusable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun EmptyPopoutDialog(
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        // Background of the dialog
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp), // Rounded corners for the dialog
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(24.dp), // Padding inside the dialog
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title of the dialog
                @Composable
                fun SectionTitle(
                    text: String,
                    fontSize: TextUnit = 20.sp,
                    color: Color = Color.Black,
                    fontWeight: FontWeight = FontWeight.Bold,
                    modifier: Modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = fontWeight,
                            fontSize = fontSize,
                            color = color
                        ),
                        modifier = modifier
                    )
                }

                // Message of the dialog
                @Composable
                fun ConfirmationText(
                    text: String,
                    fontSize: TextUnit = 14.sp,
                    color: Color = Color.Gray,
                    textAlign: TextAlign = TextAlign.Center,
                    modifier: Modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = fontSize,
                            color = color
                        ),
                        modifier = modifier,
                        textAlign = textAlign
                    )
                }

                // Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround, // Space between buttons
                ) {
                    // Negative Button
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f), // Equally distribute space
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Tidak",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // Positive Button
                    Button(
                        onClick = { /* TODO: Handle logout */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Ya",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPopoutDialogPreview() {
    EmptyPopoutDialog(onDismiss = {  })
}
