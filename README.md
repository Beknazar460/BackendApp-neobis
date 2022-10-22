# Hello Kyzzhibek Orozbekova 
## You're so realy beautiful mentor and clever
### To run the tests, you just need to go to the test folder, and then run the test api in the controllers folder
And don't forget, There is swagger ui, you're need open swagger user interface in chrome and to run my endpoints

# Docker file
### 1. Build an image for postgres ```docker pull postgres```
### 2. Run the PostgreSql server  ```docker run --name postgres-laptopshop -e POSTGRES_DB=springjpa -e POSTGRES_PASSWORD=Tommishelby04 -p 5432:5432 -d postgres```
### 3. Create jar file ```mvn clean package```
### 4. Build app container ```docker build -t laptopshop .```
### 5. Run container ```docker run -d -p 8080:8080 --name laptopshop --link postgres-laptopshop postgres```

# Docker compose
### 1. run docker-compose ```docker-compose -f docker-compose.yml up```