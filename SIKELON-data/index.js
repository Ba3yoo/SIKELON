require('./config/dotenv');
const cors = require('cors');
const authRoutes = require('./routes/authRoutes');
const functionRoutes = require('./routes/functionRoutes');
const express = require("express");
const conn = require("./config/mysql-conn");
const app = express();

const PORT = process.env.PORT || 5000;

app.listen(PORT, console.log(`Server started on port ${PORT}`));

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended:true }))

app.use("/func", functionRoutes);
app.use("/auth", authRoutes);
