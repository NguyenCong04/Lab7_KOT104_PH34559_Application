package com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MainViewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.ROUTE_SCREEN


enum class ListType {
    ROW, COLUMN, GRID
}

@Composable
fun Movie(value: List<Movie>, navController: NavHostController) {
    val mainViewModel: MainViewModel = viewModel()
    val moviesState =
        mainViewModel.movies.observeAsState(initial = emptyList())
    MovieScreen(value, navController = navController)
}

@Composable
fun MovieScreen(value: List<Movie>, navController: NavController) {
    var listType by remember { mutableStateOf(ListType.ROW) }
    Column {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { listType = ListType.ROW },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {

                Text("Row")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { listType = ListType.COLUMN },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text("Column")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { listType = ListType.GRID },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text("Grid")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                          navController.navigate(ROUTE_SCREEN.screen1.name)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text("Screen")
            }
        }
        when (listType) {
            ListType.ROW -> MovieRow(value)
            ListType.COLUMN -> MovieColumn(value)
            ListType.GRID -> MovieGrid(value)
        }
    }

}

@Composable
fun MovieRow(movies: List<Movie>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItemExerciseTwo(movie = movies[index], listType = ListType.ROW)
        }
    }
}

@Composable
fun MovieColumn(movies: List<Movie>) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieColumnItem(
                movie = movies[index], listType =
                ListType.COLUMN
            )
        }
    }
}

@Composable
fun MovieColumnItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation =
            6.dp
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .then(getItemSizeModifier(listType))
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,

                    overflow = TextOverflow.Ellipsis

                )
                BoldValueText(
                    label = "Thời lượng: ", value =
                    "${movie.time}'"
                )
                BoldValueText(
                    label = "Khởi chiếu: ", value =
                    movie.date
                )
                BoldValueText(label = "Thể loại: ", value = movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 4.dp, bottom =
                        2.dp
                    )
                )
                Text(
                    text = movie.shortDesc,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
    }
}

@Composable
fun MovieGrid(movies: List<Movie>) {
    val gridState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItemExerciseTwo(movie = movies[index], listType = ListType.GRID)
        }
    }
}

@Composable
fun MovieItemExerciseTwo(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =
            Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation =
            6.dp
        ),
    ) {
        Column(
            modifier = Modifier.then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(
                    label = "Thời lượng: ", value =
                    "${movie.time}'"
                )
                BoldValueText(
                    label = "Khởi chiếu: ", value =
                    movie.date
                )
            }
        }
    }
}

@Composable
fun BoldValueText(
    label: String, value: String, style: TextStyle =
        MaterialTheme.typography.bodySmall
) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}

@Composable
private fun getItemSizeModifier(listType: ListType): Modifier {
    return when (listType) {
        ListType.ROW -> Modifier.width(175.dp)
        ListType.COLUMN -> Modifier
            .width(130.dp)

        ListType.GRID -> Modifier
            .fillMaxWidth()
    }
}

