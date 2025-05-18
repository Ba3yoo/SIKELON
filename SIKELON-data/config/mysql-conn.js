var mysql = require("mysql");
var fs = require("fs");
var readline = require("readline");

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
});

con.connect(function (err) {
  if (err) throw err;
  console.log("Connected 1!");
});

var rl = readline.createInterface({
  input: fs.createReadStream("./sikelon.sql"),
  terminal: false,
});
rl.on("line", function (chunk) {
  con.query(chunk.toString("ascii"), function (err, sets, fields) {
    if (err) console.log(err);
  });
});

con.changeUser(
  {
    database: "sikelon",
  },
  function (err) {
    if (err) {
      console.log("change error");
    }
  }
);

module.exports = {
  con,
};
