package annotations.demo

import org.eclipse.core.runtime.Plugin
import org.osgi.framework.BundleContext

/**
 * plugin activator
 * Eclipse request this style...
 */
class Activator extends Plugin {

  Activator._plugin = this

  override def start(context: BundleContext) {
    super.start(context)
  }
}

object Activator {
  var _plugin: Activator = _

  def plugin = _plugin
}
