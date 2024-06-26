package com.congntph34559.fpoly.lab7_kot104_ph34559_application.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("filmId") val id: String,
    @SerializedName("filmName") val filmName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("national") val national: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,



) {
    override fun toString(): String {
        return "Movie(id='$id', filmName='$filmName', duration='$duration', releaseDate='$releaseDate', genre='$genre', national='$national', description='$description', image='$image')"
    }
}


//data class Movie(
//    val id: String,
//    val title: String,
//    val releaseDate: String,
//    val posterUrl: String,
//    val shotDescription: String,
//    val genre: String,
//    val duration: String,
//) {
//    companion object {
////        fun getSampleMovie() = listOf(
////            Movie(
////                "Kung Pu Panda",
////                "2024",
////                "https://s.net.vn/4SUP",
////                145,
////                "1/12/2024",
////                "Hoạt hình, hài hước",
////                "Kung Fu Panda là một bộ phim hoạt hình 3D của Mỹ do DreamWorks Animation sản xuất và ra mắt vào năm 2008. Phim kể về Po, một chú gấu trúc mũm mĩm, vụng về nhưng rất đam mê kung fu. Bất ngờ, Po được chọn làm \"Chiến binh Rồng\" huyền thoại, người được tiên tri sẽ đánh bại tên ác nhân Tai Lung. Dù ban đầu gặp nhiều khó khăn do thiếu kinh nghiệm và tự tin, Po đã nỗ lực rèn luyện dưới sự hướng dẫn của sư phụ Shifu và trở thành một chiến binh kung fu thực thụ."
////            ),
////            Movie(
////                "Kung Pu Panda",
////                "2024",
////                "https://s.net.vn/4SUP",
////                145,
////                "1/12/2024",
////                "Hoạt hình, hài hước",
////                "Kung Fu Panda là một bộ phim hoạt hình 3D của Mỹ do DreamWorks Animation sản xuất và ra mắt vào năm 2008. Phim kể về Po, một chú gấu trúc mũm mĩm, vụng về nhưng rất đam mê kung fu. Bất ngờ, Po được chọn làm \"Chiến binh Rồng\" huyền thoại, người được tiên tri sẽ đánh bại tên ác nhân Tai Lung. Dù ban đầu gặp nhiều khó khăn do thiếu kinh nghiệm và tự tin, Po đã nỗ lực rèn luyện dưới sự hướng dẫn của sư phụ Shifu và trở thành một chiến binh kung fu thực thụ."
////            ),
////            Movie(
////                "Kung Pu Panda",
////                "2024",
////                "https://s.net.vn/4SUP",
////                145,
////                "1/12/2024",
////                "Hoạt hình, hài hước",
////                "Kung Fu Panda là một bộ phim hoạt hình 3D của Mỹ do DreamWorks Animation sản xuất và ra mắt vào năm 2008. Phim kể về Po, một chú gấu trúc mũm mĩm, vụng về nhưng rất đam mê kung fu. Bất ngờ, Po được chọn làm \"Chiến binh Rồng\" huyền thoại, người được tiên tri sẽ đánh bại tên ác nhân Tai Lung. Dù ban đầu gặp nhiều khó khăn do thiếu kinh nghiệm và tự tin, Po đã nỗ lực rèn luyện dưới sự hướng dẫn của sư phụ Shifu và trở thành một chiến binh kung fu thực thụ."
////            ),
////            Movie(
////                "Kung Pu Panda",
////                "2024",
////                "https://s.net.vn/4SUP",
////                145,
////                "1/12/2024",
////                "Hoạt hình, hài hước",
////                "Kung Fu Panda là một bộ phim hoạt hình 3D của Mỹ do DreamWorks Animation sản xuất và ra mắt vào năm 2008. Phim kể về Po, một chú gấu trúc mũm mĩm, vụng về nhưng rất đam mê kung fu. Bất ngờ, Po được chọn làm \"Chiến binh Rồng\" huyền thoại, người được tiên tri sẽ đánh bại tên ác nhân Tai Lung. Dù ban đầu gặp nhiều khó khăn do thiếu kinh nghiệm và tự tin, Po đã nỗ lực rèn luyện dưới sự hướng dẫn của sư phụ Shifu và trở thành một chiến binh kung fu thực thụ."
////            ),
////            Movie(
////                "Kung Pu Panda",
////                "2024",
////                "https://s.net.vn/4SUP",
////                145,
////                "1/12/2024",
////                "Hoạt hình, hài hước",
////                "Kung Fu Panda là một bộ phim hoạt hình 3D của Mỹ do DreamWorks Animation sản xuất và ra mắt vào năm 2008. Phim kể về Po, một chú gấu trúc mũm mĩm, vụng về nhưng rất đam mê kung fu. Bất ngờ, Po được chọn làm \"Chiến binh Rồng\" huyền thoại, người được tiên tri sẽ đánh bại tên ác nhân Tai Lung. Dù ban đầu gặp nhiều khó khăn do thiếu kinh nghiệm và tự tin, Po đã nỗ lực rèn luyện dưới sự hướng dẫn của sư phụ Shifu và trở thành một chiến binh kung fu thực thụ."
////            ),
////
////            )
//    }
//}