package annotations.demo.preferences

import scala.tools.eclipse.properties.CustomPreferencePage
import scala.tools.eclipse.properties.CustomPreferencePage.Properties
import scala.tools.eclipse.properties.CustomPagePreferenceInitializer

/**
 * Prefererence page for mutable collections, plugs into extension.
 */
class MutableCollectionPreferencePage
  extends CustomPreferencePage("Mutable collections", MutableCollectionPreferencePage.properties)

object MutableCollectionPreferencePage {
  val BASE = "scala.tools.eclipse.ui.preferences.mutable."
  val P_ACTIVE = BASE + "enabled"
  val P_BOLD = BASE + "text.bold"
  val P_ITALIC = BASE + "text.italic"

  val properties = Properties(
    active = MutableCollectionPreferencePage.P_ACTIVE,
    bold = MutableCollectionPreferencePage.P_BOLD,
    italic = MutableCollectionPreferencePage.P_ITALIC)
}

/**
 * Initializer for custom highlighter, plugs into extension.
 */
class MutableCollectionPreferenceInitializer
  extends CustomPagePreferenceInitializer(MutableCollectionPreferencePage.properties)
