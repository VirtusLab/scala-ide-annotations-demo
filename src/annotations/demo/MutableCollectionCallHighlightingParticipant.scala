package annotations.demo

import org.eclipse.jface.text.source.ISourceViewer
import org.scalaide.core.extensions.SemanticHighlightingParticipant
import org.scalaide.ui.internal.editor.decorators.SemanticAction
import org.scalaide.ui.internal.editor.decorators.custom.AllMethodsTraverserDef
import org.scalaide.ui.internal.editor.decorators.custom.CustomSemanticAction
import org.scalaide.ui.internal.editor.decorators.custom.TraverserDef.TypeDefinition

/**
 * Object with some common properties used by this plugin.
 */
object MutableCollectionCallHighlightingParticipant {

  /** Id of annotation, used in plugin.xml */
  lazy val annotationId = "annotations.demo.mutable"

  /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
  lazy val traversers = Seq(
    AllMethodsTraverserDef(
      message = name => s"Method call on a mutable collection: '$name'",
      typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable")))

  /** Highlighting actions */
  def action(viewer: ISourceViewer): SemanticAction =
    new CustomSemanticAction(traversers, viewer, annotationId)
}

/**
 * Custom highlighter for mutable collection methods.
 *
 * It's plugged to Scala IDE extension (see plugin.xml).
 */
class MutableCollectionCallHighlightingParticipant
  extends SemanticHighlightingParticipant(MutableCollectionCallHighlightingParticipant.action)