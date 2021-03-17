import org.java_websocket.client.WebSocketClient
import java.net.URI;
import org.java_websocket.handshake.ServerHandshake


class ExampleClient(serverUri: URI): WebSocketClient(serverUri) {

    override fun onOpen(handshakedata: ServerHandshake ) {
        send("connection request from Kotlin program")
        println("opened connection")
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    override fun onMessage(message: String) {
        println("received: " + message)
    }


    override fun onClose(code:Int, reason:String, remote:Boolean ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        println("Connection closed by " + remote + " remote peer:" + "us" + " Code: " + code + " Reason: " + reason)
    }

    override fun onError(ex:Exception) {
        ex.printStackTrace()
        // if the error is fatal then onClose will be called additionally
    }
}

fun main() {
    println("start to retrieve data!")
    val c = ExampleClient(URI("ws://localhost:8080")) // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
    c.connect()
}