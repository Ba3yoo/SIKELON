const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const conn = require("../config/mysql-conn");

const registerBuyer = async (req, res) => {
  const { username, name, email, password } = req.body;
  const hashedPass = await bcrypt.hash(password, 10);
  const sql =
    "INSERT INTO user (role, username, name, email, password)" +
    "VALUES ('buyer',?,?,?,?); INSERT INTO cart (user_id) SELECT user_id FROM user WHERE email = ?";
  conn.con.query(
    sql,
    [username, name, email, hashedPass, email],
    (err, result) => {
      if (err)
        return res.status(500).json({
          message: "Register error",
          error: err,
        });
      res.status(201).json({
        message: "Register success",
      });
    }
  );
};

const loginBuyer = (req, res) => {
  const { email, password } = req.body;

  const sql = "SELECT * FROM user WHERE email = ? AND role = 'buyer'";
  conn.con.query(sql, [email], async (err, results) => {
    if (err)
      return res.status(500).json({ message: "Login Error", error: err });

    if (results.length == 0)
      return res.status(401).json({ message: "Invalid email or password" });

    const user = results[0];
    const isMatch = await bcrypt.compare(password, user.password);

    if (!isMatch)
      return res.status(401).json({ message: "Invalid email or password" });

    const token = jwt.sign(
      { id: user.id, email: user.email },
      process.env.JWT_SECRET,
      {
        expiresIn: "1h",
      }
    );
    console.log("in");
    res.json({ token });
  });
};

const registerSeller = async (req, res) => {
  const { username, name, email, password } = req.body;
  const hashedPass = await bcrypt.hash(password, 10);
  const sql =
    "INSERT INTO user (role, username, name, email, password)" +
    "VALUES ('seller',?,?,?,?);  INSERT INTO cart (user_id) SELECT user_id FROM user WHERE email = ?;";
  conn.con.query(
    sql,
    [username, name, email, hashedPass, email, email],
    (err, result) => {
      if (err)
        return res.status(500).json({
          message: "Register error",
          error: err,
        });
      const sql2 = "SELECT * FROM user WHERE email = ? AND role = 'seller'";
      conn.con.query(sql2, email, (err, res2) => {
        const seller = res2;
        res.status(201).json({
          message: "Register success",
          seller,
        });
      });
    }
  );
};

const loginSeller = (req, res) => {
  const { email, password } = req.body;

  const sql = "SELECT * FROM user WHERE email = ? AND role = 'seller'";
  conn.con.query(sql, [email], async (err, results) => {
    if (err)
      return res.status(500).json({ message: "Login Error", error: err });

    if (results.length == 0)
      return res.status(401).json({ message: "Invalid email or password" });

    const seller = results[0];
    const isMatch = await bcrypt.compare(password, seller.password);

    if (!isMatch)
      return res.status(401).json({ message: "Invalid email or password" });

    const token = jwt.sign(
      { id: seller.id, email: seller.email },
      process.env.JWT_SECRET,
      {
        expiresIn: "1h",
      }
    );
    console.log(seller);
    res.json({
      token,
      seller,
    });
  });
};

const registerDriver = async (req, res) => {
  const { username, name, email, password } = req.body;
  const hashedPass = await bcrypt.hash(password, 10);
  const sql =
    "INSERT INTO user (role, username, name, email, password)" +
    "VALUES ('driver',?,?,?,?);  INSERT INTO cart (user_id) SELECT user_id FROM user WHERE email = ?";
  conn.con.query(
    sql,
    [username, name, email, hashedPass, email],
    (err, result) => {
      if (err)
        return res.status(500).json({
          message: "Register error",
          error: err,
        });
      res.status(201).json({
        message: "Register success",
      });
    }
  );
};

const loginDriver = (req, res) => {
  const { email, password } = req.body;

  const sql = "SELECT * FROM user WHERE email = ? AND role = 'driver'";
  conn.con.query(sql, [email], async (err, results) => {
    if (err)
      return res.status(500).json({ message: "Login Error", error: err });

    if (results.length == 0)
      return res.status(401).json({ message: "Invalid email or password" });

    const user = results[0];
    const isMatch = await bcrypt.compare(password, user.password);

    if (!isMatch)
      return res.status(401).json({ message: "Invalid email or password" });

    const token = jwt.sign(
      { id: user.id, email: user.email },
      process.env.JWT_SECRET,
      {
        expiresIn: "1h",
      }
    );
    res.json({ token });
  });
};

module.exports = {
  registerBuyer,
  loginBuyer,
  registerSeller,
  loginSeller,
  registerDriver,
  loginDriver,
};
