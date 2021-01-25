import org.scalajs.dom

import scala.scalajs.js.annotation.JSExportAll

class HeavyFeature {
  dom.console.info("HeavyFeature Instantiated")

  def doHeavyFeature(x: Int): Int =
    x * 2

}

