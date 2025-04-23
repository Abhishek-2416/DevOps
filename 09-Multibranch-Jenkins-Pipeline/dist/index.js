const express = require('express');
const app = express();

const PORT = 3000;

app.get("/",(req,res) => {
    res.send("Hey this is from the Node server");
});

app.listen(PORT,() => {
    console.log(`"The server is running on port ${PORT}`);
})