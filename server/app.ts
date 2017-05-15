import * as express from 'express';
import { json, urlencoded } from 'body-parser';
import * as path from 'path';
import * as compression from 'compression';

import { loginRouter } from './routes/login';
import { protectedRouter } from './routes/protected';
import { publicRouter } from './routes/public';
import { feedRouter } from './routes/feed';
import { userRouter } from "./routes/user";

const app: express.Application = express();

app.disable('x-powered-by');

app.use(json());
app.use(compression());
app.use(urlencoded({ extended: true }));

app.all('/healthCheck', function(req, res) {
  res.sendStatus(200);
});

/* SERVE CERTBOT .well-known/acmechallenge */
app.use('/.well-known/acme-challenge', express.static(path.join(__dirname, '/../../.well-known/acme-challenge/')));

if('production' === process.env.NODE_ENV) {
  app.all('*', function(req, res, next) {
    if(req.headers["x-forwarded-proto"] === "https") {
      return next();
    }
    res.redirect('https://' + req.hostname + req.url);
  });
}

/*********************************************************************/
/* place all other routes below this so they get the https redirect! */
/*********************************************************************/
app.use(express.static(path.join(__dirname, '/../client/assets/')));

// api routes
/**  
 * we don't need any of these currently.
app.use('/api/secure', protectedRouter);
app.use('/api/login', loginRouter);
app.use('/api/public', publicRouter);
app.use('/api/feed', feedRouter);
app.use('/api/user', userRouter);
**/

// catch 404 and forward to error handler
app.use(function(req: express.Request, res: express.Response, next) {
  let err = new Error('Not Found');
  next(err);
});

// production error handler
// no stacktrace leaked to user
var fs = require('fs');
app.use(function(err: any, req: express.Request, res: express.Response, next: express.NextFunction) {
  if (req.accepts('html')) {
    fs.readFile(path.join(__dirname, '/../client/assets/404.html'), 'utf-8', function(err, page) {
      res.writeHead(404, {'Content-Type': 'text/html'});
      res.write(page);
      res.end();
    });
  }
  else if (req.accepts('json')) {
    res.status(404).send({error: 'Not found' });  
  }
  else {
    res.status(404).type('txt').send('Not found');
  }
});



// Auto renew certs every month
//Add certs to your Node.js Server
// var http = require('https');
// var fs = require('fs');
//
// var sslPath = 'this will be the ssl path';
//
// var options = {
//     key: fs.readFileSync(sslPath + 'privkey.pem'),
//     cert: fs.readFileSync(sslPath + 'fullchain.pem')
// };
//
// this.server = http.createServer(options, this.app);
// this.io = require('socket.io').listen(this.server);
// this.server.listen(443);

// Auto-renew SSL certificates with Lets Encrypt add this to cron job that needs to be setup in the docker.
// how to set up CronJob in Docker:  that https://www.ekito.fr/people/run-a-cron-job-with-docker/
// Monthly renewal command:  @monthly  /path/to/certbot-auto renew --standalone --pre-hook "stop yourWebService" --post-hook "start yourWebService"


export { app }
