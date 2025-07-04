package com.example.firebase

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import kotlin.math.sin

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var forgotPasswordDialogBox by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val crashlytics = Firebase.crashlytics
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        crashlytics.log("Google sign-in launcher result received")
        crashlytics.setCustomKey("sign_in_method", "google")
        try{
        GoogleSignInUtils.doGoogleSignIn(
            context = context,
            scope = scope,
            launcher = null,
            login = {
                Toast.makeText(context, "Google login successful!", Toast.LENGTH_SHORT).show()
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }catch (e:Exception){
            crashlytics.recordException(e)
            crashlytics.log("Google sign-in failed: ${e.message}")
            Toast.makeText(context, "Google sign-in failed", Toast.LENGTH_SHORT).show()

        }    }
    crashlytics.log("User navigated to login screen")
    crashlytics.setCustomKey("current_screen", "login")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Login", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it
                    crashlytics.log("User entered email")},
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it
                    crashlytics.log("User entered email")},
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    crashlytics.log("Email login attempt started")
                    crashlytics.setCustomKey("sign_in_method", "email")
                    crashlytics.setCustomKey("email_domain", email.substringAfter("@"))
                    try{
                    Firebase.auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                crashlytics.log("Email login successful")
                                crashlytics.setCustomKey("login_success", true)
                                crashlytics.setUserId(Firebase.auth.currentUser?.uid ?: "unknown")
                                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                val errorMessage = task.exception?.message ?: "Login failed"
                                crashlytics.log("Email login failed: $errorMessage")
                                crashlytics.setCustomKey("login_success", false)
                                crashlytics.setCustomKey("error_message", errorMessage)

                                task.exception?.let { exception ->
                                    crashlytics.recordException(exception)
                                }

                                Toast.makeText(
                                    context,
                                    task.exception?.message ?: errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }}catch (e:Exception){
                        crashlytics.recordException(e)
                        crashlytics.log("Unexpected error during email login: ${e.message}")
                        Toast.makeText(context, "An unexpected error occurred", Toast.LENGTH_SHORT).show()
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    crashlytics.log("Google sign-in button clicked")
                    crashlytics.setCustomKey("sign_in_method", "google")
                    try{
                    GoogleSignInUtils.doGoogleSignIn(
                        context = context,
                        scope = scope,
                        launcher = googleSignInLauncher,
                        login = {
                            crashlytics.log("Google login successful via button")
                            crashlytics.setCustomKey("login_success", true)
                            Toast.makeText(context, "Google login successful!", Toast.LENGTH_SHORT).show()
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    )
                }catch (e:Exception){
                        crashlytics.recordException(e)
                        crashlytics.log("Google sign-in button error: ${e.message}")
                        Toast.makeText(context, "Google sign-in failed", Toast.LENGTH_SHORT).show()

                    }                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign in with Google")
            }


            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                crashlytics.log("Forgot password dialog opened")
                forgotPasswordDialogBox = true }) {
                Text("Forgot password?")
            }

            TextButton(onClick = {
                crashlytics.log("User clicked signup button")
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }) {
                Text("Don't have an account? Sign up")
            }
        }

        if (forgotPasswordDialogBox) {
            var resetEmail by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = {   crashlytics.log("Forgot password dialog dismissed")
                    forgotPasswordDialogBox = false },
                title = { Text("Forgot Password") },
                text = {
                    OutlinedTextField(
                        value = resetEmail,
                        onValueChange = { resetEmail = it },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                },

                //addOnCompleteListener is a method that waits for an asynchronous task to finish and then runs some code when it's done — whether it succeeded or failed.
                confirmButton = {
                    TextButton(onClick = {
                        if (resetEmail.isNotBlank()) {
                            crashlytics.log("Password reset email attempt")
                            crashlytics.setCustomKey("reset_email_domain", resetEmail.substringAfter("@"))
                            Firebase.auth.sendPasswordResetEmail(resetEmail)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        crashlytics.log("Password reset email sent successfully")
                                        crashlytics.setCustomKey("reset_email_success", true)
                                        Toast.makeText(
                                            context,
                                            "Check your email to reset password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        forgotPasswordDialogBox = false
                                    } else {
                                        val errorMessage = task.exception?.message ?: "Failed to send email"
                                        crashlytics.log("Password reset email failed: $errorMessage")
                                        crashlytics.setCustomKey("reset_email_success", false)

                                        Toast.makeText(
                                            context,
                                            task.exception?.message ?: errorMessage,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        task.exception?.let { exception ->
                                            crashlytics.recordException(exception)
                                        }
                                    }
                                }
                        } else {
                            crashlytics.log("Password reset attempted with empty email")
                            Toast.makeText(
                                context,
                                "Please enter a valid email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("Submit")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        crashlytics.log("Forgot password dialog cancelled")
                        forgotPasswordDialogBox = false
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}