document.addEventListener("DOMContentLoaded", handleWindowLoad);
function handleWindowLoad() {
    var myVideo = document.querySelector("Video");

    var playButton = document.getElementById("playPause");
    playButton.addEventListener("click", playPauseVideo);
    function playPauseVideo() 
    {
        if (myVideo.paused === true)
        {
            myVideo.play();
            playButton.innerHTML = "&#9208;";
        }
        else
        {
            myVideo.pause();    
            playButton.innerHTML = "&#9654;";
        }
    }
    

    var muteButton = document.getElementById("volMute");
    muteButton.addEventListener("click", muteVideo);
    function muteVideo() 
    {
        if (myVideo.muted === false){
            
            myVideo.muted = true;
            muteButton.innerHTML = "&#128264;";
        }
        
        else
        {
            myVideo.muted = false;
            muteButton.innerHTML = "&#128266;";
        }
        
    }
       
    var speedupButton = document.getElementById("vidSpeed");
    speedupButton.addEventListener("click", speedupVideo);
    function speedupVideo() 
    {
         if (document.querySelector('Video').playbackRate === 1.0) 
        {
            myVideo.playbackRate = 2.0;
            speedupButton.innerHTML = "2X";
        }
       
        else 
        {
            myVideo.playbackRate = 1.0;
            speedupButton.innerHTML = "1X";
        }
        
 
    }
        
    }

