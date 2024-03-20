package com.example.androidcomposetutorialone

import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcomposetutorialone.ui.theme.AndroidComposeTutorialOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTutorialOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    var shouldShowContinueButton by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowContinueButton) {
        OnboardScreen(
            onContinueButtonClicked = { shouldShowContinueButton = false },
            modifier = modifier
        )
    } else {
        Greetings(modifier = modifier, names = names)
    }


}

@Composable
fun Greetings(modifier: Modifier, names: List<String>) {
    LazyColumn(modifier = modifier.padding(10.dp)) {
        items(items = names) { name ->
            Greeting(name = name, modifier = Modifier.padding(all = 5.dp))
        }
//        for(name in names){
//            Greeting(name = name, modifier = Modifier.padding(all = 5.dp))
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (isExpanded) 25.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Surface(color = MaterialTheme.colorScheme.primary, modifier = modifier) {
        Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = name, fontSize = 30.sp)
                Text(text = "this is the index", style = MaterialTheme.typography.titleMedium)
                if(isExpanded){
                    Text(text = ("Lerem Ipsun, animation should be bouncy").repeat(2))
                }
            }

            ElevatedButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = if (isExpanded)
                        stringResource(R.string.show_less)
                    else stringResource(R.string.show_more)
                )
            }
        }
    }

}

@Composable
fun OnboardScreen(onContinueButtonClicked: () -> Unit, modifier: Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueButtonClicked() }
        ) {
            Text("Continue")
        }
    }
}

@Preview
@Composable
fun preview() {

}

