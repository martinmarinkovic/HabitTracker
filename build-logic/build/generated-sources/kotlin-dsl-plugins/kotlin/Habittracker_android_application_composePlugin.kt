/**
 * Precompiled [habittracker.android.application.compose.gradle.kts][Habittracker_android_application_compose_gradle] script plugin.
 *
 * @see Habittracker_android_application_compose_gradle
 */
public
class Habittracker_android_application_composePlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Habittracker_android_application_compose_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
