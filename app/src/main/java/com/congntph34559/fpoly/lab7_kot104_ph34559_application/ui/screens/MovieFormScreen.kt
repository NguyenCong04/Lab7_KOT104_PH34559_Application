package com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.data.toMovieFormData
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.data.toMovieRequest
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MovieViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun MovieFormScreen(
    navController: NavController,
    movieViewModel: MovieViewModel,
    filmId: String?,
) {
    val movie =
        movieViewModel.getMovieById(filmId).observeAsState(initial = null).value
    val isEditing = filmId != null
    var formData by remember(movie) {
        mutableStateOf(movie?.toMovieFormData() ?: MovieFormData())
    }
    MovieForm(formData = formData, onSave = {
        if (filmId == null) {
            movieViewModel.addFilm(formData.toMovieRequest())
            movieViewModel.getMovies()
            navController.popBackStack()
        } else {
            movieViewModel.updateMovie(formData.toMovieRequest())
            movieViewModel.getMovies()
            navController.popBackStack()
        }
    }, filmId) { updatedFormData ->
        formData = updatedFormData
        val isValid =
            if (isEditing) movie?.let {
                validateMovieDataAndEnsureCompletion(
                    updatedFormData,
                    it
                )
            }
                ?: false
            else isAllFieldsEntered(updatedFormData)
    }
}

@Composable
fun MovieForm(
    formData: MovieFormData,
    onSave: () -> Unit,
    filmId: String?,
    onUpdateFormData: (MovieFormData) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        Text(
            text = if (filmId === null) "Add movie" else "Update movie",
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.filmName,
            onValueChange = { onUpdateFormData(formData.copy(filmName = it)) },
            label = { Text("Tên Phim *") },
            maxLines = 3,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = formData.duration,
                onValueChange = { onUpdateFormData(formData.copy(duration = it)) },
                label = { Text("Thời Lượng *") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DatePickerField(
                label = "Ngày Chiếu *",
                selectedDate = formData.releaseDate,
                onDateSelected = { newDate ->
                    onUpdateFormData(formData.copy(releaseDate = newDate))
                },
                modifier = Modifier.weight(2.6f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.genre,
            onValueChange = { onUpdateFormData(formData.copy(genre = it)) },
            label = { Text("Thể Loại *") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.national,
            onValueChange = { onUpdateFormData(formData.copy(national = it)) },
            label = { Text("Quốc Gia *") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.imageUrl,
            onValueChange = { onUpdateFormData(formData.copy(imageUrl = it)) },
            label = { Text("Liên kết ảnh minh hoạ *") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp),
            value = formData.description,
            onValueChange = { onUpdateFormData(formData.copy(description = it)) },
            label = { Text("Mô Tả *") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onSave()
            }) {
            Text(text = "Save")
        }
    }
}

@Composable
fun DatePickerField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    if (showDialog) {
        val calendar = Calendar.getInstance()
        try {
            calendar.time = dateFormat.parse(selectedDate) ?: Date()
        } catch (_: Exception) {
        }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            context, { _, selectedYear, selectedMonth, dayOfMonth ->
                val newCalendar = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, dayOfMonth)
                }
                onDateSelected(dateFormat.format(newCalendar.time))
            }, year, month, day
        ).apply {
            show()
        }
        LaunchedEffect(Unit) {
            showDialog = false
        }
    }
    OutlinedTextField(
        modifier = modifier,
        value = selectedDate,
        onValueChange = { },
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Chọn ngày",
                modifier = Modifier.clickable { showDialog = true }.size(20
                    .dp,20.dp),
            )
        }
    )
}

fun isAllFieldsEntered(formData: MovieFormData): Boolean {
    return with(formData) {
        filmName.isNotEmpty() && duration.isNotEmpty() && releaseDate.isNotEmpty() &&
                genre.isNotEmpty() && national.isNotEmpty() && description.isNotEmpty() && imageUrl.isNotEmpty()
    }
}

fun validateMovieDataAndEnsureCompletion(
    formData: MovieFormData,
    movie: Movie
): Boolean {
    if (!isAllFieldsEntered(formData)) return false
    if (formData.filmName != movie.filmName) return true
    if (formData.duration != movie.duration) return true
    if (formData.releaseDate != movie.releaseDate) return true
    if (formData.genre != movie.genre) return true
    if (formData.national != movie.national) return true
    if (formData.description != movie.description) return true
    if (formData.imageUrl != movie.image) return true
    return false
}

data class MovieFormData(
    var id: String? = "",
    var filmName: String = "",
    var duration: String = "",
    var releaseDate: String = "",
    var genre: String = "",
    var national: String = "",
    var description: String = "",
    var imageUrl: String = ""
)