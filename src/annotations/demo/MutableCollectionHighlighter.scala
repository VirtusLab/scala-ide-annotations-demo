package annotations.demo

import MutableCollectionHighlighter._
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer
import org.scalaide.ui.internal.editor.decorators.custom.AllMethodsTraverserDef
import org.scalaide.ui.internal.editor.decorators.SemanticAction
import org.scalaide.ui.internal.editor.decorators.custom.CustomSemanticAction
import org.scalaide.ui.internal.editor.decorators.custom.TraverserId
import org.scalaide.ui.internal.editor.decorators.semantichighlighting.SemanticHighlightingReconciliationParticipant
import org.scalaide.ui.internal.editor.decorators.semantichighlighting.SemanticHighlightingReconciliation
import org.scalaide.ui.internal.editor.decorators.custom.TraverserDef.TypeDefinition

/**
 * Object with some common properties used by this plugin.
 */
object MutableCollectionHighlighter {

  /** Id of annotation, used in plugin.xml */
  lazy val annotationId = "annotations.demo.mutable"

  /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
  lazy val traversers = Seq(
    AllMethodsTraverserDef(
      message = "Method call on a mutable collection.",
      typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable")))

  /** Highlighting actions */
  lazy val actions: List[JavaSourceViewer => SemanticAction] =
    List(viewer => new CustomSemanticAction(traversers, viewer, annotationId))
}

/**
 * Custom highlihter for mutable collection methods.
 *
 * It's plugged to Scala IDE extension (see plugin.xml).
 */
class MutableCollectionHighlighter extends SemanticHighlightingReconciliationParticipant(
  reconciler = new SemanticHighlightingReconciliation(actions))