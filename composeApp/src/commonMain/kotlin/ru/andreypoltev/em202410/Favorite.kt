package ru.andreypoltev.em202410

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Favorite: RealmObject {

    @PrimaryKey
    var id: String = ""
}