const express = require('express');
const execs = require("../controller/functionController");
const router = express.Router();

router.get("/user", execs.getUsers);
router.get("/user/:id", execs.getUser);
router.get("/cart", execs.getCarts);
router.get("/cart/:id", execs.getCart);
router.get("/cartdetail", execs.getCartDetails);
router.get("/paidcart", execs.getPaidCart);
router.get("/cartdetail/:id", execs.getCartDetail);
router.put("/cartdetail/:id", execs.updateCartDetails);
router.post("/addcart/:id", execs.addCartDetail);
router.post("/updatestatus", execs.updateStatus);
router.delete("/cartdetail/:id", execs.deleteCartDetail);
router.get("/item/", execs.getItems);
router.get("/item/:id", execs.getItem);
router.get("/store", execs.getStores);
router.get("/store/:id", execs.getStore);
router.post("/search/item", execs.searchItem);
router.post("/search/store", execs.searchStore);

module.exports = router;




