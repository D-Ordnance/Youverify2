package com.deeosoft.youverifytest2.core

import androidx.annotation.ColorRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest2.R

@Composable
fun YouVerifyTextButton(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    onClick: () -> Unit,
    @ColorRes textColor: Int
) {
    YouVerifyText(
        content = content,
        fontSize = fontSize,
        textAlign = textAlign,
        lineHeight = lineHeight,
        textDecoration = textDecoration,
        modifier = modifier.clickable(onClick = onClick),
        color = textColor
    )
}

@Composable
fun YouVerifyText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    @ColorRes color: Int
) {
    Text(
        modifier = modifier,
        text = content,
        textDecoration = textDecoration,
        fontSize = fontSize,
        textAlign = textAlign,
        lineHeight = lineHeight,
        fontFamily = YouVerifyFontFamily,
        fontWeight = FontWeight.Light,
        color = colorResource(id = color)
    )
}

@Composable
fun YouVerifyButton(
    modifier: Modifier = Modifier,
    content: @Composable() (RowScope.() -> Unit),
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    colors: ButtonColors,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        content = content,
        contentPadding = contentPadding,
        onClick = onClick,
        colors = colors
    )
}

@Composable
fun YouVerifyTextField(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    title: String,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {

    Column {
        YouVerifyText(
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp,
            lineHeight = 23.sp,
            content = title,
            color = R.color.titleColor
        )
        TextField(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.textFieldOutlineColor),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = value,
            visualTransformation = visualTransformation,
            onValueChange = onValueChange,
            placeholder = placeholder,
            textStyle = TextStyle(
                color = colorResource(id = R.color.titleColor),
                background = Color.Transparent,
                fontSize = fontSize,
                lineHeight = lineHeight
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

}

@Composable
fun OnboardActions(actionOneText: String,
                   actionOne: () -> Unit,
                   actionTwoTextDescription: String,
                   actionTwoText: String,
                   actionTwo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YouVerifyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            content = {
                YouVerifyText(
                    content = actionOneText,
                    color = R.color.white,
                    lineHeight = 22.sp,
                    fontSize = 18.sp
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
            onClick = actionOne
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            YouVerifyText(
                content = actionTwoTextDescription,
                color = R.color.accentColor,
                lineHeight = 22.sp,
                fontSize = 18.sp
            )
            YouVerifyTextButton(
                onClick = { actionTwo() },
                content = actionTwoText,
                lineHeight = 22.sp,
                textDecoration = TextDecoration.Underline,
                fontSize = 18.sp,
                textColor = R.color.primary
            )
        }
    }

}

val YouVerifyFontFamily = FontFamily(
    Font(R.font.capriola_regular, FontWeight.Light),
)