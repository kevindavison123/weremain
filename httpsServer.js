var http        = require('http')
var express     = require('express')
var app         = express()
var fs          = require('fs')


var static_root = __dirname + '/';
app.set('http_port', (process.env.PORT || 4200));
app.set('https_port', 443);


/*
app.all('/*', function(req, res, next) {
  if(/^http$/.test(req.protocol)) {
    var host = req.headers.host.replace(/:[0-9]+$/g, ""); // strip the port # if any 
    console.log('redirect @ %s', host);
    return res.redirect(301, "https://" + host + ":" + HTTPS_PORT + req.url);
  } else {
    console.log('next!');
    return next();  
  }
})
*/

app.use(express.static(static_root));

app.use(function(req, res, next){
  var accept = req.accepts('html');
  if(accept !== 'html') {
    console.log('serve some html!');
    return next();  
  }

  fs.createReadStream(static_root + 'index.html').pipe(res);
});

app.listen(app.get('http_port'), function() {
  console.log('serving on port ', app.get('http_port'));
})

