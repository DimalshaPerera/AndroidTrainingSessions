package com.example.test4.constraintLayoutsPractices.composables.ui


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.test4.R

@Composable
fun ConstraintActivityContent(){
ConstraintLayout( modifier = Modifier.fillMaxSize()) {



    val (userImage,icon) = createRefs()
    val camIcon = rememberVectorPainter(
        ImageVector.vectorResource(id = R.drawable.baseline_camera_alt_24)
    )


    Image(
        painter = painterResource(id = R.drawable.download),
        contentDescription = "Circular Image",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .constrainAs(userImage) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            ,
        contentScale = ContentScale.Crop,

    )

    Canvas(
        modifier = Modifier
            .size(23.dp)
            .constrainAs(icon) {
                top.linkTo(userImage.top)
                start.linkTo(userImage.end, margin = -60.dp)
                bottom.linkTo(userImage.bottom, margin = (-70).dp)
                end.linkTo(userImage.end, margin = (-5).dp)
            }
    ) {
        drawCircle(
            color = Color.Green,
            radius = size.minDimension ,
            center = size.center
        )
        with(camIcon) {

            draw(
                size = size,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

    }
}
}
