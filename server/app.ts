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
app.use(function(err: any, req: express.Request, res: express.Response, next: express.NextFunction) {

  res.status(err.status || 500);
  res.json({
    error: {},
    message: err.message
  });
});

export { app }
