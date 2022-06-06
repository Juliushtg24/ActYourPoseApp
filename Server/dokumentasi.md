**Base URL**: https://us-central1-actyourpose.cloudfunctions.net/app

---

## Dokumentasi API User

- ```create users/registrasi``` 

| Method |                                URL                                |
|:------:|:-----------------------------------------------------------------:|
|  POST  | https://us-central1-actyourpose.cloudfunctions.net/app/api/create |

| Method | Parameter | Tipe   | Keterangan           |
|:------:|:---------:|--------|----------------------|
|  POST  | name      | String | name/username user   |
|  POST  | email     | String | email yang digunakan |
|  POST  | password  | String | password user        |
|  POST  | level     | int    | confident level      |

Contoh (JSON):
```
{
    "name" : "hi",
    "email" : "hi@gmail.com",
    "password" : "testing123",
    "level" : 8
}
```

- ```get all users```

| Method |                                   URL                                  |
|:------:|:----------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/userDetails |

- ```get user by id``` 

| Method |                                     URL                                    |
|:------:|:--------------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/userDetail/{id} |


---


## Dokumentasi API Topic

- ```get all topics```

| Method |                                URL                                |
|:------:|:-----------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/getAll |

- ```get topic by id```

| Method |                                 URL                                 |
|:------:|:-------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/get/{id} |


---


## Dokumentasi API Picture

- ```get all Bali Data```

| Method |                                 URL                                |
|:------:|:------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/getBali |

- ```get Bali Data by id```

| Method |                                  URL                                 |
|:------:|:--------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/bali/{id} |

- ```get All Bandung Data```

| Method |                                  URL                                  |
|:------:|:---------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/getBandung |

- ```get Bandung Data by id```

| Method |                                   URL                                   |
|:------:|:-----------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/bandung/{id} |

- ```get All Papua Data```

| Method |                                 URL                                 |
|:------:|:-------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/getPapua |

- ```get Papua Data by id```

| Method |                                  URL                                  |
|:------:|:---------------------------------------------------------------------:|
|   GET  | https://us-central1-actyourpose.cloudfunctions.net/app/api/papua/{id} |
