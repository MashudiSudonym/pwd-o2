package c.m.pwdo2.area_stp_requirement.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardWarning(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(Color.Yellow)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Filtration Tank",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Satu jam sekali pompa effluent menyala",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Treated Water Tank / Recycle Tank",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Semua dihitung di recycle",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}