const express =  require('express');
const mongoose = require('mongoose');

const app = express();
const PORT = 3000;

//MongoDB connection URI
const MONGO_URI = 'mongodb://mongo-container:27017/mydb'; //We have to use the container name as host name

//Connecting to mongodb
mongoose.connect(MONGO_URI)
.then(()=>{
    console.log('✅ Connected to MongoDB successfully!');
})
.catch((err)=>{
    console.error('❌ Error connecting to MongoDB:',err);
});

app.get("/",(req,res) => {
    res.get("Hey here we are trying to connect to Mongodb and then create its docker container");
});

app.listen(PORT,()=>{
    console.log(`The app is running on port ${PORT}`);
});