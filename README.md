# ToDoList | Core API

### Core API of ToDoList was created in java spring 

###### contains security system against CSRF created in spring security


### 1 - start the container mysql
~~~shell
docker run -d -p 3306:3306 --network  backend_todolist -it -e MYSQL_ROOT_PASSWORD=0000 -e MYSQL_DATABASE=list --name mysql mysql
~~~
### 2 - run normally the spring

# POST METHOD:

### /account/register 
###### By default the output port is 8000
### body:
~~~json
{
  "email": "string",
  "user": "string",
  "password": "string"
}

~~~
###### output: 
~~~
 201 or 401 
~~~

### /account/login
### body:
~~~json
{
  "email": "string",
  "password": "string"
}
~~~
###### output:
~~~
 token jwt and 201 or 401 
~~~


### /tasks
### body:
~~~json
{
"title": "string",
"description": "string",
"priority": 1
}
~~~
###### priority: enum(1,2,3)
###### output:
~~~json
{
  "dataCreate": "2023-09-02T12:15:36.277889796",
  "dataUpdate": null,
  "id": "a784b94f-3825-4f5d-afbd-bfce935e62cf",
  "title": "string",
  "description": "string",
  "state": "UNFULFILLED",
  "priority": "MAX"
}
~~~

### /subTasks
### body:
~~~json
{
  "title": "string",
  "description": "string",
  "task": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
~~~
###### output: 
~~~json
{
  "dataCreate": "2023-09-02T12:16:28.224048851",
  "dataUpdate": null,
  "id": "98042999-3be1-4ade-829a-0bf112b32a5b",
  "title": "string",
  "description": "string",
  "task": {
    "dataCreate": "2023-09-02T12:15:36.27789",
    "dataUpdate": null,
    "id": "a784b94f-3825-4f5d-afbd-bfce935e62cf",
    "title": "string",
    "description": "string",
    "state": "UNFULFILLED",
    "priority": "MAX"
  },
  "state": "UNFULFILLED"
}
~~~

# GET METHOD 

~~~
tasks/source?title={String}&page={int}&pageSize={int}&status={int}
~~~ 
###### output: 
~~~json
{
  "totalPages": 0,
  "totalElements": 0,
  "pageable": {
    "pageNumber": 0,
    "pageSize": 0,
    "offset": 0,
    "sort": {
      "sorted": true,
      "empty": true,
      "unsorted": true
    },
    "paged": true,
    "unpaged": true
  },
  "size": 0,
  "content": [
    {
      "dataCreate": "2023-09-02T15:12:59.915Z",
      "dataUpdate": "2023-09-02T15:12:59.915Z",
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "title": "string",
      "description": "string",
      "state": "UNFULFILLED",
      "priority": "MAX"
    }
  ],
  "number": 0,
  "sort": {
    "sorted": true,
    "empty": true,
    "unsorted": true
  },
  "first": true,
  "last": true,
  "numberOfElements": 0,
  "empty": true
}
~~~
# PUT METHOD 
/account/update
### body:
~~~json 
{
"password": "string",
"passwordNew": "string",
"user": "string"
}

~~~
### /tasks/done
~~~ 
tasks/done?key=
~~~
###### output:
~~~json
{
  "dataCreate": "2023-09-02T09:56:15.220152",
  "dataUpdate": "2023-09-02T09:56:30.301217881",
  "id": "fffe8f21-3f25-47ef-a6b8-c11ed577d312",
  "title": "string",
  "description": "string",
  "state": "DONE",
  "priority": "x"
}
~~~


### /subTasks/done
~~~
 /subTasks/done?key={String}
~~~
###### output:
~~~json
{
  "dataCreate": "2023-09-02T12:16:28.224048851",
  "dataUpdate": null,
  "id": "98042999-3be1-4ade-829a-0bf112b32a5b",
  "title": "string",
  "description": "string",
  "task": {
    "dataCreate": "2023-09-02T12:15:36.27789",
    "dataUpdate": null,
    "id": "a784b94f-3825-4f5d-afbd-bfce935e62cf",
    "title": "string",
    "description": "string",
    "state": "UNFULFILLED",
    "priority": "MAX"
  },
  "state": "UNFULFILLED"
}
~~~

### subTasks/{subTaskId}/move
~~~ 
subTasks/{subTaskId}/move?task={String} 
~~~
~~~json
{
  "dataCreate": "2023-09-02T15:25:12.254Z",
  "dataUpdate": "2023-09-02T15:25:12.254Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "task": {
    "dataCreate": "2023-09-02T15:25:12.254Z",
    "dataUpdate": "2023-09-02T15:25:12.254Z",
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "string",
    "description": "string",
    "state": "UNFULFILLED",
    "priority": "MAX"
  },
  "state": "UNFULFILLED"
}
~~~






