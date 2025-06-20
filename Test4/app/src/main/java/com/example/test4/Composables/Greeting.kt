package com.example.test4.Composables

import android.content.Intent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.test4.constraintLayoutsPractices.ConstraintActivity

@Composable
fun Greeting(modifier: Modifier = Modifier) {
//    CircularProgressIndicator(
//        progress = 4f,
//        modifier = modifier,
//        color = Color.Cyan,
//        strokeWidth = 8.dp
//    )
//    Canvas(modifier = Modifier.size(300.dp)) {
//        // Draw a rectangle
//        drawRect(
//            color = Color.Blue,
//            topLeft = Offset(100f, 100f),
//            size = Size(200f, 200f)
//        )
//
//        // Draw a circle
//        drawCircle(
//            color = Color.Red,
//            radius = 100f,
//            center = Offset(200f, 200f)
//        )
//
//        // Draw a line
//        drawLine(
//            color = Color.Green,
//            start = Offset(50f, 50f),
//            end = Offset(250f, 250f),
//            strokeWidth = 5f
//        )
//    }
//
//    Canvas(modifier = Modifier.size(300.dp)) {
//        val path = Path().apply {
//            moveTo(100f, 100f)
//            lineTo(200f, 100f)
//            lineTo(150f, 200f)
//            close()
//        }
//
//        drawPath(
//            path = path,
//            color = Color.Magenta
//        )
//    }
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )
    Canvas(modifier = Modifier.size(300.dp)) {
        val paint = Paint().apply {
            color = Color.Cyan
            style = PaintingStyle.Stroke
            strokeWidth = 10f
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
        }

        drawCircle(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset.Zero,
                end = Offset(x = translateAnim.value, y = translateAnim.value)
            ),
            center = center,
            radius = size.minDimension / 2 - 20f,

            )
    }

//    Canvas(modifier = Modifier.size(300.dp)) {
//        rotate(45f) {
//            drawRect(
//                color = Color.Blue,
//                size = Size(200f, 100f),
//                topLeft = Offset(50f, 100f)
//            )
//        }
//
//        scale(1.5f) {
//            drawCircle(
//                color = Color.Red,
//                radius = 50f,
//                 center = Offset(50f, 120f)
//            )
//        }
//    }
//    val imageBitmap = ImageBitmap.imageResource(R.drawable.my_image)
//
//    Canvas(modifier = Modifier.size(300.dp)) {
//        drawImage(
//            image = imageBitmap,
//            dstSize = IntSize(size.width.toInt(), size.height.toInt())
//        )
//    }

//    Canvas(modifier = Modifier.size(300.dp)) {
//        drawIntoCanvas { canvas ->
//            canvas.nativeCanvas.drawText(
//                "Hello Canvas",
//                100f,
//                100f,
//                android.graphics.Paint().apply {
//                    color = android.graphics.Color.BLACK
//                    textSize = 40f
//                }
//            )
//        }
//    }
    val context = LocalContext.current
    Button(onClick = {  context.startActivity(Intent(context, ConstraintActivity::class.java))}
    ) { Text("Click me") }
}