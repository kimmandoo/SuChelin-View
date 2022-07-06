package Guide.suchelin.config

import Guide.suchelin.DataControl
import android.app.Application
import android.content.Context
import android.provider.ContactsContract

class MyApplication : Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }

        // dataControl
        lateinit var dataControl: DataControl
    }

    override fun onCreate() {
        super.onCreate()

        // 데이터베이스 초기화
        dataControl = DataControl()

    }

}