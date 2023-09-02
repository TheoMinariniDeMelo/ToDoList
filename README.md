# ToDoList | Core API

### Core API of ToDoList was created in java spring 

###### contains security system against CSRF created in spring security


### 1 - start the container
~~~shell
docker compose -f docker-compose.prod.yml up --build 
~~~

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

### tasks/source/{id}

###### output:
~~~json
 {
  "dataCreate": "2023-09-02T15:41:34.658Z",
  "dataUpdate": "2023-09-02T15:41:34.658Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "state": "UNFULFILLED",
  "priority": "MAX"
}
~~~

### subTasks/source
~~~
subTasks/source?task={String}&title={String}&pageSize={int}&page={id}&state={id}
~~~
###### output: 
~~~ json
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
      "dataCreate": "2023-09-02T15:43:14.833Z",
      "dataUpdate": "2023-09-02T15:43:14.833Z",
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "title": "string",
      "description": "string",
      "task": {
        "dataCreate": "2023-09-02T15:43:14.833Z",
        "dataUpdate": "2023-09-02T15:43:14.833Z",
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "title": "string",
        "description": "string",
        "state": "UNFULFILLED",
        "priority": "MAX"
      },
      "state": "UNFULFILLED"
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

### subTasks/source/{id}

###### output:

~~~json 
{
  "dataCreate": "2023-09-02T15:45:54.690Z",
  "dataUpdate": "2023-09-02T15:45:54.690Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "task": {
    "dataCreate": "2023-09-02T15:45:54.690Z",
    "dataUpdate": "2023-09-02T15:45:54.690Z",
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "string",
    "description": "string",
    "state": "UNFULFILLED",
    "priority": "MAX"
  },
  "state": "UNFULFILLED"
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

### subTasks/{id}
### body:
~~~json
{
  "title": "string",
  "description": "string"
}
~~~ 
###### output: 

~~~json
   {
  "dataCreate": "2023-09-02T12:38:24.872935",
  "dataUpdate": "2023-09-02T12:38:44.339172034",
  "id": "381e2226-9059-48ae-95cb-ee94662f7a9d",
  "title": "string",
  "description": "string1",
  "task": {
    "dataCreate": "2023-09-02T12:38:12.217832",
    "dataUpdate": null,
    "id": "f89dd8b5-c372-4b54-9902-fb05d2473745",
    "title": "string",
    "description": "string",
    "state": "UNFULFILLED",
    "priority": "AVERAGE"
  },
  "state": "UNFULFILLED"
}
~~~ 

### tasks/{id}
### body:
~~~json
{
  "title": "string",
  "description": "string1"
}
~~~

###### output:
~~~json
{
  "dataCreate": "2023-09-02T12:39:18.082532",
  "dataUpdate": "2023-09-02T12:39:46.919719054",
  "id": "a76efaac-7bc8-416b-99c8-494b767ca91f",
  "title": "string",
  "description": "string1",
  "state": "UNFULFILLED",
  "priority": "AVERAGE"
}
~~~

# DELETE METHOD


### account/delete
### body:

~~~ json
{
  "password": "string"
}
~~~
###### output:
~~~
200 or 401
~~~
### tasks/delete/{id}

###### output: 
~~~json
{
  "dataCreate": "2023-09-02T15:48:44.425Z",
  "dataUpdate": "2023-09-02T15:48:44.425Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "state": "UNFULFILLED",
  "priority": "MAX"
}
~~~
### tasks/deleted/{id}

###### output:
~~~json
{
  "dataCreate": "2023-09-02T15:48:44.425Z",
  "dataUpdate": "2023-09-02T15:48:44.425Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "state": "DELETED",
  "priority": "MAX"
}
~~~

### subTasks/delete/{id}

~~~json
{
  "dataCreate": "2023-09-02T15:50:57.971Z",
  "dataUpdate": "2023-09-02T15:50:57.971Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "state": "UNFULFILLED",
  "priority": "MAX"
}
~~~


### subTasks/deleted/{id}

~~~json
{
  "dataCreate": "2023-09-02T15:50:57.971Z",
  "dataUpdate": "2023-09-02T15:50:57.971Z",
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "state": "DELETED",
  "priority": "MAX"
}
~~~