package com.dicoding.composesubmission.screen.detail

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.dicoding.composesubmission.screen.home.HomeViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterDetail(
    viewModel: HomeViewModel,
    navController: NavHostController,
    uuid: String?
) {
    val agentList by viewModel.agentList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        if (!uuid.isNullOrEmpty()) {
            viewModel.fetchAgentDetails(uuid)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        if (agentList.isNotEmpty()) {
            val characterResponse = agentList[0]
            characterResponse?.let { agent ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = agent.displayName ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(agent.displayIcon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    Text(
                        text = agent.description ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
    BackHandler(onBack = {
        navController.popBackStack("home_screen", inclusive = true)
    })
}

