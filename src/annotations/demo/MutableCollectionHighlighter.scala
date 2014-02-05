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

object MutableCollectionHighlighter {
  lazy val ID = "annotations.demo.mutable"

  lazy val traverser = AllMethodsTraverserDefinition(
    id = TraverserId(MutableCollectionHighlighter.ID),
    message = "Method call on a mutable collection.",
    typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable"))

  lazy val properties = MutableCollectionPreferencePage.properties
  
  lazy val actions: List[JavaSourceViewer => SemanticAction] = try {
    List(viewer => new CustomSemanticAction(viewer, traverser, properties))
  } catch {
    case e => Nil
  }
}

class MutableCollectionHighlighter extends SemanticHighlightingReconciliationParticipant(
  reconciler = new SemanticHighlightingReconciliation(actions))