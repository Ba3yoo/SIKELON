// Requiring module
const express = require('express');
const conn = require('./mysql-conn')
// Creating express object
const app = express();

// Handling GET request
app.get('/user', (req, res) => { 
    res.setHeader('Content-Type', 'application/json')
    conn.con.query("SELECT * FROM user", function (err, result, fields) {
        if (err) throw err;
        console.log(result);
        res.end(JSON.stringify(result)) 
    });
}) 

app.get('/cart', (req, res) => { 
    res.setHeader('Content-Type', 'application/json')
    conn.con.query("SELECT * FROM cart", function (err, result, fields) {
        if (err) throw err;
        console.log(result);
        res.end(JSON.stringify(result)) 
    });
}) 

app.get('/cartdetail', (req, res) => { 
    res.setHeader('Content-Type', 'application/json')
    conn.con.query("SELECT * FROM cartdetail", function (err, result, fields) {
        if (err) throw err;
        console.log(result);
        res.end(JSON.stringify(result)) 
    });
}) 

app.get('/item', (req, res) => { 
    res.setHeader('Content-Type', 'application/json')
    conn.con.query("SELECT * FROM item", function (err, result, fields) {
        if (err) throw err;
        console.log(result);
        res.end(JSON.stringify(result)) 
    });
}) 

app.get('/store', (req, res) => { 
    res.setHeader('Content-Type', 'application/json')
    conn.con.query("SELECT * FROM store", function (err, result, fields) {
        if (err) throw err;
        console.log(result);
        res.end(JSON.stringify(result)) 
    });
}) 

// Port Number
const PORT = process.env.PORT ||5000;

// Server Setup
app.listen(PORT,console.log(
  `Server started on port ${PORT}`));