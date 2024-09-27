package com.deeosoft.youverifytest2.feature.home.presentation.page

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.deeosoft.youverifytest2.feature.home.domain.usecase.ProductUseCase
import com.deeosoft.youverifytest2.feature.home.presentation.viewmodel.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class HomeTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Mock
    private lateinit var mockUseCase: ProductUseCase

    private lateinit var viewModel: HomeViewModel

    private val titleTextNode = hasText("Hi User") and !hasClickAction()

    @Before
    fun setUp(){
        mockUseCase = Mockito.mock()
        viewModel = HomeViewModel(mockUseCase)
    }

    @Test
    fun `determine_if_title_text_exist`(){
        val headerText = rule.activity.getString(com.deeosoft.youverifytest2.R.string.hi_user)
//        rule.setContent { AppBar() }

        rule.onNode(titleTextNode).performClick()
        rule.onNodeWithText(headerText).assertExists()
    }

    @Test
    fun `determine_if_cart_exist_app_bar_composable`(){
        val toolBarCart = rule.activity.getString(com.deeosoft.youverifytest2.R.drawable.shopping_basket)
        rule.setContent {
//            AppBar()
        }

        rule.onNodeWithText(toolBarCart).assertExists()
    }
}