package dev.vdbroek.pepijn98.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import dev.vdbroek.pepijn98.Root
import dev.vdbroek.pepijn98.ui.Pepijn98Theme

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pepijn98Theme {
                Providers(AmbientBackPressHandler provides backPressHandler) {
                    Root.Content()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    val backPressHandler = BackPressHandler()
    Pepijn98Theme {
        Providers(AmbientBackPressHandler provides backPressHandler) {
            Root.Content()
        }
    }
}
