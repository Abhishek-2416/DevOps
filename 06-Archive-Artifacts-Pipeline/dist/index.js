const express =  require('express');
const app = express();

const port = 3000;

app.get("/",(req,res) => {
    res.send("Hello from Jenkins + Node.Js");
});

app.listen(port,() => {
    console.log(`The server is running on port ${port} 🚀`);
}) 

console.log("Hey i am learning Jenkins and here we learn about the archiving artifacts")
