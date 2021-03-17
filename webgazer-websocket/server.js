var http = require('http');
var fs = require('fs');
var url = require('url');
var qs = require('querystring');  
 
var WebSocketServer = require('ws').Server
  , http = require('http')
  , express = require('express')
  , app = express();

app.use(express.static(__dirname + '/public'));

var loc =''
 
// create server
var server = http.createServer( function (request, response) {  
   // analyze received data
	var body = '';  
    request.on('data', function(data) {
        body += data; 
    });
    request.on('end', function() { 
        response.writeHead(200, {"Content-Type": "text/plain", 'Access-Control-Allow-Origin': '*'});
		loc = qs.parse(body).xy
        response.write(qs.parse(body).xy);	
        response.end();
   });   
})
server.listen(8080);

console.log('Server running at http://127.0.0.1:8080/');

var wss = new WebSocketServer({server: server});
wss.on('connection', function(ws) {
  var id = setInterval(function() {
    ws.send(JSON.stringify(loc), 
      function() { });
  }, 100);
  ws.on('message', function incoming(message) {
    console.log(message);
    ws.send('connection is built!');
  });
  ws.on('close', function() {
    clearInterval(id);
  });
});