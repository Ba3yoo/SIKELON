const express = require('express');
const execs = require("../controller/authController");
const router = express.Router();

router.post("/register/buyer", execs.registerBuyer);
router.post("/login/buyer", execs.loginBuyer);
router.post("/register/seller", execs.registerSeller);
router.post("/login/seller", execs.loginSeller);
router.post("/login/buyer", execs.loginBuyer);
router.post("/register/driver", execs.registerDriver);
router.post("/login/driver", execs.loginDriver);


module.exports = router;