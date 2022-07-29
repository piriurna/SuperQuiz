package com.piriurna.superquiz.mappers

import com.piriurna.superquiz.R

enum class CategoryImage(
    val id : Int,
    val imageRes : Int = 0
) {

    GENERAL_KNOWLEDGE(9, R.drawable.ic_general_knowledge_category),
    BOOKS(10, R.drawable.ic_books_category),
    FILM(11, R.drawable.ic_film_category),
    MUSIC(12, R.drawable.ic_music_category),
    MUSICALS_AND_THEATRES(13, R.drawable.ic_musicals_and_theaters_category),
    TELEVISION(14, R.drawable.ic_television_category),
    BOARD_GAMES(16, R.drawable.ic_board_games_category),
    SCIENCE_AND_NATURE(17, R.drawable.ic_physics_category),
    COMPUTERS(18, R.drawable.ic_computers_category),
    MATHEMATICS(19, R.drawable.ic_mathematics_category),
    MYTHOLOGY(20, R.drawable.ic_mythology_category),
    SPORTS(21, R.drawable.ic_sports_category),
    GEOGRAPHY(22, R.drawable.ic_geography_category),
    HISTORY(23, R.drawable.ic_history_category),
    POLITICS(24, R.drawable.ic_politics_category),
    ART(25, R.drawable.ic_art_category),
    CELEBRITIES(26, R.drawable.ic_celebrities_category),
    ANIMALS(27, R.drawable.ic_animals_category),
    VEHICLES(28, R.drawable.ic_vehicles_category),
    COMICS(29, R.drawable.ic_comics_category),
    GADGETS(30, R.drawable.ic_gadgets_category),
    ANIME_AND_MANGA(31, R.drawable.ic_anime_and_manga_category),
    CARTOON_AND_ANIMATION(32, R.drawable.ic_cartoon_and_animation_category),
    UNKOWN_CATEGORY(-1, R.drawable.ic_unknown_category);


    companion object {
        fun getCategoryById(id : Int) : CategoryImage {
            var category =  values().firstOrNull { it.id == id }

            if(category == null) {
                category = UNKOWN_CATEGORY;
            }

            return category
        }
    }
}