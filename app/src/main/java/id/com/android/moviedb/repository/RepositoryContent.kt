package id.com.android.moviedb.repository

import android.app.Application
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import id.com.android.moviedb.model.ModelItemMovie
import id.com.android.moviedb.model.ModelMovieDetail
import id.com.android.moviedb.tools.UtilConstant


@Database(entities = [(ModelItemMovie::class)], version = UtilConstant.DATA_CONTENT_VERSION)
@TypeConverters()
abstract class RepositoryContent : RoomDatabase() {

    abstract fun contentDao(): ContentDao

    @Dao
    interface ContentDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addContent(content: ModelItemMovie): Long

        @Query("DELETE FROM content WHERE id = :id")
        fun deleteFavourite(id: Int?)

        @Query("SELECT * FROM content")
        fun allContent(): List<ModelItemMovie>

        @Query("SELECT id FROM content where hasFavourite LIKE :favourite")
        fun allContentIdFavourite(favourite: Boolean?): List<Int>

        @Query("SELECT * FROM content where id LIKE :id")
        fun getContentById(id: Int?): List<ModelItemMovie>
    }



    class ContentConverterFavourite(var content: ModelItemMovie) {
        fun toContent(): ModelItemMovie? {
            val contentDetail = content
            contentDetail.hasFavourite = true
            return contentDetail
        }
    }

    class ContentDetailConverterFavourite(var content: ModelMovieDetail?, var idMovie: Int) {
        fun toContent(): ModelItemMovie? {
            return ModelItemMovie(false,
                content?.backdrop_path,
                idMovie,
            "",
                content?.original_title,
                content?.overview,0.0,
                content?.poster_path,
                content?.release_date,
                content?.original_title,
                false,0.0,0,true)
        }
    }



    companion object {
        fun newInstance(application: Application): RepositoryContent {
            return Room.databaseBuilder(application, RepositoryContent::class.java, UtilConstant.PARAM_DATABASE)
                .addMigrations(MIGRATION_1_2)
                .build()
        }
        internal val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
             }
        }
    }


}