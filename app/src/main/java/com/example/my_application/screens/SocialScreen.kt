package com.example.my_application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Социальное") },
                actions = {
                    IconButton(onClick = { /* TODO: Открыть чаты */ }) {
                        Icon(Icons.Default.Chat, contentDescription = "Чаты")
                    }
                    IconButton(onClick = { /* TODO: Найти друзей */ }) {
                        Icon(Icons.Default.PersonAdd, contentDescription = "Найти друзей")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                FriendsSection()
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            items(sampleActivities) { activity ->
                ActivityItem(activity)
            }
        }
    }
}

@Composable
fun FriendsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Друзья онлайн",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleFriends) { friend ->
                FriendItem(friend)
            }
        }
    }
}

@Composable
fun FriendItem(friend: Friend) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box {
            AsyncImage(
                model = friend.avatarUrl,
                contentDescription = friend.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            if (friend.isOnline) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.BottomEnd)
                ) {}
            }
        }
        Text(
            text = friend.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )
        if (friend.currentTrack != null) {
            Text(
                text = friend.currentTrack,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityItem(activity: SocialActivity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = activity.userAvatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(MaterialTheme.shapes.small)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = activity.userName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = activity.action,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = activity.time,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (activity.contentImageUrl != null) {
                AsyncImage(
                    model = activity.contentImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 8.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { /* TODO: Лайк */ }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Лайк")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(activity.likes.toString())
                }
                TextButton(onClick = { /* TODO: Комментарий */ }) {
                    Icon(Icons.Default.Comment, contentDescription = "Комментарий")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(activity.comments.toString())
                }
                TextButton(onClick = { /* TODO: Поделиться */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Поделиться")
                }
            }
        }
    }
}

data class Friend(
    val name: String,
    val avatarUrl: String,
    val isOnline: Boolean,
    val currentTrack: String?
)

data class SocialActivity(
    val userName: String,
    val userAvatarUrl: String,
    val action: String,
    val time: String,
    val contentImageUrl: String?,
    val likes: Int,
    val comments: Int
)

val sampleFriends = listOf(
    Friend("Анна", "https://picsum.photos/400", true, "Слушает: Ed Sheeran - Shape of You"),
    Friend("Михаил", "https://picsum.photos/401", true, "Слушает: The Weeknd - Blinding Lights"),
    Friend("Елена", "https://picsum.photos/402", false, null),
    Friend("Дмитрий", "https://picsum.photos/403", true, "Слушает: Drake - God's Plan")
)

val sampleActivities = listOf(
    SocialActivity(
        "Анна",
        "https://picsum.photos/400",
        "добавила новый плейлист",
        "2 часа назад",
        "https://picsum.photos/500",
        12,
        3
    ),
    SocialActivity(
        "Михаил",
        "https://picsum.photos/401",
        "поделился треком",
        "4 часа назад",
        "https://picsum.photos/501",
        8,
        1
    ),
    SocialActivity(
        "Елена",
        "https://picsum.photos/402",
        "оценила альбом",
        "вчера",
        "https://picsum.photos/502",
        15,
        5
    )
) 