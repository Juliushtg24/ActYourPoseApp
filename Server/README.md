## ML Model Deployment

1. save the ml model in h5 format.
2. create main.py file and load the ml model.
3. create requirements.txt. this file specifying what python packages are required to run the project.
4. create app,yaml. this file is needed to create an app engine.
5. open google cloud console and activate cloud shell.
6. clone this repository.

```
git clone https://github.com/Juliushtg24/ActYourPoseApp.git
```

7. go to this directory.

```
cd ActYourPoseApp
cd Server
```

8. deploy the ml model to the app engine by running the following command.

```
gcloud app deploy
```

9. to see the endpoint or base url, run the following code.

```
gcloud app browse
```

10. to try the API, see the [API documentation](dokumentasi.md).
