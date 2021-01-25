import org.scalajs.dom

import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.thenable2future

@JSExportTopLevel("Main")
@JSExportAll
object Main {

  def runApp(): Unit = {

    dom.window.console.info("Hello world ! ")

    val resultPromise = js.dynamicImport {
      new HeavyFeature().doHeavyFeature(2)
    }
    resultPromise.foreach(r => {
      println(r)
    })

  }

}
