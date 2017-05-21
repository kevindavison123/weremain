#!/usr/bin/env node

'use strict';

/**
 * Module dependencies.
 */

import { app } from '../app';

/**
 * Get port from environment and store in Express.
 */
const http_port = require('normalize-port')(process.env.HTTP_PORT || 8080);
//const https_port = normalizePort(process.env.HTTPS_PORT || 8443);
const admin_email = process.env.ADMIN_EMAIL;

/**
 * default LetsEncryptServer to 'https://acme-staging.api.letsencrypt.org/directory', set to 'https://acme-v01.api.letsencrypt.org/directory' in production
 */
var LetsEncryptServer = 'https://acme-staging.api.letsencrypt.org/directory';
//if('production' === process.env.NODE_ENV) {
//  LetsEncryptServer = 'https://acme-v01.api.letsencrypt.org/directory';
//}

/**
 * instance of node-greenlock with additional helper methods
 */
const letsencrypt_directory = process.env.WEBROOT;
var lex = require('greenlock-express').create(
  {
      server: LetsEncryptServer,

      challenges: {
        'http-01': require('le-challenge-fs').create({ webrootPath: letsencrypt_directory })
      },

      store: require('le-store-certbot').create({ webrootPath: letsencrypt_directory }),

      approveDomains: approveDomains
  }
);

/** validate information before starting certificate process

Security Warning:

If you don't do proper checks in approveDomains(opts, certs, cb)
an attacker will spoof SNI packets with bad hostnames and that will
cause you to be rate-limited and or blocked from the ACME server.

**/
var ValidDomains = ["weremainfund.org", "www.weremainfund.org"];
function approveDomains(opts, certs, cb) {
  if( certs ) {
    opts.domains = certs.altnames;
  } else {
    opts.email = admin_email;
    opts.agreeTos = true;  
  }

  if((ValidDomains.length === opts.domains.length)) {
    for(let domain of ValidDomains) {
      if( opts.domains.indexOf(domain) <= 0) {
        return;
      }
    }
    cb(null, { options: opts, certs: certs }); 
  }
}

/** start server **/

require('http').createServer(lex.middleware(app)).listen(http_port, function () {
  console.log("Listening for ACME http-01 challenges on", this.address());
});
