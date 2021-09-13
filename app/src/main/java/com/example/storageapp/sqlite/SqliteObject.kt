package bag.dev.rs_task_4_db.data.sqlite

import android.provider.BaseColumns


object SqliteObject : BaseColumns {
    const val DATABASE_NAME = "food.db"
    const val TABLE_NAME = "dogs"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_AGE = "age"
    const val COLUMN_NAME_BREED = "breed"
}