package com.example.youtube.model.Search

import android.provider.BaseColumns

object DataBaseContainer {

        class Busqueda : BaseColumns{
            companion object {
                val TABLE_NAME = "Busqueda"
                val TITLE = "Title"
            }
        }

    }