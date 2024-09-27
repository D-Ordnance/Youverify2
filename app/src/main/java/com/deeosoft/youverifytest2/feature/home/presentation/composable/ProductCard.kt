package com.deeosoft.youverifytest2.feature.home.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deeosoft.youverifytest2.R
import com.deeosoft.youverifytest2.core.YouVerifyText

@Composable
fun ProductCard(
    id: Int,
    image: String,
    title: String,
    description: String,
    amount: String,
    count: Int,
    add: () -> Unit,
    remove: () -> Unit){
    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).width(150.dp).height(150.dp),
                    model = image,
                    contentScale = ContentScale.Fit,
                    contentDescription = title)

            Column(modifier = Modifier.padding(end = 8.dp), verticalArrangement = Arrangement.Center) {
                YouVerifyText(content = title, fontSize = 20.sp, lineHeight = 22.sp, color = R.color.titleColor)
                YouVerifyText(content = description, fontSize = 13.sp, lineHeight = 15.sp, maxLines = 2, overflow = TextOverflow.Ellipsis, color = R.color.placeholderColor)
                YouVerifyText(content = "$$amount", fontSize = 20.sp, lineHeight = 22.sp, color = R.color.titleColor)
            }
        }

        Column {
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier.clickable{remove()}, painter = painterResource(id = R.drawable.remove), contentDescription = "Remove")
                YouVerifyText(modifier = Modifier.padding(horizontal = 8.dp), content = "$count", fontSize = 18.sp, lineHeight = 18.sp, color = R.color.titleColor)
                Image(modifier = Modifier.clickable{add()}, painter = painterResource(id = R.drawable.add), contentDescription = "Add")

            }
        }

    }

}