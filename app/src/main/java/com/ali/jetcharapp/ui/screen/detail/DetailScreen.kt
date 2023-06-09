package com.ali.jetcharapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ali.jetcharapp.data.Repository
import com.ali.jetcharapp.ui.ViewModelFactory
import com.ali.jetcharapp.ui.common.UiState
import com.ali.jetcharapp.model.Character
import com.ali.jetcharapp.R
import com.ali.jetcharapp.ui.theme.JetCharAppTheme

@Composable
fun DetailScreen(
    characterId: Long,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Repository())),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCharacterId(characterId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.character,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContent(
    data: Character,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box{
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                AsyncImage(
                    model = data.photo,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    modifier = modifier
                        .height(350.dp)
                        .width(230.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.anime,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.desc,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                )
            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailPreview() {
    JetCharAppTheme() {
        DetailContent(
            data = Character(
                15,
                "L",
                "Death Note",
                "Secretive, meticulous and cunning, L's desire to win coupled with his intellect give him the reputation of being the best detective in the world. His disheveled and childish exterior masks his sharp and analytical mind, throwing off his adversaries. Reclusive and paranoid, L solves his cases by communicating with the outside world via his assistant Watari and a computer with voice scrambler. \n" +
                        "\n" + "L has a sweet tooth, and can often be found crouching instead of sitting normally while snacking on cakes. He is adept at mind games and much like a child, is willing to do almost anything to win, aided by his mysterious, impassive expression and his monotonous voice, which make him impossible to read.",
                "https://cdn.anime-planet.com/characters/primary/l-death-note-1.jpg?t=1625773305"
            ),
            onBackClick = {},
        )
    }

}