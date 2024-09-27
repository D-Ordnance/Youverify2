package com.deeosoft.youverifytest2.feature.home.presentation.page

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deeosoft.youverifytest2.R
import com.deeosoft.youverifytest2.core.YouVerifyText
import com.deeosoft.youverifytest2.feature.home.presentation.composable.ProductCard
import com.deeosoft.youverifytest2.feature.home.presentation.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel by viewModels<HomeViewModel>()
        homeViewModel.getProduct()
        setContent {
            Surface {
                var cartCount by remember {
                    mutableIntStateOf(0)
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    AppBar(cartCount)
                    SwipeRefreshListComposable(homeViewModel) { cartCount = it }
                }

            }
        }
    }

    @Composable
    fun AppBar(cartCount: Int){
        Column (modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)){
            Row (verticalAlignment = Alignment.CenterVertically){
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(modifier = Modifier.padding(end = 8.dp), painter = painterResource(id = R.drawable.person), contentDescription = "Person")
                    YouVerifyText(content = "Hi User", fontSize = 18.sp, lineHeight = 21.sp, color = R.color.titleColor)
                }
                Box(contentAlignment = Alignment.TopEnd){
                    Image(painter = painterResource(id = R.drawable.shopping_basket), contentDescription = "Cart")
                    if(cartCount > 0){
                        Box(contentAlignment = Alignment.Center){
                            Image(painter = painterResource(id = R.drawable.ellipse), contentDescription = "CartCount")
                            YouVerifyText(content = "$cartCount", color = R.color.white)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SwipeRefreshListComposable(viewModel: HomeViewModel = hiltViewModel(), param: (Int) -> Unit) {
        var isLoading by remember{ mutableStateOf(false) }
        viewModel.loading.observeForever {
            isLoading = it
        }
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                (viewModel::getProduct)(true)
            }
        ) {
            ContentScreen(viewModel, param)
        }
    }

    @Composable
    fun ContentScreen(viewModel: HomeViewModel, param: (Int) -> Unit){
        var count by remember {
            mutableIntStateOf(0)
        }
        viewModel.failure.observeForever{
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        var hasProduct by remember{ mutableStateOf(false) }
        viewModel.success.observeForever {
            if (it != null) {
                if (it.isNotEmpty()) {
                    hasProduct = true
                }
            }
        }

        if(hasProduct){
            LazyColumn{
                items(viewModel.success.value!!) {
                    ProductCard(
                        id = it!!.id,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        amount = it.price.toString(),
                        count = 0,
                        add = { param(++count)}) {
                        if(count > 0) param(--count)
                    }
                }
            }
        }
    }
}
