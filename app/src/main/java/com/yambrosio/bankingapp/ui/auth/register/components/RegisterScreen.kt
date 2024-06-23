package com.yambrosio.bankingapp.ui.auth.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yambrosio.bankingapp.R
import com.yambrosio.bankingapp.ui.auth.login.components.AlertDialogShow
import com.yambrosio.bankingapp.ui.auth.register.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onLoginBack: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val name: String by registerViewModel.name.observeAsState(initial = "")
    val username: String by registerViewModel.username.observeAsState(initial = "")
    val password: String by registerViewModel.password.observeAsState(initial = "")
    val confirmPassword by registerViewModel.confirmPassword.observeAsState(initial = "")
    val email: String by registerViewModel.email.observeAsState(initial = "")
    val isLoginEnable by registerViewModel.isButtonEnable.observeAsState(initial = false)

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Scaffold(modifier = Modifier
            .fillMaxSize(), topBar = {
            TopAppBar(
                title = { Text(text = "Create an Account") },
                navigationIcon = {
                    IconButton(onClick = { onLoginBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back to login"
                        )
                    }
                }
            )
        }, content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (registerViewModel.state.value.loading)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                else
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_compose),
                            contentDescription = "logo",
                            modifier = Modifier.size(152.dp)
                        )
                        CustomTextField(
                            textPlaceHolder = "Full Name",
                            name,
                            onTextChange = {
                                registerViewModel.onRegisterChanged(
                                    name = it,
                                    username = username,
                                    password = password,
                                    confirmPassword = confirmPassword,
                                    email = email
                                )
                            },
                            icon = Icons.Outlined.Person,
                            description = "profile",
                            keyboardType = KeyboardType.Text
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        CustomTextField(
                            textPlaceHolder = "Username",
                            username,
                            onTextChange = {
                                registerViewModel.onRegisterChanged(
                                    name = name,
                                    username = it,
                                    password = password,
                                    confirmPassword = confirmPassword,
                                    email = email
                                )
                            },
                            icon = Icons.Outlined.Person,
                            description = "profile",
                            keyboardType = KeyboardType.Text
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        CustomTextField(
                            textPlaceHolder = "Email",
                            email,
                            onTextChange = {
                                registerViewModel.onRegisterChanged(
                                    name = name,
                                    username = username,
                                    password = password,
                                    confirmPassword = confirmPassword,
                                    email = it
                                )
                            },
                            icon = Icons.Outlined.Email,
                            "email",
                            keyboardType = KeyboardType.Email
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        CustomPasswordTextField(
                            textPlaceHolder = "Password",
                            password,
                            onTextChange = {
                                registerViewModel.onRegisterChanged(
                                    name = name,
                                    username = username,
                                    password = it,
                                    confirmPassword = confirmPassword,
                                    email = email
                                )
                            }
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        CustomPasswordTextField(
                            textPlaceHolder = "Confirm Password",
                            confirmPassword,
                            onTextChange = {
                                registerViewModel.onRegisterChanged(
                                    name = name,
                                    username = username,
                                    password = password,
                                    confirmPassword = it,
                                    email = email
                                )
                            }
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = {
                                registerViewModel.onLoginSelected()
                            },
                            enabled = isLoginEnable,
                            shape = RoundedCornerShape(32),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                disabledContainerColor = Color(0xFF8BC34A),
                                contentColor = Color.White,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Register",
                                fontSize = 18.sp
                            )

                            if (registerViewModel.state.value.isError) {
                                AlertDialogShow(
                                    onDismissRequest = { registerViewModel.onDismissDialog() },
                                    onConfirmation = { registerViewModel.onDismissDialog() },
                                    dialogTitle = "Error",
                                    dialogText = registerViewModel.state.value.error,
                                    show = registerViewModel.state.value.isError
                                )
                            } else if (registerViewModel.state.value.isSuccess)
                                onLoginBack()
                        }
                    }
            }
        }
        )
    }
}

@Composable
fun CustomTextField(
    textPlaceHolder: String,
    textValue: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    description: String,
    keyboardType: KeyboardType
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = textValue,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = description)
        },
        placeholder = { Text(text = textPlaceHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(
                FocusDirection.Next
            )
        }),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun CustomPasswordTextField(
    textPlaceHolder: String,
    textValue: String,
    onTextChange: (String) -> Unit
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = textValue,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Password")
        },
        placeholder = { Text(text = textPlaceHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(
                FocusDirection.Next
            )
        }),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else
            PasswordVisualTransformation()
    )
}