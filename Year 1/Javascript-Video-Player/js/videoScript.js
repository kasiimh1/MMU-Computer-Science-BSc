document.addEventListener("DOMContentLoaded", handleWindowLoad);

function handleWindowLoad() {

  /*
      videoScript.js created by Kasim Hussain
      Last Updated 02:21PM
      29/11/17
      ;)
  */

  //Video Player Variables that link to the element in the HTML Doc
  var myVideo = document.querySelector("Video");
  var playButton = document.getElementById('playPause');
  var loopButton = document.getElementById('loopVideo');
  var muteButton = document.getElementById('volumeMute');
  var videoVolume = document.getElementById('volumeSlider');
  var restartButton = document.getElementById('restartVideo');
  var stopButton = document.getElementById('stopVideo');
  var fastForwardButton = document.getElementById('fastForwardVideo');
  var fullscreenButton = document.getElementById('videoFullscreen');
  var playbackValue = document.getElementById('selectedPlaybackValue');
  var scrubSlider = document.getElementById("seekBar");
  var displayDurationTime = document.getElementById("myVideo");
  var displayCurrentTime = myVideo;

  //Event Listeners that wait for the action to happen before running the linked function 
  playButton.addEventListener('click', playPauseVideo);
  loopButton.addEventListener('click', loopMyVideo); 
  muteButton.addEventListener('click', muteVideo);
  videoVolume.addEventListener('change', changeVolume); //waits for value to be changed via the slider
  restartButton.addEventListener('click', restartVideo);
  stopButton.addEventListener('click', stopVideo);
  fastForwardButton.addEventListener('dblclick', setFastForwardSpeedDblclick); //waits for the double click of the mouse on that button before firing the function
  fastForwardButton.addEventListener('mousedown', setFastForwardSpeedMouseDown); //waits for the mouse down event of the mouse on that button before firing the function
  fastForwardButton.addEventListener('mouseup', setFastForwardSpeedMouseUp); //waits for the mouse up event of the mouse on that button before firing the function
  fullscreenButton.addEventListener('click', enterFullscreen); //extension task
  playbackValue.addEventListener('click', changePlaybackRate);
  document.addEventListener('visibilitychange', handleVisibilityChange, false); //extension task
  scrubSlider.addEventListener('input', scrubVideo);
  scrubSlider.addEventListener('mousedown', pauseSlider); 
  scrubSlider.addEventListener('mouseup', resumeSlider); 
  myVideo.addEventListener('timeupdate', movePlaySlider);
  displayDurationTime.addEventListener('durationchange', displayDuration);
  displayCurrentTime.addEventListener('timeupdate', updateVideoCurrentTime);

  //start of video player functions//
 //*********************************//

 //PlayPause Video//
  function playPauseVideo() {
    if (myVideo.paused === true) { //checks the condition
      myVideo.play();
      playButton.innerHTML = "&#xf04c;"; //updates the button icon 
      console.log(myVideo.paused);
    } else {
      myVideo.pause();
      playButton.innerHTML = "&#xf04b;"; //updates the button icon
      console.log(myVideo.paused);
    }
  }

//Loop video//
  function loopMyVideo() {
    if (myVideo.loop === false) { //checks the condition
      myVideo.loop = true;
      loopButton.innerHTML = "&#xf205;"; //updates the button icon
      console.log(myVideo.loop);
    } else {
      myVideo.loop = false;
      loopButton.innerHTML = "&#xf204;"; //updates the button icon
      console.log(myVideo.loop);
    }
  }

//Mute video//
  function muteVideo() {
    if (myVideo.muted === false) { //checks the condition
      myVideo.muted = true;
      muteButton.innerHTML = "&#xf026;"; //updates the button icon
      myVideo.volume = volSlider.value = 0;
      console.log(myVideo.muted);
    } else {
      myVideo.muted = false;
      muteButton.innerHTML = "&#xf028"; //updates the button icon
      myVideo.volume = 1;
      volSlider.value = 100;
      console.log(myVideo.muted);
    }
  }

//Change volume//
  function changeVolume() {
    myVideo.volume = volumeSlider.value / 100; // we divide the value by 100 to get the percentage for the sliders value because the volume value is between 0 and 1
    console.log(myVideo.volume);
    if (myVideo.volume === 0) { //checks the condition if its 0 
      muteButton.innerHTML = '&#xf026;'; //updates the button icon
    } else {
      myVideo.volume > 0; //checks to see if the volume isn't muted 
      muteButton.innerHTML = '&#xf028;'; //updates the button icon
    }
  }

  //Restart video//
  function restartVideo() {
    myVideo.currentTime = 0; //set video time to 0 (start)
    myVideo.play();
    playButton.innerHTML = '&#xf04c;'; //set play icon to pause since the video should be playing
    videoSlider.value = 0; //set to 0 to reflect the video restarting
  }

  //Stop video//
  function stopVideo() {
    if (myVideo.paused === true) {} else {
      myVideo.pause(); //we used the pause function as there is no built in stop function
      myVideo.currentTime = 0; //we set the video current time to 0 in case the user wishes to play the video again, setting to 0 give it the feel that the video was stopped
      stopButton.innerHTML = "&#xf04d;"; //updates the button icon
      console.log('Video has been stopped');
      playPause.innerHTML = '&#xf04b;'; //updates the button icon
    }
  }

//Fast Forward button
  function setFastForwardSpeedDblclick() {
    myVideo.playbackRate = 1.0; //default speed (1.0)
    console.log(myVideo.playbackRate); //print to see if value is correct
  }

  function setFastForwardSpeedMouseDown() {
    myVideo.playbackRate = 3.0; //triple speed
    console.log(myVideo.playbackRate); //print to see if value is correct
  }

  function setFastForwardSpeedMouseUp() {
    myVideo.playbackRate = 2.0; //double speed
    console.log(myVideo.playbackRate); //print to see if value is correct
  }

// enter fullscreen function *extension task* //
  function enterFullscreen() {
    if (myVideo.requestFullscreen) {
      video.requestFullscreen();
    } else if (myVideo.webkitRequestFullscreen) {
      myVideo.webkitRequestFullscreen(); // Mozilla
    } else if (myVideo.mozRequestFullScreen) {
      myVideo.mozRequestFullScreen(); // Chrome and Safari
    }
  }

  function changePlaybackRate() {
    myVideo.playbackRate = playbackValue.value; //selects the value which is defined within the option tag and sets that as the playbackRate
  }

// enter tab visibility *extension task* //
  function handleVisibilityChange() {
    if (document.visibilityState == "hidden") { //checks if the page (tab) is visible and we aren't on another tab
      myVideo.pause();
      document.title = 'Video is Paused'; // set page title
    } else {
      myVideo.play();
      playButton.innerHTML = '&#xf04c;'; //set playbutton to pause icon
      document.title = "Video is Playing"; //set page title
    }

    // Warn if the browser doesn't support addEventListener or the Page Visibility API
    if (typeof document.addEventListener === "undefined" || typeof document[hidden] === "undefined") {
      console.log("Video cannot be played in this browser, update or download Google Chrome or Firefox, that both support the Page Visibility API.");
    } else {
      // Handle page visibility change
      document.addEventListener(visibilityChange, handleVisibilityChange, false);
    }
  }

//Srub video//
  function scrubVideo() {
    var scrubTime = myVideo.duration * (scrubSlider.value / 100); //we divide by 100 to get the percentage for the sliders value
    myVideo.currentTime = scrubTime;
  }

 //Move play Slider// 
  function movePlaySlider() {
    if (myVideo.currentTime > 0) { //this only runs sets the value if the current time is higher than 0 so anything after and including 1
      scrubSlider.value = (myVideo.currentTime / myVideo.duration) * 100;
    } else {
      scrubSlider.value = 0;
    }
  }
 //Pause play Slider// 
  function pauseSlider(){
    myVideo.pause();
    playButton.innerHTML = '&#xf04c;'; //set play icon to pause since the video should be playing    
  }
 //Play play Slider// 
  function resumeSlider(){
    myVideo.play();
  }

 //Current time// 
  function updateVideoCurrentTime() {
    // Display the current position of the video & round time to 0 dp
    var minutes = Math.floor(myVideo.currentTime / 60); //this is to convert the current time into minutes dividing the duartion by 60 and the
    var seconds = (myVideo.currentTime % 60).toFixed(0); /* % (modulus) of 60 allows us to find the remainder of the number,
    in this case resulting into the minutes passed so far as a whole number if the seconds reach 60 another minute is added on. */

    if (minutes <10 ) minutes = '0' +  minutes; //this adds a 0 if the digit is less than 10
    if (seconds <10 ) seconds = '0' + seconds; //this adds a 0 if the digit is less than 10
    document.getElementById("currentTime").innerHTML = minutes + ':' + seconds;
  }

  //Duration time//
  function displayDuration() {
    // Display the duration of the video & round time to 0 dp
    var minute = Math.floor(myVideo.duration / 60); //this is to convert the current time into minutes dividing the duration by 60 and
    var second = (myVideo.duration % 60).toFixed(0);  /* % (modulus) of 60 allows us to find the remainder of the number,
    in this case resulting into the minutes passed so far as a whole number if the seconds reach 60 another minute is added on. */

    if (second <10 ) second = '0' + second; //this adds a 0 if the digit is less than 10
    durationTime.innerHTML = minute + ':' + second; //this adds a 0 if the digit is less than 10
  }
};
