const jwt = require('jsonwebtoken');

const verifyToken = (req, res, next) => {
    const token = req.headers["authorization"];

    if (!token) {
        return res.status(403).json({ message: "Token required"});
    }

    const tokenParts = token.split(" ");
    if (tokenParts.length !== 2 || tokenParts[0] !== "Bearer") {
        return res.status(403).json({ message: "Invalid token format"});
    }

    jwt.verify(tokenParts[1], process.env.JWT_SECRET, (err, decoded) => {
        if(err){
            return res.status(401).json({ message: "Invalid token"});
        }
        req.user = decoded;
        next();
    })
};

module.exports = verifyToken;