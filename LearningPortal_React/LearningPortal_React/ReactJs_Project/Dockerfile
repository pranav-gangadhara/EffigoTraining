FROM node:20-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json first (for caching purposes)
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the application (if a build script is defined in package.json)
RUN npm run build

# Expose the port that the React app runs on
EXPOSE 3000

# Start the React application
CMD ["npm", "start"]