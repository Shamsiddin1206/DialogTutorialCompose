package shamsiddin.project.dialogtutorial

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import shamsiddin.project.dialogtutorial.ui.theme.DialogTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}
//                    val openAlertDialog = remember { mutableStateOf(false) }
//                    Screen(mutableState = openAlertDialog)
//                    when {
//                        openAlertDialog.value -> {
//                            AlertDialogExample(
//                                onDismissRequest = { openAlertDialog.value = false },
//                                onConfirmation = {
//                                    openAlertDialog.value = false
//                                    println("Confirmation registered")
//                                },
//                                dialogTitle = "Alert dialog example",
//                                dialogText = "This is an example of an alert dialog with buttons.",
//                                icon = Icons.Default.Info
//                            )
//                        }
//                    }

//AlertDialog example
@Composable
fun Screen(mutableState: MutableState<Boolean>){
    Box {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { mutableState.value = true },
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = "Press", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}


//Custom Dialog example
@Composable
fun Content() {
    val context = LocalContext.current
    var showCustomDialog by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showCustomDialog = !showCustomDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Alert Dialog")
        }

    }

    if (showCustomDialog) {
        CustomDialog({
            showCustomDialog = !showCustomDialog
        }, {
            val activity = (context as? Activity)
            activity?.finish()
        })
    }
}

@Composable
fun CustomDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(
        dismissOnBackPress = false,dismissOnClickOutside = false
    )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Red.copy(alpha = 0.8F)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {

                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "Exit app",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Text(
                    text = "Lorem Ipsum is simply dummy text",
                    modifier = Modifier.padding(8.dp), fontSize = 20.sp
                )

                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard",
                    modifier = Modifier.padding(8.dp)
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Cancel")
                    }

                    Button(
                        onClick = { onExit() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Exit")
                    }
                }

            }
        }
    }
}



