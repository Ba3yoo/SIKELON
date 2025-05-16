var mysql = require('mysql');
var fs = require('fs');
var readline = require('readline');

var con1 = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
});

con1.connect(function(err) {
    if (err) throw err;
    console.log("Connected 1!");
});

var rl = readline.createInterface({
  input: fs.createReadStream('./sikelon.sql'),
  terminal: false
 });
rl.on('line', function(chunk){
    con1.query(chunk.toString('ascii'), function(err, sets, fields){
     if(err) console.log(err);
    });
});

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "sikelon"
});

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected 2!");
});

module.exports = {
    con
}