# ADVISER
Application for sharing tips between people

# LAUNCH APP
1. Open docker;
2. Use "docker-compose up" command in terminal;

# Requests
{
	"username":"user",
	"password":"user"
}
or
{
	"username":"password",
	"password":"password"
}

# USERS:

# GET (authenticate)
http://localhost:8080/users/authenticate
# GET (users)
http://localhost:8080/users
# GET USER BY ID
http://localhost:8080/users/1
# POST (user)
http://localhost:8080/users
# PUT (user)
http://localhost:8080/users
# DELETE (user)
http://localhost:8080/users/1

# TIPS:

# GET (tips)
http://localhost:8080/tips
# GET TIP BY ID
http://localhost:8080/tips/1
# POST (tip)
http://localhost:8080/tips
# PUT (tip)
http://localhost:8080/tips
# DELETE (tip)
http://localhost:8080/tips/1

#REMARKS
1. Some features are available exclusively to the admin;
2. Only another admin can appoint an admin;

