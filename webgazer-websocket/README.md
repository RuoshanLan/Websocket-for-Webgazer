### Motivation:

Bionic tracking technology has to be supported by specific hardwares, such as VR headset and Binocular Add-on from the pupil lab.
Such requirements could make the bionic tracking technology hardly available to *everyday users*.  
To figure out whether there is another approach that can track the eye accurately and conduct the same task as what Bionic tracking does for cell tracking in 2D dimension, we had a check on the Webgazer[<sup>[1]</sup>](#webgazer-paper). Webgazer claims to be designed to provide a natural experience to *everyday users* that is not restricted to laboratories.


### Implementation:

Webgazer is an eye-tracking library and is implemented in the form of the JS file so that it can be embedded into any web page. In its official website[<sup>[2]</sup>](#webgazer-website), the instruction of using it for any website is provided in details. 
Because it relies on the webpage, we use it as the front-end. The back end is implemented with Node.js. We realize the communication between the front end and back end by using ajax.

After the server is built, webgazer will be able to connect to a kotlin program to provide real-time gaze location information for analysis. Here, to avoid the situation that client takes the polling strategy for query, Websocket is used as the communication method.

### Evaluation:

Webgazer itself provides a calibration demo[<sup>[5]</sup>](#webgazer-calibration-demo), where users can get the accuracy rate after calibration. 
According to their own evaluation, they said the average error of webgazer is around 200 pixels, where Samsung SyncMaster 2443 24-inch monitor with a resolution of 1920 by 1200 pixels was used with a Logitech Full HD C920 USB webcam, from a distance of 59cm from the user. 
Unfortunately, since we don't have the exact same experiment equipments, we tried the demo with a logitech web camera and (about) 40-inch monitor/14-inch laptop. The calibration accuracy is always lower than 70%. And after reading related works, we decide to give up this idea of using webgazer because of the following reasons.


#### The design purpose of webgazer is not suitable for our application scenario: 
1. webgazer depends on the common user's interactions with the website to calibrate, such as click, cursor movement. In this way, it can calibrate itself continuously to overcome the problems brought by head movement and can therefore inform a more sophisticated eye-tracking model. However, in our application scenario, such interaction will not be so much.
2.  According to the paper, webgazer is more suitable to be used in applications where the approximate location of the gaze is sufficient instead of highly controlled user studies. But cell tracking is a kind of task that asks for relative high accuracy.


#### The accuracy is not high enough and could be affected easily by many causes:
Although the webgazer achieves great progress compared with other projects using web cameras. But its prediction accuracy is still relatively low compared with some commercial solutions. For example, the pupil tracking technology[<sup>[3]</sup>](#pupil-eye-tracking-paper) used in bionic tracking only has an average error of 1.3 pixels. What's worse, there are still a lot of causes that may lead to failure of prediction for a web camera.

##### The following causes could affect the accuracy: 
The following two causes are from the paper of webgazer:
1. The speed of the external libraries can have a significant effect on WebGazer’s ability to match correctly frames and locations on the screen. 
2. Head movement can also affect the detected images that correspond to eye patches.

For more possible causes, The authors of Turker gazer[<sup>[4]</sup>](#Turkergaze-paper) have already done a deep study on it. Turkergaze is a crowdsourcing platform that guides users through games to collect information on image saliency. Although it takes a different purpose from the webgazer. Their calibration and prediction method is similar. So we can take their conclusion as a reference. According to them, many causes may lead to failure of prediction like follows. 

##### other possible causes: 
1. head movement
2. head pose
3. bad lighting
4. unsupported browser features
5. low computational speed
6. low memory capacity

Among them, head movement is the primary one. So in the Turkergaze, they suggest their users keep them head motionless. However, Webgazer doesn't require this because it tries to reduce the effect of head movement through continuously calibrating.


<div id="webgazer-paper"></div>

- [1] [WebGazer: Scalable Webcam Eye Tracking Using User Interactions](https://www.semanticscholar.org/paper/WebGazer%3A-Scalable-Webcam-Eye-Tracking-Using-User-Papoutsaki-Sangkloy/73fc8e9b1faf45855cceee197f094ca3c05afe1c)

<div id="webgazer-website"></div>

- [2] [webgazer website](https://webgazer.cs.brown.edu/)

<div id="pupil-eye-tracking-paper"></div>

- [3] [Pupil: An Open Source Platform for Pervasive Eye Tracking and Mobile Gaze-based Interaction](https://arxiv.org/pdf/1405.0006.pdf)

<div id="Turkergaze-paper"></div>

- [4] [TurkerGaze: Crowdsourcing Saliency with Webcam based Eye Tracking](https://arxiv.org/abs/1504.06755)

<div id="webgazer-calibration-demo"></div>

- [5] [webgazer-calibration-demo](https://webgazer.cs.brown.edu/calibration.html?)