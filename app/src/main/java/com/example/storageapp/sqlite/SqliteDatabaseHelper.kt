package bag.dev.rs_task_4_db.data.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bag.dev.rs_task_4_db.data.sqlite.SqliteObject.COLUMN_NAME_AGE
import bag.dev.rs_task_4_db.data.sqlite.SqliteObject.COLUMN_NAME_BREED
import bag.dev.rs_task_4_db.data.sqlite.SqliteObject.COLUMN_NAME_NAME
import bag.dev.rs_task_4_db.data.sqlite.SqliteObject.DATABASE_NAME
import bag.dev.rs_task_4_db.data.sqlite.SqliteObject.TABLE_NAME
import com.example.storageapp.room.Dog
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


private const val DATABASE_VERSION = 1
private const val LOG_TAG = "sql tag"
private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUMN_NAME_NAME TEXT," +
            "$COLUMN_NAME_AGE INTEGER)," +
            "$COLUMN_NAME_BREED TEXT)"
@Singleton
class SqliteDatabaseHelper @Inject constructor(@ApplicationContext context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(LOG_TAG, "onUpgrade()")
    }

    fun getData(): MutableLiveData<List<Dog>> =
        getListOfTopics("SELECT * FROM $TABLE_NAME")

    fun getSortedName(): MutableLiveData<List<Dog>> =
        getListOfTopics("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME_NAME")

    fun getSortedAge(): MutableLiveData<List<Dog>> =
        getListOfTopics("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME_AGE")


    fun getSortedBreed(): MutableLiveData<List<Dog>> =
        getListOfTopics("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME_BREED")


    @SuppressLint("Recycle")
    fun getListOfTopics(query: String): MutableLiveData<List<Dog>> {
        val dogs = mutableListOf<Dog>()
        readableDatabase.rawQuery(query, null).use{
            if (it.moveToFirst()) {
                do {
                    val name = it.getString(it.getColumnIndex(COLUMN_NAME_NAME))
                    val age = it.getInt(it.getColumnIndex(COLUMN_NAME_AGE))
                    val breed = it.getString(it.getColumnIndex(COLUMN_NAME_BREED))

                    dogs.add(Dog(0, name, age, breed))
                } while (it.moveToNext())
            }
        }




        return MutableLiveData(dogs)
    }

    fun insert(dog: Dog) {
        writableDatabase.insert(TABLE_NAME,null,ContentValues().apply {
            put(COLUMN_NAME_NAME,dog.name)
            put(COLUMN_NAME_AGE,dog.age)
            put(COLUMN_NAME_BREED,dog.breed)
        })
    }


}