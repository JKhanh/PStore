import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Property delegate for initializing activity's layout views
 */
class ActivityViewBindingProperty<T : ViewBinding>(
    private val bindingInitializer: (LayoutInflater) -> T
) : ViewBindingProperty<T>(), ReadOnlyProperty<AppCompatActivity, T> {

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        return binding ?: run {
            thisRef.lifecycle.let {
                it.addObserver(this)
                lifecycle = it
            }
            bindingInitializer.invoke(thisRef.layoutInflater).also { binding = it }
        }
    }
}