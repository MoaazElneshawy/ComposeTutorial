package com.moaazelneshawy.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextStyling() {
    val fontFamily = FontFamily(
        Font(R.font.oswald_bold, FontWeight.Bold),
        Font(R.font.oswald_light, FontWeight.Light),
        Font(R.font.oswald_medium, FontWeight.Medium),
        Font(R.font.oswald_regular, FontWeight.Normal)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My name : ",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.DarkGray,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            textDecoration = TextDecoration.Underline
                        )
                    ) { append("M") }
                    append("oaaz ")
                    withStyle(
                        SpanStyle(
                            color = Color.DarkGray,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            textDecoration = TextDecoration.Underline
                        )
                    ) { append("E") }
                    append("Elneshawy")
                },
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily
            )

        }
    }
}

