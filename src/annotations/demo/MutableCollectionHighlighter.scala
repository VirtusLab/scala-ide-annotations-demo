package annotations.demo

import scala.tools.eclipse.semantichighlighting.SemanticHighlightingReconciliation
import scala.tools.eclipse.semantichighlighting.SemanticHighlightingReconciliationParticipant
import scala.tools.eclipse.semantichighlighting.custom.AllMethodsTraverserDefinition
import scala.tools.eclipse.semantichighlighting.custom.CustomSemanticAction
import scala.tools.eclipse.semantichighlighting.custom.TraverserDefinition
import scala.tools.eclipse.semantichighlighting.custom.TraverserId
import MutableCollectionHighlighter._
import scala.tools.eclipse.semantichighlighting.custom.TraverserDefinition.TypeDefinition
import preferences.MutableCollectionPreferencePage
import scala.tools.eclipse.semantic.SemanticAction
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer

/**
 * Object with some common properties used by this plugin.
 */
object MutableCollectionHighlighter {
  
  /** Id of annotation, used in plugin.xml */
  lazy val ID = "annotations.demo.mutable"

  /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
  lazy val traverser = AllMethodsTraverserDefinition(
    id = TraverserId(ID),
    message = "Method call on a mutable collection.",
    typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable"))

  /** Preference page */
  lazy val properties = MutableCollectionPreferencePage.properties
  
  /** Highlighting actions */
  lazy val actions: List[JavaSourceViewer => SemanticAction] =
    List(viewer => new CustomSemanticAction(viewer, traverser, properties))
}

/**
 * Custom highlihter for mutable collection methods.
 * 
 * It's plugged to Scala IDE extension (see plugin.xml).
 */
class MutableCollectionHighlighter extends SemanticHighlightingReconciliationParticipant(
  reconciler = new SemanticHighlightingReconciliation(actions))