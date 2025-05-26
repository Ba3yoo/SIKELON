// import { createRequire } from "module";
// const require = createRequire(import.meta.url);

const conn = require("../config/mysql-conn");

const getUsers = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM user", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getUser = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM user WHERE user_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const getCarts = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM cart", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getCart = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM cart WHERE cart_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const getCartDetails = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM cartdetail", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getCartDetail = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM cartdetail join store on cartdetail.store_id = store.store_id join item on cartdetail.item_id = item.item_id where cartdetail.cart_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const updateCartDetails = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  console.log(req.body);
  conn.con.query(
    "UPDATE cartdetail SET item_id = ?, quantity = ? WHERE cartDetail_id = ?",
    [req.body.itemId, req.body.quantity, req.params.id],
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const deleteCartDetail = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "DELETE FROM cartdetail WHERE cartDetail_id = ?",
    [req.params.id],
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const getItems = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM item", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getItem = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM item WHERE item_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const getStores = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM store", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getStore = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM store WHERE store_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const searchItem = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM item WHERE item_name LIKE ?",
    `%${req.body.name}%`,
    function (err, result, fields) {
      if (err) throw err;
      res.end(JSON.stringify(result));
    }
  );
};

const searchStore = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM store WHERE store_name LIKE ?",
    `%${req.body.name}%`,
    function (err, result, fields) {
      if (err) throw err;
      res.end(JSON.stringify(result));
    }
  );
};

//export default {
//  getUsers,
//  getUser,
//  getCarts,
//  getCart,
//  getCartDetails,
//  getCartDetail,
//  getItems,
//  getItem,
//  getStores,
//  getStore,
//  updateCartDetails,
//  deleteCartDetail,
//  searchItem,
//  searchStore
//};

module.exports = {
  getUsers,
  getUser,
  getCarts,
  getCart,
  getCartDetails,
  getCartDetail,
  getItems,
  getItem,
  getStores,
  getStore,
  updateCartDetails,
  deleteCartDetail,
  searchItem,
  searchStore
};
