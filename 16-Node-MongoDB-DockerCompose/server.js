const express = require('express');
const mongoose = require('mongoose');

const app = express();
const PORT = 3000;
const MONGO_URI = "mongodb://mongo-container:27017/mydb";

mongoose.connect(MONGO_URI)
.then(()=>{
    console.log('✅ Connected to MongoDB successfully!');
})
.catch((err)=>{
    console.error('❌ Error connecting to MongoDB:',err);
});

app.get("/",(req,res) => {
    res.send("Here we are trying to create a node and mongo container using docker-compose");
});

app.listen(PORT,() => {
    console.log(`The server is running on port ${PORT}`);
});