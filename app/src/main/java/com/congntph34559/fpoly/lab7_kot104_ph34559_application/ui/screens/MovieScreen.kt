package com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MovieViewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.ROUTE_SCREEN


@Composable
fun MovieScreen(
    navigationController: NavController,
    movieViewModel: MovieViewModel
) {
    val moviesState =
        movieViewModel.movies.observeAsState(initial = emptyList())

    Log.d("zzzzzzzzzzz", "MovieScreen: ${moviesState.value}")
    val context = LocalContext.current
    val movies = moviesState.value
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    var id by remember {
        mutableStateOf("")
    }
    Log.d("id dialog", "MovieScreen: $id")
    if (isShowDialog) {
        DialogCompose(
            title = "Thông báo !",
            mess = "Bạn có chắc chắn xóa $id không?",
            onClose = {
                isShowDialog = false
            },
            onConfirm = {
                movieViewModel.delete(id)
                Toast.makeText(
                    context, "Delete successfully",
                    Toast.LENGTH_SHORT
                ).show()
                isShowDialog = false
                movieViewModel.getMovies()
            }
        )
    }


    MovieColumn(
        movies,
        onEditClick = {
            navigationController.navigate("${ROUTE_SCREEN.edit.name}/${it}")
        },
        onDeleteClick = {
            isShowDialog = true
            id = it

        }
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                navigationController.navigate(ROUTE_SCREEN.add.name)
            },
            containerColor = Color.Black,
            modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Add, contentDescription = null,
                tint = Color.White
            )
        }
    }

}

@Composable
fun MovieColumn(
    movies: List<Movie>,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieColumnItem(
                movie = movies[index],
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun BoldValueText(
    label: String,
    value: String,
    style: TextStyle = MaterialTheme.typography.bodySmall
) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}

@Composable
fun MovieColumnItem(
    movie: Movie,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(130.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = movie.filmName,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(100.dp)
                    )
                    Row {
                        IconButton(
                            onClick = {
                                onEditClick(movie.id)
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "Edit",
                                modifier = Modifier.size(15.dp, 15.dp)
                            )
                        }
                        IconButton(
                            onClick = { onDeleteClick(movie.id) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier.size(15.dp, 15.dp)
                            )
                        }
                    }

                }
                BoldValueText(
                    label = "Thời lượng: ",
                    value = "${movie.duration}'"
                )
                BoldValueText(label = "Khởi chiếu: ", value = movie.releaseDate)
                BoldValueText(label = "Thể loại: ", value = movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
    }
}


@Composable
fun DialogCompose(
    title: String,
    mess: String,
    onClose: () -> Unit,
    onConfirm: () -> Unit
) {

    Dialog(
        onDismissRequest = onClose
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)

                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = mess)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Close")
                }

                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text(text = "Confirm")
                }

            }

        }
    }

}


//enum class ListType {
//    ROW, COLUMN, GRID
//}
//
//@Composable
//fun Movie(value: List<Movie>, navController: NavHostController) {
//    val mainViewModel: MovieViewModel = viewModel()
//    val moviesState =
//        mainViewModel.movies.observeAsState(initial = emptyList())
////    MovieScreen(value, navController = navController)
//}
//
//@Composable
//fun MovieScreen(navController: NavController) {
//    var listType by remember { mutableStateOf(ListType.ROW) }
//    val movieViewModel: MovieViewModel = viewModel()
//    val moviesState =
//        movieViewModel.movies.observeAsState(initial = emptyList())
//    val value = moviesState.value
//    Column {
//        Row(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Button(
//                onClick = { listType = ListType.ROW },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black
//                ),
//                shape = RoundedCornerShape(size = 8.dp)
//            ) {
//
//                Text("Row")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(
//                onClick = { listType = ListType.COLUMN },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black
//                ),
//                shape = RoundedCornerShape(size = 8.dp)
//            ) {
//                Text("Column")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(
//                onClick = { listType = ListType.GRID },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black
//                ),
//                shape = RoundedCornerShape(size = 8.dp)
//            ) {
//                Text("Grid")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(
//                onClick = {
//                    navController.navigate(ROUTE_SCREEN.screen1.name)
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black
//                ),
//                shape = RoundedCornerShape(size = 8.dp)
//            ) {
//                Text("Screen")
//            }
//        }
//        when (listType) {
//            ListType.ROW -> MovieRow(value)
//            ListType.COLUMN -> MovieColumn(value,navController)
//            ListType.GRID -> MovieGrid(value)
//        }
//    }
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.BottomEnd
//    ) {
//        FloatingActionButton(
//            onClick = {
//                      navController.navigate(ROUTE_SCREEN.add.name)
//            },
//            containerColor = Color.Black,
//            modifier = Modifier.padding(end = 20.dp, bottom = 20.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = null,
//                tint = Color(0xffffffff)
//            )
//        }
//    }
//}
//
//@Composable
//fun MovieRow(movies: List<Movie>) {
//    LazyRow(
//        state = rememberLazyListState(),
//        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(movies.size) { index ->
//            MovieItemExerciseTwo(movie = movies[index], listType = ListType.ROW)
//        }
//    }
//}
//
//@Composable
//fun MovieColumn(movies: List<Movie>, navController: NavController) {
//    LazyColumn(
//        state = rememberLazyListState(),
//        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(movies.size) { index ->
//            MovieColumnItem(
//                movie = movies[index], listType =
//                ListType.COLUMN,navController
//            )
//        }
//    }
//}
//
//@Composable
//fun MovieColumnItem(
//    movie: Movie,
//    listType: ListType,
//    navController: NavController
//) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation =
//            6.dp
//        ),
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImage(
//                model = movie.image,
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .then(getItemSizeModifier(listType))
//                    .wrapContentHeight()
//                    .padding(start = 10.dp)
//                    .clip(
//                        RoundedCornerShape(
//                            8.dp
//                        )
//                    )
//            )
//            Column(
//                modifier = Modifier.padding(8.dp),
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = movie.filmName,
//                        style = MaterialTheme.typography.titleSmall,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                        modifier = Modifier.width(100.dp)
//                    )
//                    Row(
//
//                    ) {
//                        IconButton(onClick = {
//                            navController.navigate("${ROUTE_SCREEN.edit
//                                .name}/${movie.id}")
//                        }) {
//                            Icon(
//                                imageVector = Icons.Default.Edit,
//                                contentDescription =null,
//                                modifier = Modifier.size(15.dp,15.dp)
//                            )
//                        }
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                imageVector = Icons.Default.Delete,
//                                contentDescription = null,
//                                modifier = Modifier.size(15.dp,15.dp)
//                            )
//                        }
//                    }
//                }
//
//                BoldValueText(
//                    label = "Thời lượng: ", value =
//                    "${movie.duration}'"
//                )
//                BoldValueText(
//                    label = "Khởi chiếu: ", value =
//                    movie.releaseDate
//                )
//                BoldValueText(label = "Thể loại: ", value = movie.genre)
//                Text(
//                    text = "Tóm tắt nội dung",
//                    style = MaterialTheme.typography.bodySmall,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(
//                        top = 4.dp, bottom =
//                        2.dp
//                    )
//                )
//                Text(
//                    text = movie.description,
//                    style = MaterialTheme.typography.bodySmall,
//                    maxLines = 5,
//
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(end = 2.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun MovieGrid(movies: List<Movie>) {
//    val gridState = rememberLazyStaggeredGridState()
//    LazyVerticalStaggeredGrid(
//        columns = StaggeredGridCells.Fixed(2),
//        state = gridState,
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalItemSpacing = 8.dp,
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(movies.size) { index ->
//            MovieItemExerciseTwo(
//                movie = movies[index],
//                listType = ListType.GRID
//            )
//        }
//    }
//}
//
//@Composable
//fun MovieItemExerciseTwo(movie: Movie, listType: ListType) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor =
//            Color.White
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation =
//            6.dp
//        ),
//    ) {
//        Column(
//            modifier = Modifier.then(getItemSizeModifier(listType))
//        ) {
//            AsyncImage(
//                model = movie.image,
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .fillMaxWidth()
//            )
//            Column(
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Text(
//                    text = movie.filmName,
//                    style = MaterialTheme.typography.titleSmall,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis
//                )
//                BoldValueText(
//                    label = "Thời lượng: ", value =
//                    "${movie.duration}'"
//                )
//                BoldValueText(
//                    label = "Khởi chiếu: ", value =
//                    movie.releaseDate
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun BoldValueText(
//    label: String, value: String, style: TextStyle =
//        MaterialTheme.typography.bodySmall
//) {
//    Text(buildAnnotatedString {
//        append(label)
//        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
//            append(value)
//        }
//    }, style = style)
//}
//
//@Composable
//private fun getItemSizeModifier(listType: ListType): Modifier {
//    return when (listType) {
//        ListType.ROW -> Modifier.width(175.dp)
//        ListType.COLUMN -> Modifier
//            .width(130.dp)
//
//        ListType.GRID -> Modifier
//            .fillMaxWidth()
//    }
//}
//
