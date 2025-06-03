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
  conn.con.query("SELECT * FROM cartdetail join store on cartdetail.store_id = store.store_id join item on cartdetail.item_id = item.item_id join cart on cartdetail.cart_id = cart.cart_id where status= 'in cart'", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getPaidCart = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query("SELECT * FROM cartdetail join store on cartdetail.store_id = store.store_id join item on cartdetail.item_id = item.item_id join cart on cartdetail.cart_id = cart.cart_id where status= 'paid'", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.end(JSON.stringify(result));
  });
};

const getCartDetail = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * FROM cartdetail join store on cartdetail.store_id = store.store_id join item on cartdetail.item_id = item.item_id join cart on cartdetail.cart_id = cart.cart_id where cart.user_id = ?",
    req.params.id,
    function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.end(JSON.stringify(result));
    }
  );
};

const addCartDetail = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  conn.con.query(
    "SELECT * from cart where user_id = ?",
    req.params.id,
    function (err1, result, fields) {
      if (err1) throw err1;
      console.log(result[0]);
      const id = result[0].cart_id;
      console.log(id);
      conn.con.query(
        "INSERT INTO cartdetail (cart_id, store_id, item_id, quantity, status) VALUES (?,?,?,1,'in cart')", [id, req.body.store_id,req.body.item_id], function (err2, res2){
          if (err2) throw err2;
          console.log(res2);
          res.end(JSON.stringify(res2));
        }
      )
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

const updateStatus = async (req, res) => {
  res.setHeader("Content-Type", "application/json");
  console.log(req.body);
  conn.con.query(
    "UPDATE cartdetail SET status = ? WHERE cartDetail_id = ?",
    [req.body.status, req.body.id],
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
  searchStore,
  addCartDetail,
  updateStatus,
  getPaidCart
};
