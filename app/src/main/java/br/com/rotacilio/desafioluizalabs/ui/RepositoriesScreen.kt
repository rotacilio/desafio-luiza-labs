package br.com.rotacilio.desafioluizalabs.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.rotacilio.desafioluizalabs.R
import br.com.rotacilio.desafioluizalabs.extension.OnBottomReached
import br.com.rotacilio.desafioluizalabs.viewmodel.RepositoriesViewModel
import coil.compose.AsyncImage

@Composable
fun RepositoriesScreen(
    onClickRepository: (String, String) -> Unit
) {

    val viewModel = hiltViewModel<RepositoriesViewModel>()
    val repositories = viewModel.repositories.collectAsState()

    when {
        repositories.value.isEmpty() -> {
            Text(text = "Carregando...")
        }
        else -> {
            val listState = rememberLazyListState()
            LazyColumn(state = listState) {
                items(repositories.value) { item ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onClickRepository(
                                    item.owner.login,
                                    item.name
                                )
                            }
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        RepositoryInfo(
                            modifier = Modifier
                                .weight(4F)
                                .padding(end = 12.dp)
                                .align(Alignment.CenterVertically),
                            item.name,
                            item.description,
                            item.forks,
                            item.watchers
                        )
                        UserInfo(
                            modifier = Modifier
                                .weight(2F),
                            item.owner.login,
                            item.owner.avatarUrl
                        )
                    }
                }
            }

            listState.OnBottomReached {
                Log.d("RepositoriesScreen", "need more")
                viewModel.getRepositories("language:Kotlin", "stars")
            }
        }
    }
}

@Composable
fun RepositoryInfo(
    modifier: Modifier,
    name: String,
    description: String,
    forks: Long,
    watchers: Long
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = Color.Blue,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = description, //stringResource(id = R.string.lorem_ipsum),
            maxLines = 2
        )
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            RepositoryAction(
                icon = R.drawable.ic_fork,
                value = forks.toString()
            )
            Spacer(modifier = Modifier.width(12.dp))
            RepositoryAction(
                icon = R.drawable.ic_baseline_star_24,
                value = watchers.toString()
            )
        }
    }
}

@Composable
fun UserInfo(
    modifier: Modifier,
    userName: String,
    avatarUrl: String
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            model = avatarUrl,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            text = userName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Blue,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Nome Completo",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RepositoryAction(
    @DrawableRes icon: Int,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.DarkGray
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp),
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Start
        )
    }
}