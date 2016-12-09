'use strict';

const express = require('express');

// CONSTANTS
const PORT = 3000;

// APP
const app = express();
app.get('/', function (req, res) {
	res.send('Hello World\n');
});

app.listen(PORT);
console.log('Running on htpp://localhost:' + PORT);

