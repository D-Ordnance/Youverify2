package com.deeosoft.youverifytest2.feature.login.presentation.page

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.deeosoft.youverifytest2.R
import com.deeosoft.youverifytest2.core.OnboardActions
import com.deeosoft.youverifytest2.core.YouVerifyText
import com.deeosoft.youverifytest2.core.YouVerifyTextField
import com.deeosoft.youverifytest2.feature.home.presentation.page.Home
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.presentation.viewmodel.LoginViewModel
import com.deeosoft.youverifytest2.feature.registration.presentation.page.Registration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel by viewModels<LoginViewModel>()

        setContent {
            Surface {
                val emailState = remember { mutableStateOf("") }
                val passwordState = remember { mutableStateOf("") }

                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            YouVerifyText(
                                modifier = Modifier.padding(end = 8.dp),
                                content = "Let\'s get started!",
                                fontSize = 22.sp,
                                lineHeight = 32.sp,
                                color = R.color.titleColor
                            )
                            Image(
                                painter = painterResource(id = R.mipmap.party_popper),
                                contentDescription = "Party Popper"
                            )
                        }
                        YouVerifyText(
                            modifier = Modifier.padding(bottom = 32.dp),
                            content = "Join us and start managing your finances with Fintrack today.",
                            fontSize = 18.sp,
                            lineHeight = 26.sp,
                            color = R.color.titleColor
                        )

                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "Email Address",
                            placeholder = {
                                YouVerifyText(
                                    content = "e.g email@mail.com",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = emailState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            emailState.value = it
                        }
                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "Password",
                            visualTransformation = PasswordVisualTransformation(),
                            placeholder = {
                                YouVerifyText(
                                    content = "e.g email@mail.com",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = passwordState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            passwordState.value = it
                        }
                    }

                    var showDialog by remember {
                        mutableStateOf(false)
                    }
                    loginViewModel.loading.observeForever{
                        showDialog = it
                    }

                    if(showDialog){
                        Dialog(
                            onDismissRequest = {  },
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(64.dp)
                                    .padding(vertical = 20.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }

                    }
                    loginViewModel.success.observeForever {
                        if (it != null) {
                            showDialog = false
                            if(it.success){
                                startActivity(Intent(this@Login, Home::class.java))
                            }else{
                                Toast.makeText(this@Login, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    OnboardActions(
                        actionOneText = "Sign In",
                        {
                            login(
                                viewModel = loginViewModel,
                                email = emailState.value,
                                password = passwordState.value)
                        },
                        actionTwoTextDescription = "Don't have an account? ",
                        actionTwoText = "Sign Up",
                        { signUp() })
                }
            }
        }
    }

    private fun signUp() {
        startActivity(Intent(this, Registration::class.java))
    }

    private fun login(viewModel: LoginViewModel, email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            return
        }
        (viewModel::login)(LoginEntity(email = email, password = password))
    }
}