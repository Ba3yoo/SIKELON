// Requiring module
// require('./config/dotenv');
const cors = require('cors');
const authRoutes = require('./routes/authRoutes');
const functionRoutes = require('./routes/functionRoutes');
const express = require("express");
const conn = require("./config/mysql-conn");
// Creating express object
const app = express();

// Port Number
const PORT = process.env.PORT || 5000;

// Server Setup
app.listen(PORT, console.log(`Server started on port ${PORT}`));

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended:true }))

app.use("/func", functionRoutes);

// Handling GET request
// app.get("/user", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query("SELECT * FROM user", function (err, result, fields) {
//     if (err) throw err;
//     console.log(result);
//     res.end(JSON.stringify(result));
//   });
// });

// app.get("/user/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "SELECT * FROM user WHERE user_id = ?",
//     req.params.id,
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.get("/cart", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query("SELECT * FROM cart", function (err, result, fields) {
//     if (err) throw err;
//     console.log(result);
//     res.end(JSON.stringify(result));
//   });
// });

// app.get("/cart/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "SELECT * FROM cart WHERE cart_id = ?",
//     req.params.id,
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.get("/cartdetail", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query("SELECT * FROM cartdetail", function (err, result, fields) {
//     if (err) throw err;
//     console.log(result);
//     res.end(JSON.stringify(result));
//   });
// });

// app.get("/cartdetail/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "SELECT * FROM cartdetail WHERE cartDetail_id = ?",
//     req.params.id,
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.put("/cartdetail/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "UPDATE cartdetail SET item_id = ?, quantity = ? WHERE cartDetail_id = ?",
//     [req.body.itemId, req.body.quantity, req.params.id],
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.delete("/cartdetail/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "DELETE FROM cartdetail WHERE cartDetail_id = ?",
//     [req.params.id],
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.get("/item", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query("SELECT * FROM item", function (err, result, fields) {
//     if (err) throw err;
//     console.log(result);
//     res.end(JSON.stringify(result));
//   });
// });

// app.post("/search/item", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
// //   conn.con.query("SELECT * FROM item WHERE item_name LIKE ?", req.body.name, function (err, result, fields) {
// //     if (err) throw err;
// //     console.log(result1);
//     console.log(req.body);
//     res.end(JSON.stringify(req.body));
//   ;
// });

// app.get("/item/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "SELECT * FROM item WHERE item_id = ?",
//     req.params.id,
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });

// app.get("/store", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query("SELECT * FROM store", function (err, result, fields) {
//     if (err) throw err;
//     console.log(result);
//     res.end(JSON.stringify(result));
//   });
// });

// app.get("/store/:id", (req, res) => {
//   res.setHeader("Content-Type", "application/json");
//   conn.con.query(
//     "SELECT * FROM store WHERE store_id = ?",
//     req.params.id,
//     function (err, result, fields) {
//       if (err) throw err;
//       console.log(result);
//       res.end(JSON.stringify(result));
//     }
//   );
// });
