package au.com.langdale.webserver;

/**
 * Hello world!
 *
 */
import au.com.langdale.webserver._
import Driver._       // start and stop Jetty
import Connectors._   // Jetty-specific connection methods such as listen()
import Handlers._     // Jetty-specific binding methods get("...") and post("...")
import Responders._   // generic responders and implicit conversions for responses
import Requests._     // generic request extractors and wrapper

object WebServer {
  val host = "127.0.0.1"
  val port = 8080

  // tell Jetty where to receive connections
  listen(host, port)

  // may be omitted since this is the default for XML responses
  implicit val contentType = ContentType("application/xhtml+xml")

  // hello world server
  get("/") { request =>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head><title>The Test Page</title></head>
      <body>
        <h1>Testing</h1>
        <p>Hello {request("yourname") getOrElse "stranger"}!</p>
      </body>
    </html>
  }
  
  def main(args: Array[String]) = { start; join }
}
