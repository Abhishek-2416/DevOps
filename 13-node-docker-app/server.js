const express = require('express');
const app = express();

const PORT = 3000;

app.get("/",(req,res) => {
    res.send("Hey here we are learning about the basics of Docker");
});

app.listen(PORT,() => {
    console.log(`Hey the server is running on port ${PORT}`);
});