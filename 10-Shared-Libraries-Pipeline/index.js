const express = require('express');
const app = express;

const PORT = 3000;

app.get("/",(req,res) => {
    res.send("Hey here we are trying to create shared libraries");
});

app.listen(PORT,() => {
    console.log(`Hey the server is running on port ${PORT}`);
});
