package com.yambrosio.bankingapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yambrosio.bankingapp.domain.model.BottomNavigationItem

val items: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Rounded.Home
    ),
    BottomNavigationItem(
        title = "Wallet",
        icon = Icons.Rounded.Wallet
    ),
    BottomNavigationItem(
        title = "Notifications",
        icon = Icons.Rounded.Notifications
    ),
    BottomNavigationItem(
        title = "Account",
        icon = Icons.Rounded.AccountCircle
    ),
)


@Composable
fun BottomNavigationBar() {
    NavigationBar {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(selected = index == 0, onClick = { }, icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                })
            }
        }
    }
}