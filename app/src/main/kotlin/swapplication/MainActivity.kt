package swapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.swapplication.di.appModule
import org.koin.core.context.GlobalContext.startKoin
import swapplication.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализация Koin
        startKoin {
            // Задаем модули, которые будут использоваться для DI
            modules(appModule)
        }
        setContent {
            Log.i("MyLog111", "Попали в сет контент")
            AppNavHost()
        }
    }
}