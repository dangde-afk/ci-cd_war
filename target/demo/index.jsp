<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Spring Boot web Example</title>
</head>
<body>
    <h1>Welcome to Spring Boot web Example</h1>
    <form action="User Servlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" value="Submit">
    </form>
    <h2>Existing Users</h2>
    <div id="userList">
        <!-- User list will be populated here -->
    </div>
</body>
</html>
