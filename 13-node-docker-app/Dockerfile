# 1. This is uses the offical node.js image as base
FROM node:23.10.0

# 2. Set working directory inside container
WORKDIR /app

# 3. Now we need to copy the package.jsona and the package-lock.json files 
COPY package*.json ./

# 4. Installing Dependies
RUN npm install 

# 5. Copy rest of the code 
COPY . .

#6. Expose the port 3000, this is just for developers know about the port 
EXPOSE 3000

# 7. Now we just need to run the command
CMD ["node","server.js"]

# Key difference between the RUN and the CMD is , we use the RUN during the build time the time Docker is building the image
# And we use the CMD during the run time when the Docker is running 