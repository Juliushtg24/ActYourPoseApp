import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.preprocessing import image
import numpy as np
from numpy import asfarray
import cv2
import io
import urllib.request

from flask import Flask, url_for, request, render_template, redirect, flash, jsonify

app = Flask(__name__)

# load model
model = tf.keras.models.load_model('model.h5')

def model_predict(path, model):
    img = path
    pict = cv2.imdecode(np.frombuffer(img.read(), np.uint8), cv2.IMREAD_UNCHANGED)
    pict = cv2.resize(pict,(150,150))
    pict = np.expand_dims(pict, axis=0)
    imgs = np.vstack([pict])/ 255


    prediction = model.predict(imgs)
    data = {"prediction": float(prediction)}
    # return jsonify(data)
    if prediction[0] > 0.5:
      return jsonify({"message": "Inside"})
    else:
      return jsonify({"message": "Outside"})

@app.route('/', methods=['POST', 'GET'])
def predict():
	if request.method == 'POST':
		files = request.files['file']
		prediction = model_predict(files, model)
		return prediction

	return "Hello!"


if __name__ == '__main__':
    app.run(debug=True)
