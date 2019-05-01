package dk.nytmodultest.studieportal.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dk.nytmodultest.studieportal.ui.App
import org.jetbrains.anko.db.*

class StudentDbHelper(ctx: Context = App.instance): ManagedSQLiteOpenHelper(ctx,StudentDbHelper.DB_NAME,null,StudentDbHelper.DB_VERSION) {
    companion object {
        const val DB_NAME = "student.db"
        const val DB_VERSION = 1
        val instance by lazy {StudentDbHelper()}
    }

    override fun onCreate(db: SQLiteDatabase){
        db.createTable(StudentTable.NAME, true,
            StudentTable.ID to INTEGER + PRIMARY_KEY,
            StudentTable.STUDENTID to INTEGER,
            StudentTable.FIRSTNAME to TEXT,
            StudentTable.LASTNAME to TEXT,
            StudentTable.USERNAME to TEXT,
            StudentTable.EMAIL to TEXT,
            StudentTable.PHONENR to TEXT,
            StudentTable.CITIZENSHIP to TEXT,
            StudentTable.LAST5YCOUNTRY to TEXT,
            StudentTable.BIRTHCOUNTRY to TEXT,
            StudentTable.MOTHERTONGUE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(StudentTable.NAME,true)
        onCreate(db)
    }
}